public class StackDemo {
	
	public static void main(String args[]) {
		
		// the following codes are original testing code
		// make your modification to get them worked
		
		MyStack2 stack = new MyStack2(3);

		try {
			stack.push(1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(2);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(3);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			System.out.println("pop: " + stack.pop());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(4);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(5);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(6);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(7);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.push(8);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.pop();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.popall();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		try {
			stack.pop();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		

		

		/*
		pop: 3
		Exception：Index 3 out of bounds for length 3
		Exception：Index 6 out of bounds for length 6
		7
		6
		5
		4
		2
		1
		The Stack is EMPTY!!!
		Exception：Index -1 out of bounds for length 12
		*/
	}
}