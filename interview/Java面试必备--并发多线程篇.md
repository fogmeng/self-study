### Java面试必备--并发多线程篇

- [面试](https://ask.gupaoedu.com/topic/70)

这系列文章是本人从有道笔记直接拷贝过来的面试知识点，因此会存在排版不整齐的问题，也会存在知识点遗漏或者偏差，大家可以一起交流补充。。。

多线程编程优势：1.提高系统吞吐量2.提高响应性3.充分利用多核CPU资源4.简化程序结构

多线程编程注意点：1. 线程安全2. 死锁(线程生命特征)3.上下文切换4.可靠性

\1. 线程同步方式

为何要使用同步？ 

​    java允许多线程并发控制，当多个线程同时操作一个可共享的资源变量时（如数据的增删改查），将会导致数据不准确，相互之间产生冲突，因此加入同步锁以避免在该线程没有完成操作之前，被其他线程的调用，从而保证了该变量的唯一性和准确性。

synchronized关键字分为实例锁  类锁（static修饰），锁的获取和释放分别是monitorenter和monitorexit指令

1.同步方法 

​    即有synchronized关键字修饰的方法。 

​    由于java的每个对象都有一个内置锁，当用此关键字修饰方法时，内置锁会保护整个方法。在调用该方法前，需要获得内置锁，否则就处于阻塞状态。

​    代码如： 

​    public synchronized void save(){}

   注： synchronized关键字也可以修饰静态方法，此时如果调用该静态方法，将会锁住整个类

2.同步代码块 

​    即有synchronized关键字修饰的语句块。 

​    被该关键字修饰的语句块会自动被加上内置锁，从而实现同步

​    代码如： 

​    synchronized(object){}

​    注：同步是一种高开销的操作，因此应该尽量减少同步的内容。通常没有必要同步整个方法，使用synchronized代码块同步关键代码即可。同步块是更好的选择，因为它不会锁住整个对象（当然你也可以让它锁住整个对象）。同步方法会锁住整个对象，哪怕这个类中有多个不相关联的同步块，这通常会导致他们停止执行并需要等待获得这个对象上的锁。 

​    代码实例： 

package com.xhj.thread;

​    /**

​     * 线程同步的运用 

​     */

​    public class SynchronizedThread {

​        class Bank {

​            private int account = 100;

​            public int getAccount() {

​                return account;

​            }

​            /**

​             * 用同步方法实现

​             * 

​             * @param money

​             */

​            public synchronized void save(int money) {

​                account += money;

​            }

​            /**

​             * 用同步代码块实现

​             * 

​             * @param money

​             */

​            public void save1(int money) {

​                synchronized (this) {

​                    account += money;

​                }

​            }

​        }

 

​        class NewThread implements Runnable {

​            private Bank bank;

​            public NewThread(Bank bank) {

​                this.bank = bank;

​            }

​            @Override

​            public void run() {

​                for (int i = 0; i < 10; i++) {

​                    // bank.save1(10);

​                    bank.save(10);

​                    System.out.println(i + "账户余额为：" + bank.getAccount());

​                }

​            }

 

​        }

 

​        /**

​         * 建立线程，调用内部类

​         */

​        public void useThread() {

​            Bank bank = new Bank();

​            NewThread new_thread = new NewThread(bank);

​            System.out.println("线程1");

​            Thread thread1 = new Thread(new_thread);

​            thread1.start();

​            System.out.println("线程2");

​            Thread thread2 = new Thread(new_thread);

​            thread2.start();

​        }

​        public static void main(String[] args) {

​            SynchronizedThread st = new SynchronizedThread();

​            st.useThread();

​        }

​    }

3.使用特殊域变量(volatile)实现线程同步

​    a.volatile关键字为域变量的访问提供了一种免锁机制； 

​    b.使用volatile修饰域相当于告诉[虚拟机](http://www.2cto.com/os/xuniji/)该域可能会被其他线程更新；

​    c.因此每次使用该域就要重新计算，而不是使用寄存器中的值； 

​    d.volatile不会提供任何原子操作，它也不能用来修饰final类型的变量； 

​    例如： 

​        在上面的例子当中，只需在account前面加上volatile修饰，即可实现线程同步。 

​    代码实例： 

​      //只给出要修改的代码，其余代码与上同

​        class Bank {

​            //需要同步的变量加上volatile

​            private volatile int account = 100;

​            public int getAccount() {

​                return account;

​            }

​            //这里不再需要synchronized 

​            public void save(int money) {

​                account += money;

​            }

​        ｝

​    注：多线程中的非同步问题主要出现在对域的读写上，如果让域自身避免这个问题，则就不需要修改操作该域的方法。用final域，有锁保护的域和volatile域可以避免非同步的问题。

4.使用重入锁实现线程同步

​    在[Java](http://www.2cto.com/kf/ware/Java/)SE5.0中新增了一个java.util.concurrent包来支持同步。ReentrantLock类是可重入、互斥、实现了Lock接口的锁， 它与使用synchronized方法和快具有相同的基本行为和语义，并且扩展了其能力

​    ReenreantLock类的常用方法有：

​        ReentrantLock() : 创建一个ReentrantLock实例 

​        lock() : 获得锁 

​        unlock() : 释放锁 

​    注：ReentrantLock()还有一个可以创建公平锁的构造方法，但由于能大幅度降低程序运行效率，不推荐使用      

​    例如：在上面例子的基础上，改写后的代码为:   

​    代码实例： 

//只给出要修改的代码，其余代码与上同

​        class Bank {        

​            private int account = 100;

​            //需要声明这个锁

​            private Lock lock = new ReentrantLock();

​            public int getAccount() {

​                return account;

​            }

​            //这里不再需要synchronized 

​            public void save(int money) {

​                lock.lock();

​                try{

​                    account += money;

​                }finally{

​                    lock.unlock();

​                }                

​            }

​        ｝      

​    注：关于Lock对象和synchronized关键字的选择： 

 a.最好两个都不用，使用java.util.concurrent包提供的机制，能够帮助用户处理所有与锁相关的代码 

 b.如果synchronized关键字能满足用户的需求，就用synchronized，因为它能简化代码 

 c.如果需要更高级的功能，就用ReentrantLock类，此时要注意及时释放锁，否则会出现死锁，通常在finally代码释放锁

5.使用局部变量实现线程同步 

​    如果使用ThreadLocal管理变量，则每一个使用该变量的线程都获得该变量的副本，副本之间相互独立，这样每一个线程都可以随意修改自己的变量副本，而不会对其他线程产生影响。

​    ThreadLocal 类的常用方法

​    ThreadLocal() : 创建一个线程本地变量 

​    get() : 返回此线程局部变量的当前线程副本中的值 

​    initialValue() : 返回此线程局部变量的当前线程的"初始值" 

​    set(T value) : 将此线程局部变量的当前线程副本中的值设置为value

​    例如：在上面例子基础上，修改后的代码为：   

​    代码实例： 

//只改Bank类，其余代码与上同

​        public class Bank{

​            //使用ThreadLocal类管理共享变量account

​            private static ThreadLocal<Integer> account = new ThreadLocal<Integer>(){

​                @Override

​                protected Integer initialValue(){

​                    return 100;

​                }

​            };

​            public void save(int money){

​                account.set(account.get()+money);

​            }

​            public int getAccount(){

​                return account.get();

​            }

​        }

​    注：ThreadLocal与同步机制 

​        a.ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题 

​        b.前者采用以"空间换时间"的方法，后者采用以"时间换空间"的方式 

   2.[各种同步控制工具](http://my.oschina.net/hosee/blog/607677#OSC_h2_1)及[并发容器](http://my.oschina.net/hosee/blog/607677#OSC_h2_14)

同步控制工具

ReentrantLock(可重入、可中断、可限时、公平锁)

Condition

Semaphore(共享锁)

ReadWriteLock(读写锁)

CountDownLatch(倒数计时器)

CyclicBarrier(重复计数器)

LockSupport(线程阻塞原语)

并发容器

ConcurrentHashMap

BlockingQueue

原子操作

AtomicInteger

AtomicLong

AtomicReference

LongAdder

3.多线程使用最佳实践

A.给你的线程起个有意义的名字。

这样可以方便找bug或追踪。OrderProcessor, QuoteProcessor or TradeProcessor 这种名字比 Thread-1. Thread-2 and Thread-3 好多了，给线程起一个和它要完成的任务相关的名字，所有的主要框架甚至JDK都遵循这个最佳实践。

B.避免锁定和缩小同步的范围

锁花费的代价高昂且上下文切换更耗费时间空间，试试最低限度的使用同步和锁，缩小临界区。因此相对于同步方法我更喜欢同步块，它给我拥有对锁的绝对控制权。

C.多用同步类少用wait 和 notify

首先，CountDownLatch, Semaphore, CyclicBarrier 和 Exchanger 这些同步类简化了编码操作，而用wait和notify很难实现对复杂控制流的控制。其次，这些类是由最好的企业编写和维护在后续的JDK中它们还会不断优化和完善，使用这些更高等级的同步工具你的程序可以不费吹灰之力获得优化。

D.多用并发集合少用同步集合

这是另外一个容易遵循且受益巨大的最佳实践，并发集合比同步集合的可扩展性更好，所以在并发编程时使用并发集合效果更好。如果下一次你需要用到map，你应该首先想到用ConcurrentHashMap。

Thread状态

[![attachments-2018-07-ANx2yrbo5b44d24640307.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-ANx2yrbo5b44d24640307.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-ANx2yrbo5b44d24640307.png)

concurrent包的实现示意图

[![attachments-2018-07-Z88CqCoZ5b44d26bc1a5e.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-Z88CqCoZ5b44d26bc1a5e.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-Z88CqCoZ5b44d26bc1a5e.png)

 