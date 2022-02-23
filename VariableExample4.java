package edu.kh.variable.ex1;

public class VariableExample4 {
    public static void main(String[] args) {
        // main method(메서드) : 자바 애플리케이션(프로그램)을 실행하기 위해 반드시 필요한 메서드

        /* 강제 형변환
         * - 기존 자료형을 원하는 자료형으로 강제 변환 시키는 것
         *
         * 1) 값의 볌위가 큰 자료형을 작은 자료형으로 변환할 때 사용
         * 2) 출력되는 데이터의 표기법을 변화시키고 싶을 때때
         *
         * ** 강제 형변환 방법
         * - 자료형을 변환시키고 싶은 값 또는 변수 앞에 (자료형)을 작성
         * ex) double temp = 3.14;
         *     int num = (int)temp;
         */

        double tmp = 3.14;
        int num = (int) tmp;

        System.out.println("tmp = " + tmp);
        System.out.println("num = " + num);
        // 실수 -> 정수형 변환 시 소수점 버림 처리(데이터 손실)

        // int -> byte 강제 형변환
        int iNum 
    }
}
