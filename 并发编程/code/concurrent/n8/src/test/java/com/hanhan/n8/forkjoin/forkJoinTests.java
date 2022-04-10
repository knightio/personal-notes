package com.hanhan.n8.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

public class forkJoinTests {

    @Test
    void forkJoinTest(){
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new AddTask(1, 9)));
    }
}
