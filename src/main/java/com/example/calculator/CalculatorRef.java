package com.example.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 필수기능 Lv1. 계산기를 만들어라
 * <p>
 * 계산기는 2개의 숫자를 받을 수 있고 사칙연산 될 문자를 받을 수 있다.
 * 계산기는 exit을 입력할 때까지 계속해서 값을 받고 연산 결과를 반환한다.
 * </p>
 * <p>
 * 1-1. 양의 정수(0 포함)을 입력받기<br>
 * * e1) 입력받은 수가 숫자가 아닌 경우 예외처리<br>
 * 1-2. 사칙연산 기호를 입력받기<br>
 * * e2) 잘못된 사칙연산 기호 입력 시 예외처리<br>
 * 1-3. 위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기<br>
 * * e3) 나눗셈 연산에서 분모가 0일 경우 예외처리<br>
 * 1-4. 반복문을 사용하되, 반복의 종료를 알려주는 "exit" 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스코드를 수정하기
 * </p>
 *
 */
public class CalculatorRef {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int num1 = sc.nextInt();
                System.out.print("두 번째 숫자를 입력하세요: ");
                int num2 = sc.nextInt();
                sc.nextLine(); // nextInt() 입력 후 버퍼의 개행문자(\n) 제거
                System.out.print("사칙연산 기호를 입력하세요: ");
                char operator = sc.nextLine().charAt(0);

                // Enhanced Switch (Java 14)
                int result = switch (operator) {
                    case '+' -> add(num1, num2);
                    case '-' -> subtract(num1, num2);
                    case '*' -> multiply(num1, num2);
                    case '/' -> divide(num1, num2);
                    default -> throw new IllegalArgumentException("잘못된 연산기호 입력입니다."); // e2
                };
                System.out.println("결과 : " + result);
            } catch (InputMismatchException e) { // e1
                System.out.println("잘못된 숫자 입력입니다.");
                sc.nextLine(); // nextInt() 입력 후 버퍼의 개행문자(\n) 제거
            } catch (ArithmeticException | IllegalArgumentException e) {
                // 결과(result)를 출력하지 않고 에러메세지 콘솔 출력
                System.out.println(e.getMessage());
            }

            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료): ");
            if (sc.nextLine().equals("exit")) {
                return;
            }
        }
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    public static int divide(int num1, int num2) {
        if (num2 == 0) {
            // e3: ArithmeticException: / by zero
            throw new ArithmeticException("나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다.");
        }
        return num1 / num2;
    }

}