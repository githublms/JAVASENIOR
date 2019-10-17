package com.example.exer;

import java.util.concurrent.RecursiveTask;

/**
 * @author lms
 * @create 2019-10-17 9:10
 */
public class ForkjoinCalculate extends RecursiveTask<Long> {

    private long start ;
    private long end ;

    public ForkjoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    private static final long THRESHOLD = 10000L;

    @Override
    protected Long compute() {
        long length = end -start;
        long sum = 0;
        if (length < THRESHOLD){
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }else{
            long middle = (end + start) / 2 ;

            ForkjoinCalculate left = new ForkjoinCalculate(start,middle);
            left.fork();

            ForkjoinCalculate right = new ForkjoinCalculate(middle + 1 ,end);
            right.fork();

            return  left.join() + right.join();
        }
    }
}
