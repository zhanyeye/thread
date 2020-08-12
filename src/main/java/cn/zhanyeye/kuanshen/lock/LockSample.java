package cn.zhanyeye.kuanshen.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 测试Lock锁 ReentrantLock
 * @author: zhanyeye
 * @create: 2020-08-12 11:43
 **/
public class LockSample {
    public static void main(String[] args) {
        TestLock t = new TestLock();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}


class TestLock implements Runnable {

    int tickerNums = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true) {
            // 加锁
            lock.lock();
            try {
                // 要保证线程安全的代码
                if (tickerNums > 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickerNums--);
                } else {
                    break;
                }
            } finally {
                // 解锁
                lock.unlock();
            }

        }
    }
}
