package com.java.locks.base;

import java.util.*;
import java.util.stream.Collector;

public class Test04 {


    public static void main(String[] args) {
        Students studentsA = new Students();
        studentsA.setName("jetty");
        studentsA.setAge(18);

        Students studentsB = new Students();
        studentsB.setName("jetty");
        studentsB.setAge(19);


        Students studentsC = new Students();
        studentsC.setName("jetty");
        studentsC.setAge(20);


        TreeSet<Students> students = new TreeSet<>();
//        Set<Students> students = new HashSet<>();

        students.add(studentsA);
        students.add(studentsB);
        students.add(studentsC);
//        System.out.println(students)



    }


    private static class Students implements Comparable<Students> {
//    private static class Students  {



        private String name;

        private Integer age;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }


        @Override
        public boolean equals(Object o) {
            System.out.println("into equals...");
            if (this == o) return true;
            if (!(o instanceof Students)) return false;

            Students students = (Students) o;

            if (getName() != null ? !getName().equals(students.getName()) : students.getName() != null) return false;
//            return getAge() != null ? getAge().equals(students.getAge()) : students.getAge() == null;
            return true;
        }

        @Override
        public int hashCode() {
//            int result = getName() != null ? getName().hashCode() : 0;
//            result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
//            return result;
            System.out.println("into hashcode...age:"+age);

//            return (int) (Math.random() * 10);
            return (int) (Math.random() * 10);
//            return 1;
        }

        @Override
        public String toString() {
            return "Students{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Students o) {
            // 0 代表 相同的数据  1 代表正向排序 -1 代表 反向排序
            System.out.println("start");
            return 1;
        }
    }


}
