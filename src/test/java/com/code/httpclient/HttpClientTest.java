package com.code.httpclient;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {


    public static CloseableHttpClient httpClient=null;
    @BeforeClass
    public static void setUp(){
        httpClient= HttpClients.createDefault();
    }

    @AfterClass
    public static void tearDownAfterClass() throws IOException {
        httpClient.close();
    }

    @Test
    public void getTest() throws IOException {
        String username="test&fan";
        username = URLEncoder.encode(username, "utf-8");
        String url="http://101.200.167.51:8080/http/method?username="+username+"&pwd=testfan&cars=volvo";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);

        }finally {
            res.close();
        }
    }

    @Test
    public void getAndRedirectTest() throws IOException {
        String username="test&fan";
        username = URLEncoder.encode(username, "utf-8");
        String url="http://101.200.167.51:8080/http/method?redirect=true&username="+username+"&pwd=testfan&cars=volvo";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse res = httpClient.execute(httpGet);
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);

        }finally {
            res.close();
        }
    }

    @Test
    public void postTest() throws IOException {
        String url="http://101.200.167.51:8080/http/method";
        HttpPost post = new HttpPost(url);
        List<NameValuePair> paramList=new ArrayList<NameValuePair>();//将请求参数加入到此list中
        paramList.add(new BasicNameValuePair("username","txq"));
        paramList.add(new BasicNameValuePair("pwd","123456"));
        /**
         *  表单方式的body  相当于Content-Type
         *  [Content-Type: application/x-www-form-urlencoded; charset=UTF-8,Content-Length: 23,Chunked: false]
         */
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Charset.forName("utf-8"));
        System.out.println(entity);
        post.setEntity(entity);
        CloseableHttpResponse res = httpClient.execute(post);  //发送请求
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
        }finally {
            res.close();
        }
    }

    @Test
    public void postAndRedirectTest() throws IOException {
        String url="http://101.200.167.51:8080/http/method?redirect=true";
        HttpPost post = new HttpPost(url);
        List<NameValuePair> paramList=new ArrayList<NameValuePair>();//将请求参数加入到此list中
        paramList.add(new BasicNameValuePair("username","txq"));
        paramList.add(new BasicNameValuePair("pwd","123456"));
        /**
         *  表单方式的body  相当于Content-Type
         *  [Content-Type: application/x-www-form-urlencoded; charset=UTF-8,Content-Length: 23,Chunked: false]
         */
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Charset.forName("utf-8"));
        System.out.println(entity);
        post.setEntity(entity);
        CloseableHttpResponse res = httpClient.execute(post);  //发送请求
        String location=null;
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            location = res.getFirstHeader("Location").getValue();
            System.out.println(location);
        }finally {
            res.close();
        }
        HttpGet httpGet=new HttpGet(location);
        CloseableHttpResponse redirectRes = httpClient.execute(httpGet);
        try{
            int statusCode = redirectRes.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(redirectRes.getEntity());
            System.out.println(resBody);
        }finally {
            res.close();
        }
    }

    /**
     *如何向服务器发送json格式的数据
     */
    @Test
    public void postJsonTest() throws IOException {
        String url="http://101.200.167.51:8080/http/method1";
        HttpPost post = new HttpPost(url);
        String requestContent="{\"id\":1,\"name\":\"txq\",\"email\":\"535223120@qq.com\"," +
                "\"birthday\":{\"birthday\":\"19861210\"},\"regDate\":\"2015-07-07 11:35:08\"}";
        //如果是xml或json请求数据，那么通过StringEntity构造请求数据，同时设置字符集
        StringEntity entity = new StringEntity(requestContent, "utf-8");
        //告诉服务器请求内容的类型
        System.out.println(entity);
        entity.setContentType("application/json");
        System.out.println(entity);
        post.setEntity(entity);
        CloseableHttpResponse res = httpClient.execute(post);  //发送请求
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
        }finally {
            res.close();
        }
    }

    /**
     *如何向服务器发送xml格式的数据
     */
    @Test
    public void postXmlTest() throws IOException {
        String url="http://101.200.167.51:8080/http/method1";
        HttpPost post = new HttpPost(url);
        String requestContent="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><note><to>George</to>" +
                "<from>John</from><heading>Reminder</heading><body>Don't forget the meeting</body></note>";
        //如果是xml或json请求数据，那么通过StringEntity构造请求数据，同时设置字符集
        StringEntity entity = new StringEntity(requestContent, "utf-8");
        //告诉服务器请求内容的类型
        System.out.println(entity);
        entity.setContentType("application/xml");
        System.out.println(entity);
        post.setEntity(entity);
        CloseableHttpResponse res = httpClient.execute(post);  //发送请求
        try{
            int statusCode = res.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(res.getEntity());
            System.out.println(resBody);
        }finally {
            res.close();
        }
    }

    /**
     *带有token验证信息的表单：如何提取表单中token，获得token后才能在提交表单时带上token
     */
    @Test
    public void postFormWithTokenTest() throws IOException {
        HttpClientContext context = HttpClientContext.create();
        CookieStore cookieStore = new BasicCookieStore();  //创建cookie存储器，存储cookies信息
        context.setCookieStore(cookieStore);//context目的是保存各种配置信息,包括cookieStore
        String url="http://101.200.167.51:8080/http/tokenForm";
        HttpGet httpGet = new HttpGet(url);
        //执行请求的时候，会通过context中的cookiestore进行cookie保存
        CloseableHttpResponse res = httpClient.execute(httpGet,context);
        String token=null;
        try{
            String resBody = EntityUtils.toString(res.getEntity());//获得响应的http
            System.out.println(resBody);
            int t1 = resBody.indexOf("_token")+15;
            int t2 = resBody.indexOf("\"", t1);
            token = resBody.substring(t1, t2);
            System.out.println(token);

        }finally {
            res.close();
        }
        HttpPost post = new HttpPost(url);
        List<NameValuePair> paramList=new ArrayList<NameValuePair>();//将请求参数加入到此list中
        paramList.add(new BasicNameValuePair("_token",token));
        paramList.add(new BasicNameValuePair("username","txq1"));
        paramList.add(new BasicNameValuePair("pwd","123456"));
        /**
         *  表单方式的body  相当于Content-Type
         *  [Content-Type: application/x-www-form-urlencoded; charset=UTF-8,Content-Length: 23,Chunked: false]
         */
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Charset.forName("utf-8"));
        System.out.println(entity);
        post.setEntity(entity);
        CloseableHttpResponse postres = httpClient.execute(post,context);  //发送请求,带上cookie信息
        try{
            int statusCode = postres.getStatusLine().getStatusCode();
            System.out.println(statusCode);
            String resBody = EntityUtils.toString(postres.getEntity());
            System.out.println(resBody);
        }finally {
            postres.close();
        }

    }
}
