package Lab2.Exercise3;

/**
 * Created by Eveler on 27.03.2015.
 */
public class MyClass {
    private int a;
    private int b;

    public MyClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public MyClass() {
        this.a = 0;
        this.b = 0;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "[a=" + a + " " + "b=" + b + "]";
    }

}
