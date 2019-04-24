package cn.dengbin97.redislockdemo.task;

import cn.dengbin97.lock.RedisLock;
import cn.dengbin97.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @program: redislockdemo
 * @description: 定时任务
 * @author: dengbin
 * @create: 2019-04-23 10:56
 **/

@Component
@Slf4j
public class DelayTask {

    @Scheduled(cron = "0 42 11 * * ? ")
    public void scheduled() throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        log.info("机器1-定时任务开始");
        if ( RedisLock.tryLock("key", "string1", 0)) {
            jedis.incrBy("key", 1);
            log.info("机器1-获取到锁，执行定时任务");
            RedisLock.unLock("key", "string1");
        } else {
            log.info("机器1-获取锁失败");
        }
    }

    @Scheduled(cron = "0 42 11 * * ? ")
    public void scheduled2() throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        log.info("机器2-定时任务开始");
        if ( RedisLock.tryLock("key", "string2", 0)) {
            jedis.incrBy("key", 1);
            log.info("机器2-获取到锁，执行定时任务");
            RedisLock.unLock("key", "string2");
        } else {
            log.info("机器2-获取锁失败");
        }
    }

    @Scheduled(cron = "0 42 11 * * ? ")
    public void scheduled3() throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        log.info("机器3-定时任务开始");
        if ( RedisLock.tryLock("key", "string3", 0)) {
            jedis.incrBy("key", 1);
            log.info("机器3-获取到锁，执行定时任务");
            RedisLock.unLock("key", "string3");
        } else {
            log.info("机器3-获取锁失败");
        }
    }
}
