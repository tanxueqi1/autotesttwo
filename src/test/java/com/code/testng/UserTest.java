package com.code.testng;

import org.testng.annotations.Test;

import java.util.Random;

public class UserTest {

    private String username;
    private String password;
    private int userId;

    public UserTest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Test
    public void login(){
        Random ran = new Random();
        userId = ran.nextInt(100)+1;
        System.out.println("login with "+username+" and userId:"+userId);
    }

    @Test(dependsOnMethods = {"login"},enabled = false)
    public void publishArticle(){
        System.out.println("publish article with "+username+" and userId:"+userId);
    }

    @Test(dependsOnMethods = {"login"})
    public void publishArticle2(){
        System.out.println("publish article with "+username+" and userId:"+userId);
    }
}
