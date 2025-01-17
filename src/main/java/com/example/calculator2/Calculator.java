package com.example.calculator2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 연산 수행 역할
 */
public class Calculator {
    /* 연산 결과를 저장하는 컬렉션 타입 필드 선언 및 생성 */
    // final 상수 선언: resultQueue 안의 요소는 변동 가능성이 있지만 resultQueue 자체는 변동 없음
    private final Queue<Double> resultQueue = new LinkedList<>();

    /**
     * 사칙연산을 수행한 후, 결과값을 반환하는 메서드 구현
     * 1) 양의 정수 2개(0 포함)와 연산 기호를 매개변수로 받아 사칙연산 (+,-,*,%) 기능을 수행한 후
     * 2) 결과값을 반환하는 메서드와 연산 결과를 저장하는 컬렉션 타입 필드를 가진 클래스를 생성
     **/
    public double calculate(int num1, int num2, char operator) {
        double result = 0;
        switch (operator) {
            case '+':
                result = add(num1, num2); // 덧셈
                break;
            case '-':
                result = subtract(num1, num2); // 뺄셈
                break;
            case '*':
                result = multiply(num1, num2); // 곱셈
                break;
            case '/':
                result = divide(num1, num2); // 나눗셈
                break;
            default:
                // e1: 사칙연산 기호 오류
                throw new IllegalArgumentException("잘못된 연산기호 입력입니다.: " + operator);
        }
        resultQueue.add(result);
        return result;
    }

    private int add(int num1, int num2) {
        return num1 + num2;
    }

    private int subtract(int num1, int num2) {
        return num1 - num2;
    }

    private int multiply(int num1, int num2) {
        return num1 * num2;
    }

    private double divide(int num1, int num2) {
        if (num2 == 0) {
            // e2: ArithmeticException: / by zero
            throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
        }
        return (double) num1 / num2;
    }

    /* Getter 메서드 구현 */
    public Queue<Double> getResultQueue() {
        return resultQueue;
    }

    /* Setter 메서드 구현 (사용X 주석 처리) */
//    public void setResultQueue(Queue<Integer> resultQueue) {
//        this.resultQueue = resultQueue;
//    }

    /* removeResult: 가장 먼저 저장된 데이터를 삭제하는 기능 */
    public void resetResult() {
        resultQueue.poll();
    }
}