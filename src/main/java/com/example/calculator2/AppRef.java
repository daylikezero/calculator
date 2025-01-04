package com.example.calculator2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 필수기능 Lv2. 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기
 * <p>
 * Lv1에서 구현한 App 클래스의 메서드에 Calculator 클래스가 활용될 수 있도록 수정
 * 연산 수행 역할은 Calculator 클래스가 담당
 * 연산 결과는 Calculator 클래스의 연산 결과를 저장하는 필드에 저장
 * </p>
 * ref1) loopQuit 분기로 command 반복 수행: remove를 입력한 후 다시 exit, remove, 그 외 입력받기
 * ref2) finally : 계산 성공, 실패 여부와 상관 없이 사용자 입력(command) 실행
 */
public class AppRef {
    public static boolean loopQuit = false;

    public static void main(String[] args) {
        /* Calculator 인스턴스 생성 */
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                loopQuit = false;
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int num1 = sc.nextInt();
                System.out.print("두 번째 숫자를 입력하세요: ");
                int num2 = sc.nextInt();
                sc.nextLine(); // nextInt() 입력 후 버퍼의 개행문자(\n) 제거
                System.out.print("사칙연산 기호를 입력하세요: ");
                char operator = sc.nextLine().charAt(0);

                /* 연산 수행 역할은 Calcuator 클래스가 담당 */
                int result = calculator.calculate(num1, num2, operator);

                System.out.println("결과 : " + result);

            } catch (InputMismatchException e) {
                System.out.println("잘못된 숫자 입력입니다.");
                sc.nextLine(); // nextInt() 입력 후 버퍼의 개행문자(\n) 제거
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                // 사용자 메뉴: 계산 성공과 예외 발생에 상관 없이 수행되어야 하는 로직
                while (!loopQuit) {
                    System.out.println("더 계산하시겠습니까? (exit: 입력 시 종료 ");
                    System.out.println("remove: 가장 오래된 결과 삭제");
                    System.out.print("그 외: 프로그램을 계속 실행): ");
                    command(sc.nextLine(), calculator);
                }
            }
        }
    }

    public static void command(String input, Calculator calculator) {
        switch (input) {
            case "exit":
                System.out.print("전체 연산 결과: ");
                System.out.println(calculator.getResultQueue().toString());
                System.exit(0);
                break;
            case "remove":
                System.out.println("가장 오래된 연산 결과를 삭제합니다.");
                calculator.resetResult();
                loopQuit = false;
                break;
            default:
                loopQuit = true;
        }
    }


}
