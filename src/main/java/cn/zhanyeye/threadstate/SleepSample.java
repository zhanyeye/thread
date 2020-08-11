package cn.zhanyeye.threadstate;

/**
 * @description: sleep() 的使用
 * 模拟网络延时：放大问题发生的可能性，倒计时等
 * 注意 : 每个对象都有一个锁，sleep 不会释放锁
 * @author: zhanyeye
 * @create: 2020-08-11 15:47
 **/
public class SleepSample implements Runnable {

    @Override
    public void run() {

    }

    // 模拟倒计时
    public static void countDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num < 0) {
                break;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        countDown();
    }
}
