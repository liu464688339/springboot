package com.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileUtils {
	/**
	 * 写文件，存在就覆盖
	 * @param filePath
	 * @param content
	 */
	public static void writeFile(String filePath, String content) {  
        try {  
            File f = new File(filePath);  
            if (f.exists()) {  
                System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在创建");  
                f.createNewFile();// 不存在则创建  
            }  
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
            output.write(content);  
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }
	
	/**
	 * 写文件，存在就追加
	 * @param filePath
	 * @param content
	 */
	public static void appendFile(String filePath, String content) {  
        String str = new String(); //原有txt内容  
        String s1 = new String();//内容更新  
        try {  
            File f = new File(filePath);  
            if (f.exists()) {  
                System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在创建");  
                f.createNewFile();// 不存在则创建  
            }  
            BufferedReader input = new BufferedReader(new FileReader(f));  
  
            while ((str = input.readLine()) != null) {  
                s1 += str + "\n";  
            }  
            //System.out.println(s1);  
            input.close();  
            s1 += content;  
  
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
            output.write(s1);  
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }
	
	/**   
     * 追加文件：使用RandomAccessFile   
     *    
     * @param fileName 文件名   
     * @param content 追加的内容   
     */    
    public static void randomAccessAppendFile(String fileName, String content) {   
        RandomAccessFile randomFile = null;  
      
        try {     
            // 打开一个随机访问文件流，按读写方式     
            randomFile = new RandomAccessFile(fileName, "rw");   
            // 文件长度，字节数     
            long fileLength = randomFile.length();     
            // 将写文件指针移到文件尾。     
            randomFile.seek(fileLength);     
            randomFile.write(content.getBytes());      
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally{  
        	
            if(randomFile != null){  
                try {     
                    randomFile.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }    


    /**
     * 写文件，存在就追加
     * @param filePath
     * @param content
     */
    public static String getFile(String filePath) {
        String str = new String(); //原有txt内容
        String s1 = new String();//内容更新
        StringBuffer buf = new StringBuffer();
        try {
            File f = new File(filePath);
            if (f.exists()) {
                System.out.print("文件存在");
            } else {
                System.out.print("文件不存在创建");
                f.createNewFile();// 不存在则创建
            }
            BufferedReader input = new BufferedReader(new FileReader(f));

            while ((str = input.readLine()) != null) {
               buf.append(str + "\n");
            }
            System.out.println(s1);
            input.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return  buf.toString();
    }
}
