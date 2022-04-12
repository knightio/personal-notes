package com.hanhan.n8.pool;


import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * @author hanhan
 */

@Slf4j(topic = "c.ThreadPool")
public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue;

    /**
     * 线程集合
     */
    private HashSet<Worker> workers = new HashSet<>();

    private int coreSize;

    private long timeout;
    private TimeUnit timeUnit;
    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit,int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.taskQueue = new BlockingQueue<>(queueCapacity);
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
    }

    public void execute(Runnable task){
        synchronized (workers){
            if(workers.size() < coreSize){
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            }else {
                taskQueue.tryPut(rejectPolicy, task);
            }
        }

    }

    class Worker extends Thread{

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while(task == null || ((task = taskQueue.poll(timeout,timeUnit)) != null)){
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
                synchronized (workers) {
                    log.debug("");
                    workers.remove(this);
                }
            }
        }
    }
}
