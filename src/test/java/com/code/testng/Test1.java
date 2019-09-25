package com.code.testng;

import org.testng.annotations.Test;

public class Test1 {

    @Test
    public void g1(){
        System.out.println("Test1.in function g1......");
    }

    @Test(dependsOnMethods ={"g1"})
    public void f1(){
        System.out.println("Test1.in function f1......");
    }

    @Test(dependsOnMethods = {"g1"})
    public void f2(){
        System.out.println("Test1.in function f2......");
    }

}
