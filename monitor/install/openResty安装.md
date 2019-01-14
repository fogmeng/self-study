# Openresty资料之windows环境安装

2018年03月28日 11:35:59 [宅羽](https://me.csdn.net/sky6even) 阅读数：1788

 版权声明：本文为博主原创文章，未经博主允许不得转载。	https://blog.csdn.net/sky6even/article/details/79717718

◆ 1、下载windows版本的openresty：

```
http://openresty.org/cn/download.html1
```

◆ 2、解压到要安装的目录（例如：解压到D盘根目录）：

```
D:\openresty-1.13.6.1-win32>dir
 驱动器 D 中的卷没有标签。
 卷的序列号是 A25B-3CEA

 D:\openresty-1.13.6.1-win32 的目录

2018/03/27  18:44    <DIR>          .
2018/03/27  18:44    <DIR>          ..
2018/03/27  18:44    <DIR>          client_body_temp
2017/11/13  13:50    <DIR>          conf
2017/11/13  13:50            22,924 COPYRIGHT
2018/03/27  18:44    <DIR>          fastcgi_temp
2017/11/13  13:50    <DIR>          html
2017/11/13  13:50    <DIR>          include
2017/11/13  13:50           117,932 libgcc_s_dw2-1.dll
2018/03/27  19:00    <DIR>          logs
2017/11/13  13:50    <DIR>          lua
2017/11/13  13:50         2,721,077 lua51.dll
2017/11/13  13:50           102,605 luajit.exe
2017/11/13  13:50    <DIR>          lualib
2017/11/13  13:50        22,553,342 nginx.exe
2017/11/13  13:50    <DIR>          pod
2018/03/27  18:44    <DIR>          proxy_temp
2017/11/13  13:50             8,690 README.txt
2017/11/13  13:50            19,535 resty
2017/11/13  13:50            19,953 resty.bat
2017/11/13  13:50            14,957 restydoc
2017/11/13  13:50             8,452 restydoc-index
2017/11/13  13:50             9,229 restydoc-index.bat
2017/11/13  13:50            16,003 restydoc.bat
2018/03/27  18:44    <DIR>          scgi_temp
2018/03/27  18:44    <DIR>          uwsgi_temp
              12 个文件     25,614,699 字节
              14 个目录 156,103,303,168 可用字节12345678910111213141516171819202122232425262728293031323334
```

◆ 3、启动nginx.exe：

双击nginx.exe运行 

◆ 4、验证：

方式一、打开控制台执行命令：

```
tasklist /fi "imagename eq nginx.exe"1
```

显示结果：

```
映像名称                       PID 会话名              会话#       内存使用
========================= ======== ================ =========== ============
nginx.exe                    13388 Console                    1      7,256 K
nginx.exe                    13516 Console                    1      7,456 K1234
```

方式二、登陆网址：

```
http://127.0.0.1:8888/1
```

注：本例端口号改为了8888。

◆ 问题处理：

1、 bind() to 0.0.0.0:80 failed

原因：80端口被占用

处理方案：修改nginx.conf配置文件中的监听端口

```
http -> server -> listen

    server {
        listen       8888;
        server_name  localhost;
```