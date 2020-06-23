package edu.cc.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//������
public class Utils {
	private Word word;
	//��Word.txt�������е��ʼ���
	public static List  readTxtFile(String filePath){
		List linesTxt = new ArrayList<String>();
		InputStreamReader read = null ;
		BufferedReader bufferedReader = null;
		try {//���ö�ȡ��ʽ
                String encoding="GBK";
                //������ȡ�ļ�
                File file=new File(filePath);
                //������ȡͨ��
                     read = new InputStreamReader(new FileInputStream(file),encoding);//���ǵ������ʽ
                     //��ȡ����
                     bufferedReader = new BufferedReader(read);
                    String linetxt = null;
                    //ÿ�ζ�ȡһ��
                    while((linetxt = bufferedReader.readLine()) != null){
                       linesTxt.add(linetxt);
                    }
                    read.close();
        }catch (Exception e) {
			System.out.println("��ȡweord.txt����");
		}
		return  linesTxt;
    }
	//�Ӷ�ȡ��Arraylist��ȡ��Word����
	public  Word getWords() {
		Random r = new Random();
		//����System�����ȡWord.TXT��·��
    	String path = System.getProperty("user.dir")+"\\word.txt";
    	//ȡ��������Ϊ��װWord����x�����y�����������
    	Word word = new Word((String) readTxtFile(path).get(r.nextInt(160)),r.nextInt(500)+20,45);
		return word;
    }

}
