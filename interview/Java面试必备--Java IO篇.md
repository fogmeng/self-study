### Java面试必备--Java IO篇

- [面试](https://ask.gupaoedu.com/topic/70)

这系列文章是本人从有道笔记直接拷贝过来的面试知识点，因此会存在排版不整齐的问题，也会存在知识点遗漏或者偏差，大家可以一起交流补充。。。

 1. 讲讲IO里面的常见类，字节流、字符流、接口、实现类、方法阻塞

流的本质是数据传输，根据数据传输特性将流抽象为各种类，方便更直观的进行数据操作

Java流操作有关的类或接口：

[![attachments-2018-07-qGcj6Tga5b44cdc36d79e.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-qGcj6Tga5b44cdc36d79e.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-qGcj6Tga5b44cdc36d79e.png)

Java流类图结构：

[![attachments-2018-07-HIlrCCKu5b44ce39c5cf9.jpeg](https://ask.gupaoedu.com/image/show/attachments-2018-07-HIlrCCKu5b44ce39c5cf9.jpeg)](https://ask.gupaoedu.com/image/show/attachments-2018-07-HIlrCCKu5b44ce39c5cf9.jpeg)

   2. 讲讲NIO（Java NIO原理 图文分析.note  ）

一.NIO中的几个基础概念

Channel（通道），Buffer（缓冲区），Selector（选择器）

二.Channel

Channel同传统IO中的Stream来类比，传统IO中，Stream是单向的，比如InputStream只能进行读取操作，OutputStream只能进行写操作，Channel是双向的，既可用来进行读操作，又可用来进行写操作

以下是常用的几种通道：

- FileChannel
- SocketChanel
- ServerSocketChannel
- DatagramChannel

　　通过使用FileChannel可以从文件读或者向文件写入数据；通过SocketChannel，以TCP来向网络连接的两端读写数据；通过ServerSocketChanel能够监听客户端发起的TCP连接，并为每个TCP连接创建一个新的SocketChannel来进行数据读写；通过DatagramChannel，以UDP协议来向网络连接的两端读写数据。

注意在调用channel的write方法之前必须调用buffer的flip方法，否则无法正确写入内容

三.Buffer

Buffer（缓冲区），实际上是一个容器，是一个连续数组，Channel提供从文件、网络读取数据的渠道，但是读取或写入的数据都必须经由Buffer（limit、posiion和capacity）

[![attachments-2018-07-vrxBhtLe5b44ce8e6e083.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-vrxBhtLe5b44ce8e6e083.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-vrxBhtLe5b44ce8e6e083.png)

　上面的图描述了从一个客户端向服务端发送数据，然后服务端接收数据的过程。客户端发送数据时，必须先将数据存入Buffer中，然后将Buffer中的内容写入通道。服务端这边接收数据必须通过Channel将数据读入到Buffer中，然后再从Buffer中取出数据来处理。

　　在NIO中，Buffer是一个顶层父类，它是一个抽象类，常用的Buffer的子类有：

- ByteBuffer
- IntBuffer
- CharBuffer
- LongBuffer
- DoubleBuffer
- FloatBuffer
- ShortBuffer

　　如果是对于文件读写，上面几种Buffer都可能会用到。但是对于网络读写来说，用的最多的是ByteBuffer

四.Selector

　Selector类是NIO的核心类，Selector能够检测多个注册的通道上是否有事件发生，如果有事件发生，便获取事件然后针对每个事件进行相应的响应处理。这样一来，只是用一个单线程就可以管理多个通道，也就是管理多个连接。这样使得只有在连接真正有读写事件发生时，才会调用函数来进行读写，就大大地减少了系统开销，并且不必为每个连接都创建一个线程，不用去维护多个线程，并且避免了多线程之间的上下文切换导致的开销

　　与Selector有关的一个关键类是SelectionKey，一个SelectionKey表示一个到达的事件，这2个类构成了服务端处理业务的关键逻辑

Selector的作用就是用来轮询每个注册的Channel，一旦发现Channel有注册的事件发生，便获取事件然后进行处理

[![attachments-2018-07-gT66HfUT5b44ced31454d.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-gT66HfUT5b44ced31454d.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-gT66HfUT5b44ced31454d.png)

用单线程处理一个Selector，然后通过Selector.select()方法来获取到达事件，在获取了到达事件之后，就可以逐个地对这些事件进行响应处理

   3. String 编码UTF-8 和GBK的区别？

GBK的中文编码是双字节来表示的，英文编码是用ASC||码表示的，既用单字节表示。但GBK编码表中也有英文字符的双字节表示形式，所以英文字母可以有2中GBK表示方式。为区分中文，将其最高位都定成1。英文单字节最高位都为0。当用GBK解码时，若高字节最高位为0，则用ASC||码表解码；若高字节最高位为1，则用GBK编码表解码

UTF－8编码则是用以解决国际上字符的一种多字节编。码，它对英文使用8位（即一个字节），中文使用24位（三个字节）来编码。对于英文字符较多的论坛则用UTF－8节省空间。

GBK包含全部中文字符，UTF-8则包含全世界所有国家需要用到的字符

GBK是在国家标准GB2312基础上扩容后兼容GB2312的标准（好像还不是国家标准）

UTF-8编码的文字可以在各国各种支持UTF8字符集的浏览器上显示。

   4. 什么时候使用字节流、什么时候使用字符流？

​     字节：通常由8比特组成。由于通常用来编辑计算机上文本的单个字符，所以字节是很多的计算机架构中的内存的最小可寻址单元

​     字符：信息单元；大概相当于1个字母或者符号等。比如数字，字母，标点符号等。英文字符一般由1个字节组成。汉字一般由两个字节组成

 所有字节流类都是InputStream或OutputStream的子类。最主要的两个是FileInputStream和FileOutputStream

​     字节流代表的是一种低级别的I/O,所以应该尽量避免使用。尤其是文本文件，包含的是字符，最好的方式是使用字符流。字节流只考虑用在最原始的I/O上。但是字节流是所有其他流的基础

 所有的字符流类都是Reader和Writer的子类

​     字符流经常会“封装”字节流。字符流使用字节流来操作物理I/O，然后字符流执行字节和字符间的转换。比如，FileReader使用FileInputStream、FileWriter使用FileOutputStream

使用InputStreamReader可以将字节流转换成字符流；使用OutputStreamWriter类可以将字符流转换成字节流