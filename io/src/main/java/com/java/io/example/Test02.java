package com.java.io.example;

import java.io.*;

/**
 * Created by Ness on 2017/6/24.
 */
public class Test02 {
    public static void main(String[] args) {



        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(""))));

             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(""))))) {

        } catch (IOException e) {

        }
    }
}
