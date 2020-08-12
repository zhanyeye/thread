package cn.zhanyeye.kuanshen.callable;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @description: Callable接口创建线程, 可以定义返回值，可以抛出异常
 * @author: zhanyeye
 * @create: 2020-08-11 09:05
 **/
public class CallableSample implements Callable<Boolean> {

    /**
     * 网络图片地址
     */
    private String url;
    /**
     * 保存文件名
     */
    private String name;

    public CallableSample(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        Web3Downloader web3Downloader = new Web3Downloader();
        web3Downloader.downloader(url, name);
        System.out.println("下载文件名为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallableSample t1 = new CallableSample("https://cdn.nlark.com/yuque/0/2020/png/215718/1596691647103-521aa632-a800-4dd1-98ad-05c88a671098.png", "pic1.png");
        CallableSample t2 = new CallableSample("https://cdn.nlark.com/yuque/0/2020/png/215718/1596526302583-d46d0ab3-9e05-4097-8dbb-eb7ed9a45911.png", "pic2.png");
        CallableSample t3 = new CallableSample("https://cdn.nlark.com/yuque/0/2020/jpeg/84141/1596159634242-b899ae6f-76d7-4766-b676-67609834c7a4.jpeg", "pic3.png");


        // 创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> future1 = service.submit(t1);
        Future<Boolean> future2 = service.submit(t2);
        Future<Boolean> future3 = service.submit(t3);
        // 获取结果
        boolean res1 = future1.get();
        boolean res2 = future2.get();
        boolean res3 = future3.get();
        // 关闭服务
        service.shutdown();

    }
}


class Web3Downloader {
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}