package com.example.java.io.seria;

import java.io.*;

public class TestExternalize {


    public static void main(String[] args) {

        String filePath = "F:\\test\\a.txt";

        Person person = new Person();
        person.setAge("321");
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(new File(filePath));
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(person);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
