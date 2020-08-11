package cn.zhanyeye.staticproxy;

/**
 * @description: 静态代理实现
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象代理真实角色
 * 优点：
 * 代理对象可以做很多真实对象做不了的事情
 * 真实对象专注做自己的事情
 * @author: zhanyeye
 * @create: 2020-08-11 11:39
 **/
public class StaticProxy {

    public static void main(String[] args) {
        WeddingCompancy weddingCompancy = new WeddingCompancy(new You());
        weddingCompancy.happyMarry();


        // 静态代理在java中的应用
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }).start();

        // lambda 表达式简化代码
        new Thread(() -> System.out.println("hello")).start();

    }

}

interface Marry {
    void happyMarry();
}

/**
 * 真实角色，你去结婚
 */
class You implements Marry {
    @Override
    public void happyMarry() {
        System.out.println("要结婚了，超开心");
    }
}

/**
 * 代理角色，帮助你结婚（婚庆公司）
 */
class WeddingCompancy implements Marry {

    /**
     * 代理谁? 真实目标角色
     */
    private Marry target;

    WeddingCompancy(Marry target) {
        this.target = target;
    }

    @Override
    public void happyMarry() {
        before();

        // 真实对象结婚
        this.target.happyMarry();

        after();
    }

    private void before() {
        System.out.println("结婚之前，布置现场");
    }

    private void after() {
        System.out.println("结婚之后，收尾款");
    }
}