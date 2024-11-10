public class MyStack1 {
	//
	final static int MAX_STACK_SIZE = 5;
	static int size = 0;
	static int[] stack = new int[MAX_STACK_SIZE];
	
	public static boolean isFull() {
		if (size < MAX_STACK_SIZE) {
			return false;
		}
		else {
			System.out.println("The Stack is FULL!!!");
			return true;
		}
	}
	
	public static boolean isEmpty() {
		if (size > 0) {
			return false;
		}
		else {
			System.out.println("The Stack is EMPTY!!!");
			return true;
		}
	}
	
	public static void push(int theElement) {
		if (!isFull())
			stack[size++] = theElement;
	}
	
	public static void pop() {
		if (!isEmpty())
			System.out.println(stack[--size]);
	}
	
	public static int top() {
		return stack[size - 1];
	}
	
	public static void popall() {
		while (!isEmpty()) {
			pop();
		}
	}
	
	public static void main(String args[]) {
		
		// the following codes are used for test
		// please do not modify the following codes
		
		push(1);
		push(2);
		push(3);
		System.out.println(top());
		pop();
		push(4);
		push(5);
		push(6);
		push(7);
		push(8);
		pop();
		popall();
		pop();
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