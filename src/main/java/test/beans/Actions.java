package test.beans;


import java.util.Stack;

public class Actions {
    private Stack<Action> actions = null;

    public Actions() {
        actions = new Stack<Action>();
    }

    public Actions addPush() {
        actions.push(new Action(Action.PUSH));
        return this;
    }

    public Actions addPop(double v) {
        actions.push(new Action(v, Action.POP));
        return this;
    }

    public Actions addStart() {
        actions.push(new Action(Action.START));
        return this;
    }

    public Actions addEnd() {
        actions.push(new Action(Action.END));
        return this;
    }

    public Stack<Action> list() {
        return this.actions;
    }


}
