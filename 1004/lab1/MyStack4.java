class MyStack4 extends MyStack2 {
	
	public MyStack4(int capacity) {
		super(capacity);
	}
	
	public boolean isFull() {
		if(top >= maxCapacity - 1) {
			int[] arr = new int[maxCapacity*2];
			System.arraycopy(stack, 0, arr, 0, stack.length);
			maxCapacity = maxCapacity * 2;
			stack = arr;
		}
		return false;
	}
	
	public int size() {
		return top+1;
	}
}