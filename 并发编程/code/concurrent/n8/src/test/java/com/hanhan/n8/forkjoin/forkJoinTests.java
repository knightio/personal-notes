package com.hanhan.n8.forkjoin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ForkJoinPool;

@SpringBootTest
public class forkJoinTests {

    @Test
    void forkJoinTest(){
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new AddTask(1, 9)));
    }
}
