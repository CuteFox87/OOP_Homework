class MyStack2 {
	protected int maxCapacity;
	public int[] stack;
	protected int top;
	
	public static int value = 100;
	
	public MyStack2() {
		/*
		maxCapacity = 5;
		stack = new int[maxCapacity];
		top = -1;
		*/
		this(5);
	}
	
	public MyStack2(int capacity) {
		maxCapacity = capacity;
		stack = new int[maxCapacity];
		top = -1;
	}
	
	public boolean isFull() {
		if(top == maxCapacity - 1) {
			System.out.println("The Stack is FULL!!!");
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		if(top >= 0) {
			return false;
		}
		System.out.println("The Stack is EMPTY!!!");
		return true;
	}
	
	public void push(int theElement) {
		if(!isFull()) {
			stack[++top] = theElement;
		}
	}
	
	public int pop() {
		if(!isEmpty()) {
			return stack[top--];
		}
		return 0;
	}
	
	public int top() {
		if(!isEmpty()) {
			return stack[top];
		}	
		return 0;
	}
	
	public void popall() {
		while(!isEmpty()) {
			System.out.println(top());
			pop();
		}
	}
}