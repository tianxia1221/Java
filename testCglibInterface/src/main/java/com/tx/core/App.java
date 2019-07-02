package com.tx.core;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //有money人张三
        ISomeService zhangsan = new ISomeServiceImp();
        //屌丝李四
        ISomeService lisi = new ISomeServiceImp();
        //张三请人打官司
        ISomeService someService = new CGLibFactory(zhangsan).myCGLibCreator();
        System.out.println("zhangsan"+someService.Litigate()+"-----"+someService.eat());
        //苦逼的李四自己打官司，自己吃饭
        System.out.println("lisi"+lisi.Litigate()+"-----"+lisi.eat());
    }
}
