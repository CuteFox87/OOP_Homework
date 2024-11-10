public class PostfixEvaluation {
	// use Character.isDigit() to decide whether the input character is a digit
	// char c1 = 'A', c2 = '4';
	// Character.isDigit(c1)); ==> False
	// Character.isDigit(c2)); ==> True
	// Character.digit(c2, 10); ==> 4

	
	
	public int evaluate(char[] input) {
		MyStack4 myStack = new MyStack4(10);
		for(char i:input) {
			if(Character.isDigit(i)){
				myStack.push(Character.digit(i, 10));
			}
			else{
				int a, b;
				b = myStack.pop();
				a = myStack.pop();
				switch(i) {
					case '+':
						myStack.push(a+b);
						break;
					case '-':
						myStack.push(a-b);	
						break;
					case '*':
						myStack.push(a*b);
						break;
					case '/':
						myStack.push(a/b);
						break;
				}
			}
		}
		return myStack.top();
	}
	
	public static void main(String args[]) {
		PostfixEvaluation pe = new PostfixEvaluation();
		
		System.out.println(pe.evaluate("234*+".toCharArray()));
		System.out.println(pe.evaluate("23*4+".toCharArray()));
		System.out.println(pe.evaluate("12+7*".toCharArray()));
		System.out.println(pe.evaluate("68*2/".toCharArray()));
		System.out.println(pe.evaluate("123-4+*51-*3*".toCharArray()));
		System.out.println(pe.evaluate("42/3-4+5*13*-".toCharArray()));
	}
}
	