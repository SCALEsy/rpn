package test.beans;

public class Command {
    public final static String ADD = "+";
    public final static String SUB = "-";
    public final static String MUL = "*";
    public final static String DIV = "/";
    public final static String SQRT = "sqrt";
    public final static String UNDO = "undo";
    public final static String CLEAR = "clear";
    private String cmd;
    private int index;

    public Command(String cmd, int index) {
        this.cmd = cmd;
        this.index = index;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return cmd + ":" + index;
    }
}
