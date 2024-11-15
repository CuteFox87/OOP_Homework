public class QueueDemo {
    public static void main(String[] args) {
        MyQueueT<Integer> queue = new MyQueueT<>();
        queue.addQ(1);
        queue.addQ(2);
        queue.addQ(3);
        queue.deleteAll();
        queue.deleteQ();
        queue.deleteQ();

        MyQueueT<String> queue2 = new MyQueueT<>();
        queue2.addQ("a");
        queue2.addQ("b");
        queue2.addQ("c");
        queue2.deleteAll();
        queue2.deleteQ();
        queue2.deleteQ();
    }
}
