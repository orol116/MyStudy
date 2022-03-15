package edu.kh.poly.ex2.model.vo;

public abstract class Animal {
    // 추상 클래스
    // 1. 미완성 메서드(추상 메서드)를 보유하고 있는 클래스

    // 2. (추상 메서드는 없지만)객체로 만들면 안되는 클래스


    // 필드
    private String type;
    private String eatType;

    // 생성자
    // - 추상 클래스는 new 연산자를 이용해서 직접적인 객체 생성은 불가능하지만
    //   상속받은 객체 생성 시 내부에 생성될 때 사용된다.
    //   == super() 생성자
    public Animal() { }

    public Animal(String type, String eatType) {
        this.type = type;
        this.eatType = eatType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEatType() {
        return eatType;
    }

    public void setEatType(String eatType) {
        this.eatType = eatType;
    }

    @Override // 오버라이딩 되었음을 컴파일러에게 알려주는 어노테이션
    public String toString() {
        return type + " / " + eatType;
    }

    // 동물의 공통 기능
    // -> 동물은 공통적으로 먹고, 숨쉬지만
    //    어떤 동물이냐에 따라 그 방법이 다름! -> 해당 클래스에 메서드를 정의할 수 없다.
    //    --> 그럼 어떻게 해야 하는가??
    //        미완성 상태로 두어 상속 받은 자식이 해당 메서드를 정의하도록
    //        오버라이딩을 강제화 시킴 --> 추상(abstract) 메서드로 작성

    // 먹다
    public abstract void eat();
    // 숨쉬다
    public abstract void breath();
}
