package cn.zhanyeye;

/**
 * @description: 多线程操作临界区
 * @author: zhanyeye
 * @create: 2020-08-10 22:02
 **/
public class CurrencySample implements Runnable {

    int ticketNums = 10;


    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                // 模拟延时
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : 拿到了第" + ticketNums + "票");
            ticketNums--;
        }
    }

    public static void main(String[] args) {
        CurrencySample ticket = new CurrencySample();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "小红").start();
        new Thread(ticket, "黄牛").start();
    }
}
