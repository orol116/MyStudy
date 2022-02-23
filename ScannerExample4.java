package edu.kh.variable.ex2;

import java.util.Scanner;

public class ScannerExample4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("입력 1 : ");
        String str1 = sc.next() + " ";  // 띄어쓰기 추가

        System.out.println(str1);

        System.out.print("입력 2 : ");
        str1 = str1 + sc.next() + " ";
        // 대입 연산자(=) : 오른쪽에 작성된 값을 왼쪽 변수에 대입

        System.out.println(str1);

        System.out.print("입력 3 : ");
        str1 = str1 + sc.next() + " ";

        System.out.println(str1);
        // 누적 효과 (변수의 재사용성)
    }
}
