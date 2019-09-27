package com.game.luckySeven.window;

import com.game.luckySeven.objects.Button;
import com.game.luckySeven.objects.Slot;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 800;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final String TITLE = "Lucky7";

	private static Game game = new Game();
	private Thread thread;
	private boolean running = false;
	public Slot slot1, slot2, slot3;
	private static Button buttons;
	private static MouseInput mouseInput = new MouseInput();
	private Random random = new Random();


	public void init() {

		slot1 = new Slot(125, 175, 150, 150);
		slot2 = new Slot(325, 175, 150, 150);
		slot3 = new Slot(525, 175, 150, 150);

		buttons = new Button();

		this.addMouseListener(mouseInput);
		
		spinSlots();

	}

	public synchronized void start() {
		if (running) {
			return;
		}
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		if (! running) {
			return;
		}
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;                                            // ticks (updates) set to 60 per second
		double ns = 1000000000 / amountOfTicks;                                    // , not same as rendering (fps)
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	public void tick() {
		
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//////////////////////////////////////////////////////// DRAW

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		Font font;

		font = new Font("arial", Font.BOLD, 50);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("LUCKY 7", Game.WIDTH / 2 - 100, 100);

		font = new Font("arial", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Coins: " + mouseInput.getSumOfCoins(), 125, 400);

		font = new Font("arial", Font.BOLD, 15);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Attempts: " + mouseInput.getNumOfAttempts(), 125, 430);

		slot1.render(g);
		slot2.render(g);
		slot3.render(g);
		buttons.render(g);


		//////////////////////////////////////////////////////// END DRAW
		g.dispose();
		bs.show();
	}

	public static MouseInput getInstance() {
		return mouseInput;
	}

	public static Game getInstanceGame() {
		return game;
	}
	
	public static Button getInstanceButtons(){
		return buttons;
	}

	public void spinSlots() {
		slot1.setValue(random.nextInt(8));
		slot2.setValue(random.nextInt(8));
		slot3.setValue(random.nextInt(8));
	}

	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, TITLE, game);
	}


}
