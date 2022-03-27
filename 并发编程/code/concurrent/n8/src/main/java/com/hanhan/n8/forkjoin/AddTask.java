package src.main.java.com.hanhan.n8.forkjoin;

import java.util.concurrent.RecursiveTask;

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
        

        return null;
    }
}
