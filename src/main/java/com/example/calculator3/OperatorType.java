package com.example.calculator3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * 연산자 타입
 */
public enum OperatorType {
    PLUS("+", BigDecimal::add),
    MINUS("-", BigDecimal::subtract),
    MULTIPLY("*", BigDecimal::multiply),
    DIVIDE("/", (big1, big2) -> {
        if (big2.equals(BigDecimal.ZERO)) {
            throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
        }
        return big1.divide(big2, 3, RoundingMode.HALF_UP); // 소수점 3자리 표시, 3자리 이하 반올림 처리
    }),
    MOD("%", BigDecimal::remainder); // mod 연산 추가

    private final String operator;
    private final BiFunction<BigDecimal, BigDecimal, Number> biFunction;

    OperatorType(String operator, BiFunction<BigDecimal, BigDecimal, Number> biFunction) {
        this.operator = operator;
        this.biFunction = biFunction;
    }

    public static OperatorType getOperatorType(String operator) {
        for (OperatorType value : OperatorType.values()) {
            if (value.operator.equals(operator)) {
                return value;
            }
        }
        throw new IllegalArgumentException("잘못된 연산기호 입력: " + operator);
    }


    /**
     * calculate() : 외부에서 Enum 상수에 정의된 biFunction을 실행할 때 사용
     *
     * @param num1
     * @param num2
     * @return apply(num1, num2)
     */
    public Number calculate(BigDecimal num1, BigDecimal num2) {
        return biFunction.apply(num1, num2);
    }
}
