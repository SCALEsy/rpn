package test.beans;

public class Action {
    public static final int START = 0;
    public static final int END = 1;
    public static final int PUSH = 2;
    public static final int POP = 3;


    private double value;
    private int type;

    public Action(double v, int type) {
        this.value = v;
        this.type = type;
    }

    public Action(int type) {
        //this.value = v;
        this.type = type;
    }


    public double getValue() {
        return value;
    }

    public int getType() {
        return type;
    }
}
