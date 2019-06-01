package test;


import org.apache.commons.lang3.math.NumberUtils;
import test.beans.Command;
import test.exception.ParameterLessException;
import test.parse.InputParse;

import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Calculator rpn = new Calculator();
        String str = null;
        InputParse parse = new InputParse();
        while ((str = scanner.nextLine()) != null) {
            Queue<Command> cmds = parse.parse(str);

            Exception me = null;
            String operator = null;
            Command cmd = null;
            if (cmds != null) {
                while (!cmds.isEmpty()) {
                    cmd = cmds.poll();
                    operator = cmd.getCmd();

                    if (NumberUtils.isParsable(operator)) {
                        rpn.addToStack(Double.valueOf(operator));
                    } else {
                        try {
                            switch (operator) {
                                case Calculator.ADD:
                                    rpn.add();
                                    break;
                                case Calculator.SUB:
                                    rpn.sub();
                                    break;
                                case Calculator.MUL:
                                    rpn.mul();
                                    break;
                                case Calculator.DIV:
                                    rpn.div();
                                    break;
                                case Calculator.SQRT:
                                    rpn.sqrt();
                                    break;
                                case Calculator.UNDO:
                                    rpn.undo();
                                    break;
                                case Calculator.CLEAR:
                                    rpn.clear();
                                    break;
                                default:
                                    break;
                            }
                        } catch (Exception e) {
                            me = e;

                            break;
                        }
                    }
                }

            }


            if (me != null) {
                if (me instanceof ParameterLessException) {
                    System.out.println("operator " + operator + "(position " + (cmd.getIndex() + 1) + "):insufficient parameters " + rpn.toString());
                } else {
                    System.out.println("operator " + operator + "(position " + (cmd.getIndex() + 1) + "):" + me.getMessage() + " " + rpn.toString());
                }
                printlnLast(cmds);
            } else {
                System.out.println(rpn.toString());
            }
        }
    }

    /*public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }*/

    public static void printlnLast(Queue<Command> cmds) {
        if (!cmds.isEmpty()) {
            StringBuilder builder = new StringBuilder("(the [");
            while (!cmds.isEmpty()) {
                Command cmd = cmds.poll();
                if (NumberUtils.isParsable(cmd.getCmd())) {
                    builder.append(cmd.getCmd());
                    builder.append(",");
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("] were not pushed on to the stack due to the previous error)");
            System.out.println(builder.toString());
        }
    }
}
