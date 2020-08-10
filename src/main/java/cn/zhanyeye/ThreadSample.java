package cn.zhanyeye;

/**
 * @description: 通过继承 Thread 来实现多线程, 避免单继承局限性
 * @author: zhanyeye
 * @create: 2020-08-10 15:47
 **/
public class ThreadSample extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("--- 运行子线程" + i);
        }
    }

    public static void main(String[] args) {

        // 创建一个线程对象
        ThreadSample threadSample = new ThreadSample();

        // 调用start()方法，开启线程
        threadSample.start();

        // 调用run()方法，并不会开启线程
        // testThread1.run();

        // 主线程
        for (int i = 0; i < 200; i++) {
            System.out.println("+++ 运行主线程：" + i);
        }
    }
}
