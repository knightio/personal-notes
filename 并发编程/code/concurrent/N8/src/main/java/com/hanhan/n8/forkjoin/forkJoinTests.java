package com.hanhan.n8.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

@Slf4j(topic = "c.forkJoinTests")
public class forkJoinTests {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        log.debug("{}",pool.invoke(new AddTask(1, 9)));
    }
}
