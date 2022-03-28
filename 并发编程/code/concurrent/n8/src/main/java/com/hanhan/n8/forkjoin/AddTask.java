package com.hanhan.n8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author hanhan12
 */
public class AddTask extends RecursiveTask<Integer> {

    int begin;
    int end;

    public AddTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(begin == end){
            return begin;
        }
        if(end - begin == 1){
            return end + begin;
        }
        int mid = (end + begin)/2;

        AddTask t1 = new AddTask(begin,mid);
        t1.fork();
        AddTask t2 = new AddTask(mid+1,end);
        t2.fork();

        int result = t1.join() + t2.join();

        return result;
    }
}
