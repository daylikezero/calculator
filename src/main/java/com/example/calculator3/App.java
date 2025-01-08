package com.example.calculator3;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/**
 * 도전기능 Lv3. Enum, 제네릭, 람다 & 스트림을 이해한 계산기 만들기
 * <p>
 * 3-1. Enum 타입을 활용하여 연산자 타입에 대한 정보를 관리하고 이를 사칙연산 계산기 ArithmeticCalculator 클래스에 활용
 * 3-2. 실수, 즉 double 타입의 값을 전달 받아도 연산이 수행하도록 만들기
 * 3-3. 저장된 연산 결과들 중 Scanner 로 입력받은 값보다 큰 결과값 들을 출력
 * </p>
 */
public class App {
    public static boolean loopQuit = false;

    public static void main(String[] args) {
        /* Calculator 인스턴스 생성 */
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                loopQuit = false;
                System.out.print("첫 번째 숫자를 입력하세요: ");
                Number num1 = sc.nextBigDecimal();
                System.out.print("두 번째 숫자를 입력하세요: ");
                Number num2 = sc.nextBigDecimal();
                sc.nextLine(); // nextInt() 입력 후 버퍼의 개행문자(\n) 제거
                System.out.print("사칙연산 기호를 입력하세요: ");
                String operator = sc.nextLine();

                /* 연산 수행 역할은 Calculator 클래스가 담당 */
                Number result = calculator.calculate(num1, num2, operator);

                System.out.println("결과 : " + result);

            } catch (InputMismatchException e) {
                System.out.println("잘못된 숫자 입력입니다.");
                sc.nextLine(); // nextInt() 입력 후 버퍼의 개행문자(\n) 제거
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                // 사용자 메뉴: 계산 성공과 예외 발생에 상관 없이 수행되어야 하는 로직
                while (!loopQuit) {
                    System.out.print("더 계산하시겠습니까? (E: 종료, R: 기록 삭제, B: 큰 수 찾기, C: 계산 실행): ");
                    command(sc.nextLine(), calculator);
                }
            }
        }
    }

    public static void command(String input, ArithmeticCalculator calculator) {
        Optional<CommandType> command = CommandType.getCommand(input.toUpperCase()); // 대소문자 구분 없이 입력
        if (command.isPresent()) {
            switch (command.get()) {
                case EXIT:
                    System.out.print("전체 연산 결과: ");
                    System.out.println(calculator.getResultQueue().toString());
                    System.exit(0);
                    break;
                case REMOVE:
                    System.out.println("가장 오래된 연산 결과를 삭제합니다.");
                    calculator.resetResult();
                    loopQuit = false;
                    break;
                case BIGGER:
                    System.out.println("입력한 숫자보다 큰 결과값들을 조회합니다.");
                    System.out.print("비교할 숫자를 입력해주세요: ");
                    calculator.compareQueue(new Scanner(System.in).nextBigDecimal());
                    loopQuit = false;
                    break;
                case CONTINUE:
                    loopQuit = true;
                    break;
            }
        } else {
            System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            loopQuit = false;
        }
    }
}
