# JAVA ThreadPoolExecutor线程池参数设置技巧

2016.03.26 15:46 12508浏览

**唠唠**
之前发了一篇文章还是在学校的时候，很久没有回到慕课学习了。
加入公司，成为一名程序员已经过了大半年，接手了大大小小的项目不少，忙碌中有很多磨砺和收获，但也变得有些浮躁，怀念在学校里能投入的埋头学习时光。
感觉是时候重新静下心来重拾学习之路，探究一些较深入的问题。

今天带来些干货，关于java线程池的参数设置。线程池是java多线程里开发里的重要内容，使用难度不大，但如何用好就要明白参数的含义和如何去设置。干货里的内容大多是参考别人的，加入了一些知识点的扩充和看法。希望能对多线程开发学习的童鞋有些启发和帮助。

一、ThreadPoolExecutor的重要参数

```
    1、corePoolSize：核心线程数
        * 核心线程会一直存活，及时没有任务需要执行
        * 当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
        * 设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭

    2、queueCapacity：任务队列容量（阻塞队列）
        * 当核心线程数达到最大时，新任务会放在队列中排队等待执行

    3、maxPoolSize：最大线程数
        * 当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
        * 当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常

    4、 keepAliveTime：线程空闲时间
        * 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        * 如果allowCoreThreadTimeout=true，则会直到线程数量=0

    5、allowCoreThreadTimeout：允许核心线程超时
    6、rejectedExecutionHandler：任务拒绝处理器
        * 两种情况会拒绝处理任务：
            - 当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务
            - 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务
        * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常
        * ThreadPoolExecutor类有几个内部实现类来处理这类情况：
            - AbortPolicy 丢弃任务，抛运行时异常
            - CallerRunsPolicy 执行任务
            - DiscardPolicy 忽视，什么都不会发生
            - DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
        * 实现RejectedExecutionHandler接口，可自定义处理器
```

二、ThreadPoolExecutor执行顺序

```
        线程池按以下行为执行任务
    1. 当线程数小于核心线程数时，创建线程。
    2. 当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
    3. 当线程数大于等于核心线程数，且任务队列已满
        - 若线程数小于最大线程数，创建线程
        - 若线程数等于最大线程数，抛出异常，拒绝任务
```

三、如何设置参数

```
    1、默认值
        * corePoolSize=1
        * queueCapacity=Integer.MAX_VALUE
        * maxPoolSize=Integer.MAX_VALUE
        * keepAliveTime=60s
        * allowCoreThreadTimeout=false
        * rejectedExecutionHandler=AbortPolicy()

    2、如何来设置
        * 需要根据几个值来决定
            - tasks ：每秒的任务数，假设为500~1000
            - taskcost：每个任务花费时间，假设为0.1s
            - responsetime：系统允许容忍的最大响应时间，假设为1s
        * 做几个计算
            - corePoolSize = 每秒需要多少个线程处理？ 
                * threadcount = tasks/(1/taskcost) =tasks*taskcout =  (500~1000)*0.1 = 50~100 个线程。corePoolSize设置应该大于50
                * 根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可
            - queueCapacity = (coreSizePool/taskcost)*responsetime
                * 计算可得 queueCapacity = 80/0.1*1 = 80。意思是队列里的线程可以等待1s，超过了的需要新开线程来执行
                * 切记不能设置为Integer.MAX_VALUE，这样队列会很大，线程数只会保持在corePoolSize大小，当任务陡增时，不能新开线程来执行，响应时间会随之陡增。
            - maxPoolSize = (max(tasks)- queueCapacity)/(1/taskcost)
                * 计算可得 maxPoolSize = (1000-80)/10 = 92
                * （最大任务数-队列容量）/每个线程每秒处理能力 = 最大线程数
            - rejectedExecutionHandler：根据具体情况来决定，任务不重要可丢弃，任务重要则要利用一些缓冲机制来处理
            - keepAliveTime和allowCoreThreadTimeout采用默认通常能满足

    3、 以上都是理想值，实际情况下要根据机器性能来决定。如果在未达到最大线程数的情况机器cpu load已经满了，则需要通过升级硬件（呵呵）和优化代码，降低taskcost来处理。
```

[JAVA](https://www.imooc.com/article/tag/3)