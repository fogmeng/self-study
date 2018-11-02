package fogmeng.hamcrest.samples;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertThat;

/**
 * @Author ll
 * @Date 2018/10/24 17:13
 *
 */
public class HamcrestMatcherTest {

    public String testString = "hello world";

    /**
     * 一般匹配
     * allOf:所有条件都必须满足， 相当于&&
     * anyOf: 其中一个满足就通过，相当于||
     * both:&&
     * either: ||
     * everyItem:每个元素都需要满足特定条件
     * hasItem:是否存在这个元素
     * hasItems:是否存在多个元素
     * anything:任何情况都匹配
     * is: equalTo(xxx) 或 instanceOf(clazz.class)的简写
     * not: ！
     * nullValue():值为空
     * notNullValue():值不为空
     *
     */
    @Test
    public void testCore(){

        /**
         * 核心匹配：与、非、不等、或、成员、相等
         */
        //allOf:所有条件都必须满足 &&
        assertThat(testString,allOf(startsWith("hello"),endsWith("world")));

    }

}
