package com.tx.core;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.tx.core.HelloWorld;;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		ConfigurableApplicationContext  context = new ClassPathXmlApplicationContext(
				"SpringBeans.xml");

		HelloWorld obj = (HelloWorld) context.getBean("helloBean");
		obj.printHello();
		context.close();
    }
}
