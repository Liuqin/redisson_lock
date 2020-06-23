package qin.redisson.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qin.redisson.RedissonLock;

import java.util.Map;

/**
 * @author LiuQin
 */
@Service
@Slf4j
public class PayService {


    @Autowired
    private RedissonLock redissonLock;

    public int pay2(Map<String, Object> abc, String xyz) {

        Boolean isLock = redissonLock.lock10ms(xyz, 3000);
        if (isLock) {
            log.info("模拟函数执行时间2");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 0;
        } else {
            log.info("命中的是缓存");
            return 1;
        }


    }


}
