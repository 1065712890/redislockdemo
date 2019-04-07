package cn.dengbin97.redislockdemo.controller;

import cn.dengbin97.lock.RedisLock;
import cn.dengbin97.lock.lock.ZookeeperLock;
import cn.dengbin97.redislockdemo.util.HostHolder;
import cn.dengbin97.util.RedisUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutionException;

/**
 * @program: redislockdemo
 * @description: 购买商品测试controller
 * @author: dengbin
 * @create: 2018-11-19 15:12
 **/

@Controller
public class DemoController implements InitializingBean {

    @Autowired
    HostHolder hostHolder;

    Jedis jedis;

    @RequestMapping("/buy")
    @ResponseBody
    public String buy(String id) throws ExecutionException {
        String lockRes = ZookeeperLock.tryLock(id, hostHolder.getId(), 1);
        //获取锁失败
        if(StringUtils.isEmpty(lockRes)){
            return "fail";
        }
        int res = Integer.parseInt(jedis.get(id));
        if(res > 0){
            jedis.decr(id);
            ZookeeperLock.unLock(id);
            return "success";
        }else{
            ZookeeperLock.unLock(id);
            return "fail";
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(11111);
        jedis = RedisUtil.getJedis();
    }
}
