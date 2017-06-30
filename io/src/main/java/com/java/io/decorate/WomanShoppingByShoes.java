package com.java.io.decorate;

/**
 * 抽象类的具体实现类
 *
 * <p>
 *
 *     购买鞋子
 * </p>
 *
 * Created by Ness on 2017/6/24.
 */
public class WomanShoppingByShoes extends WomanShopping {

    WomanShoppingByShoes(PurChase purChase) {
        super(purChase);
    }

    @Override
    public void buy() {
        super.buy();
        buyShoes();
    }

    public void buyShoes() {
        System.out.println("I`m buy shoes...");
    }

}
