package com.hanhan.n8.stampedlock;

import static java.lang.Thread.sleep;

public class StampedLockTests {
    public static void main(String[] args) {
//        readReadTest();
        readWriteTest();
    }

    private static void readWriteTest(){
        /*
        22:48:49.993 [t1] DEBUG c.DataContainerStamped - optimistic read locking...256
        22:48:50.497 [t2] DEBUG c.DataContainerStamped - write lock 384
        22:48:51.009 [t1] DEBUG c.DataContainerStamped - updating to read lock... 256
        22:48:52.502 [t2] DEBUG c.DataContainerStamped - write unlock 384
        22:48:52.503 [t1] DEBUG c.DataContainerStamped - read lock 513
        22:48:53.517 [t1] DEBUG c.DataContainerStamped - read finish...513, data:100
        22:48:53.518 [t1] DEBUG c.DataContainerStamped - read unlock 513
         */
        DataContainerStamped dataContainer = new DataContainerStamped(1);
        new Thread(() -> {
            dataContainer.read(1);
        }, "t1").start();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            dataContainer.write(100);
        }, "t2").start();
    }

    private static void readReadTest() {
        /*
        22:47:03.836 [t1] DEBUG c.DataContainerStamped - optimistic read locking...256
        22:47:04.332 [t2] DEBUG c.DataContainerStamped - optimistic read locking...256
        22:47:04.333 [t2] DEBUG c.DataContainerStamped - read finish...256, data:1
        22:47:04.848 [t1] DEBUG c.DataContainerStamped - read finish...256, data:1
         */
        DataContainerStamped dataContainer = new DataContainerStamped(1);
        new Thread(() -> {
            dataContainer.read(1);
        }, "t1").start();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            dataContainer.read(0);
        }, "t2").start();
    }
}
