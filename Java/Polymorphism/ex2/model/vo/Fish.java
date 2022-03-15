package edu.kh.poly.ex2.model.vo;

public class Fish extends Animal {

    // 생성자
    // -> 생성자 미작성시 컴파일러가 자동으로 생성해준다.
    public Fish() {
        super();
    }

    public Fish(String type, String eatType) {
        super(type, eatType);
    }

    // 추상 메서드는 상속받으면 오버라이딩이 강제된다.
    @Override
    public void eat() {
        System.out.println("입을 뻐끔뻐끔 거리면서 먹는다.");
    }

    @Override
    public void breath() {
        System.out.println("아가미로 호흡을 한다.");
    }

    // toString()
    @Override
    public String toString() {
        return "Fish : " + super.toString();
    }
}
