package com.game.luckySeven.framework;

import java.awt.image.BufferedImage;

public class Texture {
	
	public BufferedImage slot_pirate = null;
	public BufferedImage slot_coin = null;
	public BufferedImage slot_seven = null;
	
	
	public Texture(){
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			slot_seven = loader.grabImage("/slot_seven.png");
			slot_coin = loader.grabImage("/slot_coin.png");
			slot_pirate = loader.grabImage("/slot_pirate.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
