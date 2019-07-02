package com.tx.core;

public class PersonServiceImpl implements PersonService {
	public void addPerson(String personName) {
		System.out.println("add person " + personName);
	}

	public Integer deletePerson(String personName) {
		System.out.println("delete person " + personName);
		return 1;
	}

	public void editPerson(String personName) {
		System.out.println("edit person " + personName);
		throw new RuntimeException("edit person throw exception");
	}
}