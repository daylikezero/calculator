package com.example.calculator3;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;


/**
 * 연산 수행 역할
 */
public class ArithmeticCalculator {
    /* 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성 */
    // final 상수 선언: resultQueue 안의 요소는 변동 가능성이 있지만 resultQueue 자체는 변동 없음
    private final Queue<Number> resultQueue = new LinkedList<>();

    public <T extends Number> Number calculate(T num1, T num2, String operatorStr) {
        Optional<OperatorType> operator = OperatorType.getOperatorType(operatorStr);
        BigDecimal big1 = (BigDecimal) num1;
        BigDecimal big2 = (BigDecimal) num2;
        if (operator.isPresent()) {
            // OperatorType 값이 있는 경우
            OperatorType operatorType = operator.get();
            Number result = operatorType.calculate(big1, big2); // operatorType에 따라 계산한 후 결과를 반환
            resultQueue.add(result);
            return result;
        } else {
            // OperatorType 값이 없는 경우 (empty)
            // e1: 사칙연산 기호 오류 : throw IllegalArgumentException
            throw new IllegalArgumentException("잘못된 연산기호: " + operatorStr);
        }
    }

    /* Getter 메서드 구현 */
    public Queue<Number> getResultQueue() {
        return resultQueue;
    }

    /* removeResult: 가장 먼저 저장된 데이터를 삭제하는 기능 */
    public void resetResult() {
        resultQueue.poll();
    }

    /* compareQueue: 입력값을 받아 저장된 요소와 비교하여 큰 결과값들을 출력 */
    public <T extends Number> void compareQueue(T target) {
        System.out.println("입력값보다 더 큰 결과값: " + resultQueue.stream()
                .filter(num -> ((BigDecimal) num).compareTo((BigDecimal) target) > 0)
                .toList());
    }
}