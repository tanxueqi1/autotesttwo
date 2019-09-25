package com.code.response;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONExtractorTest {
    public static CloseableHttpClient httpClient = null;

    @BeforeClass
    public static void setUp() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void phoneTest1() throws IOException {
        String phone = "18823833658";
        String key = "c1de9dea1b14b181ebd41f0078ac8441";
        HttpGet httpGet = new HttpGet("http://apis.juhe.cn/mobile/get?phone=" + phone + "&key=" + key);
        //HttpGet httpGet = new HttpGet("http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=" + phone);
        //httpGet.addHeader("apikey","248b30ecdf493be9d5fb818fe3b5e93d");
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try {
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
            JSONObject jsonObject = JSONObject.parseObject(resBody);
            String reason = jsonObject.getString("reason");
            JSONObject result = jsonObject.getJSONObject("result");
            //getString 因为city的类型是String
            String city = result.getString("province");
            System.out.println(reason);
            System.out.println(city);


        } finally {
            res.close();
        }
    }


    /**
     *一个接口的输出做处理，然后才可以作为下一个用例的输入
     */
    @Test
    public void phoneTest2() throws IOException {
        String phone = "18823833658";
        String key = "c1de9dea1b14b181ebd41f0078ac8441";
        HttpGet httpGet = new HttpGet("http://apis.juhe.cn/mobile/get?phone=" + phone + "&key=" + key);
        //HttpGet httpGet = new HttpGet("http://apis.baidu.com/apistore/mobilenumber/mobilenumber?phone=" + phone);
        //httpGet.addHeader("apikey","248b30ecdf493be9d5fb818fe3b5e93d");
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try {
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
            JSONObject jsonObject = JSONObject.parseObject(resBody);
            System.out.println(jsonObject);
            String reason = jsonObject.getString("reason");
            JSONObject result = jsonObject.getJSONObject("result");
            System.out.println(result);
            result.remove("card");
            System.out.println(result);
            result.put("month",12);
            result.put("company","联通");
            System.out.println(result);
            List list=new ArrayList();
            list.add("18823833658");
            list.add("18823833659");
            list.add("18823833660");
            result.put("contactHistory",list);
            System.out.println(result);
            //getString 因为city的类型是String
            String city = result.getString("province");
            System.out.println(reason);
            System.out.println(city);

        } finally {
            res.close();
        }
    }

}
