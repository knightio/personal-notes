package com.hanhan.n8.pool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hanhan12
 */
public class BlockingQueue<T> {

    private Deque<T> deque = new ArrayDeque<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWaitSet = lock.newCondition();

    private Condition emptyWaitSet = lock.newCondition();

    private int capacity;


    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public T take(){
        lock.lock();
        try{
            while(deque.isEmpty()){
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();

        }
    }

    public void put(T task){
        lock.lock();
        try{
            while(deque.size()==capacity){
                fullWaitSet.await();
            }
            deque.addLast(task);
            emptyWaitSet.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T poll(long timeout, TimeUnit timeUnit){
        lock.lock();
        try{
            Long nanos = timeUnit.toNanos(timeout);
            while(deque.isEmpty()){
                if(nanos<= 0){
                    return null;
                }
                try {
                    nanos = emptyWaitSet.awaitNanos(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = deque.removeFirst();
            fullWaitSet.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }

    public void offer(T task, long timeout, TimeUnit timeUnit){
        lock.lock();
        try {
            Long nanos = timeUnit.toNanos(timeout);
            while(deque.size() == capacity){
                if(nanos <= 0){
                    return;
                }
                try {
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            deque.addLast(task);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy,T task){
        lock.lock();
        try {
            if(deque.size() == capacity){
                rejectPolicy.reject(this,task);
            }else {
                deque.addLast(task);
                emptyWaitSet.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    public int size(){
        lock.lock();
        try {
            return deque.size();
        } finally {
            lock.unlock();
        }
    }



}
