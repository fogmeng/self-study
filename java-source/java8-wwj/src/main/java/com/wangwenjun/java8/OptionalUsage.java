package com.wangwenjun.java8;

import java.util.Optional;

/***************************************
 * @author:Alex Wang
 * @Date:2016/10/24 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class OptionalUsage {

    public static void main(String[] args) {
/*
        Optional<Insurance> insuranceOptional = Optional.<Insurance>empty();

//        insuranceOptional.get();

        Optional<Insurance> insuranceOptional1 = Optional.of(new Insurance());

        insuranceOptional1.get();

        Optional<Insurance> objectOptional = Optional.ofNullable(null);

        Insurance insuranceOrElseGet = objectOptional.orElseGet(Insurance::new);

        Insurance insuranceOrElse = objectOptional.orElse(new Insurance());

        Insurance insuranceOrElseThrow = objectOptional.orElseThrow(RuntimeException::new);

        objectOptional.orElseThrow(() -> new RuntimeException("Not have reference"));


        Insurance insurance = insuranceOptional1.filter(i -> i.getName() != null).get();
        System.out.println(insurance);

       Optional<String> nameOptional = insuranceOptional1.map(i -> i.getName());

        System.out.println(nameOptional.orElse("empty Value"));

        System.out.println(nameOptional.isPresent());

        nameOptional.ifPresent(System.out::println);
*/
        Insurance insurance = new Insurance();
        insurance.setName(null);
       // System.out.println(getInsuranceName(null));
        System.out.println(getInsuranceNameByOptional(insurance));
    }


    private static String getInsuranceName(Insurance insurance) {
        if (null == insurance)
            return "unknown";
        return insurance.getName();
    }

    private static String getInsuranceNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}