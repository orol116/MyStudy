package edu.kh.oop.abstraction.model.vo;

// VO(Value Object) : 값 저장용 객체를 만들기 위한 클래스를 모아두는 패키지
public class People { // 국민(사람) 클래스

    // 클래스란? => 객체의 특성(속성, 기능)을 정의한 것으로써
    //             객체를 만들기 위한 설계도

    // 캡슐화(Encapsulation)
    // - 데이터와 기능을 하나로 묶어서 관리하는 기법
    // - 데이터의 직접적인 접근을 제한하는 것이 원칙이다.
    // --> 직접 접근을 못하기 때문에
    //     클래스 내부에 접근 할 수 있게 getter / setter 사용

    // 속성 == 값 == data
    // (값을 저장하기 위한 변수 선언)
    // -> 국민이라면 공통적으로 가지고있는 속성만 작성(추상화)
    // -> 이름, 성별, 주민번호, 주소, 전화번호, 나이
    private String name;
    private char gender;
    private String pNo;
    private String address;
    private String phone;
    private int age;

    // *** 데이터 직접 접근 제한 ***
    // public(공공의) -> private(사적인, 개인적인) 변경

    // 기능 == 행동 == method == 메서드
    public void tax() {
        System.out.println("세금을 냅니다.");
    }

    public void vote() {
        System.out.println("투표를 합니다.(꼭 하세요)");
    }

    // 캡슐화에서 사용할 간접 접근 기능(getter / setter)
    public void setName(String name) { // setter
        // 외부로부터 전달 받은 name을
        // 현재 객체의 속성 중 name( == this.name)에 대입

        this.name = name;
    }

    public String getName() { // getter
        // 현재 객체의 속성 중 name을
        // 호출한 곳으로 반환(return)
        return name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getpNo() {
        return pNo;
    }

    public void setpNo(String pNo) {
        this.pNo = pNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 작업 화면에서 alt + insert
    //         ==> Generate ==> Getter and Setter 자동 완성 기능
}
