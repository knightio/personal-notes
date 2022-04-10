package com.hanhan.n8.aqs;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j(topic = "c.aqsTests")
public class aqsTests {
    public static void main(String[] args) {
//        aqsTest();
        unEntrantAqsTest();
    }

    private static void unEntrantAqsTest() {
        MyLock lock = new MyLock();
        new Thread(()->{
            lock.lock();
            log.debug("locking...");
            lock.lock();
            log.debug("locking...");
            try {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        },"t1").start();
    }

    private static void aqsTest() {
        MyLock lock = new MyLock();

        new Thread(()->{
            lock.lock();
            try {
                log.debug("locking...");
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            lock.lock();
            try {
                log.debug("locking...");
            }finally {
                log.debug("unlocking...");
                lock.unlock();
            }
        },"t2").start();
    }
}
