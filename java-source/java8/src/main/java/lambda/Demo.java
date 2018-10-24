package lambda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.UUID;
import java.util.function.*;

public class Demo {

    public static void main(String[] args) {

    }

    @Test
    public void testPredicate(){
        Predicate<String> nameStartWithS = name -> name.startsWith("S");
        boolean hello = nameStartWithS.test("hello");
        boolean isSmart = nameStartWithS.test("Smart");
        assertThat(hello,is(false));
        assertThat(isSmart,is(true));
    }

    @Test
    public void testFunction(){
        Function<String,String> toUpCase = name -> name.toUpperCase();
        String java = toUpCase.apply("java");
        assertThat("JAVA",is(java));
    }

    @Test
    public  void testConsumer(){
        Consumer<String> messageConsumer = message -> System.out.println(message);
        messageConsumer.accept("Hello world");
    }

    @Test
    public void testSupplier(){
        Supplier<String> uuidGenerator = () -> UUID.randomUUID().toString();
        String s = uuidGenerator.get();
        System.out.println(s);
        System.out.println(s);
    }

    @Test
    public void testFuctionSum(){
        BinaryOperator<Integer> sumFuction =(x,y)->x+y;
        Integer sum = sumFuction.apply(1, 2);
        assertThat(sum,is(3));
    }
}
