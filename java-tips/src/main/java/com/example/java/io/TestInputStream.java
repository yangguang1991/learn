package com.example.java.io;

import java.io.*;

/**
 * @author Yangguang
 * @Title: TestInputStream
 * @ProjectName learn
 * @Description: TODO
 * @date 2019/10/24 11:11
 * @Version 1.0.0
 */
public class TestInputStream {


    /**
     * 测试一些IO的类
     */
    public static void method1() {

        String path = "F:\\excel\\sqoop.txt";
        File file = new File(path);// 针对磁盘的操作


        byte[] bytesArray = new byte[1024];
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("F:\\excel\\test.txt");
            FileInputStream fileInputStream = new FileInputStream(file);// 针对字节流的操作
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            int length = 0;
            while ((length = bufferedInputStream.read(bytesArray)) != -1) {
                System.out.println(new String(bytesArray,0,length, "UTF-8"));
                fileOutputStream.write(bytesArray,0,length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        method1();

    }


}
