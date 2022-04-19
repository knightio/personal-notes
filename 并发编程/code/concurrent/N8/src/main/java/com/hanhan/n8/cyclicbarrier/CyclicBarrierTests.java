package com.hanhan.n8.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.CyclicBarrierTests")
public class CyclicBarrierTests {

    public static void main(String[] args) {
//        test();
//        poolTest(2,2);
//        poolTest(3,2);//error

    }


    private static void poolTest(int nThreads,int parties) {
        /* nThreads:2,parties:2
        19:59:43.885 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task1 begin...
        19:59:43.885 [pool-1-thread-2] DEBUG c.CyclicBarrierTests - task2 begin...
        19:59:45.893 [pool-1-thread-2] DEBUG c.CyclicBarrierTests - task1,task2 finish...
        19:59:45.893 [pool-1-thread-2] DEBUG c.CyclicBarrierTests - task2 begin...
        19:59:45.893 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task1 begin...
        19:59:47.897 [pool-1-thread-2] DEBUG c.CyclicBarrierTests - task1,task2 finish...
        19:59:47.897 [pool-1-thread-2] DEBUG c.CyclicBarrierTests - task1 begin...
        19:59:47.897 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task2 begin...
        19:59:49.911 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task1,task2 finish...
         */
        /* nThreads:3,parties:2
        20:00:54.100 [pool-1-thread-2] DEBUG c.CyclicBarrierTests - task2 begin...
        20:00:54.100 [pool-1-thread-3] DEBUG c.CyclicBarrierTests - task1 begin...
        20:00:54.100 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task1 begin...
        20:00:55.108 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task1,task2 finish...
        20:00:55.108 [pool-1-thread-1] DEBUG c.CyclicBarrierTests - task2 begin...
        20:00:55.108 [pool-1-thread-3] DEBUG c.CyclicBarrierTests - task1 begin...
        20:00:56.123 [pool-1-thread-3] DEBUG c.CyclicBarrierTests - task1,task2 finish...
        20:00:56.123 [pool-1-thread-3] DEBUG c.CyclicBarrierTests - task2 begin...
        20:00:58.129 [pool-1-thread-3] DEBUG c.CyclicBarrierTests - task1,task2 finish...
         */
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties,()->{
            log.debug("task1,task2 finish...");
        });

        for(int i = 0 ;i< 3;i++){
            executorService.submit(()->{
                try {
                    log.debug("task1 begin...");
                    sleep(1000);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });

            executorService.submit(()->{
                try {
                    log.debug("task2 begin...");
                    sleep(2000);
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void test() {
        /*
        19:42:52.737 [T1] DEBUG c.CyclicBarrierTests - 线程1开始..
        19:42:52.737 [T2] DEBUG c.CyclicBarrierTests - 线程2开始..
        19:42:54.745 [T2] DEBUG c.CyclicBarrierTests - 线程2继续向下运行...
        19:42:54.745 [T1] DEBUG c.CyclicBarrierTests - 线程1继续向下运行...
         */
        CyclicBarrier cb = new CyclicBarrier(2);

        new Thread(()->{
            log.debug("线程1开始..");
            try {
                cb.await(); // 当个数不足时，等待
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            log.debug("线程1继续向下运行...");
        },"T1").start();

        new Thread(()->{
            log.debug("线程2开始..");
            try { sleep(2000); } catch (InterruptedException e) { }
            try {
                cb.await(); // 2 秒后，线程个数够2，继续运行
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            log.debug("线程2继续向下运行...");
        },"T2").start();
    }

}
