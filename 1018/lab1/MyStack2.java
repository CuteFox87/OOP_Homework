public class MyStack2 {
	private int maxCapacity;
	private int[] stack;
	private int top;
	private final int defaultCapacity = 5;
	private int size = 0;
	

	public MyStack2() {
		stack = new int[defaultCapacity];
		maxCapacity = defaultCapacity;
	}
	
	public MyStack2(int capacity) {
		stack = new int[capacity];
		maxCapacity = capacity;
	}
	
	public boolean isFull() {
		if(size < maxCapacity)
			return false;
		System.out.println("The Stack is FULL!!!");
		resize();
		throw new ArrayIndexOutOfBoundsException("Index " + size + " out of bounds for length " + maxCapacity);
	}
	
	public boolean isEmpty() {
		if(size > 0)
			return false;
		System.out.println("The Stack is EMPTY!!!");
		throw new ArrayIndexOutOfBoundsException("Index " + size + " out of bounds for length " + maxCapacity);
	}
	
	public void push(int theElement) {
		if(size >= maxCapacity) {
			resize();
			System.err.println("The Stack is FULL!!!");
		}
		stack[size++] = theElement;
	}

	public void push(String theElement) {
		push(Integer.parseInt(theElement));
	}
	
	public int pop() {
		if (!isEmpty())
			size--;
		return stack[size];
	}
	
	public int top() {
		return stack[size - 1];
	}
	
	public void popall() {
		while (!isEmpty()) {
			System.out.println(top());
			pop();
		}
	}

	private void resize() {
		int[] newStack = new int[maxCapacity * 2];
		for(int i = 0; i < maxCapacity; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
		maxCapacity *= 2;
	}
	
	public static void main(String args[]) {
		
		// the following codes are original testing code
		// make your modification to get them worked

		MyStack2 stack = new MyStack2();
		
		stack.push("1");
		stack.push("2");
		stack.push("3");
		System.out.println(stack.top());
		stack.pop();
		stack.push("4");
		stack.push("5");
		stack.push("6");
		stack.push("7");
		stack.push("8");
		stack.pop();
		stack.popall();
		stack.pop();
		// Final Output should be like:
		// 3
		// The Stack is FULL!!!
		// The Stack is FULL!!!
		// 5
		// 4
		// 2
		// 1
		// The Stack is EMPTY!!!
		// The Stack is EMPTY!!!
	}

	
	
		
}