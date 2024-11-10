class BinarySearchTree implements MySearchable {
    int array[];

    BinarySearchTree() {
        array = new int[32];
    }

    public void put(int item) {
        int index = 1;
        while(array[index] != 0) {
            if (item < array[index]) {
                index = index * 2;
            } else {
                index = index * 2 + 1;
            }
        }

        array[index] = item;
    }
    
    public boolean exist(int item) {
        int index = 1;
        while(array[index] != 0) {
            if (array[index] == item) {
                return true;
            } else if (item < array[index]) {
                index = index * 2;
            } else {
                index = index * 2 + 1;
            }
        }
        return false;
    }
    
}