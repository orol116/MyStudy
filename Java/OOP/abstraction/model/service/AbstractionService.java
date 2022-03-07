package edu.kh.oop.abstraction.model.service;

import edu.kh.oop.abstraction.model.vo.People;

// Service : 특정 기능(비즈니스 로직)을 제공하는 클래스
public class AbstractionService {
    // 해당 클래스가 객체가 되면 아래 예제 기능을 수행할 수 있다.

    public void ex1() {
        // 국민 객체 만들기
        People p1 = new People();
        // People p1 : People 객체의 주소를 저장하여 참조하는 변수 p1
        // new People() : 새로운 People 객체 Heap 영역에 생성

        // *** 클래스 이름이 자료형처럼 사용되어짐!!
        // ==> 그래서 클래스를 "사용자 정의 자료형" 이라고도 한다!! ***

        People p2 = new People();

        // 만들어진 객체는 추상화가 적용되어 있어 누군지 알 수 없음
        // -> 속성(데이터)를 추가하여 누구인지 알 수 있게함(구체화)

        // p1.name = "홍길동"; // The field People.name is not visible
        p1.setName("홍길동"); // setter를 이용한 간접 접근 방법

        // p1.age = 25;
        p1.setAge(25);
        p1.setGender('남');
        p1.setPhone("010-1234-1234");
        p1.setpNo("980303-1234567");
        p1.setAddress("인천시 계양구 계산동");

        System.out.println("p1의 name : " + p1.getName());
        System.out.println("p1의 age : " + p1.getAge());
        System.out.println("p1의 gender : " + p1.getGender());
        System.out.println("p1의 phone : " + p1.getPhone());
        System.out.println("p1의 pNo : " + p1.getpNo());
        System.out.println("p1의 address : " + p1.getAddress());

        p2.vote();
        p2.tax();
    }
}
