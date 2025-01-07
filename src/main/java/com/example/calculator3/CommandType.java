package com.example.calculator3;

import java.util.Optional;

/**
 * 사용자 메뉴
 */
public enum CommandType {
    EXIT("E"), REMOVE("R"), BIGGER("B"), CONTINUE("C");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static Optional<CommandType> getCommand(String command) {
        for (CommandType value : CommandType.values()) {
            if (value.command.equals(command)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
