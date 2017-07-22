package com.java.locks.base;

import java.util.*;
import java.util.stream.Collectors;

public class Test01 {

    private volatile double aDouble;

    public synchronized static void main(String[] args) {

        int a = 1;

        //int---4
        //
        long b = 254545454546846846l;
        double c = 3d;


        List<Person> list = new LinkedList<>();

        Person person1 = new Person();
        person1.setName("12");
        person1.setAge(24);


        list.add(person1);


        Person person2 = new Person();
        person2.setName("12");
        person2.setAge(25);

        list.add(person2);

        Collections.sort(list);


        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>((o1, o2) -> {
            if (o2.getName().equals(o1.getName())) {


                return 0;
            } else {
                return 1;
            }
        })), ArrayList::new))

        ;
        System.out.println(list);


    }

    private static class Person implements Comparable<Person> {

        private String name;

        private Integer age;

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Person o) {

            //这里调节 需要的自定义相同排序的参数
            return o.getAge() - this.getAge();
        }
    }

}
