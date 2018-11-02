package fogmeng.test;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class A {
    @Test
    public void test1(){
        assertThat(1, is(1));
    }
}
