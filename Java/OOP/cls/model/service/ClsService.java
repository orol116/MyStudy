package edu.kh.oop.cls.model.service;

import edu.kh.oop.cls.model.vo.Student;
// import edu.kh.oop.cls.model.vo.TestVO;

public class ClsService extends Student{
                        // 부모 Student를 상속 받음
    public void ex1() {

        // 클래스 접근 제한자 확인하기
        Student std = new Student();
        // public class인 Student는 import 가능

        // TestVo test = new TestVO();
        // (default) class 인 TestVO는 import 불가 (다른 패키지)

        System.out.println("다른 패키지");

        System.out.println(std.v1);
        // 다른 패키지에서도 접근 가능한 public만 에러 X

        // System.out.println(std.v2);
        // System.out.println(std.v3);
        // System.out.println(std.v4);

        // 상속 관계에서 직접 접근 가능
        System.out.println(v1);
        System.out.println(v2); // protected 직접 접근 가능
        // System.out.println(v3);
        // System.out.println(v4);
    }

    public void ex2() {
        // static 필드 확인 예제

        // 학생 객체 2개 생성
        Student std1 = new Student();
        Student std2 = new Student();
        
        // 학생 객체에 name 세팅
        std1.setName("홍길동");
        std2.setName("김영희");

        // 정보 출력
        System.out.println(std1.schoolName); // public 이기 때문에 직접 접근 가능
        System.out.println(std1.getName());

        System.out.println(std2.schoolName); // public 이기 때문에 직접 접근 가능
        System.out.println(std2.getName());

        // schoolName 변경
        std1.schoolName = "서울대학교";

        System.out.println("변경 후 std1 : " + std1.schoolName); // 서울대학교
        System.out.println("변경 후 std2 : " + std2.schoolName); // 한국대학교(X) -> 서울대학교

        // std3 생성
        Student std3 = new Student();
        System.out.println("std3 : " + std3.schoolName);

        System.out.println(std3.getName());

        // schoolName에 노란줄이 뜨는 이유 -> 제대로 작성하지 않아서

        // *** static이 붙은 필드(변수)는 클래스명.변수명으로 사용함
        Student.schoolName = "연대";
        System.out.println("Student.schoolName : " + Student.schoolName);

        /* static
         * 1) 공유 메모리 영역 (또는 정적 메모리 영역) 이라고 함.
         *
         * why? 프로그램 시작 시 static이 붙은 코드들이 모두
         * static 영역에 생성되고,
         * 프로그램이 종료될 때까지 사라지지 않음. (정적)
         * 그리고 static 영역에 생성된 변수는 어디서든지 공유할 수 있다. (공유)
         *
         * 2) 사용 방법 : 클래스명.변수명
         */
    }
}
