package com.inspur.learn.util;

import com.inspur.learn.vo.User;

import java.io.*;

/**
 * @Description:
 * @user: yangguang_lc
 * @Time: 2018/7/17  15:02
 */
public class VoSerial {


    // 一个对象的序列化
    public static byte[] serival(Object obj) {

        ByteArrayOutputStream bao = null;
        ObjectOutputStream oos = null;
        byte[] arr = null;
        try {
            bao = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bao);
            oos.writeObject(obj);
            arr = bao.toByteArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return arr;
    }

    // 对象的反序列化

    public static Object getObject(byte[] arr) {

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        Object obj = null;
        try {
            bis = new ByteArrayInputStream(arr);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return obj;
    }

    public static void main(String[] args) {
        User u1 = new User(21, "rondo");
        byte[] arr = serival(u1);
        System.out.println(arr.length);
        u1 = (User) getObject(arr);
        System.out.println(u1.getName());

    }


}
