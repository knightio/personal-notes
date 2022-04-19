package com.hanhan.n8.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.CountDownLatchTests")
public class CountDownLatchTests {
    public static void main(String[] args) throws InterruptedException {
//        test();
        useThreadPoll();
    }

    private static void useThreadPoll() {
        /*
        19:06:47.227 [pool-1-thread-3] DEBUG c.CountDownLatchTests - begin...
        19:06:47.227 [pool-1-thread-1] DEBUG c.CountDownLatchTests - begin...
        19:06:47.227 [pool-1-thread-2] DEBUG c.CountDownLatchTests - begin...
        19:06:47.227 [pool-1-thread-4] DEBUG c.CountDownLatchTests - waiting...
        19:06:48.238 [pool-1-thread-1] DEBUG c.CountDownLatchTests - end...2
        19:06:48.746 [pool-1-thread-2] DEBUG c.CountDownLatchTests - end...1
        19:06:49.237 [pool-1-thread-3] DEBUG c.CountDownLatchTests - end...0
        19:06:49.252 [pool-1-thread-4] DEBUG c.CountDownLatchTests - wait end...
         */

        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(4);

        service.submit(() -> {
            log.debug("begin...");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        });

        service.submit(() -> {
            log.debug("begin...");
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        });

        service.submit(() -> {
            log.debug("begin...");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        });

        service.submit(()->{
            try {
                log.debug("waiting...");
                latch.await();
                sleep(10);
                log.debug("wait end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test() throws InterruptedException {
        /*
        19:01:45.232 [Thread-0] DEBUG c.CountDownLatchTests - begin...
        19:01:45.232 [Thread-2] DEBUG c.CountDownLatchTests - begin...
        19:01:45.232 [main] DEBUG c.CountDownLatchTests - waiting...
        19:01:45.232 [Thread-1] DEBUG c.CountDownLatchTests - begin...
        19:01:46.245 [Thread-0] DEBUG c.CountDownLatchTests - end...2
        19:01:46.738 [Thread-2] DEBUG c.CountDownLatchTests - end...1
        19:01:47.242 [Thread-1] DEBUG c.CountDownLatchTests - end...0
        19:01:47.257 [main] DEBUG c.CountDownLatchTests - wait end...
         */
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            log.debug("begin...");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        }).start();

        new Thread(() -> {
            log.debug("begin...");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        }).start();

        new Thread(() -> {
            log.debug("begin...");
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latch.countDown();
            log.debug("end...{}", latch.getCount());
        }).start();

        log.debug("waiting...");
        latch.await();
        log.debug("wait end...");
    }
}
