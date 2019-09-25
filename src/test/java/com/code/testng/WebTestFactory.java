package com.code.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class WebTestFactory {

    @DataProvider(name="userpass")
    public Object[][] userPasswordDataProvider(){
        return new Object[][]{
                {"testfan","123456"},
                {"testng","111111"}
        };
    }

    @Factory(dataProvider = "userpass")
    public Object[] userFactory(String username,String password) {
        return new Object[]{
                new UserTest(username,password)
        };
    }


}
