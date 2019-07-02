package com.tx.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringBeans.xml");
    	PersonService personService = appContext.getBean(PersonService.class);
    	String personName = "Tom";
    	System.out.println("---------addPerson----------");
    	personService.addPerson(personName);
    	System.out.println("---------deletePerson----------");
    	personService.deletePerson(personName);
    	System.out.println("---------editPerson----------");
    	personService.editPerson(personName);
    	((AbstractApplicationContext) appContext).close();
    }
}
