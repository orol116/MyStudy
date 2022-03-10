package edu.kh.oop.method.run;

// toString 메서드의 오버라이드 차이점 확인하기

class A {
    // toString을 정의하지 않음
}

class B {
    int x;

    // 생성자
    B(int x) { this.x = x; }

    // toString을 오버라이드하기
    public String toString() { return "B[" + x + "]"; }
}

public class ToStringTest {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        B b1 = new B(18);
        B b2 = new B(55);

        System.out.println("a1.toString() = " + a1.toString());
        System.out.println("a2 = " + a2);
        System.out.println("b1.toString() = " + b1.toString());
        System.out.println("b2 = " + b2);
    }
}
