class SequenceSearch implements MySearchable {
	int array[];
	int index = 0;
	SequenceSearch() {
		array = new int[32];
	}
	
	public void put(int item) {
		array[index++] = item;
	}
	
	public boolean exist(int item) {
		for (int i = 0; i<index; i++) {
			if (array[i] == item) {
				return true;
			}
		}
		return false;
	}
}