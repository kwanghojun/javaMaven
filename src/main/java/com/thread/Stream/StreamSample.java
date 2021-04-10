package com.thread.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamSample {
	private Random ageRandom = new Random();

	  @Test
	  public void Test() {
		int corecnt= Runtime.getRuntime().availableProcessors();
		System.out.println("core...cnt..["+corecnt+"]");
	    List<Person> person = createPerson(
	        Arrays.asList("Jhon", "Yeom", "Takenaka", "Hikari","Hikari","111","222","333","444"));

	    person.forEach(System.out::println);
	  }

	  public List<Person> createPerson(List<String> names) {
	    return names.stream()
	    	//.parallel()
	        .map(Person::new)
	        .map(person -> person.updateAge(findAge(person.getName())))
	        .collect(Collectors.toList());
	  }

	  public Integer findAge(String name) {
	    Integer age = ageRandom.nextInt(name.length() * 8) + 1;
	    try {
	      TimeUnit.SECONDS.sleep(3);
	    } catch (InterruptedException ignore) {
	    }

	    return age;
	  }
}
