package com.game.luckySeven.window;

import com.game.luckySeven.objects.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private int sumOfCoins = 0;
	private int numOfAttempts = 0;
	private Game game = Game.getInstanceGame();
	private Button buttons = Game.getInstanceButtons();


	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (mx >= 625 && mx <= 675) {
			if (my >= 400 && my <= 430) {
				//pressed spin
				System.out.println("SPIN pressed");
				game.spinSlots();
				numOfAttempts++;

				if (game.slot1.getValue() + game.slot2.getValue() + game.slot3.getValue() == 0) {
					System.out.println("LOST ALL COINS, GAME CONTINUES");
					sumOfCoins = 0;
				} else if (game.slot1.getValue() + game.slot2.getValue() + game.slot3.getValue() == 21) {
					System.out.println("GAME OVER, MONEY EARNED: " + sumOfCoins);
					sumOfCoins = 0;
					numOfAttempts = 0;
					System.exit(1);
				} else {
					if(game.slot1.getValue() != 0 && game.slot1.getValue() != 7) sumOfCoins++;
					if(game.slot2.getValue() != 0 && game.slot2.getValue() != 7) sumOfCoins++;
					if(game.slot3.getValue() != 0 && game.slot3.getValue() != 7) sumOfCoins++;
				}

			}
		}

		if (mx >= 450 && mx <= 530) {
			if (my >= 400 && my <= 430) {
				//pressed restart
				System.out.println("RESTART pressed");
				sumOfCoins = 0;
				numOfAttempts = 0;
			}
		}
		
		if (mx >= 450 && mx <= 500) {
			if (my >= 450 && my <= 480) {
				//pressed quit
				System.out.println("EXIT pressed");
				System.exit(1);
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public int getSumOfCoins() {
		return sumOfCoins;
	}

	public void setSumOfCoins(int sumOfCoins) {
		this.sumOfCoins = sumOfCoins;
	}

	public int getNumOfAttempts() {
		return numOfAttempts;
	}

	public void setNumOfAttempts(int numOfAttempts) {
		this.numOfAttempts = numOfAttempts;
	}
}
