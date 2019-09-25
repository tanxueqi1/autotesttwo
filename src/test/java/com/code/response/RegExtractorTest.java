package com.code.response;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExtractorTest {
    public static CloseableHttpClient httpClient=null;

    @BeforeClass
    public static void setUp(){
        httpClient= HttpClients.createDefault();
    }
    //正则表达式一个取值
    @Test
    public void phoneTest() throws IOException {
        String phone="18823833658";
        String key="c1de9dea1b14b181ebd41f0078ac8441";
        HttpGet httpGet = new HttpGet("http://apis.juhe.cn/mobile/get?phone=" + phone+"&key="+key);
        //HttpGet httpGet = new HttpGet("http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=" + phone);
        //httpGet.addHeader("apikey","248b30ecdf493be9d5fb818fe3b5e93d");
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try{
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
            //得到要匹配内容的模式
            Pattern compile = Pattern.compile("\"city\":\"(.+?)\"");
            //通过相应内容进行匹配，得到一个匹配器
            Matcher matcher = compile.matcher(resBody);
            if(matcher.find()){
                int groupCount = matcher.groupCount();
                System.out.println(groupCount);
                String str = matcher.group(0);
                String city = matcher.group(1);
                System.out.println(str);
                System.out.println(city);
            }

        }finally {
            res.close();
        }
    }
    //正则表达式2个取值
    @Test
    public void phoneTest1() throws IOException {
        String phone="18823833658";
        String key="c1de9dea1b14b181ebd41f0078ac8441";
        HttpGet httpGet = new HttpGet("http://apis.juhe.cn/mobile/get?phone=" + phone+"&key="+key);
        //HttpGet httpGet = new HttpGet("http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=" + phone);
        //httpGet.addHeader("apikey","248b30ecdf493be9d5fb818fe3b5e93d");
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try{
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
            //得到要匹配内容的模式
            Pattern compile = Pattern.compile("\"city\":\"(.+?)\".*?\"zip\":\"(.+?)\"");
            //通过相应内容进行匹配，得到一个匹配器
            Matcher matcher = compile.matcher(resBody);
            while (matcher.find()){
                int groupCount = matcher.groupCount();
                System.out.println(groupCount);
                String str = matcher.group(0);
                String city = matcher.group(1);
                String company = matcher.group(2);
                System.out.println(str);
                System.out.println(city);
                System.out.println(company);
            }

        }finally {
            res.close();
        }
    }
}
