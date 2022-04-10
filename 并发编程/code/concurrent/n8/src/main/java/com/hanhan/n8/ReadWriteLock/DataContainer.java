package com.hanhan.n8.ReadWriteLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/**
 * @author hanhan12
 */
@Slf4j(topic = "c.DataContainer")
public class DataContainer {
    Object data = new Object();
    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read(){
        log.debug("获取读锁。。。");
        r.lock();
        try {
            log.debug("读取。");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        } finally {
            log.debug("释放读锁。。。");
            r.unlock();
        }
    }

    public void write(){
        log.debug("获取写锁。。。");
        w.lock();
        try{
            log.debug("写入。");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            log.debug("释放写锁。。。");
            w.unlock();
        }
    }


}
