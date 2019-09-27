package com.game.luckySeven.objects;

import com.game.luckySeven.window.Game;
import com.game.luckySeven.window.MouseInput;

import java.awt.*;

public class Button {

	private int x, y;
	private int width, height;
	Font font;


	public Rectangle btnExit = new Rectangle(450, 450, 50, 30);
	public Rectangle btnRestart = new Rectangle(450, 400, 80, 30);
	public Rectangle btnSpin = new Rectangle(625, 400, 50, 30);


	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;


		font = new Font("arial", Font.BOLD, 20);
		g.setFont(font);
		g.drawString("Restart", btnRestart.x+5, btnRestart.y+20);
		g2d.draw(btnRestart);
		g.drawString("Spin", btnSpin.x+5, btnSpin.y+20);
		g2d.draw(btnSpin);
		g.drawString("Exit", btnExit.x+5, btnExit.y+20);
		g2d.draw(btnExit);

	}

}
