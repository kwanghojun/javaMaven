package com.thread.Stream;

public class Person {

	  private String name;
	  private Integer age;

	  public Person(String name) {
	    this.name = name;
	  }

	  public String getName() {
	    return name;
	  }

	  public Integer getAge() {
	    return age;
	  }

	  public Person updateAge(Integer age) {
	    this.age = age;
	    return this;
	  }

	  @Override
	  public String toString() {
	    return "Person{" +
	        "name='" + name + '\'' +
	        ", age=" + age +
	        '}';
	  }
	}
