package com.code.testng;

import org.testng.annotations.*;

public class Test2 {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite......");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("afterSuite......");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("beforeClass......");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("afterClass......");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod......");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod......");
    }

    @Test
    public void f1() {
        System.out.println("Test2.in function f1......");
    }

    @Test
    public void f2() {
        System.out.println("Test2.in function f2......");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest......");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest......");
    }
}
