package com.hanyh.code.controller.io;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileStream {
    public static void main(String[] args) {
        fileInputStream("C:\\Users\\Administrator\\Desktop\\1.txt");

    }


    public static String fileInputStream(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }

        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            String str = "";
            for (int i = 0; i < inputStream.available(); i++) {
                str += (char) inputStream.read();
            }

            System.out.println(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if (null != inputStream) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;

    }
}
