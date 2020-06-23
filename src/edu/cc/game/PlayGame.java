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
	String srt = null;//监听时用来接收键盘输入的字符串
	Container container;//contain用于把窗口覆盖为黑色
    JTextField jTextField;//键盘输入框
	BorderLayout bl ;//布局
	static Word word;//接收getword传来的Word对象
	static ArrayList<Word> listwords = new ArrayList<Word>();
	public  void  initGame() {
		this.setTitle("打字游戏");
		//由于JFrame使用流失布局，所以直接给JFrame谁知的背景色会被布局管理器覆盖，因此使用一个容器覆盖窗体
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
		 wordlisten();//监听输入单词
		 //新建线程用来实现移动功能移动
		 new Thread() {
			@Override
			public void run() {
				while(true) {
					addword();
					removeword();
					repaint();
					try {
						//两次重绘间停顿1秒
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	//监听jTextField输入
    private void wordlisten() {
    	jTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				srt = jTextField.getText();
				//遍历listWords。如果输入的单词一样就把这个单词移除
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
    //移动单词。实现：通过改变单词的y 坐标实现
	protected void removeword() {
    	 for (int i=0;i<listwords.size();i++){
             int y = listwords.get(i).getY_index();
             //每次移动15 像素
             y += 15;
             listwords.get(i).setY_index(y);
         }
	}
	//把单词对象添加到listwords
	protected void addword() {
    		word =  util.getWords();
    		listwords.add(word);
	}
	//线程调用repaint 方法时系统自动调用paint方法
	@Override
	public void paint (Graphics g) {
    	super.paint(g);
    	for(int i =0;i<listwords.size();i++) {
    	Word word = listwords.get(i);
    	g.setColor(word.getColor());
        g.setFont(new Font("楷体", Font.BOLD, 20));
		g.drawString(word.getWord(),word.getX_index(),word.getY_index());
    	}
	}
	public static void main(String[] args){
		PlayGame playgame = new PlayGame();
		playgame.initGame();
	}
}
