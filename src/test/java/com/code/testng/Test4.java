package com.code.testng;

import org.testng.annotations.Test;

public class Test4 {

    @Test(groups = {"reg","login"})
    public void f1() {
        System.out.println("Test1.in function f1......");
    }

    @Test(groups = {"login"})
    public void f2() {
        System.out.println("Test1.in function f2......");
    }

    @Test(groups = {"reg"})
    public void f3() {
        System.out.println("Test1.in function f3......");
    }

    @Test(dependsOnGroups = {"login"})
    public void f4() {
        System.out.println("Test1.in function f4......");
    }

}
