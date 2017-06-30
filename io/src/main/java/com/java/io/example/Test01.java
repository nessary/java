package com.java.io.example;

import java.io.*;

/**
 * Created by Ness on 2017/6/24.
 */
public class Test01 implements Serializable {

    private static final long serialVersionUID = -4735122963231910686L;


    public static void main(String[] args) {

        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream("1234".getBytes());

        } catch (Exception e) {

        } finally {

            if (inputStream != null) {


                try {
                    inputStream.close();
                } catch (IOException e) {

                }

            }

        }


        try (ByteArrayInputStream inputStreams = new ByteArrayInputStream("1234".getBytes())) {

        } catch (Exception e) {

        }


    }

    public void example01() throws IOException, ClassNotFoundException {

        //二进制的读取
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1234".getBytes());

        //相同类型的嵌套 可以多种组合
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        //读的时候 inputStream也进行读取
        objectInputStream.readObject();

    }

    public void example02() throws IOException, ClassNotFoundException {

        //二进制的写
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //相同类型的嵌套 可以多种组合
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        //写的时候 outputStream也进行写
        objectOutputStream.writeDouble(0.);
        objectOutputStream.writeObject(Test01.class);


    }

    public void example03() throws IOException, ClassNotFoundException {

        new BufferedInputStream(new DataInputStream(new ByteArrayInputStream("1".getBytes())));

        new BufferedOutputStream(new FileOutputStream(new File("")));


        //缓冲字符集---input的字符---用二进制流进行读取
        new BufferedReader(new InputStreamReader(new FileInputStream(new File("")), "utf-8"));


        //缓冲字符接


        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(""), true), "utf-8"));


        new BufferedWriter(new PrintWriter(new File("")));

    }

}
