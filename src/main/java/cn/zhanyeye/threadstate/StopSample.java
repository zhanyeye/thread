package cn.zhanyeye.threadstate;

/**
 * @description: 测试停止线程
 * 1. 建议正常停止
 * 2. 建议使用标志位
 * 3. 建议不要使用stop或者destory等过时的方法
 *
 * 好像不好用啊??? 好像只适合一致循环重复的操作
 *
 * @author: zhanyeye
 * @create: 2020-08-11 15:26
 **/
public class StopSample implements Runnable {

    /**
     * 设置一个标志位
     */
    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run ... Thread" + i++);
        }
    }

    /**
     * 停止线程，转换标志位
     */
    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        StopSample stopSample = new StopSample();
        new Thread(stopSample).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main" + i);
            if (i == 900) {
                stopSample.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
