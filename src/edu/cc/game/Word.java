package edu.cc.game;

import java.awt.Color;
import java.util.Random;
//单词类，用于包装一些变化的数据
public class Word {
	Random r = new Random();
	private String word;
	private int x_index;
	private int y_index;
	private Color color;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getX_index() {
		return x_index;
	}
	public void setX_index(int x_index) {
		this.x_index = x_index;
	}
	public int getY_index() {
		return y_index;
	}
	public void setY_index(int y_index) {
		this.y_index = y_index;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Word(String word, int x_index, int y_index) {
		super();
		this.word = word;
		this.x_index = x_index;
		this.y_index = y_index;
		this.color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	}
	public Word() {
		super();
	}
	@Override
	public String toString() {
		return "Word [word=" + word + ", x_index=" + x_index + ", y_index=" + y_index + ", color=" + color + "]";
	}
	

}
