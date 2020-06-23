package edu.cc.test;


import java.awt.Graphics;

import javax.swing.JFrame;
//以为之下搞出个截屏的玩意，感兴趣的可以研究下，我也不知道为什么
//提示次截屏功能不可改动代码
public class PaintTest extends JFrame{

	@Override
	public void paint(Graphics g) {
		String str = "i love you";
		g.drawString(str, 50, 50);
	}
     public void launchJFrame() {
             this.setTitle("我的游戏");       
             this.setSize(8000, 8000);        
             this.setLocation(0, 0);
             this.setVisible(true);         
             this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     }
     public static void main(String args[]) {
    	 PaintTest game = new PaintTest();
                    game.launchJFrame();
             }}