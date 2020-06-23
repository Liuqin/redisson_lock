package qin.redisson;


import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @title: RedissonLock
 * @decription: 锁
 * @author: liuqin
 * @date: 2020/6/23 14:24
 */

@Component
public class RedissonLock {
    @Autowired
    private Redisson redisson;


    /**
     * @return 加锁成功标识
     * @descripttion 独立加锁代码
     * @parms keyLock 加锁字符串，intervalMillisecond 间隔时间，timeOutMillisecond 过期时间
     * @author liuqin
     * @date 2020/6/23
     */
    public Boolean lock(String keyLock, long intervalMillisecond, long timeOutMillisecond) {
        try {
            return redisson.getLock(keyLock).tryLock(intervalMillisecond, timeOutMillisecond, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }


    public Boolean lock10ms(String keyLock, long timeOutMillisecond) {
        try {
            return redisson.getLock(keyLock).tryLock(10, timeOutMillisecond, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * @return 操作成功失败标识
     * @descripttion 删除锁
     * @parms 锁对应字符串
     * @author liuqin
     * @date 2020/6/23
     */
    public boolean delLock(String keyLock) {
        redisson.getLock(keyLock).unlock();
        return true;
    }
}
