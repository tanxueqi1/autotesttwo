package com.code.httpclient;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class PhoneTest {

    public static CloseableHttpClient httpClient=null;

    @BeforeClass
    public static void setUp(){
        httpClient= HttpClients.createDefault();
    }

    @Test
    public void phoneTest() throws IOException {
        String phone="18823833658";
        String key="c1de9dea1b14b181ebd41f0078ac8441";
        HttpGet httpGet = new HttpGet("http://apis.juhe.cn/mobile/get?phone=" + phone+"&key="+key);
        //HttpGet httpGet = new HttpGet("http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=" + phone);
        //httpGet.addHeader("apikey","248b30ecdf493be9d5fb818fe3b5e93d");
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
            int t1 = resBody.indexOf("city") + 7;
            //从t1的位置开始，找到第一个以"'"结果的index
            int t2 = resBody.indexOf("\"", t1);
            String city = resBody.substring(t1, t2);
            System.out.println(city);
            String s = StringEscapeUtils.unescapeJava("\u5317\u4eac");//将\u5317转换成中文
            String s1 = StringEscapeUtils.unescapeXml("\u5317");
            System.out.println(s);
            System.out.println(s1);
        }finally {
            res.close();
        }
    }

    @Test
    public void test(){
        System.out.println(1);
    }
}
