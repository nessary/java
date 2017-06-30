package com.java.io.decorate;

/**
 * 具体的一个实例
 * 装饰者
 *
 * <p>
 *     购物--具体的体现
 * </p>
 *
 * Created by Ness on 2017/6/24.
 */
public class Shopping implements  PurChase {
    @Override
    public void buy() {
        System.out.println("I`m  buying...");
    }
}
