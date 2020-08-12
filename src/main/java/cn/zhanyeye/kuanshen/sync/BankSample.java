package cn.zhanyeye.kuanshen.sync;

/**
 * @description: synchronized的使用
 * @author: zhanyeye
 * @create: 2020-08-11 22:55
 **/
public class BankSample {
    public static void main(String[] args) {
        Account account = new Account(1000, "结婚基金");
        Drawing you = new Drawing(account, 50, "you");
        Drawing girl = new Drawing(account, 100, "girl");
        you.start();
        girl.start();

    }
}


class Account {
    /**
     * 余额
     */
    int money;
    /**
     * 账户
     */
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {
    Account account;
    /**
     * 取出多少钱
     */
    int drawingMoney;
    /**
     * 用户手中有多少前
     */
    int nowMonery;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    /**
     * 注意： synchronized 默认锁的是this,
     */
    @Override
    public void run() {
        // 同步块的使用
        synchronized (account) {
            // 判断有没有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + " 钱不够，取不了");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money -= drawingMoney;
            nowMonery += drawingMoney;
            System.out.println(account.name + " 余额：" + account.money);
            // 这里 Thread.currentThread().getName() = this.getName()
            System.out.println(this.getName() + "手里的钱" + nowMonery);
        }
    }
}