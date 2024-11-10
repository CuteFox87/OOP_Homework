import java.util.ArrayList;

public class MyStackT<T> extends ArrayList<T> {
    
    public boolean isEmpty() {
        if (size() == 0) {
            System.out.println("The Stack is EMPTY!!!");
            return true;
        } else {
            return false;
        }

    }
    
    public void push(T value) {
        add(value);
    }
    
    public T pop() {
        if (!isEmpty()){
            T temp = get(size() - 1);
            remove(size() - 1);
            return temp;
        }
        return null;
    }
    
    public T top() {
        if (isEmpty())
            return null;
        return get(size() - 1);
    }
    
    public void popall() {
        while (!isEmpty()) {
            System.out.println(pop());
        }
    }
}
