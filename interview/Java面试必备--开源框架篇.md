### Java面试必备--开源框架篇

- [面试](https://ask.gupaoedu.com/topic/70)

这系列文章是本人从有道笔记直接拷贝过来的面试知识点，因此会存在排版不整齐的问题，也会存在知识点遗漏或者偏差，大家可以一起交流补充。。。

springMVC工作原理

[![attachments-2018-07-UBNCBaMd5b44d1ff1b064.png](https://ask.gupaoedu.com/image/show/attachments-2018-07-UBNCBaMd5b44d1ff1b064.png)](https://ask.gupaoedu.com/image/show/attachments-2018-07-UBNCBaMd5b44d1ff1b064.png)

1、客户端发出一个http请求给web服务器，web服务器对http请求进行解析，如果匹配DispatcherServlet的请求映射路径（在web.xml中指定），web容器将请求转交给DispatcherServlet

2、DipatcherServlet接收到这个请求之后将根据请求的信息（包括URL、Http方法、请求报文头和请求参数Cookie等）以及HandlerMapping的配置找到处理请求的处理器（Handler）

3-4、DispatcherServlet根据HandlerMapping找到对应的Handler,将处理权交给Handler（Handler将具体的处理进行封装），再由具体的HandlerAdapter对Handler进行具体的调用

5、Handler对数据处理完成以后将返回一个ModelAndView()对象给DispatcherServlet

6、Handler返回的ModelAndView()只是一个逻辑视图并不是一个正式的视图，DispatcherSevlet通过ViewResolver将逻辑视图转化为真正的视图View

7、Dispatcher通过model解析出ModelAndView()中的参数进行解析最终展现出完整的view并返回给客户端

​    spring中beanFactory和ApplicationContext的联系和区别

一、功能

BeanFactory：是Spring里面最底层的接口，提供了最简单的容器的功能，只提供了实例化对象和拿对象的功能；

ApplicationContext：应用上下文，它是Spring的一各更高级的容器，提供了更多的有用的功能； 

1，ApplicationContext继承了BeanFactory接口，所以，ApplicationContext也能像BeanFactory从容器中得到Bean； 

2，ApplicationContext提供的额外的功能： 

A、国际化的功能；

B、消息发送、响应机制；

C、统一加载资源的功能；

D、AOP的功能；

二、延迟实例化加载

BeanFactory在启动的时候不会去实例化Bean，只有从容器中拿Bean的时候才会去实例化；

ApplicationContext在启动的时候就把所有的Bean全部实例化了。它还可以为Bean配置lazy-init=true来让Bean延迟实例化；

延迟优点：应用启动的时候占用资源很少；对资源要求较高的应用，比较有优势； 

不延迟优点：

1，所有的Bean在启动的时候都加载，系统运行的速度快； 

2，在启动的时候所有的Bean都加载了，就能在系统启动的时尽早的发现系统中的配置问题 

3，建议web应用，在启动的时候就把所有的Bean都加载了（把费时的操作放到系统启动中完成）

三、FactoryBean：以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的Id从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身， 如果要获取FactoryBean对象，可以在id前面加一个&符号来获取

​    spring注入方式

- Set注入
- 构造器注入
- 静态工厂的方法注入
- 实例工厂的方法注入

​    springIOC原理：基于反射，基于有向图的Spring IOC解析 --IoC也被称为依赖注入（DI）。 它是一个过程，对象通过构造函数参数，工厂方法的参数或在工厂方法构造或返回后在对象实例上设置的属性来定义它们的依赖关系，即它们使用的其他对象。 容器在创建bean时会注入这些依赖关系。 这个过程从根本上来说是相反的，因此名为控制反转（IoC），bean本身通过使用类的直接构造或诸如Service Locator模式之类的机制来控制其依赖关系的实例化或位置。

​    springAOP原理：动态代理(JdkProxy/CGLib)

JDK的动态代理机制只能代理实现了接口的类，cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。jdk proxy 通过反射机制调用委托类的方法(字节码)实现（Proxy类InovactionHandler接口定义代理类实现），cglib 采用类似索引的方式直接调用委托类方法通过字节码增强，动态生成java类（CglibAopProxyMethodInterceptor接口拦截 Enhancer类生成代理类）

区别只是生成 AOP 代理类的时机不同：AspectJ 采用编译时生成 AOP 代理类，因此具有更好的性能，但需要使用特定的编译器进行处理；而 Spring AOP 则采用运行时生成 AOP 代理类，因此无需使用特定编译器进行处理。由于 Spring AOP 需要在每次运行时生成 AOP 代理，因此性能略差一些

AOP 代理

代理中类里A方法调用N多方法，proxy.B给B方法加代理，而A方法其他调用方法不会被代理

[@Autowired(Spring注解)和@Resource的区别](http://www.cnblogs.com/wangxiaoce/p/7056907.html)(用途：装配bean)

@Autowired  默认按类型装配，依赖对象必须存在，如果要允许null值，可以设置它的required属性为false   @Autowired(required=false)，也可以使用名称装配，配合@Qualifier注解

@Resource  （推荐使用）默认按名称进行装配，通过name属性进行指定

mybatis中的#和$的区别

\#相当于对数据 加上双引号，$相当于直接显示数据

1. #将传入的数据都当成一个字符串，会对自动传入的数据加一个双引号。如：order by #user_id#，如果传入的值是111,那么解析成sql时的值为order by "111", 如果传入的值是id，则解析成的sql为order by "id".

\2. $将传入的数据直接显示生成在sql中。如：order by $user_id$，如果传入的值是111,那么解析成sql时的值为order by user_id,  如果传入的值是id，则解析成的sql为order by id.

3. #方式能够很大程度防止sql注入;$方式无法防止Sql注入。

4.$方式一般用于传入数据库对象，例如传入表名.

6.一般能用#的就别用$.

MyBatis排序时使用order by 动态参数时需要注意，用$而不是#

字符串替换

默认情况下，使用#{}格式的语法会导致MyBatis创建预处理语句属性并以它为背景设置安全的值（比如?）。这样做很安全，很迅速也是首选做法，有时你只是想直接在SQL语句中插入一个不改变的字符串。比如，像ORDER BY，你可以这样来使用：ORDER BY ${columnName}这里MyBatis不会修改或转义字符串。

重要：接受从用户输出的内容并提供给语句中不变的字符串，这样做是不安全的。这会导致潜在的SQL注入攻击，因此你不应该允许用户输入这些字段，或者通常自行转义并检查。

\> 1 实例化

\>

\> 2 填充属性

\>

\> 3 调用BeanNameAware的setBeanName()方法

\>

\> 4 调用BeanFactoryAware的setBeanFactory()方法

\>

\> 5 调用ApplicationContextAware的setApplicationContext()方法

\>

\> 6 调用BeanPostProcessor()的预初始化方法

\>

\> 7 调用@PostConstruct注解指定的方法

\>

\> 8 调用InitializingBean的afterPropertiesSet()方法

\>

\> 9 调用init-method属性指定的自定义初始化方法

\>

\> 10 调用BeanPostProcessor的初始化后方法

\>

\> 11 bean可以进行使用了

\>

\> 12 容器关闭

\>

\> 13 调用@PreDestroy注解指定的方法

\>

\> 14 调用DisposableBean的destroy()方法

\>

\> 15 调用destroy-method属性指定的自定义销毁方法

 连接池/线程池

连接池的主要优点：(性能/资源利用率)

1）减少连接的创建时间，连接池中的连接是已准备好的，可以重复使用的，获取后可以直接访问数据库，因此减少了连接创建的次数和时间

2）简化的编程模式。当使用连接池时，每一个单独的线程能够像创建自己的JDBC连接一样操作，允许用户直接使用JDBC编程技术

3）控制资源的使用。如果不使用连接池，每次访问数据库都需要创建一个连接，这样系统的稳定性受系统的连接需求影响很大，很容易产生资源浪费和高负载异常。连接池能够使性能最大化，将资源利用控制在一定的水平之下。连接池能控制池中的链接数量，增强了系统在大量用户应用时的稳定性

一：降低资源消耗。二：提高响应速度 ，三：提高线程的可管理性。

连接池的工作原理：连接池的核心思想是连接的复用，通过建立一个数据库连接池以及一套连接使用、分配和管理策略，使得该连接池中的连接可以得到高效，安全的复用，避免了数据库连接频繁建立和关闭的开销

第一、连接池的建立。一般在系统初始化时，连接池会根据系统配置建立，并在池中建立几个连接对象，以便使用时能从连接池中获取，连接池中的连接不能随意创建和关闭，这样避免了连接随意建立和关闭造成的系统开销

第二、连接池的管理。连接池管理策略是连接池机制的核心，连接池内连接的分配和释放对系统的性能有很大的影响。其策略是：

当客户请求数据库连接时，首先查看连接池中是否有空闲连接，如果存在空闲连接，则将连接分配给客户使用；如果没有空闲连接，则查看当前所开的连接数是否已经达到最大连接数，例如如果没有达到就重新创建一个请求的客户；如果达到，就按设定的最大等待时间进行等待，如果超出最大等待时间，则抛出异常给客户。当客户释放数据库连接时，先判断该连接的引用次数是否超过了规定值，如果超过了就从连接池中删除该连接，否则就保留为其他客户服务。该策略保证了数据库连接的有效复用，避免了频繁建立释放连接所带来的系统资源的开销

第三、连接池的关闭