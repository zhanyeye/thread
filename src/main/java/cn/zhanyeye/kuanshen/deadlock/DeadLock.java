package cn.zhanyeye.kuanshen.deadlock;

/**
 * @description: 死锁的产生
 * 死锁产生的条件
 * 1.互斥条件：一个资源每次只能被一个进程使用。
 * 2.请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * 3.不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺。
 * 4.循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系。
 *
 * 破坏其中任意一个条件就可以避免死锁发生
 *
 * @author: zhanyeye
 * @create: 2020-08-12 09:55
 **/
public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "小明");
        Makeup g2 = new Makeup(1,"小红");
        g1.start();
        g2.start();
    }
}

/**
 * 口红
 */
class Lipstick {
}


/**
 * 镜子
 */
class Mirror {
}

class Makeup extends Thread {

    /**
     * 需要的资源只有一份，用static来保证只有一份
     */
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    /**
     * 选择
     */
    int choice;
    /**
     * 使用化妆品的人
     */
    String girlName;

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        // 化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void makeup() throws InterruptedException {
        if(choice == 0) {
            synchronized (lipstick) {
                // 获得口红的锁
                System.out.println(this.girlName + "获得口红的锁,即将去找镜子");
                Thread.sleep(1000);

                // 1秒后想获得镜子, (想要获取对方资源的同时，不释放自己持有的资源)
//                synchronized (mirror) {
//                    System.out.println(this.girlName + "获得镜子的锁");
//                }
            }

            synchronized (mirror) {
                System.out.println(this.girlName + "获得镜子的锁");
            }

        } else {
            synchronized (mirror) {
                // 获得镜子的锁
                System.out.println(this.girlName + "获得镜子的锁,即将去找口红");

                Thread.sleep(2000);

                // 2秒后想获得口红, (想要获取对方资源的同时，不释放自己持有的资源)
//                synchronized (lipstick) {
//                    System.out.println(this.girlName + "获得口红的锁");
//                }
            }

            synchronized (lipstick) {
                System.out.println(this.girlName + "获得口红的锁");
            }
        }

    }
}

