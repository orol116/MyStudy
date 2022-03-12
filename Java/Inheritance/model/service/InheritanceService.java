package edu.kh.inheritance.model.service;

import edu.kh.inheritance.model.vo.Employee;
import edu.kh.inheritance.model.vo.Person;
import edu.kh.inheritance.model.vo.Student;

import java.util.Scanner;

public class InheritanceService {
    public void ex1() {
        // 상속 확인
        // - Person을 상속받은 Student가 Person의 필드, 메서드를 사용할 수 있는가?

        Person p = new Person();

        // p.name = "홍길동"; // private 때문에 직접 접근 불가

        p.setName("홍길동");
        p.setAge(25);
        p.setNationality("대한민국");

        System.out.println(p.getName());
        System.out.println(p.getAge());
        System.out.println(p.getNationality());

        System.out.println("-------------------------------");

        // Student 객체 생성
        Student std = new Student();

        // Student만의 고유한 메서드
        std.setGrade(3);
        std.setClassRoom(5);

        // Person 클래스로 부터 상속 받은 메서드
        std.setName("고길동");
        std.setAge(19);
        std.setNationality("대한민국");

        System.out.println(std.getGrade());
        System.out.println(std.getClassRoom());

        // Student가 Person의 메서드 뿐만 아니라 필드도 상속 받았는지 확인
        System.out.println(std.getName());
        System.out.println(std.getAge());
        System.out.println(std.getNationality());

        System.out.println("-------------------------------");

        Employee emp = new Employee();

        // Employee만의 고유 메서드
        emp.setCompany("청와대");

        // Person 클래스로부터 상속 받은 메서드
        emp.setName("윤주빈");
        emp.setAge(24);
        emp.setNationality("대한민국");

        System.out.println(emp.getCompany());
        System.out.println(emp.getName());
        System.out.println(emp.getAge());
        System.out.println(emp.getNationality());

        System.out.println("--------------------------------");

        // 추가된 breath() 메서드 확인하기
        p.breath();
        std.breath();
        emp.breath();
    }

    public void ex2() {
        // super() 생성자 사용 방법

        // Student 매개변수 5개짜리 생성자
        Student std = new Student("김철수", 17, "대한민국", 1, 3);

        System.out.println(std.getName());
        System.out.println(std.getAge());
        System.out.println(std.getNationality());
        System.out.println(std.getGrade());
        System.out.println(std.getClassRoom());
   }

   public void ex3() {
       // 오버라이딩 확인 예제

       Student std = new Student();
       Employee emp = new Employee();

       std.move(); // 오버라이딩 X -> Person의 메서드 수행
       emp.move(); // 오버라이딩 O -> Employee의 메서드 수행
   }

   public void ex4() {
       // 모든 클래스는 Object 클래스의 후손이다.
       // == 모든 클래스의 최상위 부모는 Object 클래스다.

       // 1) Object 상속 여부 확인
       Person p = new Person(); // Object를 상속받은 Person 객체 생성

       Student std = new Student(); // Person을 상속받은 Student 객체 생성
       // Object - Person - Student

       Scanner sc = new Scanner(System.in);
       String str = "abc";

       // ** Object 상속돠 단계적인 상속 확인
       System.out.println(p.hashCode()); // Object에서 물려 받은 hashCode()
       System.out.println(std.getAge()); // Person에서 물려 받은 getAge()

        System.out.println(std.hashCode());
       // Person이 Object에서 물려 받은 hashCode()를
       // Student가 또 물려 받아 사용
       // -> 상속의 상속의 상속의 ... 상속 가능

       // * Object가 모든 클래스의 최상위 부모인지 확인
       System.out.println(sc.hashCode());
       // Object - hashCode()

       System.out.println(str.hashCode());
       // String - hashCode()
       // -> String이 Object에게 물려 받은 hashCode()를 오버라이딩함
   }

   public void ex5() {
       // toString() 오버라이딩과 super 참조변수
       Person p = new Person("김개똥", 20, "대한민국");

       System.out.println(p.toString());
       System.out.println(p);

       // print 구문 수행 시 참조 변수명을 작성하면
       // 자동으로 toString() 메서드를 호출해서 출력한다!

       System.out.println("--------------------------------------");
       Student std = new Student("이백점", 18, "미국", 2, 6);

       System.out.println(std.toString());
       // 1) Student 클래스 toString() 오버라이딩 전
       // Person으로 부터 상속받은 오버라이딩된 toString()을 수행

       // 2) Student 클래스 toString() 오버라이딩 후
       // Student의 toString() 수행
       Employee emp = new Employee("김근호", 26, "한국", "00증권");
       System.out.println(emp);

   }
}
