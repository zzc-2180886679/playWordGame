package edu.cc.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//工具类
public class Utils {
	private Word word;
	//从Word.txt返回所有单词集合
	public static List  readTxtFile(String filePath){
		List linesTxt = new ArrayList<String>();
		InputStreamReader read = null ;
		BufferedReader bufferedReader = null;
		try {//设置读取格式
                String encoding="GBK";
                //建立读取文件
                File file=new File(filePath);
                //建立读取通道
                     read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
                     //读取操作
                     bufferedReader = new BufferedReader(read);
                    String linetxt = null;
                    //每次读取一行
                    while((linetxt = bufferedReader.readLine()) != null){
                       linesTxt.add(linetxt);
                    }
                    read.close();
        }catch (Exception e) {
			System.out.println("读取weord.txt出错");
		}
		return  linesTxt;
    }
	//从读取的Arraylist里取出Word对象
	public  Word getWords() {
		Random r = new Random();
		//利用System对象获取Word.TXT的路径
    	String path = System.getProperty("user.dir")+"\\word.txt";
    	//取出单词作为组装Word对象。x坐标和y坐标随机生成
    	Word word = new Word((String) readTxtFile(path).get(r.nextInt(160)),r.nextInt(500)+20,45);
		return word;
    }

}
