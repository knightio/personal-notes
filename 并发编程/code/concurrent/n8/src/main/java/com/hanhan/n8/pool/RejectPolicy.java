package com.hanhan.n8.pool;

@FunctionalInterface
interface RejectPolicy<T> {
    /**
     *  拒绝策略
     * @param queue
     * @param task
     */
    void reject(BlockingQueue<T> queue, T task);
}
