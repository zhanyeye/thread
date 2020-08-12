package cn.zhanyeye.kuanshen.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Arraylist 中使用 synchronized 的使用
 * @author: zhanyeye
 * @create: 2020-08-11 23:32
 **/
public class ListSample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized(list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
