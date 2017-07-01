package com.java.io.channel2;

import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Ness on 2017/6/27.
 */
public class FileCopy {


    //ByteBuffer.allocate----
    //执行时间 ：113100。ms
    //ByteBuffer.allocateDirect----
    //执行时间 ：117426。ms

    //channel.map
    //执行时间 ：149787。ms


    //35987--direct
    //11553--direct
    //44816--direct
    //65644--java


    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        zeroCopy();


    }

    /**
     * java层面上的o拷贝
     */

    public static void zeroCopy() throws IOException {
        //建议不用这个
        //        new FileInputStream(new File("C:\\Users\\Ness\\Desktop\\user1.sql")).getChannel();

        try (

                FileChannel read = FileChannel.open(Paths.get("C:\\Users\\Ness\\Desktop\\user.sql"), StandardOpenOption.READ);
                FileChannel write = FileChannel.open(Paths.get("C:\\Users\\Ness\\Desktop\\user1.sql"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {





            //堆外内存一次拷贝
//            MappedByteBuffer readMap = read.map(FileChannel.MapMode.READ_ONLY, 0, read.size());
//            MappedByteBuffer writeMap = write.map(FileChannel.MapMode.READ_WRITE, 0, read.size());
//
//
//            readMap.get("");
//
//            writeMap.put()

            read.transferTo(0, read.size(), write);


        }

//        l

    }


    /**
     * 聚集写入(gather)
     */
    public static void gatherWrite() throws IOException {

        try (FileChannel writeChannel = new RandomAccessFile("C:\\Users\\Ness\\Desktop\\user1.sql", "rw").getChannel()) {

            writeChannel.write(scatterRead());
//            writeChannel.force(true);


        }
    }

    /**
     * 分散读取 (scatter)
     */

    public static ByteBuffer[] scatterRead() throws IOException {

        ByteBuffer[] buffers = new ByteBuffer[2];
        //第一个作用是为彼此约定的读写大小
        //加速操作文件一次的读写

        try (FileChannel readChannel = new RandomAccessFile("C:\\Users\\Ness\\Desktop\\user.sql", "r").getChannel()) {

            ByteBuffer buffer1 = ByteBuffer.allocate(44);
            ByteBuffer buffer2 = ByteBuffer.allocate(46);

            buffers[0] = buffer1;
            buffers[1] = buffer2;


            readChannel.read(buffers);


            //切换读写的模式
            buffer1.flip();
            buffer2.flip();

            System.out.println(new String(buffer1.array(), 0, buffer1.limit()));
            System.out.println(new String(buffer2.array(), 0, buffer2.limit()));


        }

        return buffers;


    }


    /**
     * 传统型BIO进行拷贝
     *
     * @throws IOException
     */
    public static void fileCopyByBIO() throws IOException {


        long start = System.currentTimeMillis();

        //try----with---resources   实现closeable 自动关闭流
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("E:\\课程\\Redhat.Linux.9.i386.dvd.iso"))));


             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:\\课程\\copy.iso"))));

        ) {
            int len = 0;

            //缓冲字符数组   后面1024 可以自己定义
            char[] chars = new char[1024];

            while ((len = reader.read(chars)) != -1) {

                writer.write(chars, 0, len);

            }


        }

        System.out.println("执行时间 ：" + (System.currentTimeMillis() - start) + "。ms");


    }


    /**
     * 面向JVM上的缓冲 channel+buffer拷贝
     */
    public static void fileCopyByJVMNIO() throws IOException {
        long start = System.currentTimeMillis();


        try (FileChannel readChannel = new FileInputStream(new File("E:\\课程\\Redhat.Linux.9.i386.dvd.iso")).getChannel();

             FileChannel writerChannel = new FileOutputStream(new File("E:\\课程\\copy.iso")).getChannel()) {


            //面向JVM上的内存
//          ByteBuffer buffer = ByteBuffer.allocate(1024);
            //面向内存映射文件的直接内存
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

            int len = 0;

            while ((len = readChannel.read(buffer)) != -1) {

                //切换成写的状态
                buffer.flip();

                writerChannel.write(buffer);

                //清除本次的读写数据的所有状态--初始化
                buffer.clear();

            }
            System.out.println("执行时间 ：" + (System.currentTimeMillis() - start) + "。ms");

        }
    }


    /**
     * 面向内存映射文件 channel+Map获取
     */

    public static void fileCopyByMemNIO2() throws IOException {

        long start = System.currentTimeMillis();


        //通过AIO(NIO2)直接获取异步IO
        try (FileChannel readChannel = FileChannel.open(Paths.get("E:\\课程\\Redhat.Linux.9.i386.dvd.iso"), StandardOpenOption.READ);

             FileChannel writerChannel = FileChannel.open(Paths.get("E:\\课程\\copy.iso"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE)) {


            /**
             *
             *   第一个参数是操作的可读可写权限 总共有三个READ_ONLY--只写
             *                                          READ_WRITE--可读可写
             *                                          PRIVATE--当前线程的可读可写
             *
             * 第二个参数是起始位置
             *
             *
             * 第三个三参数是开辟的文件大小
             *         这里引用的是DirectByteBuffer  这里的是先通过unsafe.allocateMemory(起初的缓存大小)
             *                                                    unsafe.setMemory(设置缓存大小)
             *
             *
             *
             */


            MappedByteBuffer readMap = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());


            MappedByteBuffer writeMap = writerChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());

            //这里为了方便直接把可读的文件的全部限制
            byte[] bytes = new byte[readMap.limit()];


            //1 实际操作中不会s使用文件的大小

            //2 java或操作复制的文件

            ///3shifang ziyuan


            //从数据库获取
            readMap.get(bytes);

            //写入缓存
            writeMap.put(bytes);


            System.out.println("执行时间 ：" + (System.currentTimeMillis() - start) + "。ms");

        }
    }


    /**
     * 面向内存映射文件 channel+Map获取
     */

    public static void fileCopyByMemNIO() throws IOException {


        long start = System.currentTimeMillis();


//        try (FileChannel readChannel = new FileInputStream( new File("E:\\课程\\Redhat.Linux.9.i386.dvd.iso")).getChannel();
////
////
//             FileChannel writerChannel = new FileOutputStream(new File("E:\\课程\\copy.iso"), true).getChannel()) {

        try (FileChannel readChannel = new RandomAccessFile("E:\\课程\\Redhat.Linux.9.i386.dvd.iso", "r").getChannel();


             FileChannel writerChannel = new RandomAccessFile("E:\\课程\\copy.iso", "rw").getChannel()) {

            MappedByteBuffer readMap = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());

            MappedByteBuffer writeMap = writerChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());


            try {
                //这里因为是直接内存 考虑利用反射进行关闭直接内存的资源流 FileChannelImpl  还可以反射  Cleaner
                Method m = FileChannelImpl.class.getDeclaredMethod("unmap",
                        MappedByteBuffer.class);
                m.setAccessible(true);
                m.invoke(FileChannelImpl.class, writeMap);
            } catch (Exception e) {
                e.printStackTrace();
            }


            //------

            byte[] bytes = new byte[readMap.limit()];

            readMap.get(bytes);

            writeMap.put(bytes);


            //------


        }

    }


}
