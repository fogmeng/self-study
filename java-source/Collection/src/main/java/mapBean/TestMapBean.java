package mapBean;

import java.util.HashMap;
import java.util.Map;

public class TestMapBean {

    public static void testMapToBean(int i){

        Map<String, Object> map = new HashMap < String, Object>();
        map.put("name","www.ixiaocao.cn");
        map.put("age",i);
        try {
            Users u = BeanUtils.mapToBean(map,Users.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testMapToBeanByA(int i){

        Map<String, Object> map = new HashMap < String, Object>();
        map.put("name","www.ixiaocao.cn");
        map.put("age",i);
        try {
            Users u = (Users) A.mapToObject(map,Users.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



        public static void main(String[] args) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                testMapToBean(i);
            }
            long endTime = System.currentTimeMillis();
            System.out.println("耗时======>" + (endTime - startTime));

            long startTime1 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                testMapToBeanByA(i);
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("耗时======>" + (endTime1 - startTime1));
        }
}
