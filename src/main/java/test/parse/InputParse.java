package test.parse;

import test.beans.Command;

import java.util.ArrayDeque;
import java.util.Queue;

public class InputParse {
    public Queue<Command> parse(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Queue<Command> commands = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        int index = -1;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isWhitespace(c)) {
                builder.append(c);
            } else {
                if (builder.length() > 0) {
                    commands.offer(new Command(builder.toString(), index + 1));
                    builder = new StringBuilder();
                }
                index = i;
            }
        }
        if (builder.length() > 0) {
            commands.add(new Command(builder.toString(), index + 1));
        }
        return commands;
    }
}
