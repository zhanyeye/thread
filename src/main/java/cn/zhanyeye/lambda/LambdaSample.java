package cn.zhanyeye.lambda;

/**
 * @description: lambda 表达式的使用
 * 用于避免匿名内部内定义过多，其实质属于函数式编程
 * 理解 Function Interface (函数式接口) 是学习 java8 lambda 表达式的关键所在
 * 函数式接口的定义
 * 1. 任何接口如果只包含唯一一个抽象方法，那么它就是函数式接口。 例如 Runnable 接口
 * 2. 对于函数式接口，我们可以通过lambda表达式来创建该接口的对象。
 *
 *
 * @author: zhanyeye
 * @create: 2020-08-11 12:52
 **/
public class LambdaSample {

    public static void main(String[] args) {
        // 原来的操作：
        Flyable flyable = new Bird();
        flyable.fly();

        // 使用静态内部类
        flyable = new Plane();
        flyable.fly();

        // 局部内部类
        class Rocket implements Flyable {
            @Override
            public void fly() {
                System.out.println("rocket can fly");
            }
        }
        flyable = new Rocket();
        flyable.fly();

        // 匿名内部类, 没有类的名称，必须借助接口或者父类
        flyable = new Flyable() {
            @Override
            public void fly() {
                System.out.println("anonymous inner class also can fly");
            }
        };
        flyable.fly();

        // 用lambdda简化
        flyable = () -> System.out.println("lambda also can fly");
        flyable.fly();

    }

    // 静态内部类，只是用一次
    static class Plane implements Flyable {
        @Override
        public void fly() {
            System.out.println("plane can fly");
        }
    }

}


/**
 * 定义一个函数式接口（只有一个方法）
 */
interface Flyable {
    void fly();
}

/**
 * 实现类
 */
class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("bird can fly");
    }
}