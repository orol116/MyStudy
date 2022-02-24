package edu.kh.op.practice;

import java.util.Scanner;

public class OpPractice {
    Scanner sc = new Scanner(System.in);

    // 인원 수와 사탕 개수를 키보드로 입력 받고
    // 1인당 동일하게 나눠가진 사탕 개수와 나눠주고 남은 사탕의 개수를 출력하기
    public void practice1() {
        System.out.print("인원 수 : ");
        int num = sc.nextInt();
        System.out.print("사탕 개수 : ");
        int candy = sc.nextInt();
        System.out.println();

        System.out.println("1인당 사탕 개수 : " + candy / num);
        System.out.println("남은 사탕 개수 : " + candy % num);
    }

    // 키보드로 입력 받은 값들을 변수에 기록하고 저장된 변수 값을 화면에 출력하여 확인하기
    public void practice2() {
        System.out.print("이름 : ");
        String name = sc.next();
        System.out.print("학년(정수) : ");
        int grade = sc.nextInt();
        System.out.print("반(정수) : ");
        int classNum = sc.nextInt();
        System.out.print("번호(정수) : ");
        int number = sc.nextInt();
        System.out.print("성별(남학생/여학생) : ");
        String gender = sc.next();
        System.out.print("성적(소수점 아래 둘째 자리까지) : ");
        double result = sc.nextDouble();
        System.out.println();

        System.out.printf("%d학년 %d반 %d번 %s %s의 성적은 %.2f점 입니다.", grade, classNum, number, name, gender, result);
    }

    // 정수를 하나 입력 받아 짝수/홀수를 구분하기
    public void practice3() {
        System.out.print("정수 입력 : ");
        int num = sc.nextInt();
        System.out.println(num % 2 == 0 ? "짝수 입니다." : "홀수 입니다.");
    }

    // 국어, 영어, 수학에 대한 점수를 키보드를 이용해 정수로 입력 받고,
    // 세 과목에 대한 합계(국어+영어+수학)와 평균(합계/3.0)을 구하기
    //  또한 세 과목의 점수와 평균을 가지고 합격 여부를 처리하는데
    // 세 과목 점수가 각각 40점 이상이면서 평균이 60점 이상일 때 합격, 아니라면 불합격을 출력하기
    public void practice4() {
        System.out.print("국어 : ");
        int score1 = sc.nextInt();
        System.out.print("영어 : ");
        int score2 = sc.nextInt();
        System.out.print("수학 : ");
        int score3 = sc.nextInt();
        System.out.println();

        int sum = score1 + score2 + score3;
        double avg = sum / 3;

        System.out.println("합계 : " + sum);
        System.out.printf("평균 : %.1f\n", avg);
        System.out.println((score1 >= 40 && score2 >= 40 && score3 >= 40
                                && avg >= 60) ? "합격" : " ");
    }
}
