package com.java.locks;

import java.util.Arrays;

public class SelectLowerElement {


    public int[] swapArray(int[] swap) {
        int len = swap.length;
        int lowerIndex;
        for (int i = 0; i < len; i++) {
            lowerIndex = selectLowerNum(swap, i);
            if (lowerIndex != i) {
                swap[i] ^= swap[lowerIndex];
                swap[lowerIndex] ^= swap[i];
                swap[i] ^= swap[lowerIndex];

            }

        }
        return swap;


    }

    private int selectLowerNum(int[] data, int start) {

        int len = data.length;
        int lower = data[start];
        int lowerIndex = start;
        for (int i = start+1; i < len; i++) {
            if (data[i] < lower) {
                lower = data[i];
                lowerIndex = i;
            }


        }
        return lowerIndex;


    }

    public static void main(String[] args) {
        SelectLowerElement element = new SelectLowerElement();
        int[] arr = {3, 1, 4,6, 5};
        int[] ints = element.swapArray(arr);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
