package com.hanhan.n8.ReadWriteLock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "c.ReadWriteLockTests")
public class ReadWriteLockTests {

    @Test
    void readReadTest(){
        DataContainer dataContainer = new DataContainer();

        new Thread(()->{
            dataContainer.read();
        },"t1").start();

        new Thread(()->{
            dataContainer.write();
        },"t2").start();
    }
}
