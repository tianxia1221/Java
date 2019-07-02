package com.tx.core;

public interface PersonService {
	public void addPerson(String personName);

	public Integer deletePerson(String personName);

	public void editPerson(String personName);
}