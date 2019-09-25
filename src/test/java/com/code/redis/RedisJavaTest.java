package com.code.redis;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import redis.clients.jedis.Jedis;

public class RedisJavaTest extends AbstractJavaSamplerClient {
    private Jedis jedis = null;
    private String host = null;
    private int port = 0;
    private String password = null;

    @Override
    //设置传入的参数，可以设置多个，已设置的参数会显示到Jmeter的参数列表中
    public Arguments getDefaultParameters() {
        return super.getDefaultParameters();
    }

    @Override
    //初始化方法，实际运行时每个线程仅执行一次，再测试方法运行前执行
    public void setupTest(JavaSamplerContext context) {
        System.out.println("start to connect redis!");
        JMeterContext jmctx = JMeterContextService.getContext();
        JMeterVariables vars = jmctx.getVariables();
        host = vars.get("redis-host");
        port = Integer.parseInt(vars.get("redis-port"));
        password = vars.get("password");
        try {
            jedis = new Jedis(host,port,2000);
            jedis.auth(password);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("success to connect redis!");
        super.setupTest(context);
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }

    //测试执行的循环体，根据线程数和循环次数的不同可执行多次
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        try{
            String mobile = jedis.get("mobile");
            result.setResponseData(mobile,"UTF-8");
            result.setSuccessful(true);
            result.setResponseMessage("OK");
            result.setResponseCode("200");
        }catch (Exception e){
            result.setSuccessful(false);
            result.setResponseMessage(e.getMessage());
        }
        result.sampleEnd();
        return result;
    }
}
