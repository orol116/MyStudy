package edu.kh.poly.ex2.model.vo;

import edu.kh.poly.ex2.model.service.Calculator;

public class Person extends Animal {
            // Animal의 추상 메서드를 오버라이딩하지 않으면 오류 발생

    private String name;

    // 생성자
    public Person() { // 기본 생성자
        super(); // == Animal 기본 생성자
    }

    public Person(String type, String eatType, String name) { // 매개변수 생성자
        super(type, eatType);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println("숟가락, 젓가락을 이용해서 먹는다.");
    }

    @Override
    public void breath() {
        System.out.println("코와 입으로 숨쉰다.");
    }

    // toString()
    @Override
    public String toString() {
        return "Person : " + super.toString() + " / " + name;
    }
}
