package edu.cc.test;


import java.awt.Graphics;

import javax.swing.JFrame;
//��Ϊ֮�¸�������������⣬����Ȥ�Ŀ����о��£���Ҳ��֪��Ϊʲô
//��ʾ�ν������ܲ��ɸĶ�����
public class PaintTest extends JFrame{

	@Override
	public void paint(Graphics g) {
		String str = "i love you";
		g.drawString(str, 50, 50);
	}
     public void launchJFrame() {
             this.setTitle("�ҵ���Ϸ");       
             this.setSize(8000, 8000);        
             this.setLocation(0, 0);
             this.setVisible(true);         
             this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     }
     public static void main(String args[]) {
    	 PaintTest game = new PaintTest();
                    game.launchJFrame();
             }}