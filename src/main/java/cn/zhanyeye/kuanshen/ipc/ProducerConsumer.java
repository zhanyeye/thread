package cn.zhanyeye.kuanshen.ipc;

/**
 * @description: 生产者消费者模型
 * wait() 表示线程等待，直到其他线程通知，与sleep不同会释放锁
 * wait(long timeout) 指定等待的毫秒数
 * notify() 唤醒一个处于等待状态的线程，如果有多个，则任选一个
 * notifyAll() 唤醒同一个对象上所有调用wait()方法的线程，优先级别高的线程优先调度
 *
 * 以上均是 Object 类的方法，都只能在同步方法或者同步代码块中使用
 *
 * @author: zhanyeye
 * @create: 2020-08-12 14:20
 **/
public class ProducerConsumer {
    public static void main(String[] args) {
        BufferZone bufferZone = new BufferZone();
        new Producer(bufferZone).start();
        new Consumer(bufferZone).start();
    }

}

/**
 * 生产者
 */
class Producer extends Thread {
    /**
     * 缓冲区
     */
    BufferZone bufferZone;

    public Producer(BufferZone bufferZone) {
        this.bufferZone = bufferZone;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了第" + i + "个产品");
            bufferZone.push(new Product(i));
        }
    }
}

/**
 * 消费者
 */
class Consumer extends Thread {
    /**
     * 缓冲区
     */
    BufferZone bufferZone;
    public Consumer(BufferZone bufferZone) {
        this.bufferZone = bufferZone;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int id = bufferZone.pop().id;
            System.out.println("消费了第" + id + "个产品");
        }
    }
}

/**
 * 商品
 */
class Product {
    int id;

    public Product(int id) {
        this.id = id;
    }
}

class BufferZone {
    /**
     * 容器大小
     */
    Product[] products = new Product[10];
    /**
     * 计数器
     */
    int count = 0;

    /**
     * 生产者放入产品
     */
    public synchronized void push(Product product) {
        // 如果容器满了，就等待消费者消费
        if (count == products.length) {
            // 生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果没有满，我们就需要丢入产品
        products[count] = product;
        count++;

        // 可以通知消费者消费
        this.notifyAll();

    }


    /**
     * 消费者消费产品
     */
    public synchronized Product pop() {
        // 判断能否消费
        if (count == 0) {
            // 等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果可以消费
        count--;
        Product product = products[count];

        // 吃完了，等待生产者生产
        this.notifyAll();

        return product;

    }

}