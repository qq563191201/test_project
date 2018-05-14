package com.redis;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class DemoRedis {

	@Test
	public void test1Normal() {
	    Jedis connection = new Jedis("localhost");
	    connection.auth("fish_redis");
	    Date exp=new Date();
        // 去上一天的时间
        String key=null;
//        connection.psetex("WLD:111",exp.getTime(),"50");
        
        Set<Tuple> result = connection.zrevrangeWithScores("RK:BY:04", 0 , 4);
        for (Tuple tuple : result) {
        	System.out.println(tuple.getScore());
        }
        
        connection.disconnect();
	}
	
}
