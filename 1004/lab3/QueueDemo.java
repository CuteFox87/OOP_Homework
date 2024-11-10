public class QueueDemo {
	public static int value = 5;
	
	public static void main(String args[]) {
		
		// the following codes are original testing code
		// make your modification to get them worked
		
		MyQueue queue = new MyQueue();
		
		queue.addQ(1);
		queue.addQ(2);
		queue.addQ(3);
		System.out.println("size: "+queue.size());
		System.out.println("deleteQ: "+queue.deleteQ());
		queue.addQ(4);
		queue.addQ(5);
		queue.addQ(6);
		queue.addQ(7);
		queue.addQ(8);
		queue.deleteQ();
		queue.deleteAll(); // print all the items in Queue while deletion
		queue.deleteQ();
		// Final Output should be like:
		// size: 3
		// deleteQ: 1
		// The Queue is FULL!!!
		// The Queue is FULL!!!
		// 3
		// 4
		// 5
		// 6
		// The Queue is EMPTY!!!
		// The Queue is EMPTY!!!
	}
}

class MyQueue {
    MyStack2 stack1;
    public int currentSize = 0;
    public int maxSize = 0;

    public MyQueue() {
        this(5);
    }

    public MyQueue(int size) {
        maxSize = size;
        currentSize = 0;
        stack1 = new MyStack2(maxSize);
    }

    public void addQ(int value) {
        if (currentSize == maxSize) {
            System.out.println("The Queue is FULL!!!");
            return;
        }
        stack1.push(value);
        currentSize++;
    }

    public int deleteQ() {
        if (currentSize == 0) {
            System.out.println("The Queue is EMPTY!!!");
            return -1; // Indicates empty queue
        }

        MyStack2 stack2 = new MyStack2(maxSize);
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        int removedValue = stack2.pop();
        currentSize--; // Update currentSize after removing an element

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }

        return removedValue;
    }

    public int size() {
        return currentSize;
    }

    public void deleteAll() {
        while (currentSize > 0) {
            System.out.println(deleteQ());
        }
    }
}
