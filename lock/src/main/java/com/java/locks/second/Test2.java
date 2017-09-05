package com.java.locks.second;

public class Test2 {
    static class B {
        public void printClassName(A a) {
            System.out.println(a.getClass().getName());
        }
    }
    static class A {
        public static B b;

        public void globalVariablePointerEscape() { // 给全局变量赋值，发生逃逸
            b = new B();
        }

        public B methodPointerEscape() { // 方法返回值，发生逃逸
            return new B();
        }

        public void instancePassPointerEscape() {
            methodPointerEscape().printClassName(this); // 实例引用传递，发生逃逸
        }
    }



    public static void main(String[] args) {
        long start = System.nanoTime();
        for (int i = 0; i < 1000 * 1000 * 10; ++i){
            A a=new A();
            a.globalVariablePointerEscape();
        }

        System.out.println( System.nanoTime()-start);
    }
}
