package dev.CodeWizz.Luqara;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.GameContainer;

public class Camera {

	public float x, y, velX, velY;
	
	public Camera() {
		this.x = 0;
		this.y = 0;
		this.velX = 0;
		this.velY = 0;
	}
	
	public void update(GameContainer gc) {
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			this.velX = -5;
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			this.velX = 5;
		}

		if(gc.getInput().isKey(KeyEvent.VK_W)) {
			this.velY = -5;
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_S)) {
			this.velY = 5;
		}
		
		////////////////////////////////////////
		
		if(!gc.getInput().isKey(KeyEvent.VK_A) && !gc.getInput().isKey(KeyEvent.VK_D)) {
			this.velX = 0;
		}
		
		if(!gc.getInput().isKey(KeyEvent.VK_W) && !gc.getInput().isKey(KeyEvent.VK_S)) {
			this.velY = 0;
		}
		
		x+=velX;
		y+=velY;
	}
}
