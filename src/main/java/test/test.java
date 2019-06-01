package test;

import test.beans.Command;
import test.parse.InputParse;

import java.util.Queue;
import java.util.Scanner;

public class test {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String str = null;
        InputParse parse = new InputParse();
        while ((str = scanner.nextLine()) != null) {
            Queue<Command> cmds = parse.parse(str);
            for (Command c : cmds) {
                System.out.println(c);
            }
        }
    }
}
