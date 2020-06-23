package edu.cc.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.xml.stream.events.StartDocument;

import com.sun.org.apache.bcel.internal.classfile.Field;

import sun.rmi.runtime.NewThreadAction;

public class PlayGame extends JFrame {
	private static Utils util = new Utils();
	String srt = null;//����ʱ�������ռ���������ַ���
	Container container;//contain���ڰѴ��ڸ���Ϊ��ɫ
    JTextField jTextField;//���������
	BorderLayout bl ;//����
	static Word word;//����getword������Word����
	static ArrayList<Word> listwords = new ArrayList<Word>();
	public  void  initGame() {
		this.setTitle("������Ϸ");
		//����JFrameʹ����ʧ���֣�����ֱ�Ӹ�JFrame˭֪�ı���ɫ�ᱻ���ֹ��������ǣ����ʹ��һ���������Ǵ���
		 container = this.getContentPane();
		 this.setSize(600, 900);
		 this.setLocation(200, 100);
		 bl = new BorderLayout();
		 this.setLayout(bl);
		 jTextField = new JTextField(12);
		 container.add(jTextField);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 container.setBackground(Color.BLACK);
		 this.add(BorderLayout.SOUTH,jTextField);
		 this.setVisible(true);
		 wordlisten();//�������뵥��
		 //�½��߳�����ʵ���ƶ������ƶ�
		 new Thread() {
			@Override
			public void run() {
				while(true) {
					addword();
					removeword();
					repaint();
					try {
						//�����ػ��ͣ��1��
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	//����jTextField����
    private void wordlisten() {
    	jTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				srt = jTextField.getText();
				//����listWords���������ĵ���һ���Ͱ���������Ƴ�
				for(int i = 0;i<listwords.size();i++) {
				if(srt.equals(listwords.get(i).getWord())) {
					listwords.remove(i);
					jTextField.setText("");
					break;
				}
			}
			}
		});
	}
    //�ƶ����ʡ�ʵ�֣�ͨ���ı䵥�ʵ�y ����ʵ��
	protected void removeword() {
    	 for (int i=0;i<listwords.size();i++){
             int y = listwords.get(i).getY_index();
             //ÿ���ƶ�15 ����
             y += 15;
             listwords.get(i).setY_index(y);
         }
	}
	//�ѵ��ʶ�����ӵ�listwords
	protected void addword() {
    		word =  util.getWords();
    		listwords.add(word);
	}
	//�̵߳���repaint ����ʱϵͳ�Զ�����paint����
	@Override
	public void paint (Graphics g) {
    	super.paint(g);
    	for(int i =0;i<listwords.size();i++) {
    	Word word = listwords.get(i);
    	g.setColor(word.getColor());
        g.setFont(new Font("����", Font.BOLD, 20));
		g.drawString(word.getWord(),word.getX_index(),word.getY_index());
    	}
	}
	public static void main(String[] args){
		PlayGame playgame = new PlayGame();
		playgame.initGame();
	}
}
