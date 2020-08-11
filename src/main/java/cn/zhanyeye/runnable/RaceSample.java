package cn.zhanyeye.runnable;

/**
 * @description: 龟兔赛跑的例子
 * @author: zhanyeye
 * @create: 2020-08-10 23:02
 **/
public class RaceSample implements Runnable {
    private static String winner;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {

            // 兔子每10步会休息一次
            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 判断是否有人到达终点
            if (gameOver(i)) {
                break;
            }

            System.out.println(Thread.currentThread().getName() + " --> 跑了 " + i + "步");
        }
    }

    // 判断比赛是否结束
    private boolean gameOver(int step) {
        if (winner != null) {
            return true;
        } else if (step >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner is " + winner);
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        RaceSample race = new RaceSample();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }

}
