package com.java.io.decorate;

/**
 * 抽象类的具体实现类
 * <p>
 * <p>
 * <p>
 * 购买衣服
 * </p>
 * <p>
 * Created by Ness on 2017/6/24.
 */
public class WomanShoppingByClothes extends WomanShopping {
    WomanShoppingByClothes(PurChase purChase) {
        super(purChase);
    }

    @Override
    public void buy() {
        super.buy();
        buyClothes();
    }

    public void buyClothes() {
        System.out.println("I`m by clothes...");
    }


}
