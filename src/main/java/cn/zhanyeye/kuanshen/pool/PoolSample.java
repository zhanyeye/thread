package cn.zhanyeye.kuanshen.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 线程池
 * JDK5.0起提供了线程池相关API:ExecutorService 和 Executors
 *
 * ExecutorService:真正的线程池接口。常见子类ThreadPoolExecutor
 * void execute(Runnable command):执行任务/命令，没有返回值，一般用来执行Runnable
 * <T>Future<T> submit(Callable<T>task):执行任务，有返回值，一般又来执行Callable
 * void shutdown()：关闭连接池
 *
 * Executors:工具类、线程池的工厂类，用于创建并返回不同类型的线程池
 *
 * @author: zhanyeye
 * @create: 2020-08-12 16:59
 **/
public class PoolSample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        service.shutdown();
    }
}


class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
