package com.bug1024.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

/**
 * @author wangyu
 * @date 2020-05-31
 */
public class ByteBufDemo {

    /**
     * ByteBuf有读readerIndex和写writerIndex两个指针，用来标记“可读”、“可写”、“可丢弃”的字节
     * 调用write*方法写入数据后，写指针将会向后移动
     * 调用read*方法读取数据后，读指针将会向后移动
     * 写入数据或读取数据时会检查是否有足够多的空间可以写入和是否有数据可以读取
     * 写入数据之前，会进行容量检查，当剩余可写的容量小于需要写入的容量时，需要执行扩容操作
     * 扩容时有一个4MB的阈值，需要扩容的容量小于阈值或大于阈值所对应的扩容逻辑不同
     * clear等修改读写指针的方法，只会更改读写指针位置的值，并不会影响ByteBuf中已有的内容
     * setZero等修改字节值的方法，只会修改对应字节的值，不会影响读写指针的值以及字节的可读写状态
     * @param args
     */
    public static void main(String[] args) {
        // 1.创建一个非池化的ByteBuf，大小为10个字节
        ByteBuf buf = Unpooled.buffer(10);
        System.out.println("原始ByteBuf为====================>" + buf.toString());
        System.out.println("1.ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

        // 2.写入一段内容
        byte[] bytes = {1, 2, 3, 4, 5};
        buf.writeBytes(bytes);
        System.out.println("写入的bytes为====================>" + Arrays.toString(bytes));
        System.out.println("写入一段内容后ByteBuf为===========>" + buf.toString());
        System.out.println("2.ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

        // 3.读取一段内容
        byte b1 = buf.readByte();
        byte b2 = buf.readByte();
        System.out.println("读取的bytes为====================>" + Arrays.toString(new byte[]{b1, b2}));
        System.out.println("读取一段内容后ByteBuf为===========>" + buf.toString());
        System.out.println("3.ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

        // 4.将读取的内容丢弃
        buf.discardReadBytes();
        System.out.println("将读取的内容丢弃后ByteBuf为(读写指针都向前移动)===>" + buf.toString());
        System.out.println("4.ByteBuf中的内容为(可读数据向前移动)===>" + Arrays.toString(buf.array()));
        buf.writeByte(9);
        buf.writeByte(9);
        System.out.println("再写入2个9后的ByteBuf为========>" + buf.toString());
        System.out.println("ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

        // 5.清空读写指针
        buf.clear();
        System.out.println("将读写指针清空后ByteBuf为==========>" + buf.toString());
        System.out.println("5.ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

        // 6.再次写入一段内容，比第一段内容少
        byte[] bytes2 = {1, 2, 3};
        buf.writeBytes(bytes2);
        System.out.println("写入的bytes为====================>" + Arrays.toString(bytes2));
        System.out.println("写入一段内容后ByteBuf为===========>" + buf.toString());
        System.out.println("6.ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

        // 7.将ByteBuf清零
        buf.setZero(0, buf.capacity());
        System.out.println("将内容清零后ByteBuf为==============>" + buf.toString());
        System.out.println("7.ByteBuf中的内容为================>" + Arrays.toString(buf.array()) + "\n");

        // 8.再次写入一段超过容量的内容
        byte[] bytes3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        buf.writeBytes(bytes3);
        System.out.println("写入的bytes为====================>" + Arrays.toString(bytes3));
        System.out.println("写入一段内容后ByteBuf为===========>" + buf.toString());
        System.out.println("8.ByteBuf中的内容为===============>" + Arrays.toString(buf.array()) + "\n");

    }
}
