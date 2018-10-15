# Typora 参考手册

[Typora 参考手册](http://www.jipucoffee.com/#header-n3)[块状元素](http://www.jipucoffee.com/#header-n7)[标题](http://www.jipucoffee.com/#header-n8)[块引用](http://www.jipucoffee.com/#header-n18)[列表](http://www.jipucoffee.com/#header-n31)[任务列表](http://www.jipucoffee.com/#header-n39)[代码块](http://www.jipucoffee.com/#header-n56)[数学表达式模块](http://www.jipucoffee.com/#header-n63)[表格](http://www.jipucoffee.com/#header-n70)[脚注](http://www.jipucoffee.com/#header-n113)[水平分割线](http://www.jipucoffee.com/#header-n114)[行内元素](http://www.jipucoffee.com/#header-n117)[添加链接](http://www.jipucoffee.com/#header-n118)[图片](http://www.jipucoffee.com/#header-n119)[强调](http://www.jipucoffee.com/#header-n120)[代码](http://www.jipucoffee.com/#header-n121)[删除线](http://www.jipucoffee.com/#header-n122)[下划线](http://www.jipucoffee.com/#header-n341)[表情](http://www.jipucoffee.com/#header-n123)[下标](http://www.jipucoffee.com/#header-n125)[上标](http://www.jipucoffee.com/#header-n126)[高亮](http://www.jipucoffee.com/#header-n127)[目录](http://www.jipucoffee.com/#header-n719)[导出](http://www.jipucoffee.com/#header-n740)

## 块状元素

### 标题

标题用`#`符号来作为引子，最多有六个层级

1

```
# 标题1
```

2

```
## 标题2
```

3

```
### 标题3
```

4

```
#### 标题4
```

5

```
##### 标题5
```

6

```
###### 标题6
```

效果如下:

![img1](http://www.jipucoffee.com/img/img1.png)

### 块引用

块引用`>`符号作为引子

1

```
>这是引用的内容
```

效果如下：

> 这是引用的内容

### 列表

列表可以用`*`,`+`,`-`等符号来作为引子,不添加数字就是无序列表，添加数字就是有序列表。

```

```

1

```
### un-ordered list
```

2

```
*   Red
```

3

```
*   Green
```

4

```
*   Blue
```

5

```

```

6

```
### ordered list
```

7

```
1.  Red
```

8

```
2.  Green
```

9

```
3.  Blue
```

效果如下:

![img2](http://www.jipucoffee.com/img/img2.png)

### 任务列表

任务列表可以用`- [ ]`作为引子。其实任务列表就是一个复选按钮，如果在中括号内加上x，就是一个被选中的复选按钮。

1

```
- [ ] 去吃饭
```

2

```
- [ ] 去唱歌
```

3

```
- [x] 在家打游戏
```

效果如下：

- 去吃饭
- 去唱歌
- 在家打游戏

### 代码块

代码块可以用`````作为引子。

```

```

1

```
​```
```

2

```
function code(){
```

3

```
  return "code";
```

4

```
}
```

5

```
​```
```

效果如下：

```

```

1

```
function code(){
```

2

```
  return "code";
```

3

```
}
```

### 数学表达式模块

数学表达式模块用两个`$$`作为引子：

```

```

1

```
$$
```

2

```
\mathbf{V}_1 \times \mathbf{V}_2 =  \begin{vmatrix} 
```

3

```
\mathbf{i} & \mathbf{j} & \mathbf{k} \\
```

4

```
\frac{\partial X}{\partial u} &  \frac{\partial Y}{\partial u} & 0 \\
```

5

```
\frac{\partial X}{\partial v} &  \frac{\partial Y}{\partial v} & 0 \\
```

6

```
\end{vmatrix}
```

7

```
$$
```

效果如下：

### 表格

表格可以用快捷键command+T来打开选择器。

![img3](http://www.jipucoffee.com/img/img3.png)

打开之后可以设定行 高上的格子数量。按OK之后。

效果如下：

|      |      |      |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |

还可以用这种方式来建造表格

1

```
| First Header  | Second Header |
```

2

```
| ------------- | ------------- |
```

3

```
| Content Cell  | Content Cell  |
```

4

```
| Content Cell  | Content Cell  |
```

效果如下：

| First Header | Second Header |
| ------------ | ------------- |
| Content Cell | Content Cell  |
| Content Cell | Content Cell  |

自己感觉这个方法比较麻烦，不推荐。

### 脚注

脚注需要定义鼠标移上后显示的内容,用`[^字段]`来定义：

```

```

1

```
You can create footnotes like this[^footnote].
```

2

```

```

3

```
[^footnote]: Here is the *text* of the **footnote**.
```

效果如下：

You can create footnotes like this[1](http://www.jipucoffee.com/#dfref-footnote-1).

### 水平分割线

水平分割线可以用`***`或者`—`作为引子:

```

```

1

```
***
```

2

```
---  
```

效果如下:

------

------

## 行内元素

### 添加链接

链接可以用`[需要添加链接的字段](链接的地址)`的形式：

```

```

1

```
This is [an example](http://example.com/ "Title") inline link.
```

2

```

```

3

```
[This link](http://example.net/) has no title attribute.
```

效果如下:

This is [an example](http://example.com/) inline link.

[This link](http://example.net/) has no title attribute.

### 图片

图片可以使用下面的格式来引用：

```

```

1

```
![Alt text](img/dog1.jpg)
```

2

```
![Alt text](img/dog2.jpg)
```

Alt text是无法显示图片时的替换文本。

效果如下：

![Alt text](http://www.jipucoffee.com/img/dog1.jpg) ![Alt text](http://www.jipucoffee.com/img/dog2.jpg)

### 强调

强调可以用`**`或者`__`作为引子:

```

```

1

```
**double asterisks**
```

2

```

```

3

```
__double underscores__
```

效果如下:

**double asterisks**

**double underscores**

### 代码

```

```

1

```
Use the `printf()` function.
```

效果如下：

Use the `printf()` function.

### 删除线

删除线可以用`~~`作为引子:

```

```

1

```
~~Mistaken text.~~ 
```

效果如下：

~~Mistaken text.~~

### 下划线

下划线可以用`<u></u>`作为引子:

1

```
<u>Underline</u>
```

效果如下：

Underline

### 表情

下划线可以用`：`作为引子:

```

```

1

```
:smile:
```

2

```
:cry:
```

效果如下：

😀

😭

### 下标

下划线可以用`~`作为引子，需要在`Preference` Panel -> `Markdown` Tab中打开：

```

```

1

```
H~2~O
```

效果如下：

H2O

### 上标

下划线可以用`^`作为引子，需要在`Preference` Panel -> `Markdown` Tab中打开：

```

```

1

```
X^2^
```

效果如下：

X2

### 高亮

高亮可以用`==`作为引子,需要在`Preference` Panel -> `Markdown` Tab中打开：

```

```

1

```
Here is the text of the ==footnote==.
```

效果如下:

Here is the text of the footnote.

## 目录

可以使用`[TOC]`自动根据层级生成目录：

```

```

1

```
[TOC]
```

效果如下:

[Typora 参考手册](http://www.jipucoffee.com/#header-n3)[块状元素](http://www.jipucoffee.com/#header-n7)[标题](http://www.jipucoffee.com/#header-n8)[块引用](http://www.jipucoffee.com/#header-n18)[列表](http://www.jipucoffee.com/#header-n31)[任务列表](http://www.jipucoffee.com/#header-n39)[代码块](http://www.jipucoffee.com/#header-n56)[数学表达式模块](http://www.jipucoffee.com/#header-n63)[表格](http://www.jipucoffee.com/#header-n70)[脚注](http://www.jipucoffee.com/#header-n113)[水平分割线](http://www.jipucoffee.com/#header-n114)[行内元素](http://www.jipucoffee.com/#header-n117)[添加链接](http://www.jipucoffee.com/#header-n118)[图片](http://www.jipucoffee.com/#header-n119)[强调](http://www.jipucoffee.com/#header-n120)[代码](http://www.jipucoffee.com/#header-n121)[删除线](http://www.jipucoffee.com/#header-n122)[下划线](http://www.jipucoffee.com/#header-n341)[表情](http://www.jipucoffee.com/#header-n123)[下标](http://www.jipucoffee.com/#header-n125)[上标](http://www.jipucoffee.com/#header-n126)[高亮](http://www.jipucoffee.com/#header-n127)[目录](http://www.jipucoffee.com/#header-n719)[导出](http://www.jipucoffee.com/#header-n740)

## 导出

可以导出三种文件格式。PDF，html和不带样式的html.

![img4](http://www.jipucoffee.com/img/img4.png)

------

1 Here is the *text* of the **footnote**.[↩](http://www.jipucoffee.com/#ref-footnote-1)