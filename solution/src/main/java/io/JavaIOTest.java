package io;

import javafx.beans.binding.StringBinding;
import pojo.SNode;

import java.io.*;

/**
 * Created by god on 2019/4/26.
 */
public class JavaIOTest {

    public static  void main(String[] args){
        SNode d = new SNode();
        SNode d2 = new SNode();

        StringBuilder builder;
        StringBuffer buffer;
        if(d == d2){

        }

        //字节流
        try {
            FileInputStream in = new FileInputStream("");
            int i =in.read();
            byte[] bytes = new byte[1024];
            int size = in.read(bytes);//返回字节数
            FileOutputStream out = new FileOutputStream("");
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //字符流
        try{
            FileReader reader = new FileReader("");
           char ch = (char) reader.read();
           char[] chs = new char[32];
            int c =reader.read(chs);
        }catch (Exception e){


        }




    }


}
