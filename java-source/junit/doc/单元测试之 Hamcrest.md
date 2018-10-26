# 单元测试之 Hamcrest

阅读 1033

收藏 11

2016-11-29

原文链接：[thinkdevos.net](https://link.juejin.im/?target=http%3A%2F%2Fthinkdevos.net%2F2017%2F09%2F29%2F2017-9-29-hamcrest%2F)

概述：
最近研究如何在android中做单元测试，那就从基础的Junit开始吧；在Junit4中常用的断言都非常简单容易理解，但是到了assertThat时，我茫然了，这是个神马东西，于是参考了[Junit Wiki](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fjunit-team%2Fjunit4%2Fwiki)才知道了有一个Hamcrest，于是就有了本文的产生，本文大部分翻译于官方教程，但在自定义匹配器的过程中，无法自己调试通过，就自己研究了一个过程；本文中所有代码都调试通过了，可以自己去我fork的Junit4的源码branch [junit-read](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fborneywpf%2Fjunit4%2Ftree%2Fjunit-read)的demo目录下查看

# Hamcrest 教程

[原文连接](https://link.juejin.im/?target=https%3A%2F%2Fcode.google.com%2Farchive%2Fp%2Fhamcrest%2Fwikis%2FTutorial.wiki)
[官方demo地址](https://link.juejin.im/?target=https%3A%2F%2Fcode.google.com%2Farchive%2Fp%2Fhamcrest%2Fdownloads)

## 介绍

Hamcrest 是一个用于编写匹配对象的框架，允许以声明方式定义“匹配规则”。多数情况下，匹配器是不可用的，如UI验证，数据过滤，但是在编写灵活测试领域中，匹配器是最常用的。本教程将向你介绍如何使用Hamcrest进行单元测试。

当写测试时，有时很难在过度指定测试之间获得平衡（并使其易于改变），并没有指定足够的（使测试更有价值，即使测试的东西被中断，它任然能继续传递）。有一个工具使得你能够精确的选择测试的方面并描述它应该具有的价值，精确的掌控。极大的帮助编写“恰到好处”的测试，当测试方面的行为偏离预期的行为时，这样的测试失败，但是当对行为进行次要的，无关紧要的改变时测试通过继续。

## 第一个Hamcrest测试

我们将从写一个简单的Junit3测试开始，但是不适用Junit的assertEquals方法，我们使用Hamcrest的assertThat结构和标准的匹配器，我们静态导入：

```
import static org.hamcrest.MatcherAssert.assertThat;  
import static org.hamcrest.Matchers.*;  
import junit.framework.TestCase;
public class BiscuitTest extends TestCase { 
  public void testEquals() {
      Biscuit theBiscuit = new Biscuit("Ginger");
      Biscuit myBiscuit = new Biscuit("Ginger");
      assertThat(theBiscuit, equalTo(myBiscuit));
  }
}
```

*assertThat* 是用于进行断言测试的程式化句子。在例子中，断言的主题是作为第一个方法参数对象 *Biscuit* 。第二个参数是 *Biscuit* 对象的匹配器，这个匹配器使用 *Object equals* 法规法检查一个对象等于另一个对象。因为 *Biscuit* 重写了equals方法，测试通过。

如果测试中有多个断言，可以在断言中包含被测试值的标识符：

```
public void testIdentifier() {
    Biscuit theBiscuit = new Biscuit("Ginger");
    assertThat("chocolate chips", theBiscuit.getChocolateChipCount(), equalTo(10));
    assertThat("hazelnuts", theBiscuit.getHazelnutCount(), equalTo(3));
}
```

## 其他测试框架

Hamcrest从一开始设计就可以和其他不同的测试框架结合，例如，hamcrest可以和Junit3，Junit4和TestNG一起使用（有关详细信息，可以查看完整的Hamcrest发行版附带的示例）。这是很容易迁移使用hamcrest样式断言到现有的测试套件中，因为其他断言样式可以和Hamcrest并存。

Hamcrest可以和模拟对象框架一起使用，通过适配器框架从模拟器对象框架的匹配器概念桥接到Hamcrest框架，JMock1使用Hamcrest匹配器被限制.Hamcrest提供了一个JMock1适配器，允许在JMock1测试中使用Hamcrest匹配器。JMock2不需要这样的适配器层，因为它被设计成使用Hamcrest作为其匹配库。Hamcrest还为EasyMock2提供了适配器，有关更多详细信息，请参阅Hamcrest示例。

## 通用匹配器的浏览

Hamcrest自带一个有用的匹配库，这里是一些最重要的

- 核心
  *anyting* - 总是匹配，如果你不关心被测试的对象是否有用
  *describedAs* - 装饰器添加自定义失败描述
  *is* - 装饰器用以提高可读性，见下面的”Sugar”
- 逻辑
  *allOf* - 如果所有匹配器匹配则匹配，短路（如java中的&&)
  *anyOf* - 如果任何匹配器匹配则匹配，短路（如java中的||)
  *not* - 如果匹配器不匹配则匹配，反之亦然
- 对象
  *equalTo* - 使用Object.equals测试对象相等
  *hasToString* - 测试Object.toString
  *instanceOf，isCompatibleType* - 测试类型
  *notNullValue，nullValue* - null测试
  *sameInstance* - 测试对象标示
- Bean
  *hasProperty* - 测试JavaBeans属性
- 集合
  *array* - 针对数组的匹配器，测试数组元素
  *hasEntry, hasKey, hasValue* - 测试Map的entry,key,value
  *hasItem, hasItems* - 测试一个集合包含的元素
  *hasItemInArray* - 测试一个数组中包含的一个元素
- Number
  *closeTo* - 测试浮点值接近给定值
  *greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo* - 测试大小排序
- 文本
  *equalToIgnoringCase* - 测试字符串忽略大小写等式
  *equalToIgnoringWhiteSpace* - 测试字符串等式忽略大小写和空白符
  *containsString, endsWith, startsWith* - 测试字符串匹配

## Sugar

Hamcrest努力使得你的测试尽可能的可读，例如 *is*是一个匹配器的包装器不向基础匹配器添加任何额外的行为。如下断言都是等效的

```
public void testSugar() {
    Biscuit theBiscuit = new Biscuit("Ginger");
    Biscuit myBiscuit = new Biscuit("Ginger");
    assertThat(theBiscuit, equalTo(myBiscuit));
    assertThat(theBiscuit, is(equalTo(myBiscuit)));
    assertThat(theBiscuit, is(myBiscuit));
}  
```

最后一个形式是被允许的，因为is(T value)被重载返回is(equalTo(value)).

## 编写自定义匹配器

Hamcrest捆绑了很多有用的匹配器，但是你可能会发现，你需要不断创建自己的匹配器，以适应自己的测试需求。这通常发生在你发现一个代码片段，一次又一次测试相同的属性（和做不同的测试），并且你希望将碎片捆绑在一个断言中。通过编写自己的匹配器，你将消除代码的重复，使你的测试更可读。
让我们编写自己的匹配器来测试double值是否具有值NaN(不是数字)，这是我们要写的测试

```
public void testSquareRootOfMinusOneIsNotANumber() {   
  assertThat(Math.sqrt(-1), is(notANumber())); 
}
```

这里是实现

```
package org.hamcrest.examples.tutorial;
import org.hamcrest.Description; 
import org.hamcrest.Factory; 
import org.hamcrest.Matcher; 
import org.hamcrest.TypeSafeMatcher;
public class IsNotANumber extends TypeSafeMatcher<Double> {
  @Override public boolean matchesSafely(Double number) { 
    return number.isNaN(); 
  }
  public void describeTo(Description description) {     
    description.appendText("not a number"); 
  }
  @Factory public static Matcher notANumber() { 
    return new IsNotANumber(); 
  }
}
```

*assertThat* 方法是一个通用的方法，接收一个由断言的主题类型化的匹配器参数。我们断言关于Double的值，所以我们知道我们需要一个Matcher ,对于我们的Matcher的实现，最方便的是子类化TypeSafeMatcher,其可以类型转化成Double给我们，我们只需要实现matchesSafely方法，它只是检查Double是否是NaN,实现describeTo方法，它提供测试失败时的描述信息，下面是一个失败信息的例子

```
assertThat(1.0, is(notANumber()));
//失败消息
//java.lang.AssertionError: Expected: is not a number got : <1.0>
```

在我们匹配器中的第三个方法是一个方便的工厂方法。我们静态导入这个方法在我们的匹配器测试中：

```
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.hamcrest.examples.tutorial.IsNotANumber.notANumber;
import junit.framework.TestCase;
public class NumberTest extends TestCase {
  public void testSquareRootOfMinusOneIsNotANumber() { 
    assertThat(Math.sqrt(-1), is(notANumber())); 
  } 
} 
```

尽管notANumber方法在每次被调用时创建一个新的匹配器，你不应该假定这是你的匹配器唯一的使用模式。因此你应该确保你的匹配器是无状态的，如此，单个实例可以在匹配器之间重用。

## Sugar 生成

如果你生成了多个自定义的匹配器，必须单个导入它们变得使人烦恼，将它们组合在一个类中这将是一个很高兴的事情。因此它们可以使用一个静态导入，就像Hamcrest库匹配器一样。Hamcrest通过使用generator来做到这一点。
首先、创建一个xml配置文件，列出应该使用org.hamcrest.Factory注解注解的工厂方法的所有Matcher类。例如

```
<matchers>
    <!-- Core -->
    <factory class="com.think.junit.hamcrest.IsNotANumber"/>
</matchers>
```

其次、将自己的Matcher class打成jar包（该步骤非官方提供，因为我按照官方的方法，没有调试通就自己摸索了这种方式生成Sugar）
![hamcrest-sugar-jar.png](https://user-gold-cdn.xitu.io/2017/9/29/ed21e2ca877ba181b015371849432535?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
再次、运行Hamcrest附带的org.hamcrest.generator.config.XmlConfigurator命令行工具，此工具使用xml配置文件，生成一个包含xml文件指定的所有工厂方法的java类.

```
java -cp .:demo/hamcrest-generator-1.3.jar org.hamcrest.generator.config.XmlConfigurator
```

运行它没有参数时将输出使用信息
![hamcrest-sugar-args.png](https://user-gold-cdn.xitu.io/2017/9/29/8540eb5f00cd1e4e5aea123d2eec7337?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
下面通过终端命令行输出实例。
![hamcrest-sugar-generator.png](https://user-gold-cdn.xitu.io/2017/9/29/57d9e4f6c3a40a60d13ed7f7a9ab7b1a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)
生成的实例代码

```
// Generated source.
package com.think.junit.hamcrest;
public class Matchers {
  public static org.hamcrest.Matcher notANumber() {
    return com.think.junit.hamcrest.IsNotANumber.notANumber();
  }
}
```

最后，我们可以更新我们的测试类以使用新的Matchers类

```
import org.junit.Test;
import static com.think.junit.hamcrest.Matchers.notANumber;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * Created by borney on 11/29/16.
 */
public class CustomSugarNumberTest {
    @Test
    public void testSquareRootOfMinusOneIsNotANumber() {
        assertThat(Math.sqrt(-1), is(notANumber()));
    }
}
```

运行结果如下：
![hamcrest-sugar-testrun.png](https://user-gold-cdn.xitu.io/2017/9/29/57e983596064dae247698aae6b256ae2?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

注意，我们现在使用的是Hamcrest库是从我们自己的Matcher类中导入的Matcher
本文[[示例代码\]](https://link.juejin.im/?target=https%3A%2F%2Fgithub.com%2Fborneywpf%2Fjunit4%2Ftree%2Fjunit-read%2Fdemo)

```
㈠ 一般匹配符

1、allOf

     匹配符表明如果接下来的所有条件必须都成立测试才通过,相当于“与”(&;&;)
    assertThat( testedNumber, allOf( greaterThan(8), lessThan(16) ) );
2、anyOf  

    匹配符表明如果接下来的所有条件只要有一个成立则测试通过,相当于“或”(||)
    assertThat( testedNumber, anyOf( greaterThan(16), lessThan(8) ) );
3、anything

    匹配符表明无论什么条件,永远为true
    assertThat( testedNumber, anything() );
4、is

    匹配符表明如果前面待测的object等于后面给出的object,则测试通过
   assertThat( testedString, is( "developerWorks" ) );
5、not

    匹配符和is匹配符正好相反,表明如果前面待测的object不等于后面给出的object,则测试通过
    assertThat( testedString, not( "developerWorks" ) );

㈡ 字符串相关匹配符

1、containsString

     匹配符表明如果测试的字符串testedString 包含 子字符串"developerWorks"则测试通过
     assertThat( testedString, containsString( "developerWorks" ) );
2、endsWith

     匹配符表明如果测试的字符串testedString以子字符串"developerWorks"结尾则测试通过
     assertThat( testedString, endsWith( "developerWorks" ) ); 
3、startsWith

     匹配符表明如果测试的字符串testedString以子字符串"developerWorks"开始则测试通过
     assertThat( testedString, startsWith( "developerWorks" ) ); 
4、equalTo

     匹配符表明如果测试的testedValue等于expectedValue则测试通过,equalTo可以测试数值之间,字符串

     之间和对象之间是否相等,相当于Object的equals方法
     assertThat( testedValue, equalTo( expectedValue ) ); 
5、equalToIgnoringCase

     匹配符表明如果测试的字符串testedString在忽略大小写的情况下等于"developerWorks"则测试通过
     assertThat( testedString, equalToIgnoringCase( "developerWorks" ) ); 
6、equalToIgnoringWhiteSpace

     匹配符表明如果测试的字符串testedString在忽略头尾的任意个空格的情况下等于"developerWorks"则

     测试通过,注意:字符串中的空格不能被忽略
     assertThat( testedString, equalToIgnoringWhiteSpace( "developerWorks" ) );

㈢ 数值相关匹配符

1、closeTo

     匹配符表明如果所测试的浮点型数testedDouble在20.0±0.5范围之内则测试通过
     assertThat( testedDouble, closeTo( 20.0, 0.5 ) );
2、greaterThan

     匹配符表明如果所测试的数值testedNumber大于16.0则测试通过
     assertThat( testedNumber, greaterThan(16.0) );
3、lessThan

     匹配符表明如果所测试的数值testedNumber小于16.0则测试通过
     assertThat( testedNumber, lessThan (16.0) );
4、greaterThanOrEqualTo

     匹配符表明如果所测试的数值testedNumber大于等于16.0则测试通过
     assertThat( testedNumber, greaterThanOrEqualTo (16.0) );
5、lessThanOrEqualTo

     匹配符表明如果所测试的数值testedNumber小于等于16.0则测试通过
     assertThat( testedNumber, lessThanOrEqualTo (16.0) );

㈣ collection相关匹配符

1、hasEntry

     匹配符表明如果测试的Map对象mapObject含有一个键值为"key"对应元素值为"value"的Entry项则测试通过
     assertThat( mapObject, hasEntry( "key", "value" ) );
2、hasItem

     匹配符表明如果测试的迭代对象iterableObject含有元素“element”项则测试通过
     assertThat( iterableObject, hasItem ( "element" ) );
3、hasKey

     匹配符表明如果测试的Map对象mapObject含有键值“key”则测试通过
     assertThat( mapObject, hasKey ( "key" ) );
4、hasValue

     匹配符表明如果测试的Map对象mapObject含有元素值“value”则测试通过
     assertThat( mapObject, hasValue ( "key" ) );
```

