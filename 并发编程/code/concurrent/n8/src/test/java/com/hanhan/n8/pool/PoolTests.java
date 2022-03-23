package com.hanhan.n8.pool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PoolTests {

    @Test
    void pollTest(){
        ThreadPool threadPool = new ThreadPool(1,1000, TimeUnit.MILLISECONDS, 1,
                (queue, task)->{
                    // 1. 死等
                    // queue.put(task);
                    // 2) 带超时等待
                    // queue.offer(task, 1500, TimeUnit.MILLISECONDS);
                    // 3) 让调用者放弃任务执行
                    // log.debug("放弃{}", task);
                    // 4) 让调用者抛出异常
                    // throw new RuntimeException("任务执行失败 " + task);
                    // 5) 让调用者自己执行任务
                    task.run();
                });
        for (int i = 0; i < 4; i++) {
            int j = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
