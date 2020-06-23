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
//简易版打字游戏用于实验
public class MoveGame extends JFrame implements Runnable , KeyListener {
    private Random random = new Random();
    private Thread thread;
    //字母个数
    private final int DEFAULT_SIZE = 10;
    //各个值的x位置
    private ArrayList<Integer> x_index = new ArrayList<>();
    //y坐标位置
    private int y_index = 50;
    //存放String字母集
    private volatile ArrayList<String> numbers = new ArrayList<>();

    //构造函数
    public MoveGame() {
        super("打字游戏");
        //设置窗体内字体的样式
        setFont(new Font("Time New Roman",Font.BOLD,20));
        //启动事件监控
        addKeyListener(this);
        //初始化字母集
        init(DEFAULT_SIZE);
        //启动线程
        start();
        //设置窗体属性
        setSize(700, 600);
        setVisible(true);
        this.setLocation(440,330);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //生成随机大小写字母的方法
    private String getNumber(){
        int flag = random.nextInt(2);
        char str = 'A';
        String s = "";
        if(flag == 0){
            //大写字母
            s += (char)(str + random.nextInt(26));
        }else{
            //小写字母
            //char str = 'a';
            s += (char)(str + random.nextInt(26)+32);
        }
        return s;
    }
    /**
     * 初始化字母集及字母的x坐标
     * @param count 字母个数
     */
    public void init(int count){
        int x = 10;
        for(int i=0;i<count;i++) {
            numbers.add(getNumber());
            x_index.add(x);
            x = random.nextInt(30)+50+x;
        }
    }
    //开始进程
    private void start(){
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }
    //线程
    @Override
    public void run() {
        while(thread != null){
            //游戏成功
            if(numbers.size() == 0){
                stop();
                System.exit(0);
            }
            //游戏失败
            if(y_index == 600){
                stop();
                System.exit(0);
            }
            y_index += 10;
            //调用方法重绘界面
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //停止线程
    public void stop(){
        thread = null;
    }
    //绘图
    public void paint(Graphics g){
        Graphics2D G = (Graphics2D)g;
        //获取窗口大小
        Dimension dim = getSize();
        //设背景为白色
        g.setColor(Color.white);
        g.fillRect(0,0,dim.width,dim.height);
        //设置字体颜色
        g.setColor(Color.black);
        //画出字母
        for(int i=0;i<numbers.size();i++){
            //获取字母
            String ch = numbers.get(i);
            //获取x坐标
            int x = x_index.get(i);
            //写字母
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
	//主函数
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
