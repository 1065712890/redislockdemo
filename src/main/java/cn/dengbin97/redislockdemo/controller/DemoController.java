package cn.dengbin97.redislockdemo.controller;

import cn.dengbin97.lock.RedisLock;
import cn.dengbin97.lock.lock.ZookeeperLock;
import cn.dengbin97.redislockdemo.util.HostHolder;
import cn.dengbin97.util.RedisUtil;
import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


/**
 * @program: redislockdemo
 * @description: 购买商品测试controller
 * @author: dengbin
 * @create: 2018-11-19 15:12
 **/

@Controller
public class DemoController {

    @Autowired
    HostHolder hostHolder;

//    @RequestMapping("/buy")
//    @ResponseBody
//    public String buy(String id) throws  InterruptedException {
//        Jedis jedis = RedisUtil.getJedis();
//        int res = Integer.parseInt(jedis.get(id));
//        if (res > 0) {
//            Thread.sleep(100);
//            jedis.decr(id);
//            RedisUtil.returnResource(jedis);
//            return "success";
//        } else {
//            RedisUtil.returnResource(jedis);
//            return "fail";
//        }
//    }

//    @RequestMapping("/buy")
//    @ResponseBody
//    public String buy(String id) throws InterruptedException {
//        Boolean lockRes = RedisLock.tryLock(id, hostHolder.getId(), TimeUnit.SECONDS, 1L);
//        //获取锁失败
//        if (!lockRes) {
//            return "fail";
//        }
//        Jedis jedis = RedisUtil.getJedis();
//        int res = Integer.parseInt(jedis.get(id));
//        if (res > 0) {
//            Thread.sleep(100);
//            jedis.decr(id);
//            RedisLock.unLock(id, hostHolder.getId());
//            RedisUtil.returnResource(jedis);
//            return "success";
//        } else {
//            RedisLock.unLock(id, hostHolder.getId());
//            RedisUtil.returnResource(jedis);
//            return "fail";
//        }
//    }

//    @RequestMapping("/buy")
//    @ResponseBody
//    public String buy(String id) throws ExecutionException, InterruptedException {
//        String lockRes = ZookeeperLock.tryLock(id, hostHolder.getId(), 10);
//        //获取锁失败
//        if (StringUtils.isEmpty(lockRes)) {
//            return "fail";
//        }
//        Jedis jedis = RedisUtil.getJedis();
//        int res = Integer.parseInt(jedis.get(id));
//        if (res > 0) {
//            Thread.sleep(100);
//            jedis.decr(id);
//            ZookeeperLock.unLock(lockRes);
//            RedisUtil.returnResource(jedis);
//            return "success";
//        } else {
//            ZookeeperLock.unLock(lockRes);
//            RedisUtil.returnResource(jedis);
//            return "fail";
//        }
//    }


    @RequestMapping("/buy")
    @ResponseBody
    @CrossOrigin(origins = {"http://localhost:8080", "null"})
    public String buy(String id) throws InterruptedException {
        Boolean lockRes = RedisLock.tryLock(id, hostHolder.getId(), 1);
        //获取锁失败
        if (!lockRes) {
            return "fail";
        }
        Jedis jedis = RedisUtil.getJedis();
        int res = Integer.parseInt(jedis.get(id));
        if (res > 0) {
            jedis.decr(id);
            RedisLock.unLock(id, hostHolder.getId());
            RedisUtil.returnResource(jedis);
            return "success";
        } else {
            RedisLock.unLock(id, hostHolder.getId());
            RedisUtil.returnResource(jedis);
            return "fail";
        }
    }
}
