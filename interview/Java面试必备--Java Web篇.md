### Java面试必备--Java Web篇

- [面试](https://ask.gupaoedu.com/topic/70)

这系列文章是本人从有道笔记直接拷贝过来的面试知识点，因此会存在排版不整齐的问题，也会存在知识点遗漏或者偏差，大家可以一起交流补充。。。

\1. session和cookie的区别和联系，session的生命周期，多个服务部署时session管理

一、定义

cookie机制采用的是在客户端保持状态的方案，而session机制采用的是在服务器端保持状态的方案

cookie分为二种

1，以文件方式存在硬盘空间上的长期性的cookie

2，停留在浏览器所占内存中的临时性的cookie

session是一种服务器端的信息管理机制，它把这些文件信息以文件的形势存放在服务器的硬盘空间上

session产生的session_id放在cookie里面，如果用户把cookie禁止掉，session_id可跟在url后面，或者以表单的形式提交到服务器端，从而使服务器端了解客户端的状态（URL地址重写）

cookie 和session 的区别：

1、cookie数据存放在客户的浏览器上，session数据放在服务器上

2、cookie不是很安全，别人可以分析存放在本地的COOKIE并进行COOKIE欺骗，考虑到安全应当使用session

3、session会在一定时间内保存在服务器上。当访问增多，会比较占用你服务器的性能考虑到减轻服务器性能方面，应当使用COOKIE

4、单个cookie保存的数据不能超过4K，很多浏览器都限制一个站点最多保存20个cookie

5、所以个人建议：

   将登陆信息等重要信息存放为SESSION

   其他信息如果需要保留，可以放在COOKIE中

二、session的生命周期

Session存储在服务器端，一般放在服务器的内存中（为了高速存取），Sessinon在用户访问第一次访问服务器时创建，需要注意只有访问JSP、Servlet等程序时才会创建Session，只访问HTML、IMAGE等静态资源并不会创建Session，可调用request.getSession(true)强制生成Session

Session什么时候失效？

　1. 服务器会把长时间没有活动的Session从服务器内存中清除，此时Session便失效。Tomcat中Session的默认失效时间为20分钟。

　　2. 调用Session的invalidate方法

三、多服务session管理

\1. Session复制，Web服务器之间同步session信息

\2. 负载均衡支持会话亲和，相同的会话请求发送给同一个Web服务器

\3. Session不存在Web服务器本地，而是放在缓存服务器如Redis上(去掉持久化)

4.基于iphash的session黏贴

   2. servlet的一些相关问题

一、Servlet生命周期

​      1. servlet容器完成加载Servlet类加载和实例化

当servlet容器启动时，或者servlet容器检测到需要这个servlet服务的第一个请求时， servlet容器会加载这个servlet，并生成servlet实例。也可在两者之间的任何时候执行（配置为load on start  up的 servlet是在容器启动时被加载的）

​      2. serlet初始化，调用init()方法

当servlet实例化后，容器将调用这个对象的init()方法进行初始化，初始化的目的是在这个实例为请求提供服务前完成初始化工作，如建立配置连接，获取配置信息等。servlet实例可以使用容器为其提供的ServletConfig对象，从web应用程序的配置信息中（即web.xml文件），获取初始化的参数信息

​      3. 服务，响应客户请求，调用service()方法

Servlet容器调用servlet实例的service()方法来对请求进行处理。在service()方法中，servlet实例通过ServletRequest对象获取客户端相关信息和请求信息；处理完成后，servlet实例通过ServletResponse对象来设置相应信息

​      4.     销毁 ，终止阶段，调用destroy()方法

​       当容器检测到某个servlet实例需要在服务中移除时，则容器将调用servlet实例的destroy()方法，以便释放实例所使用的资源，并将数据存储到持久存储设备中。当调用destroy()方法后，容器将释放此servlet实例，该实例随后将由垃圾回收器进行垃圾回收处理。如果再有对此实例的服务请求时，容器将重新创建一个新的servlet实例

二、创建和配置Servlet

​    * 建 java 项目，导入 servlet.jar 包  

​    * 继承自 HttpSerlvet  

​    * 重写 doGet()/doPost()  

​    * 拷贝 .class 文件到发布路径/WEB-INF/classes/  

​    * 在发布路径/WEB-INF/web.xml中配置servlet 

​    配置servlet分为2部分：  

​    1、  声明部分  

​    2、  部署部分

三、转发与重定向

response . sendRedirect(“路径”) 

1、重定向：其实是发一次请求，等响应回来之后，重新再去请求，然后再响应，也就是请求去了回来，然后再去再回来，第二次去也就是我们所要重定向的地址

1>显示最新的请求  

2>地址栏会变显示最新的请求地址 

3>是客户端跟服务器之间  

4>request不同，所以不能使用：reqeust. setAttribute(key,value)  

2、转发：  

request.getRequestDispatcher(“ ”).forward( req ,resp)  

1>地址栏只显示第一次的请求地址，不会变  

2>服务器内部的处理过程  

3>参数可以累加  

4>可以使用 reqeust. setAttribute(key,value)存放对象在当前请求中 

 四、Servlet线程安全

Servlet引擎采用多线程模式运行

Servlet本身是无状态的，一个无状态的Servlet是绝对线程安全的，无状态对象设计也是解决线程安全问题的一种有效手段

Servlet是否线程安全是由它的实现来决定的，如果它内部的属性或方法会被多个线程改变，它就是线程不安全的，反之，就是线程安全的.多个线程对共享资源的访问就造成了线程不安全问题

如何控制Servlet的线程安全性？

如何避免使用实例变量

避免使用非线程安全的集合

在多个Servlet中对某个外部对象(例如文件)的修改是务必加锁（Synchronized，或ReentrantLock），互斥访问

属性的线程安全：ServletContext、HttpSession是线程安全的；ServletRequest是非线程安全的

如何设计线程安全的Servlet

1.实现 SingleThreadModel 接口（避免使用）

2.同步对共享数据的操作

3.避免使用实例变量，变量等公用的定义在方法内部，最好是将servlet写成无状态的对象或都是final的成员

 五、细节

1.  针对客户端的多次servlet请求，通常情况下，服务器只会创建一个servlet实例对象，也就是说servlet实例对象一旦被创建 ，它就会驻留再内存中，为后续的其他请求服务，直到web容器推出servlet实例对象才会销毁

2.  同一个servlet可以被映射到多个URL上，即<servlet-name>一个它可以同时有多个映射  

​    <servlet-mapping>  

​        <servlet-name></servlet-name>  

​        <url-pattern></url-pattern>  

​    </servlet-mapping>

3.  URL中可以使用通配符，但只能有2种：

一种是：*.扩展名，另一种是/开头并以  /*结尾。*代表所有东西

   3. webservice相关问题

Web Service也叫XML Web Service WebService是一种可以接收从Internet或者Intranet上的其它系统中传递过来的请求，轻量级的独立的通讯技术,是:通过SOAP在Web上提供的软件服务，使用WSDL文件进行说明,并通过UDDI进行注册

WSDL：(Web Services Description Language)文件是一个XML文档,用于说明一组SOAP消息以及如何交换这些消息

UDDI就是统一描述、发现和集成（Universal Description, Discovery, and Integration）用于集中存放和查找WSDL描述文件,起着目录服务器的作用

一是SOAP协议方式，需要WSDL,UDDI等，针对RPC的一种解决方案，简单对象访问协议，很轻量，同时作为应用协议可以基于多种传输协议来传递消息（Http,SMTP等）

二是REST方式，不需要WSDL,UDDI等

1．面向资源的接口设计

2．抽象操作为基础的CRUD（Http中的get,put,post,delete分别对应read,update,create,delete操作）

3．Http是应用协议而非传输协议 

4．无状态，自包含

   3. jsp和servlet的区别

JSP在本质上就是SERVLET,都能生成动态网页

JSP的优点是擅长于网页制作，生成动态页面比较直观，缺点是不容易跟踪与排错

Servlet是纯Java语言，擅长于处理流程和业务逻辑，缺点是生成动态网页不直观

Servlet中没有内置对象，原来JSP中的内置对象都必须由程序显示创建

对于静态的HTML标签，servlet都必须使用页面输出流逐行输出

普通Servlet类里的service()方法的作用，完全等同于JSP生成Servlet类的_jspService()方法

[![attachments-2018-07-ePllLDRi5b44d01680ed3.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-ePllLDRi5b44d01680ed3.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-ePllLDRi5b44d01680ed3.png)

Web浏览器发送HTTP请求到服务端，被Controller(Servlet)获取并进行处理（例如参数解析、请求转发）

Controller(Servlet)调用核心业务逻辑——Model部分，获得结果

Controller(Servlet)将逻辑处理结果交给View（JSP），动态输出HTML内容

动态生成的HTML内容返回到浏览器显示