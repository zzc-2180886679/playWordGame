package edu.cc.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
//���װ������Ϸ����ʵ��
public class MoveGame extends JFrame implements Runnable , KeyListener {
    private Random random = new Random();
    private Thread thread;
    //��ĸ����
    private final int DEFAULT_SIZE = 10;
    //����ֵ��xλ��
    private ArrayList<Integer> x_index = new ArrayList<>();
    //y����λ��
    private int y_index = 50;
    //���String��ĸ��
    private volatile ArrayList<String> numbers = new ArrayList<>();

    //���캯��
    public MoveGame() {
        super("������Ϸ");
        //���ô������������ʽ
        setFont(new Font("Time New Roman",Font.BOLD,20));
        //�����¼����
        addKeyListener(this);
        //��ʼ����ĸ��
        init(DEFAULT_SIZE);
        //�����߳�
        start();
        //���ô�������
        setSize(700, 600);
        setVisible(true);
        this.setLocation(440,330);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //���������Сд��ĸ�ķ���
    private String getNumber(){
        int flag = random.nextInt(2);
        char str = 'A';
        String s = "";
        if(flag == 0){
            //��д��ĸ
            s += (char)(str + random.nextInt(26));
        }else{
            //Сд��ĸ
            //char str = 'a';
            s += (char)(str + random.nextInt(26)+32);
        }
        return s;
    }
    /**
     * ��ʼ����ĸ������ĸ��x����
     * @param count ��ĸ����
     */
    public void init(int count){
        int x = 10;
        for(int i=0;i<count;i++) {
            numbers.add(getNumber());
            x_index.add(x);
            x = random.nextInt(30)+50+x;
        }
    }
    //��ʼ����
    private void start(){
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }
    //�߳�
    @Override
    public void run() {
        while(thread != null){
            //��Ϸ�ɹ�
            if(numbers.size() == 0){
                stop();
                System.exit(0);
            }
            //��Ϸʧ��
            if(y_index == 600){
                stop();
                System.exit(0);
            }
            y_index += 10;
            //���÷����ػ����
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //ֹͣ�߳�
    public void stop(){
        thread = null;
    }
    //��ͼ
    public void paint(Graphics g){
        Graphics2D G = (Graphics2D)g;
        //��ȡ���ڴ�С
        Dimension dim = getSize();
        //�豳��Ϊ��ɫ
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width,dim.height);
        //����������ɫ
        g.setColor(Color.black);
        //������ĸ
        for(int i=0;i<numbers.size();i++){
            //��ȡ��ĸ
            String ch = numbers.get(i);
            //��ȡx����
            int x = x_index.get(i);
            //д��ĸ
            g.drawString(ch,x,y_index);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        for(String str:numbers){
            char[] chars = str.toCharArray();
            char ch = chars[0];
            if(ch == c){
                numbers.remove(str);
                break;
            }
        }
    }
	//������
    public static void main(String[] args) {
        MoveGame moveGame = new MoveGame();
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
