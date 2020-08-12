package cn.zhanyeye.kuanshen.threadstate;

/**
 * @description: join 方法
 * join 合并线程，待此线程执行完后，再执行其他线程，其他线程阻塞
 * @author: zhanyeye
 * @create: 2020-08-11 16:42
 **/
public class JoinSample implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程vip来了" + i);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        JoinSample joinSample = new JoinSample();
        Thread thread = new Thread(joinSample);
        thread.start();

        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                // 强行插队，直至执行完被插队的才能运行
                thread.join();
            }
            System.out.println("main" + i);
        }


    }
}
