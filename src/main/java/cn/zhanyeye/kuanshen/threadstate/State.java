package cn.zhanyeye.kuanshen.threadstate;

/**
 * @description: 观察测试线程的状态
 * @author: zhanyeye
 * @create: 2020-08-11 16:52
 **/
public class State {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("/////////");
            }
        });

        Thread.State state = thread.getState();
        System.out.println(state); // NEW


        thread.start();
        state = thread.getState();
        System.out.println(state); // RUNNABLE

        while (state != Thread.State.TERMINATED) {
            Thread.sleep(100);
            state = thread.getState();
            System.out.println(state);
        }

        // 报错，死亡后的线程不可再启动
        thread.start();

    }
}
