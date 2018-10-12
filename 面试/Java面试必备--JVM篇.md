### Java面试必备--JVM篇

- [面试](https://ask.gupaoedu.com/topic/70)

这系列文章是本人从有道笔记直接拷贝过来的面试知识点，因此会存在排版不整齐的问题，也会存在知识点遗漏或者偏差，大家可以一起交流补充。。。

   1. Java的内存模型以及GC算法

一、Java内存模型

Java 虚拟机具有一个堆，堆是运行时数据区域，所有类实例和数组的内存均从此处分配

JVM主要管理两种类型内存：堆和非堆，堆内存（Heap Memory）是在 Java 虚拟机启动时创建，非堆内存(Non-heap Memory)是在JVM堆之外的内存

简单来说，堆是Java代码可及的内存，留给开发人员使用的；非堆是JVM留给自己用的，包含方法区、JVM内部处理或优化所需的内存（如 [JIT](http://baike.baidu.com/view/112978.htm#10)Compiler，Just-in-time Compiler，即时编译后的代码缓存）、每个类结构（如运行时常数池、字段和方法数据）以及方法和构造方法的代码

JVM 内存包含如下几个部分：

- 堆内存（Heap Memory）： 存放Java对象
- 非堆内存（Non-Heap Memory）： 存放类加载信息和其它meta-data
- 其它（Other）： 存放JVM 自身代码等

Method Area 和 Heap 是线程共享的

JVM初始运行的时候都会分配好 Method Area（方法区） 和Heap（堆）,而JVM 每遇到一个线程，就为其分配一个 Program Counter Register（程序计数器） , VM Stack（虚拟机栈）和Native Method Stack （本地方法栈）， 当线程终止时，三者（虚拟机栈，本地方法栈和程序计数器）所占用的内存空间也会被释放掉。

[![attachments-2018-07-FF9tdBDM5b44d09ed8ecc.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-FF9tdBDM5b44d09ed8ecc.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-FF9tdBDM5b44d09ed8ecc.png)

二、Java内存分配

Java的内存管理实际上就是变量和对象的管理，其中包括对象的分配和释放。

[![attachments-2018-07-tdGvVBGM5b44d0b9d5c75.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-tdGvVBGM5b44d0b9d5c75.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-tdGvVBGM5b44d0b9d5c75.png)

JVM内存申请过程如下：

1. JVM 会试图为相关Java对象在Eden中初始化一块内存区域
2. 当Eden空间足够时，内存申请结束；否则到下一步
3. JVM 试图释放在Eden中所有不活跃的对象（这属于1或更高级的垃圾回收）,释放后若Eden空间仍然不足以放入新对象，则试图将部分Eden中活跃对象放入Survivor区
4. Survivor区被用来作为Eden及OLD的中间交换区域，当OLD区空间足够时，Survivor区的对象会被移到Old区，否则会被保留在Survivor区
5. 当OLD区空间不够时，JVM 会在OLD区进行完全的垃圾收集（0级）
6. 完全垃圾收集后，若Survivor及OLD区仍然无法存放从Eden复制过来的部分对象，导致JVM无法在Eden区为新对象创建内存区域，则出现”out of memory”错误

四、GC分代划分

为了进行高效的垃圾回收，虚拟机把堆内存划分成新生代（Young Generation）、老年代（Old Generation）和永久代（Permanent Generation）3个区域。

[![attachments-2018-07-MF3GCPGL5b44d0e87de16.jpeg](https://ask.gupaoedu.com/image/show/attachments-2018-07-MF3GCPGL5b44d0e87de16.jpeg)](https://ask.gupaoedu.com/image/show/attachments-2018-07-MF3GCPGL5b44d0e87de16.jpeg)

1） 在Young Generation中，有一个叫Eden Space的空间，主要是用来存放新生的对象，还有两个Survivor Spaces（from、to），它们的大小总是一样，它们用来存放每次垃圾回收后存活下来的对象

2） 在Old Generation中，主要存放应用程序中生命周期长的内存对象

3） 在Young Generation块中，垃圾回收一般用Copying的算法，速度快。每次GC的时候，存活下来的对象首先由Eden拷贝到某个SurvivorSpace，当Survivor Space空间满了后，剩下的live对象就被直接拷贝到OldGeneration中去。因此，每次GC后，Eden内存块会被清空。

4） 在Old Generation块中，垃圾回收一般用mark-compact的算法，速度慢些，但减少内存要求

5） 垃圾回收分多级，0级为全部(Full)的垃圾回收，会回收OLD段中的垃圾；1级或以上为部分垃圾回收，只会回收Young中的垃圾，内存溢出通常发生于OLD段或Perm段垃圾回收后，仍然无内存空间容纳新的Java对象的情况

   2. jvm性能调优都做了什么

[![attachments-2018-07-anXPmFXV5b44d106365f9.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-anXPmFXV5b44d106365f9.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-anXPmFXV5b44d106365f9.png)

- JVM启动参数：调整各代的内存比例和垃圾回收算法，提高吞吐量

​       目标：

- GC的时间足够的小
- GC的次数足够的少
- 发生Full GC的周期足够的长

​       实践：

（1）针对JVM堆的设置，一般可以通过-Xms -Xmx限定其最小、最大值，为了防止垃圾收集器在最小、最大之间收缩堆而产生额外的时间，我们通常把最大、最小设置为相同的值

   （2）年轻代和年老代将根据默认的比例（1：2）分配堆内存，可以通过调整二者之间的比率NewRadio来调整二者之间的大小，也可以针对回收代，比如年轻代，通过 -XX:newSize -XX:MaxNewSize来设置其绝对大小。同样，为了防止年轻代的堆收缩，我们通常会把-XX:newSize -XX:MaxNewSize设置为同样大小

   （3）年轻代和年老代设置多大才算合理？这个我问题毫无疑问是没有答案的，否则也就不会有调优。我们观察一下二者大小变化有哪些影响

- 更大的年轻代必然导致更小的年老代，大的年轻代会延长普通GC的周期，但会增加每次GC的时间；小的年老代会导致更频繁的Full GC
- 更小的年轻代必然导致更大年老代，小的年轻代会导致普通GC很频繁，但每次的GC时间会更短；大的年老代会减少Full GC的频率
- 如何选择应该依赖应用程序对象生命周期的分布情况：如果应用存在大量的临时对象，应该选择更大的年轻代；如果存在相对较多的持久对象，年老代应该适当增大。但很多应用都没有这样明显的特性，在抉择时应该根据以下两点：（A）本着Full GC尽量少的原则，让年老代尽量缓存常用对象，JVM的默认比例1：2也是这个道理 （B）通过观察应用一段时间，看其他在峰值时年老代会占多少内存，在不影响Full GC的前提下，根据实际情况加大年轻代，比如可以把比例控制在1：1。但应该给年老代至少预留1/3的增长空间

  （4）在配置较好的机器上（比如多核、大内存），可以为年老代选择并行收集算法： -XX:+UseParallelOldGC ，默认为Serial收集

  （5）线程堆栈的设置：每个线程默认会开启1M的堆栈，用于存放栈帧、调用参数、局部变量等，对大多数应用而言这个默认值太了，一般256K就足用。理论上，在内存不变的情况下，减少每个线程的堆栈，可以产生更多的线程，但这实际上还受限于操作系统。

- 程序算法：改进程序逻辑算法提高性能

调优原则：

1、多数的Java应用不需要在服务器上进行GC优化；

2、多数导致GC问题的Java应用，都不是因为我们参数设置错误，而是代码问题；

3、在应用上线之前，先考虑将机器的JVM参数设置到最优（最适合）；

4、减少创建对象的数量；

5、减少使用全局变量和大对象；

6、GC优化是到最后不得已才采用的手段；

7、在实际使用中，分析GC情况优化代码比优化GC参数要多得多；

调优目的：

1、将转移到老年代的对象数量降低到最小；

2、减少full GC的执行时间；

调优手段：

1、减少使用全局变量和大对象；

2、调整新生代的大小到最合适；

3、设置老年代的大小为最合适；

4、选择合适的GC收集器；

调优步骤：

1，监控GC的状态

2，分析结果，判断是否需要优化

如果各项参数设置合理，系统没有超时日志出现，GC频率不高，GC耗时不高，那么没有必要进行GC优化；如果GC时间超过1-3秒，或者频繁GC，则必须优化；

注：如果满足下面的指标，则一般不需要进行GC：

   Minor GC执行时间不到50ms；

   Minor GC执行不频繁，约10秒一次；

   Full GC执行时间不到1s；

   Full GC执行频率不算频繁，不低于10分钟1次；

3，调整GC类型和内存分配

4，不断的分析和调整

5，全面应用参数

   3. 介绍JVM中7个区域，然后把每个区域可能造成内存的溢出的情况说明

顺口溜：器池堆，栈栈区区；弃池堆，站站曲曲（一离开卫生间，肚子疼的站不起身）

[![attachments-2018-07-o5OEhs7l5b44d12675527.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-o5OEhs7l5b44d12675527.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-o5OEhs7l5b44d12675527.png)

虚拟机规范中的7个内存区域分别是三个线程私有的和四个线程共享的内存区

[![attachments-2018-07-Kpy8jG7G5b44d1409c7f1.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-Kpy8jG7G5b44d1409c7f1.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-Kpy8jG7G5b44d1409c7f1.png)

线程私有内存区域：与线程具有相同生命周期，分别是：指令计数器、线程栈和本地线程栈

四个共享区：是所有线程共享的，在JVM启动时就会分配，分别是：方法区、 常量池、直接内存区和堆（即我们通常所说的JVM的内存分为堆和栈中的堆，后者就是前面的线程栈）

   4. jvm 如何分配直接内存，常量池解析

直接内存：并不是虚拟机运行时数据区的一部分，但直接内存被频繁使用也可能导致OutOfMemoryError异常出现。在NIO中，引入了一种基于通道和缓冲区的I/O方式，它可以使用native函数直接分配堆外内存，然后通过一个存储在java堆中的DirectByteBuffer对象作为这块内存的引用进行操作

-XX:MaxDirectMemorySize设置最大值，默认与java堆最大值一样

常量池：JVM为每个已加载的类型维护一个常量池，常量池就是这个类型用到的常量的一个有序集合。包括直接常量(基本类型，String)和对其他类型、方法、字段的符号引用。池中的数据和数组一样通过索引访问。由于常量池包含了一个类型所有的对其他类型、方法、字段的符号引用，所以常量池在Java的动态链接中起了核心作用。常量池存在于堆中

   5. 数组多大放在JVM老年代

虚拟机提供了一个-XX:PretenureSizeThreshold参数，令大于这个设置值的对象直接在老年代中分配。这样做的目的是避免在Eden区及两个Survivor区之间发生大量的内存拷贝

新生代经历minor gc ，年龄到达阈值。过大对象。minor gc后仍存在大量对象，分配survivor不能存储的对象到老年代。某种相同年龄对象相加综合大于survivor一半，大于或等于这个年龄值的对象直接到老年代

对应的年轻代回收机制是：标记-复制。（图片来自网络）

[![attachments-2018-07-833m6fZ15b44d170ec429.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-833m6fZ15b44d170ec429.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-833m6fZ15b44d170ec429.png)

对应的老年代回收机制是：标记-清除(或标记-整理)。（图片来自网络）

[![attachments-2018-07-ghvc6WLs5b44d18ff39b9.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-ghvc6WLs5b44d18ff39b9.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-ghvc6WLs5b44d18ff39b9.png)

\1. 标记-清除 

\2. 标记-复制 

\3. 标记-整理 

\4. 分代回收