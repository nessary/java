package com.java.io.decorate;

/**
 * 具体的抽象类
 * <p>
 * <p>
 * 所有的女性的集合
 * <p>
 * </p>
 * <p>
 * Created by Ness on 2017/6/24.
 */
public abstract class WomanShopping implements PurChase {

    PurChase purChase;
    WomanShopping(PurChase purChase) {
        this.purChase = purChase;
    }

    @Override
    public void buy() {


        purChase.buy();

    }
}
