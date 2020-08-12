package cn.zhanyeye.kuanshen.juc;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: zhanyeye
 * @create: 2020-08-12 09:35
 **/
public class JucSample {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
            }).start();
        }

        Thread.sleep(3000);

        System.out.println(list.size());
    }
}
