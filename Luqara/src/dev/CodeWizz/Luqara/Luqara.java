package dev.CodeWizz.Luqara;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Luqara extends AbstractGame {

	
	public static Luqara inst;
	
	
	public Luqara() {
		inst = this;
	}
	
	@Override
	public void init(GameContainer gc) {

	}
	
	@Override
	public void update(GameContainer gc, float dt) {

	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}
	
	

	@Override
	public void render(GameContainer gc, Renderer r) {

	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Luqara());
		gc.start();
	}
}
