package com.code.testng;

import org.testng.annotations.Test;

public class Test3 {

    @Test(priority = 1)
    public void f1() {
        System.out.println("Test1.in function f1......");
    }

    @Test(priority = 3)
    public void f2() {
        System.out.println("Test1.in function f2......");
    }

    @Test(priority = 2)
    public void f3() {
        System.out.println("Test1.in function f3......");
    }

}
