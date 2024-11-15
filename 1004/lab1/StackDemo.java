public class StackDemo {
	
	public static void main(String args[]) {
		
		// the following codes are original testing code
		// make your modification to get them worked
		
		MyStack4 stack = new MyStack4(1);
		
		stack.push(1);
		System.out.println("size: "+stack.size());
		stack.push(2);
		System.out.println("size: "+stack.size());
		stack.push(3);
		System.out.println("size: "+stack.size());
		System.out.println("pop: "+stack.pop());
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.pop();
		stack.popall();
		stack.pop();
	}
}