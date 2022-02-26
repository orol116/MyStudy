package edu.kh.control.loop;

import java.util.Scanner;

public class ForExample {
    /* for 문
     * - 끝이 정해져 있는(횟수가 지정되어 있는) 반복문
     *
     * [작성법]
     *
     * for(초기식; 조건식; 증감식) {
     *     반복 수행할 코드
     * }
     *
     * - 초기식 : for문을 제어하는 용도의 변수 선언
     *
     * - 조건식 : for문의 반복 여부를 지정하는 식 (다음 반복 진행?)
     *           보통 초기식에 사용된 변수를 이용하여 조건식을 작성함
     *
     * - 증감식 : 초기식에 사용된 변수를
     *           for문이 끝날 때 마다 증가 또는 감소시켜
     *           조건식의 결과를 변하게 하는 식.
     */

    public void ex1() {
        // for문 기초 사용법1
        // - 1~10 출력하기
        // --> 1부터 10까지 1씩 증가하며 출력

        // * 반복문은 조건식이 true일 때만 반복
        for (int i = 1; i <= 10; i++) {
            // 초기식;   조건식;   증감식

            // 반복 수행할 코드
            System.out.println(i);
        }
    }

    public void ex2() {
        // for 기초 사용법2

        // 3에서 7까지 1씩 증가하며 출력
        //      => i의 값이 7이하일때 true가 되는 조건식
        // 3 4 5 6 7

        for (int i = 3; i <= 7; i++)
            System.out.println(i);
    }

    Scanner sc = new Scanner(System.in);

    public void ex3() {
        // 1부터 입력 받은 수까지 1씩 증가하며 출력

        System.out.print("끝 번호 : ");
        int input = sc.nextInt();

        for (int i = 1; i <= input; i++)
            System.out.println(i);
    }

    public void ex4() {
        // 1부터 입력 받은 수까지 1씩 증가하며 출력

        System.out.print("끝 번호 : ");
        int input = sc.nextInt();

        for (int i = 1; i <= input; i += 2)
            // 증감식에 2씩 더함
            System.out.println(i);
    }

    public void ex5() {
        // 1부터 입력 받은 수까찌 0.5씩 증가하며 출력

        System.out.print("끝 번호 : ");
        double input = sc.nextDouble();

        for (double i = 1.0; i <= input; i += 0.5)
            System.out.println(i);
    }

    public void ex6() {
        // 영어 알파벳 A부터 Z까지 한 줄로 출력
        // * char 자료형은 문자형이지만 실제로는 정수(문자표 번호)를 저장한다.

        for (int i = 'A'; i <= 'Z'; i++)
            System.out.print((char) i); // 강제 형변환

        System.out.println("\n------------------------------");

        for (char i = 'A'; i <= 'Z'; i++)
            System.out.print(i);
    }

    public void ex7() {
        // 10에서 1까지 1씩 감소하며 출력
        for (int i = 1; i <= 10; i--)
            System.out.println(i);
    }

    public void ex8() {
        // for문 응용 1 : 반복문을 이용한 값의 누적
        // 1부터 10까지 모든 정수의 합 구하기

        int sum = 0;    // 반복되어 증가되는 i값의 합계를 저장할 변수
        // 0으로 시작하는 이유 : 아무것도 더하지 않음으로
        //                    정확히 결과를 도출할 수 있기 때문에

        for (int i = 1; i <= 10; i++)
            sum += i;

        System.out.println("합계 : " + sum);
    }

    public void ex9() {
        // for문 응용 2 : 정수 5개를 입력 받아서 합계 구하기

        int sum = 0;    // for문 밖에 존재하는 변수로
        // 아래 for문이 끝나도 사용 가능

        for (int i = 1; i <= 5; i++) {
            System.out.printf("정수 입력 %d : ", i);
            sum += sc.nextInt();
        }
        // {} 안에서 생성된 변수는 {} 밖에서는 사용할 수 없다.
        // => {} 끝나는 시점에 사라지기 때문에
        //    {} 밖에서 실행하면 에러가 난다.

        System.out.println(sum);
    }

    public void ex10() {
        // 정수를 몇 번 입력 받을지 먼저 입력 받고
        // 입력된 정수의 합계를 출력

        System.out.print("입력 받을 정수의 개수 : ");
        int num = sc.nextInt();

        int sum = 0;
        for (int i = 1; i <= num; i++) {
            System.out.print("입력 " + i + " : ");
            sum += sc.nextInt();
        }

        System.out.println("합계 : " + sum);
    }

    public void ex11() {
        // 1부터 20까지 1씩 증가하면서 출력
        // 단, 5의 배수에 () 를 붙여서 출력
        // ex) 1 2 3 4 (5) 6 7 ..

        for (int i = 1; i <= 20; i++) {
            if (i % 5 == 0) System.out.print("(" + i + ") ");   // 5의 배수인 경우
            else System.out.print(i + " "); // 5의 배수가 아닌 경우
        }
    }

    public void ex12() {
        // 1부터 20까지 1씩 증가하면서 출력
        // 단, 입력 받은 수의 배수는 () 표시

        // ex)
        // 괄호를 표시할 배수 : 3
        // 1 2 (3) 4 5 (6) ...

        System.out.print("괄호를 표시할 배수 : ");
        int num = sc.nextInt();

        for (int i = 1; i <= 20; i++) {
            if (i % num == 0) System.out.print("(" + i + ") "); // i가 num에 저장된 값의 배수인 경우
            else System.out.print(i + " "); // i가 num에 저장된 값의 배수가 아닌 경우
        }
    }

    public void ex13() {
        // [구구단 출력]
        // 2 ~ 9 사이 수를 하나 입력 받아
        // 해당 단을 출력
        // 단, 입력 받은 수가 2 ~ 9 사이 숫자가 아니면 "잘못 입력하셨습니다" 출력

        System.out.print("단 입력 : ");
        int dan = sc.nextInt();

        if (dan >= 2 && dan <= 9) { // dan이 2 ~ 9 사이일 경우
            for (int i = 1; i <= 9; i++)
                System.out.printf("%d X %d = %d\n", dan, dan, dan * dan);
        } else { // dan이 2 ~ 9 사이가 아닌 경우
            System.out.println("잘못 입력 하셨습니다.");
        }
    }

    // *** 중첩 반복문
    public void ex14() {
        // 구구단 모두 출력하기

        for (int dan = 2; dan <= 9; dan++) { // 2 ~ 9단까지 차례대로 증가

            // 안쪽 for문이 반복하면서 하나의 단을 한 줄로 출력
            for (int num = 1; num <= 9; num++) { // 각 단에 곱해질 1 ~ 9까지 차례대로 증가
                System.out.printf("%2d X %2d = %2d  ", dan, num, dan * num);
            }

            // 하나의 단 출력이 끝났을 때 줄바꿈
            System.out.println(); // 아무 내용 없는 println() ==> 줄바꿈
        }
    }

    public void ex15() {
        // 구구단 역순 출력

        // 9단 -> 2단까지 역방향
        // 곱해지는 수는 1 -> 9까지 정방향

        for (int dan = 9; dan >= 2; dan--) {
            for (int num = 1; num <= 9; num++) {
                System.out.printf("%2d X %2d = %2d  ", dan, num, dan * num);
            }
            System.out.println();
        }
    }

    public void ex16() {
        // 2중 for문을 이용해서 다음 모양을 출력하시오.

        // 12345
        // 12345
        // 12345
        // 12345
        // 12345


        for (int i = 0; i < 5; i++) {   // 5바퀴 반복하는 for문
            for (int j = 1; j <= 5; j++)    // 12345 한 줄 출력하는 for문
                System.out.print(j);
            System.out.println();   // 줄바꿈
        }

        System.out.println("-----------------------------");

        // 54321
        // 54321
        // 54321

        for (int i = 0; i < 3; i++) {
            for (int j = 5; j > 0; j--)
                System.out.print(j);
            System.out.println();
        }
    }

    public void ex17() {
        // 2중 for문을 이용하여 다음 모양을 출력하시오.

        // 1
        // 12
        // 123
        // 1234

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print(j);        // j 대신 '*' 을 넣으면 별찍기가 됌
            System.out.println();
        }

        System.out.println("---------------------");

        // 4321
        // 321
        // 21
        // 1

        for (int i = 4; i >= 1; i--) {      // 줄 반복
            for (int j = i; j >= 1; j--)    // 숫자 출력
                System.out.print(j);
            System.out.println();
        }
    }

    public void ex18() {
        // count (숫자 세기)

        // 1부터 20까지 1씩 증가하면서 출력
        // 3의 배수의 총 개수 출력
        // 3 6 9 12 15 18 : 6개

        int count = 0;

        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) {    // 3의 배수
                System.out.print(i + " ");
                count++;         // if문이 동작할 때 마다 1씩 증가
            }
        }

        System.out.println(" : " + count + "개");
    }

    public void ex19() {
        // 2중 for문과 count를 이용해서 아래 모양 출력하기

        //  1  2  3  4
        //  5  6  7  8
        //  9 10 11 12

        int count = 1;

        for (int x = 1; x <= 3; x++) {  // 3줄
            for (int i = 1; i <= 4; i++) {    // 4칸
                System.out.printf("%3d", count);
                count++;
            }
            System.out.println();
        }
    }
}
