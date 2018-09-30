package com.hanyh.code.controller.io;

import java.io.File;

public class FileDeno {
        //    exists() ： 判断文件夹是否存在，如果存在，则返回true
        //    delete() ： 删除文件夹，如果删除成功，则返回true
        //    mkdir() ： 创建文件夹
        //    mkdirs() ： 创建嵌套的文件夹
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\4301.txt");
        if (file.exists()) {
            System.out.println("存在");
        }
    }
}
