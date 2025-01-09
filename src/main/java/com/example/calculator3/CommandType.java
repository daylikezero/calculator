package com.example.calculator3;

import java.util.Optional;
import java.util.Scanner;

/**
 * 사용자 메뉴
 */
public enum CommandType {
    EXIT("E") {
        @Override
        public boolean action(ArithmeticCalculator calculator) {
            System.out.print("전체 연산 결과: ");
            System.out.println(calculator.getResultQueue().toString());
            System.exit(0);
            return true;
        }
    }, REMOVE("R") {
        @Override
        public boolean action(ArithmeticCalculator calculator) {
            System.out.println("가장 오래된 연산 결과를 삭제합니다.");
            calculator.resetResult();
            return false;
        }
    }, BIGGER("B") {
        @Override
        public boolean action(ArithmeticCalculator calculator) {
            System.out.println("입력한 숫자보다 큰 결과값들을 조회합니다.");
            System.out.print("비교할 숫자를 입력해주세요: ");
            calculator.compareQueue(new Scanner(System.in).nextBigDecimal());
            return false;
        }
    }, CONTINUE("C") {
        @Override
        public boolean action(ArithmeticCalculator calculator) {
            return true;
        }
    };

    private final String command;

    public abstract boolean action(ArithmeticCalculator calculator);

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommand(String command) {
        for (CommandType value : CommandType.values()) {
            if (value.command.equals(command)) {
                return value;
            }
        }
        throw new IllegalArgumentException("잘못된 메뉴 입력: " + command);
    }
}
