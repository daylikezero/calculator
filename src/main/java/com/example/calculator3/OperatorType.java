package com.example.calculator3;

import java.util.Optional;

/**
 * 연산자 타입
 */
public enum OperatorType {
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

    private final String operator;

    OperatorType(String operator) {
        this.operator = operator;
    }

    public static Optional<OperatorType> getOperatorType(String operator) {
        for (OperatorType value : OperatorType.values()) {
            if(value.operator.equals(operator)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
