# JPF-EndlessLoopDetector-Enhancement

The aim of this project is to enhance the functionality of an existing JPF listener called __EndlessLoopDetector__. Currently this listener detects if any program is caught in a non-terminating loop based on a threshold value. When detecting an endless loop, the listener does not provide any valuable information, such as, what causes the program to go into an endless loop (i.e. a line of code in the program or the state where the endless loop occurs). Reviewing the existing documentation for the listener, we noticed that there is no documentation regarding the configuration that allows a user to set this threshold value. Also, there are edge cases where this listener does not detect an endless loop (e.g. infinite recursion).

Therefore, the purpose of this project is to enhance the EndlessLoopDetector listener class within jpf-core and address the issues mentioned above.

### Milestones
1. Enhance the EndlessLoopDetector listener to detect if a program is caught in an infinite recursion.
2. Enhance the EndlessLoopDetector listener documentation to provide the configuration details that will allow a user to set the threshold value that will be validated against when considering if the program is in an endless loop.
3. Return analysis when the program is detected to be caught in an endless loop.
* Line of code
* Method/instruction Name
* Memory
4. Test suite to test the enhanced functionality of the listener.

#### The first milestone is optional.

### Deliverables
1. Listener class (Enhancements implemented and documented).
2. Test suite (fully documented).
3. README file that describes how the listener can be used and configured.
