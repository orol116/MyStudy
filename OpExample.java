package edu.kh.op.ex;

import java.util.Scanner;

public class OpExample {    // 예제 코드 작성용 클래스

    // ex1() 메서드
    // -> OpExample이 가지고 있는 기능 중 ex1()이라는 기능
    public void ex1() {
        System.out.println("OpExample 클래스에 ex1() 기능 수행");
        System.out.println("클래스 분리 테스트");
        System.out.println("6개월만 힘내자");
    }

    // ex2() 메서드(기능)
    public void ex2() {
        Scanner sc = new Scanner(System.in);

        System.out.print("정수 입력 1 : ");
        int input1 = sc.nextInt();  // 다음 입력되는 정수를 읽어옴.

        System.out.print("정수 입력 2 : ");
        int input2 = sc.nextInt();

        System.out.printf("%d / %d = %d\n", input1, input2, input1 / input2);
        System.out.printf("%d %% %d = %d\n", input1, input2, input1 % input2);
                            // %% : printf 에서 '%' 문자 출력하는 방법
    }

    public void ex3() {

        // 증감(증가, 감소) 연산자 : ++, --
        // -> 피연산자(값)를 1 증가 또는 감소시키는 연산자

        int iNum1 = 10;
        int iNum2 = 10;

        iNum1++;    // iNum1 : 1 증가
        iNum2--;    // iNum2 : 1 감소

        System.out.println("iNum1 = " + iNum1);
        System.out.println("iNum2 = " + iNum2);

        // 전위 연산 : ++3, --2 연산자가 앞쪽에 배치
        // - 다른 연산자보다 먼저 증가 / 감소
        int temp1 = 5;
        System.out.println( ++temp1 + 5 );  // 10
                            // ++5 + 5
                            //  6  + 5 == 11

        // 후위 연산 : 10++, 6-- 연산자가 뒤쪽에 배치
        // - 다른 연산자보다 나중에 증가 / 감소
        int temp2 = 3;
        System.out.println( temp2-- + 2 );  // 5
                            // 3--  + 2
                            // temp2 = 2;

        int a = 3;
        int b = 5;
        int c = a++ + --b;
        // 최종적으로 a, b, c는 각각 얼마인가?

        System.out.printf("%d / %d / %d\n", a, b, c);
    }

    public void ex4() {

        // 비교 연산자 : > < >= <= == !=
        // - 비교 연산자의 결과는 항상 논리값(true / false)
        // - 등호(=)가 포함된 연산자에서 등호는 항상 오른쪽에 있어야 한다.

        // 같다 기호는 =, == 어떤 것일까?
        // 정답은 '==' 이다.
        // 왜?? => 등호 1개(=)는 대입 연산자로 사용
        //      -> 구분을 위해서 두개(==)를 "같다"라는 의미로 사용한다.

        int a = 10;
        int b = 20;

        System.out.println(a > b);     // false
        System.out.println(a < b);     // true
        System.out.println(a != b);    // true
        System.out.println((a == b) == false);    // true

        int c = 4;
        int d = 5;

        System.out.println(c < d);           // true
        System.out.println(c + 1 <= d);      // true
        System.out.println(c >= d - 1);      // true

        System.out.println((++c != d) == (--c != d));      // false
                       //  (++4 != 5) == (--4 != 5))
                       //    false    ==    true      ===>   false

        int temp = 123;

        System.out.println("temp는 짝수인가? " + (temp % 2 == 0));
        System.out.println("temp는 짝수인가? " + (temp % 2 != 1));


        System.out.println("temp는 홀수인가? " + (temp % 2 == 1));
        System.out.println("temp는 홀수인가? " + (temp % 2 != 0));


        System.out.println("temp는 3의 배수인가? " + (temp % 3 == 0));
        System.out.println("temp는 4의 배수인가? " + (temp % 4 == 0));
        System.out.println("temp는 5의 배수인가? " + (temp % 5 == 0));
    }

    public void ex5() {

        // 논리 연산자 : &&(AND), ||(OR)
        // &&(AND) 연산자 : 둘 다 true이면 true, 나머진 false

        // 부호 읽는법
        // & : 앰퍼센트
        // | : 버티칼바

        int a = 100;

        // a는 100이상 이면서 짝수인가?
        System.out.println(a >= 100);       // a는 100이상? => true
        System.out.println(a % 2 == 0);     // a는 짝수?    => true

        // 위의 두 줄을 한 줄로 줄여보기!
        System.out.println(a >= 100 && a % 2 == 0);

        int b = 5;

        // b는 1부터 10까지 숫자 범위에 포함되어 있는가??

        System.out.println(b >= 1); // b는 1 이상인가? => true
        System.out.println(b <= 10); // b는 10 이하인가? => true

        // 위의 두 줄을 한 줄로 줄여보기!
        System.out.println(b >= 1 && b <= 10);


        // || (OR) 연산자 : 둘 다 false 이면 false, 하나라도 true라면 true(AND의 반대)

        int c = 10;

        // c는 10을 초과하거나 짝수인가?
        System.out.println(c > 10 || c % 2 == 0);
    }
    
    public void ex6() {

        // 논리 부정 연산자 : !
        // -> 논리 값을 반대로 바꾸는 연산자

        boolean bool1 = true;
        boolean bool2 = !bool1;     // false

        System.out.println("bool1 = " + bool1);     // true
        System.out.println("bool2 = " + bool2);     // false

        System.out.println("-------------------");

        Scanner sc = new Scanner(System.in);

        // 정수를 하나 입력 받았을 때
        // 1) 해당 정수가 1부터 100 사이 값이 맞는지 확인 (1이상 100이하)
        // 2) (반대) 1부터 100사이 값이 아닌지 확인

        System.out.print("정수 입력 : ");
        int input = sc.nextInt();

        // 1 <= input <= 100
        boolean result1 = (1 <= input) && (input<= 100);

        System.out.printf("%d는 1이상, 100이하의 정수인가? : %b\n", input, result1);

        // 1미만 또는 100초과
        boolean result2 = (input < 1) || (input > 100);
        boolean result3 = !((1 <= input) && (input<= 100));

        System.out.printf("%은/는 1미만 또는 100초과 정수인가? : %b / %b\n", input, result2, result3);
    }
}
