package com.game.luckySeven.objects;

import com.game.luckySeven.framework.BufferedImageLoader;
import com.game.luckySeven.framework.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Slot {

	private int x, y;
	private int width, height;
	private int value;
	private Texture tex;
	private BufferedImageLoader loader;
	private BufferedImage image;


	public Slot(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		tex = new Texture();
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.drawRect(x, y, width, height);

		if (value == 0) g.drawImage(tex.slot_pirate, x, y, width, height, null);
		else if (value == 7) g.drawImage(tex.slot_seven, x, y, width, height, null);
		else g.drawImage(tex.slot_coin, x, y, width, height, null);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
