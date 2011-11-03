import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.jws.Oneway;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Orc extends JPanel {
	private int xPos, yPos, ballW, ballH, panelW, panelH, speed;
	private String currentDirection = "";
	private Timer timer, timer2;
	private boolean turbo = false, turboText = false;
	JLabel turboLabel;
	
	/*
	 * Main, orc
	 * */
	public Orc() {
		// Variabler
		panelW = 300;
		panelH = 300;
		ballW = 20;
		ballH = 20;
		xPos = 6;
		yPos = (panelH / 2) - (ballW / 2);
		speed = 1;

		// Lyssnare av tangenttryckningar
		addKeyListener(new DirectionListener());

		// Timer
		timer = new Timer(10, new TimerListener());
		timer.start();
		timer2 = new Timer(300, new TimerListener());
		timer2.start();

		// Turbo-mode-label
		turboLabel = new JLabel("TURBO-MODE");
		turboLabel.setForeground(Color.red);
		turboLabel.setFont(new Font("Impact", Font.PLAIN, 25));
		add(turboLabel);

		// Allm�nna inst�llningar f�r panelen
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(panelW, panelH));
		setFocusable(true);
	}

	/* 
	 * Paint Component
	 * Ritar upp panelen och dess inneh�ll
	 * */
	public void paintComponent(Graphics page) {
		// Anropas f�rst f�r att det ska ritas n�got
		super.paintComponent(page);

		// Bollen
		page.setColor(Color.yellow);// f�rg
		page.fillOval(xPos, yPos, ballW, ballH);// form
		turboLabel.setVisible(turboText);
		
		// V�ggarna
		page.setColor(Color.blue);
		page.fillRect(0, 0, 5, panelH);
		page.fillRect(0, 0, panelW, 5);
		page.fillRect(0, panelH-5, panelW, 5);
		page.fillRect(panelW-5, 0, 5, panelH);

	}

	// Lyssnar p� tangenttryckningar
	private class DirectionListener implements KeyListener {

		// S�tter currentDirection �t det h�ll som anv�ndaren trycker
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				currentDirection = "up";
				break;

			case KeyEvent.VK_DOWN:
				currentDirection = "down";
				break;

			case KeyEvent.VK_LEFT:
				currentDirection = "left";
				break;

			case KeyEvent.VK_RIGHT:
				currentDirection = "right";
				break;

			// �kar och s�nker farten med space
			case KeyEvent.VK_SPACE:
				if (!turbo) {
					speed = 3;
					turbo = true;
					turboText = true;

				} else if (turbo) {
					speed = 1;
					turbo = false;
					turboText = false;
				}
				break;

			// Stannar bollen med ctrl
			case KeyEvent.VK_CONTROL:
				currentDirection = "";
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	}// keylistener

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ((Timer) e.getSource() == timer) {

				// Avg�r �t vilket h�ll bollen r�r sig
				if (currentDirection.equals("right")) {
					if (xPos>=(panelW-ballW-5))
						xPos=xPos;
					else
					xPos += speed;
				} else if (currentDirection.equals("left")) {
					if (xPos<=(5))
						xPos=xPos;
					else
					xPos -= speed;
				} else if (currentDirection.equals("up")) {
					if (yPos<=(5))
						yPos=yPos;
					else
					yPos -= speed;
				} else if (currentDirection.equals("down")) {
					if (yPos>=(panelH-ballH-5))
						yPos=yPos;
					else
					yPos += speed;
				}
			} else if ((Timer) e.getSource() == timer2) {
				if (turbo) {
					if (turboText)
						turboText = false;
					else
						turboText = true;
				}

			}

			repaint();
		}
	}// actionlistener

}
