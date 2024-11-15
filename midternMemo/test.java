class Animal {
    Animal() {
        System.out.println("An animal is created");
    }
}

class Dog extends Animal {
    Dog() {
        super();  // Calls the constructor of Animal
        System.out.println("A dog is created");
    }
}

public class test {
    public static void main(String[] args) {
        Dog myDog = new Dog();
    }
}
