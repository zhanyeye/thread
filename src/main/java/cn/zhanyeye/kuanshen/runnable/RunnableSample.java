package cn.zhanyeye.kuanshen.runnable;

/**
 * @description: Runnable接口创建线程
 * @author: zhanyeye
 * @create: 2020-08-10 17:39
 **/
public class RunnableSample implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("--- 子线程" + i);
        }
    }

    public static void main(String[] args) {

        // 创建 Runnable 接口的实现类对象
        RunnableSample runnableSample = new RunnableSample();
        // 创建线程对象，通过线程对象来开启线程，代理
        Thread thread = new Thread(runnableSample);
        thread.start();

        // new Thread(testThread3).start();

        for (int i = 0; i < 200; i++) {
            System.out.println("+++ 主线程" + i);
        }
    }
}
