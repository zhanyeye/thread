package cn.zhanyeye.runnable;

/**
 * @description: 多线程操作临界区
 * @author: zhanyeye
 * @create: 2020-08-10 22:02
 **/
public class CurrencySample implements Runnable {

    // 剩余票数
    int ticketNums = 10;
    // 外部停止方式
    boolean flag = true;


    @Override
    public void run() {
        while (true) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 买票, 同步方法，锁的是this
     * @throws InterruptedException
     */
    private synchronized void buy() throws InterruptedException {
        // 判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " : 拿到了第" + ticketNums + "票");
        ticketNums--;
    }


    public static void main(String[] args) {
        CurrencySample ticket = new CurrencySample();
        new Thread(ticket, "小明").start();
        new Thread(ticket, "小红").start();
        new Thread(ticket, "黄牛").start();
    }
}
