package com.hanhan.n8.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author hanhan12
 */
public class MySync extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        if(arg == 1){
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if(arg == 1){
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        return false;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

    protected Condition newCondition() {
        return new ConditionObject();
    }
}
