package com.java.io.decorate;

/**
 * Created by Ness on 2017/6/24.
 */
public class Test {
    public static void main(String[] args) {
        Shopping shopping = new Shopping();


        WomanShoppingByClothes byClothes = new WomanShoppingByClothes(shopping);

        WomanShoppingByShoes byShoes = new WomanShoppingByShoes(byClothes);


        //=1======>2======3>
        //3<=====<2=======1


        //
        //......
        byShoes.buy();


//        new WomanShoppingByClothes(new WomanShoppingByShoes(new Shopping()));


    }
}
