package cn.zhanyeye.kuanshen.daemon;

/**
 * @description: 守护线程
 * 线程分为用户线程
 * 虚拟机必须确保用户线程执行完毕， 而不必等待守护线程执行完毕
 * 如后台记录操作日志，监控内存，垃圾回收等
 *
 * @author: zhanyeye
 * @create: 2020-08-11 17:55
 **/
public class DaemonSample {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        // 默认false表示用户进程，正常的线程都是用户线程 。。。
        thread.setDaemon(true);
        // 神灵守护线程启动， 注意虚拟机不必等待守护进程执行完毕
        thread.start();
        // 你，用户线程启动
        new Thread(new You()).start();
    }
}


class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("神灵保佑着你");
        }
    }
}


class You implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生开心的活着");
        }
        System.out.println("======= goodbye world! =======");
    }
}