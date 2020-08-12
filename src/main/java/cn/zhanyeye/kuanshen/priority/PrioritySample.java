package cn.zhanyeye.kuanshen.priority;

/**
 * @description: 设置进程优先级
 * 优先级只代表被调用的概率，实际循序还是看cpu心情
 * Thread.MIN_PRIORITY = 1
 * Thread.MAX_PRIORITY = 10
 * Thread.NORMAL_PRIORITY = 5
 *
 * @author: zhanyeye
 * @create: 2020-08-11 17:17
 **/
public class PrioritySample {
    public static void main(String[] args) {

        // 主线程默认优先级
        System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());

        MyPriority myPriority = new MyPriority();

        Thread t1 = new Thread(myPriority);
        Thread t2 = new Thread(myPriority);
        Thread t3 = new Thread(myPriority);
        Thread t4 = new Thread(myPriority);
        Thread t5 = new Thread(myPriority);
        Thread t6 = new Thread(myPriority);

        t1.start();

        t2.setPriority(1);
        t2.start();

        t3.setPriority(4);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

        // 报错
//        t5.setPriority(-1);
//        t5.start();
//
        // 报错
//        t6.setPriority(11);
//        t6.start();



    }
}


class MyPriority implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());
    }
}