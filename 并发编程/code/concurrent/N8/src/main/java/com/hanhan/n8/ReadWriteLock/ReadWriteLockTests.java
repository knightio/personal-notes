package com.hanhan.n8.ReadWriteLock;

import static java.lang.Thread.sleep;

public class ReadWriteLockTests {

    public static void main(String[] args) {
//        readReadTest();
//        readWriteTest();
    }

    private static void readWriteTest() {
        /*
        19:33:52.435 [t1] DEBUG c.DataContainer - 获取读锁。。。
        19:33:52.443 [t1] DEBUG c.DataContainer - 读取。
        19:33:53.442 [t2] DEBUG c.DataContainer - 获取写锁。。。
        19:33:53.456 [t1] DEBUG c.DataContainer - 释放读锁。。。
        19:33:53.456 [t2] DEBUG c.DataContainer - 写入。
        19:33:54.464 [t2] DEBUG c.DataContainer - 释放写锁。。。
         */
        DataContainer dataContainer = new DataContainer();

        new Thread(() -> {
            dataContainer.read();
        }, "t1").start();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            dataContainer.write();
        }, "t2").start();
    }

    private static void readReadTest() {
        /*
        19:37:43.459 [t1] DEBUG c.DataContainer - 获取读锁。。。
        19:37:43.466 [t1] DEBUG c.DataContainer - 读取。
        19:37:43.459 [t2] DEBUG c.DataContainer - 获取读锁。。。
        19:37:43.466 [t2] DEBUG c.DataContainer - 读取。
        19:37:44.472 [t2] DEBUG c.DataContainer - 释放读锁。。。
        19:37:44.472 [t1] DEBUG c.DataContainer - 释放读锁。。。
         */
        DataContainer dataContainer = new DataContainer();

        new Thread(()->{
            dataContainer.read();
        },"t1").start();

        new Thread(()->{
            dataContainer.read();
        },"t2").start();
    }
}
