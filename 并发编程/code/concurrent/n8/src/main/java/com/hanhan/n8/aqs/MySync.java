package com.hanhan.n8.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author hanhan12
 */
public class MySync extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }

    protected Condition newCondition() {
        return new ConditionObject();
    }
}
