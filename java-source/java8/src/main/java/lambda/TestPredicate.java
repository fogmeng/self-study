package lambda;

import lambda.Entity.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TestPredicate {

    static List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

    private static List<Apple> filter(List<Apple> list, Predicate<Apple> p){

        List<Apple> result = new ArrayList<>();

        for (Apple a: list ) {
            if (p.test(a)) {
                result.add(a);
            }
        }

        return  result;
    }

    public static List<Apple> biFilter(List<Apple> list, BiPredicate<String,Long> p){
        List<Apple> result = new ArrayList<>();

        for (Apple a: list ) {
            if (p.test(a.getColor(),a.getWeight())){
                result.add(a);
            }
        }

        return  result;
    }

    @Test
    public void testFilter(){
        List<Apple> result = filter(source,a->a.getColor().equals("green"));
        System.out.println(result);
    }

    @Test
    public void testBiFilter(){
        List<Apple> result = biFilter(source,(a,b)-> a.equals("green")&&b<100);
        System.out.println(result);
    }
}
