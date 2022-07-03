package com.harley.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindAndDelete {
    public static void main(String[] args) throws IOException {
        String rootPath = "/Users/harley/Desktop/code/FileTest";
        String condition = ".txt";
        List<File> resultList = new ArrayList<>();// 用于存放符合条件的文件
        //遍历整棵树，找到符合天条件的所有文件，并将符合条件的文件放入resultFile
        File rootFile = new File(rootPath);
        traversal(rootFile, condition, resultList);

        // 一次针对每个结果，询问用户是否要删除
        for(File file : resultList) {
            System.out.println("是否要删除文件：" + file.getCanonicalPath());
            Scanner scanner = new Scanner(System.in);
            if(!scanner.hasNextBoolean()) {
                System.out.println("退出");
                return;
            }
            boolean isDelete = scanner.nextBoolean();
            if(isDelete) {
                System.out.println("文件删除" + file.delete());
            }

//            String choice = scanner.nextLine();
//            while(true) {
//                if(choice.equals("true")) {
//                    System.out.println(file.delete());
//                    break;
//                }else if (choice.equals("false")) {
//                    break;
//                }else {
//                    System.out.println("输入有误，请重新输入！");
//                }
//            }
        }
    }

    private static void traversal(File rootFile, String condition, List<File> resultList) {
        File[] files = rootFile.listFiles();
        if(files == null) {
            return;
        }

        for(File file : files) {
            if(file.isDirectory()) {
                traversal(file, condition, resultList);
            }else if(file.isFile()) {
                // 判断文件是否满足条件：是否已xxx结尾
                String name = file.getName();;
                if(name.endsWith(condition)) {
                    resultList.add(file);
                }
            }
        }
    }
}
