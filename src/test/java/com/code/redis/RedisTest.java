package com.code.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public class RedisTest {

    @Test
    public void stringTest(){
        Jedis jedis = new Jedis("101.200.167.51", 6379);
        jedis.auth("testfan");

        //String
        jedis.set("articlecomment:2","1");
        String value = jedis.get("articlecomment:2");
        System.out.println(value);
        Long incr = jedis.incr("articlecomment:2");
        String value1=jedis.get("articlecomment:2");
        System.out.println(incr);
        jedis.close();
    }

    @Test
    public void listTest(){
        Jedis jedis = new Jedis("101.200.167.51", 6379);
        jedis.auth("testfan");

        //List
        jedis.lpush("uarticle:3","1","3");
        List<String> list = jedis.lrange("uarticle:3", 0, -1);
        System.out.println(list);
        jedis.close();
    }

    @Test
    public void setTest(){
        Jedis jedis = new Jedis("101.200.167.51", 6379);
        jedis.auth("testfan");

        //Set
        jedis.sadd("nicknames1","txq","txq1","txq");
       // System.out.println(jedis.smembers("nicknames1"));
        System.out.println(jedis.sismember("nicknames1","txq"));
        jedis.close();
    }

    @Test
    public void hashTest(){
        Jedis jedis = new Jedis("101.200.167.51", 6379);
        jedis.auth("testfan");

        //hash
        jedis.hset("u:5","nickname","zhangsan");
        jedis.hset("u:5","password","123456");
        jedis.hset("u:5","age","26");
        System.out.println(jedis.hgetAll("u:5"));
        jedis.close();
    }

    @Test
    public void sortedSetTest(){
        Jedis jedis = new Jedis("101.200.167.51", 6379);
        jedis.auth("testfan");

        //sortedSet
        jedis.zadd("invest",9999,"小江");
        jedis.zadd("invest",8888,"小幺");
        jedis.zadd("invest",10000,"sicong");
        jedis.zadd("invest",500,"devlin");
        System.out.println(jedis.zrange("invest",0,-1));
        System.out.println(jedis.zrevrange("invest",0,-1));
        System.out.println(jedis.zrevrangeWithScores("invest",0,-1));
        Set<Tuple> invest = jedis.zrevrangeWithScores("invest", 0, -1);
        for (Tuple score:invest) {
            System.out.println(score.getElement()+"\t"+score.getScore());
        }
        jedis.close();
    }
}
