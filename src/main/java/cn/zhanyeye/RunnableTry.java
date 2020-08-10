package cn.zhanyeye;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @description:
 * @author: zhanyeye
 * @create: 2020-08-10 18:17
 **/
public class RunnableTry implements Runnable {
    /**
     * 网络图片地址
     */
    private String url;
    /**
     * 保存文件名
     */
    private String name;

    public RunnableTry(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        Web2Downloader web2Downloader = new Web2Downloader();
        web2Downloader.downloader(url, name);
        System.out.println("下载文件名为：" + name);
    }

    public static void main(String[] args) {
        RunnableTry t1 = new RunnableTry("https://cdn.nlark.com/yuque/0/2020/png/215718/1596691647103-521aa632-a800-4dd1-98ad-05c88a671098.png", "pic1.png");
        RunnableTry t2 = new RunnableTry("https://cdn.nlark.com/yuque/0/2020/png/215718/1596526302583-d46d0ab3-9e05-4097-8dbb-eb7ed9a45911.png", "pic2.png");
        RunnableTry t3 = new RunnableTry("https://cdn.nlark.com/yuque/0/2020/jpeg/84141/1596159634242-b899ae6f-76d7-4766-b676-67609834c7a4.jpeg", "pic3.png");

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

        // 下载的循序不固定

    }
}

class Web2Downloader {
    public void downloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，downloader方法出现问题");
        }
    }
}