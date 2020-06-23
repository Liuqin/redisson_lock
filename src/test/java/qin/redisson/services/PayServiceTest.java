package qin.redisson.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import qin.DemoApplication;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * @title: PayServiceTest
 * @decription:
 * @author: liuqin
 * @date: 2020/6/23 14:47
 */


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class,PayService.class})
@Slf4j
public class PayServiceTest {

    @Autowired
    private PayService payService;

    @Test
    public void pay2() throws InterruptedException {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("123", "abc");
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    payService.pay2(map, "123");
                }
            });

        }
        executorService.shutdown();
        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {

        }
    }
}