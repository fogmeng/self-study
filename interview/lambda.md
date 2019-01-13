| 函数式接口       | 函数描述符     |
| ---------------- | -------------- |
| Predicate<T>     | T->boolean     |
| BiPredicate<T,U> | (T,U)->boolean |
| Consumer<T>      | T->void        |
| BiConsumer<T,U>  | (T,U)->void    |
|                  |                |
|                  |                |
|                  |                |
|                  |                |
|                  |                |



###Predicate 

```java
private static List<Apple> filter(List<Apple> source,Predicate<Apple> p){
    
    List<Apple> result = new ArrayList<Apple>();
    for(Apple a : source){
        if(p.test(a)){
            result.add(a);
        }
    }
    return result;
}

List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150));

List<Apple> result = filter(source,a->a.getColor().equals("green"));
```

### BiPredicate<T,U>

```java
public static List<Apple> biFilter(List<Apple> list, BiPredicate<String,Long> p){
        List<Apple> result = new ArrayList<>();

        for (Apple a: list ) {
            if (p.test(a.getColor(),a.getWeight())){
                result.add(a);
            }
        }

        return  result;
    }
List<Apple> source = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
List<Apple> result = biFilter(source,(a,b)-> a.equals("green")&&b<100);
```

