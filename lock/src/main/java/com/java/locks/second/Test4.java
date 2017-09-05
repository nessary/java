package com.java.locks.second;

import java.io.IOException;

/**
 * 标量替换
 */
public class Test4 {


    static class Child {
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
    }


    private int calcAge(int age) {
        Child child = new Child();
        child.setAge(age);
        age = child.getAge() + 1;
        return age;

    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 100000; i++) {
            Test4 test4 = new Test4();
            test4.calcAge(15);
        }

        System.in.read();


    }
    /**
     * 开启逃逸分析 即默认标量替换后(-XX:+DoEscapeAnalysis)
     *   3:        100000         800000  com.java.locks.second.Test4
     *   4:         47922         766752  com.java.locks.second.Test4$Child
     *
     *
     * 关闭后（(-XX:-DoEscapeAnalysis)）
     *    2:        100000        1600000  com.java.locks.second.Test4$Child
     *  4:        100000         800000  com.java.locks.second.Test4

     *
     */


}
