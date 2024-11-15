public class StackDemo {

    public static void main(String[] args) {
        MyStackT<Integer> stack = new MyStackT<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.popall();
        stack.pop();
        stack.pop();

        MyStackT<String> stack2 = new MyStackT<>();
        stack2.push("a");
        stack2.push("b");
        stack2.push("c");
        stack2.popall();
        stack2.pop();
        stack2.pop();
    }
}
