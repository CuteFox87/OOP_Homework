public class FractionDemo {
    public static void main(String[] args) {
        Fraction fraction = new Fraction();

        System.out.println("divide1: " + fraction.divide1(10, 0));

        try {
            System.out.println("divide2: " + fraction.divide2(10, 0));
        }
        catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

class Fraction {
    public Fraction() {
        System.out.println("Welcome to Fraction Calculator");
    }

    public int divide1(int a, int b){
        try {
            return a / b;
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return a/1;
    }

    public int divide2(int a, int b){
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}



