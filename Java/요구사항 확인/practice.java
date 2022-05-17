package edu.kh.practice;

// 1번
public class A {
	private B b;
}

public class B {
	private String f1;
	private int f2;
}

// 2번
public class B {
	private String f1;
	private int f2;
}

public class Child extends Parent{
	private String name;
	public void childMethod() {...}
	protectd void print(){...}
}

// 3번
public abstract class Shape{
	public void draw(){...}
	public void erase(){...}
	public int getLength(){...}
	protected abstract double getArea();
}

public interface Resizable {
	void resize();
}

public class Triangle extends Shape implements Resizable {
	public boolean isEquilateral() {...}
	protected double getArea() {...}
	public void resize() {...}
}

public class Rectangle extends Shape implements Resizable {
	public boolean isSquare() {...}
	protected double getArea() {...}
	public void resize() {...}
}

// 4번
public class ClassRoom{
	private Student std;
	public ClassRoom(Student std){this.std = std;}
}

public class Student{
	private String name;
	private int age;
}

// 5번
public class Library{
	private Book book;
	public Library(){this.book = new book();}
}

public class Book{
	private String title;
	private String author;
}

// 6번
public class Shop{
	private Product product;
	private Employee emp;
	public Shop(Product p){
		this.product = p;
		this.emp = new Employee();	
	}
}

public class Employee{
	private String name;
}

public class Product{
	private String pName;
	private int price;
}

// 7번
public class CafeLatte extends coffee{
	private temperature;
	private int price;
	public void drink(){Option opt = new Option();}
}

public class Option{
	private int shot;
	private String ice;
	private int syrup;
}

public abstract class coffee{
	public String beans;
}

public class Cafe{
	private String name;
	private String address;
	private CafeLatte latte;
	public Cafe(CafeLatte latte){this.latte = latte;}
}