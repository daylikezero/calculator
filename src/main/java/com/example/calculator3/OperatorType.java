package com.example.calculator3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

/**
 * 연산자 타입
 */
public enum OperatorType {
    PLUS("+") {
        @Override
        public Number calculate(BigDecimal big1, BigDecimal big2) {
            return big1.add(big2); // 덧셈
        }
    }, MINUS("-") {
        @Override
        public Number calculate(BigDecimal big1, BigDecimal big2) {
            return big1.subtract(big2); // 뺠샘
        }
    }, MULTIPLY("*") {
        @Override
        public Number calculate(BigDecimal big1, BigDecimal big2) {
            return big1.multiply(big2); // 곱셈
        }
    }, DIVIDE("/") {
        @Override
        public Number calculate(BigDecimal big1, BigDecimal big2) {
            if (big2.equals(BigDecimal.ZERO)) {
                // e2: ArithmeticException: / by zero
                throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
            }
            // devide(divisor, roundingMode) : 계산결과 소수점 반올림 처리
            return big1.divide(big2, RoundingMode.HALF_UP); // 나눗셈
        }
    };

    private final String operator;
    public abstract Number calculate(BigDecimal big1, BigDecimal big2);

    OperatorType(String operator) {
        this.operator = operator;
    }

    public static Optional<OperatorType> getOperatorType(String operator) {
        for (OperatorType value : OperatorType.values()) {
            if (value.operator.equals(operator)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
