package com.java.io.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Ness on 2017/6/27.
 */
public class CopyOnWriteTest {


    public static void main(String[] args) throws IOException {
        scatter();
    }


    public static void gather() throws IOException {

        RandomAccessFile file = new RandomAccessFile("D:\\workplace\\teach\\io\\src\\main\\java\\com\\java\\io\\channel\\1.txt", "rw");


        FileChannel channel = file.getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(8);


        buffer1.put("1234".getBytes());
        buffer1.flip();

        buffer2.put("12345678".getBytes());

        buffer2.flip();

        ByteBuffer[] gather = {buffer1, buffer2};

        channel.write(gather);



    }


    public static void scatter() throws IOException {

        RandomAccessFile file = new RandomAccessFile("D:\\workplace\\teach\\io\\src\\main\\java\\com\\java\\io\\channel\\1.txt", "r");


        FileChannel channel = file.getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(8);



        ByteBuffer[] gather = {buffer1, buffer2};

        channel.read(gather);


        System.out.println(new String(buffer1.array(), 0, buffer1.limit()));
        System.out.println(new String(buffer2.array(), 0, buffer2.limit()));



    }


    public static void channelCopyTransfer() throws IOException {
        long star = System.currentTimeMillis();
        try (FileChannel inputChannel = FileChannel.open(Paths.get("E:\\课程\\Redhat.Linux.9.i386.dvd.iso"), StandardOpenOption.READ);
             FileChannel outputChannel = FileChannel.open(Paths.get("E:\\课程\\copy.iso"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {


            inputChannel.transferTo(0, inputChannel.size(), outputChannel);


            System.out.println(System.currentTimeMillis() - star);

        }


    }


    public static void channelCopyByDirect() throws IOException {

        long star = System.currentTimeMillis();
        try (FileChannel inputChannel = FileChannel.open(Paths.get("E:\\课程\\Redhat.Linux.9.i386.dvd.iso"), StandardOpenOption.READ);
             FileChannel outputChannel = FileChannel.open(Paths.get("E:\\课程\\copy.iso"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {

            MappedByteBuffer readByteBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());

            MappedByteBuffer writeByteBuffer = outputChannel.map(FileChannel.MapMode.PRIVATE, 0, inputChannel.size());


            byte[] dist = new byte[readByteBuffer.limit()];


            readByteBuffer.get(dist);


            writeByteBuffer.put(dist);


            System.out.println(System.currentTimeMillis() - star);

        }
    }


    public static void channelCopy() throws IOException {

        long star = System.currentTimeMillis();
        try (


                //35987--direct
                //11553--direct
                //44816--direct
                //65644--java
                FileInputStream inputStream = new FileInputStream(new File("E:\\课程\\Redhat.Linux.9.i386.dvd.iso"));

                FileOutputStream outputStream = new FileOutputStream(new File("E:\\课程\\copy.iso"));

                FileChannel inputChannel = inputStream.getChannel();
                FileChannel outputChannel = outputStream.getChannel()) {


            ByteBuffer buffer = ByteBuffer.allocate(1024);


            int len = 0;
            while ((len = inputChannel.read(buffer)) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }

            System.out.println(System.currentTimeMillis() - star);


        }


    }

}
