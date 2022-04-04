package dev.CodeWizz.Luqara;

import dev.CodeWizz.Luqara.generation.World;
import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.hud.Button;
import dev.CodeWizz.engine.hud.DropDown;
import dev.CodeWizz.engine.hud.IDropDownListener;
import dev.CodeWizz.engine.hud.ISliderListener;
import dev.CodeWizz.engine.hud.Slider;
import dev.CodeWizz.engine.util.WNoise;

public class Generation extends AbstractGame implements ISliderListener, IDropDownListener {

	
	public static Generation inst;
	
	public static boolean lines = true;
	public static boolean debug = false;
	public static boolean perChunkBiomes = false;
	
	public static int amplifier = 10;
	public static int moveY = 0;
	public static World world;
	public static Camera cam;
	
	public Generation() {
		inst = this;
		world = new World();
		cam = new Camera();
	}
	
	@Override
	public void init(GameContainer gc) {
		Slider slid = (Slider) gc.gethMan().addComponent(new Slider(20, 20, 100, this));
		slid.setValue(10 + 20);
		
		gc.gethMan().addComponent(new DropDown(140, 20, 100, 2, this, "Normal", "Debug"));
		gc.gethMan().addComponent(new DropDown(260, 20, 100, 2, this, "Lines", "Tiles"));
		gc.gethMan().addComponent(new DropDown(380, 20, 100, 2, this, "Per chunk biomes", "Leveling Noise"));
		
		gc.gethMan().addComponent(new Button(20, 40, 100, 20, "Generate!") {
			
			@Override
			public void click(GameContainer gc) {
				world.noise = new WNoise();
				world.generate();
			}
			
			
		});
	
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		cam.update(gc);
	}
	
	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}
	
	

	@Override
	public void render(GameContainer gc, Renderer r) {
		world.render(gc, r);
	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Generation());
		gc.start();
	}

	@Override
	public void onSliderSet(float value) {
		amplifier = (int) value / 2;
		world.generate();
	}

	@Override
	public void onSliderMove(float value) {
		amplifier = (int) value / 2;
		world.generate();
	}

	@Override
	public void onDropDownSet(int i, String string) {
		if(string.equalsIgnoreCase("Lines")) {
			lines = true;
		} else if(string.equalsIgnoreCase("Tiles")) {
			lines = false;
		}
		
		if(string.equalsIgnoreCase("Normal")) {
			debug = false;
		} else if(string.equalsIgnoreCase("Debug")) {
			debug = true;
		}
	}
}
