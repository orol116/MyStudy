package edu.kh.poly.ex1.model.vo;

public class Tesla extends Car { // 전기차

    private int batteryCapacity;

    public Tesla() {
        super(); // 부모 생성자(Object)
    }

    public Tesla(String engine, String fuel, int wheel, int batteryCapacity) {
        super(engine, fuel, wheel);
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    // Car.toString() 오버라이딩
    @Override
    public String toString() {
        return super.toString() + " / " + batteryCapacity;
    }
}
