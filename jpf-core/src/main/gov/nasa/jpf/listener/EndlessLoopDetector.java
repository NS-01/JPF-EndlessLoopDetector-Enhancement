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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import gov.nasa.jpf.Config;
import gov.nasa.jpf.annotation.JPFOption;
import gov.nasa.jpf.annotation.JPFOptions;
import gov.nasa.jpf.search.Search;
import gov.nasa.jpf.vm.Instruction;
import gov.nasa.jpf.vm.MethodInfo;
import gov.nasa.jpf.vm.ThreadInfo;
import gov.nasa.jpf.vm.VM;

/**
 * little listener that tries to detect endless while() loops by counting
 * backjumps, breaking transitions if the count exceeds a threshold, and then
 * checking if program states match. If they do, there would be no progress in
 * this thread.
 */
@JPFOptions({
		@JPFOption(type = "Long", key = "endlessloopdetector.max_loops", defaultValue = "500", comment = "stop search after specified number of loops have occurred") })
public class EndlessLoopDetector extends IdleFilter {

	// information to print if endlessloop is detected
	private List<String> information;
	// HashTable key -> method name , value -> count
	private Hashtable<String, Integer> methodCalls = new Hashtable<String, Integer>();

	// max time set by configuration
	private long maxTime;

	boolean foundEndlessLoop = false;
	private boolean inMain; // whether the execution is in the main method

	public EndlessLoopDetector(Config config) {
		super(config);
		// default times recursion can occur before considering infinite is 500
		maxTime = config.getDuration("endlessloopdetector.max_loops", 500);
		this.information = new ArrayList<String>();
		action = Action.BREAK;
	}

	@Override
	public void stateAdvanced(Search search) {
		if (brokeTransition && search.isVisitedState()) {
			foundEndlessLoop = true;
		}
	}

	/**
	 * Whenever JPF enters a method, checks if it is the main method.
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
	}

	/**
	 * Whenever JPF is executing an instruction, checks if the instruction belongs
	 * to the main method. Keeps count of the number of times a method is called.
	 * 
	 * @param vm
	 *            JPF's virtual machine.
	 * @param thread
	 *            the thread currently executing.
	 * @param instruction
	 *            the instruction that is executing.
	 */
	@Override
	public void executeInstruction(VM vm, ThreadInfo thread, Instruction instruction) {
		if (this.inMain) {
			String currentMethodName = instruction.getMethodInfo().getName();
			if (this.methodCalls.containsKey(currentMethodName)) {
				// update count
				if (this.methodCalls.get(currentMethodName) >= this.maxTime) {
					foundEndlessLoop = true;
				} else {
					this.methodCalls.put(currentMethodName, this.methodCalls.get(currentMethodName) + 1);
				}
			} else {
				// add count = 1
				this.methodCalls.put(currentMethodName, 1);
			}

			if (this.checkFinished(instruction)) {
				// stop
				// have to find a better way to do this.
				vm.terminateProcess(thread);
			}

		}
	}

	/**
	 * Verifies if an endless loop is found
	 */
	private boolean checkFinished(Instruction instruction) {
		if (this.foundEndlessLoop) {
			this.information
					.add("Infinite Recursion detected at Line Number : " + String.valueOf(instruction.getLineNumber()));
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

	@Override
	public boolean check(Search search, VM vm) {
		return !foundEndlessLoop;
	}

	@Override
	public void reset() {
		foundEndlessLoop = false;
	}

}
