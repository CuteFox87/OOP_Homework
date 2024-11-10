// Source code is decompiled from a .class file using FernFlower decompiler.

import java.util.ArrayList;

class MyQueueT <T> extends ArrayList<T>{
    public int front;
    public int rear;
    public int size;

    public MyQueueT() {
       super(10);
       front = 0;
       rear = -1;
       size = 0;

       for (int i = 0; i < 10; i++) {
          add(null);
       }
    }
 
    public void addQ(T var1) {
        if (size == 10) {
            System.out.println("The Queue is FULL!!!");
            return;
        } 
        rear = (rear + 1) % 10;
        set(rear, var1);
        size++;
    }
 
    public T deleteQ() {
       if (size == 0) {
            System.out.println("The Queue is EMPTY!!!");
            return null;
        } 
        else {
            T var1 = get(front);
            set(front, var1);
            front = (front + 1) % 10;
            size--;
            return var1;
       }
    }
 
    public void deleteAll() {
       while(size > 0) {
          System.out.println(this.deleteQ());
       }
 
    }
 }
 