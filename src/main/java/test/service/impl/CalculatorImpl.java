package test.service.impl;

import test.beans.Action;
import test.beans.Actions;
import test.exception.DivZeroException;
import test.exception.ParameterLessException;
import test.exception.SqrtException;
import test.exception.UndoException;
import test.service.Calculator;

import java.util.Stack;

public class CalculatorImpl implements Calculator {
    private Stack<Double> stack = null;
    private Actions actions = null;

    public CalculatorImpl() {
        stack = new Stack<>();
        actions = new Actions();
    }

    @Override
    public void add() {
        if (stack.size() < 2) {
            throw new ParameterLessException("the size of stack is less than 2");
        }
        double a = stack.pop();
        double b = stack.pop();
        double d = a + b;
        stack.push(d);
        actions.addStart().addPop(a).addPop(b).addPush().addEnd();
    }

    @Override
    public void sub() {
        if (stack.size() < 2) {
            throw new ParameterLessException("the size of stack is less than 2");
        }
        double a = stack.pop();
        double b = stack.pop();
        double d = b - a;
        stack.push(d);
        actions.addStart().addPop(a).addPop(b).addPush().addEnd();
    }

    @Override
    public void mul() {
        if (stack.size() < 2) {
            throw new ParameterLessException("the size of stack is less than 2");
        }
        double a = stack.pop();
        double b = stack.pop();
        double d = a * b;
        stack.push(d);
        actions.addStart().addPop(a).addPop(b).addPush().addEnd();
    }

    @Override
    public void div() {
        if (stack.size() < 2) {
            throw new ParameterLessException("the size of stack is less than 2");
        }
        double a = stack.pop();
        if (a == 0) {
            stack.push(a);
            throw new DivZeroException("the dividend number is zero ");
        }
        double b = stack.pop();
        double d = b / a;
        stack.push(d);
        actions.addStart().addPop(a).addPop(b).addPush().addEnd();
    }

    @Override
    public void sqrt() {
        if (stack.size() < 1) {
            throw new ParameterLessException("the size of stack is less than 1");
        }
        double a = stack.pop();
        if (a < 0) {
            stack.push(a);
            throw new SqrtException("the radicand number is less than 0");
        }
        double d = Math.sqrt(a);
        stack.push(d);
        actions.addStart().addPop(a).addPush().addEnd();
    }

    @Override
    public void undo() {
        Stack<Action> list = actions.list();
        if (list.isEmpty()) {
            return;
        }
        Action a = list.pop();
        if (a.getType() != Action.END) {
            throw new UndoException("command don't had well end");
        }
        boolean flag = true;
        while (!list.isEmpty() && flag) {
            a = list.pop();
            switch (a.getType()) {
                case Action.PUSH:
                    stack.pop();
                    break;
                case Action.POP:
                    stack.push(a.getValue());
                    break;
                case Action.START:
                    flag = false;
                    break;
                case Action.END:
                    flag = false;
                    break;
            }
        }
    }

    public void startCMD() {
        actions.addStart();
    }

    public void finishCMD() {
        actions.addEnd();
    }

    @Override
    public void clear() {
        actions.addStart();
        while (!stack.isEmpty()) {
            double d = stack.pop();
            actions.addPop(d);
        }
        actions.addEnd();
    }
    @Override
    public void push(Double v) {
        stack.push(v);
        actions.addStart().addPush().addEnd();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("stack:");
        if (stack.isEmpty()) {
            return builder.toString();
        }

        for (int i = 0; i < stack.size(); i++) {
            builder.append(stack.get(i));
            if (i != stack.size() - 1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

}
