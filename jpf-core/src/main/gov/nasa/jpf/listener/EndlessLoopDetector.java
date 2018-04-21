/*
 * Copyright (C) 2014, United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 *
 * The Java Pathfinder core (jpf-core) platform is licensed under the
 * Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0. 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package gov.nasa.jpf.listener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import gov.nasa.jpf.Config;
import gov.nasa.jpf.annotation.JPFOption;
import gov.nasa.jpf.annotation.JPFOptions;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.MethodInfo;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.VM;

/**
 * Configuration : idle.max_backjumps (found in
 * gov.nasa.jpf.listener.IdleFilter) - allows user to set the maximum number of
 * backjumps that can occur before considering current execution an endless loop
 */
@JPFOptions({
		/**
		 * Configuration : endlessloopdetector.max_loops - allows user to set maximum
		 * number of times a method can execute before considering current execution an
		 * infinitely recursive method, thereby an endless loop
		 */
		@JPFOption(type = "Long", key = "endlessloopdetector.max_loops", defaultValue = "500", comment = "stop search after specified number of loops have occurred"),
		/**
		 * Configuration : endlessloopdetector.max_heap_size - allows user to set
		 * maximum size for memory heap that a program can consume without causing a
		 * memory issue.
		 */
		@JPFOption(type = "Long", key = "endlessloopdetector.max_heap", defaultValue = "-1", comment = "stop search when VM heapsize reaches specified limit") })
/**
 * little listener that tries to detect endless while() loops by counting
 * backjumps, breaking transitions if the count exceeds a threshold, and then
 * checking if program states match. If they do, there would be no progress in
 * this thread.
 */
public class EndlessLoopDetector extends IdleFilter {

	// information to print if endlessloop is detected
	private List<String> information;

	// HashTable key -> method name , value -> count
	private Hashtable<String, Integer> methodCalls = new Hashtable<String, Integer>();

	// max time set by configuration
	private long maxTime;

	// Memory Usage
	private Hashtable<String, Integer> memUse = new Hashtable<String, Integer>();

	// max heap size set by configuration
	private long maxMemory;

	// checker to identify endless loop detection
	private boolean foundEndlessLoop = false;

	// checker to determine if the current execution is in the main method
	private boolean inMain;

	// Constructor
	public EndlessLoopDetector(Config config) {
		super(config);
		// default times recursion can occur before considering infinite is 500
		this.maxTime = config.getDuration("endlessloopdetector.max_loops", 500);
		this.maxMemory = config.getMemorySize("endlessloopdetector.max_heap", 0);
		this.information = new ArrayList<String>();
	}

	@Override
	public void stateAdvanced(Search search) {
		if (brokeTransition && search.isVisitedState()) {
			foundEndlessLoop = true;
		}
	}

	/**
	 * Whenever JPF enters a method, checks if it is the main method. Also
	 * increments counter if a non-main method is entered.
	 * 
	 * @param vm
	 *            JPF's virtual machine.
	 * @param thread
	 *            the thread currently executing.
	 * @param method
	 *            the method that is entered.
	 */
	@Override
	public void methodEntered(VM vm, ThreadInfo thread, MethodInfo method) {
		if (method.getFullName().contains("main([Ljava/lang/String;)V")) {
			this.inMain = true;
		}
		if (this.inMain) {
			String currentMethodName = method.getName();
			if (!(currentMethodName.equals("main"))) {
				if (this.methodCalls.containsKey(currentMethodName)) {
					// update count
					if (this.methodCalls.get(currentMethodName) >= this.maxTime) {
						// infinite loop detected
						foundEndlessLoop = true;
					} else {
						this.methodCalls.put(currentMethodName, this.methodCalls.get(currentMethodName) + 1);
					}
				} else {
					// add count = 1
					this.methodCalls.put(currentMethodName, 1);
				}
			}

			/*
			 * addition check if memory used exceeds a memory allocated
			 */
			if (!vm.getSearch().checkStateSpaceLimit()) {
				foundEndlessLoop = true;
				// this.information.add("memory limit reached: " +
				// vm.getSearch().getSearchConstraint().toString());
			}
			if (checkFinished()) {
				thread.breakTransition("EndlessLoop Detected");
			}
		}
	}

	/**
	 * Decrements the count of the method exiting, once it finishes executing.
	 * 
	 * @param vm
	 *            JPF's virtual machine.
	 * @param currentThread
	 *            the current thread.
	 * @param exitedMethod
	 *            the method that is existing.
	 */
	@Override
	public void methodExited(VM vm, ThreadInfo currentThread, MethodInfo exitedMethod) {
		if (this.inMain) {
			String executedMethodName = exitedMethod.getName();
			if (!(executedMethodName.equals("main"))) {
				if (this.methodCalls.containsKey(executedMethodName)) {
					this.methodCalls.put(executedMethodName, this.methodCalls.get(executedMethodName) - 1);
				}
			}
		}
	}

	/**
	 * Verifies if an endless loop is found
	 */
	private boolean checkFinished() {
		if (this.foundEndlessLoop) {
			this.information.add("Endless Loop has been detected");
			this.printInformation();
			return true;
		}
		return false;
	}

	/**
	 * Prints an error message when an endless loop is found
	 */
	private void printInformation() {
		for (String s : this.information) {
			System.out.println(s);
		}
	}

	/**
	 * Checks if endless loop is found
	 */
	@Override
	public boolean check(Search search, VM vm) {
		return !foundEndlessLoop;
	}

	@Override
	public void reset() {
		foundEndlessLoop = false;
	}

}