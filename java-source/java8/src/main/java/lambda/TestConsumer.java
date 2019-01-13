package lambda;

import lambda.Entity.Apple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TestConsumer {

    static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

    private static void consumer(List<Apple> list, Consumer<Apple> consumer){

        for (Apple a : list) {
            consumer.accept(a);
        }
    }

    private static void biConsumer(List<Apple> list, BiConsumer<Apple,String> biConsumer,String c){

    }

    @Test
    public void testConsumer(){
        consumer(source,a-> System.out.println(a));
    }
}
