package cn.dengbin97.redislockdemo.controller;

import cn.dengbin97.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: redislockdemo
 * @description:
 * @author: dengbin
 * @create: 2019-04-10 11:48
 **/

public class Test {
    public static void main(String[] args){
        CyclicBarrier c = new CyclicBarrier(2000);
        ExecutorService executorService = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 2000; ++i) {
            executorService.submit(() -> {
                c.await();
                Jedis jedis = RedisUtil.getJedis();
                int res = Integer.parseInt(jedis.get("888"));
                if(res > 0){
                    Thread.sleep(500);
                    jedis.decr("888");
                    // ZookeeperLock.unLock(lockRes);
                    System.out.println("success");
                    return "success";
                }else{
                    //ZookeeperLock.unLock(lockRes);
                    System.out.println("fail");
                    return "fail";
                }
            });
        }
    }
}
