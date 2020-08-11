# thread


 #### 线程状态

![image-20200811150820173](https://raw.githubusercontent.com/zhanyeye/Figure-bed/img/img/image-20200811150820173.png)



![image-20200811151010397](https://raw.githubusercontent.com/zhanyeye/Figure-bed/img/img/image-20200811151010397.png)

| 方法                           | 说明                                       |
| ------------------------------ | ------------------------------------------ |
| setPriority(int newPriority)   | 更改线程的优先级                           |
| static void sleep(long millis) | 在当前正在执行的线程休眠指定毫秒           |
| void join()                    | 等待该进程终止                             |
| static void yield()            | 暂停当前正在执行的线程对象，并执行其他线程 |
| void interrupt()               | 中断线程                                   |
| boolean isAlive()              | 测试线程是否处于激活状态                   |

