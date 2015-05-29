package states;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ui.SpriteView;
import data.ObjectBatch;
import data.Player;
import data.Point;


public class HarvestFullMoon extends StateBasedGame {
	private List<BasicGameState> states;
	public Player player;
	public ObjectBatch globalObjects;
	
	public HarvestFullMoon(String name) {
		super(name);
		states = new ArrayList<BasicGameState>();
		globalObjects = new ObjectBatch();
	}
	
	private void createObjects() throws SlickException {
		int duration = 200;
	}
	
	private void createPlayer() throws SlickException {
		int duration = 200;
		Animation up = new Animation();
		//up.addFrame(new Image("res/spritetest/dot.png"), duration);
		up.addFrame(new Image("res/playersprite/up2.png"), duration);
		up.addFrame(new Image("res/playersprite/up1.png"), duration);
		up.addFrame(new Image("res/playersprite/up2.png"), duration);
		up.addFrame(new Image("res/playersprite/up3.png"), duration);
		up.setAutoUpdate(false);

		Animation down = new Animation();
		//down.addFrame(new Image("res/spritetest/dot.png"), duration);
		down.addFrame(new Image("res/playersprite/down2.png"), duration);
		down.addFrame(new Image("res/playersprite/down1.png"), duration);
		down.addFrame(new Image("res/playersprite/down2.png"), duration);
		down.addFrame(new Image("res/playersprite/down3.png"), duration);
		down.setAutoUpdate(false);

		Animation left = new Animation();
		//left.addFrame(new Image("res/spritetest/dot.png"), duration);
		left.addFrame(new Image("res/playersprite/left2.png"), duration);
		left.addFrame(new Image("res/playersprite/left1.png"), duration);
		left.addFrame(new Image("res/playersprite/left2.png"), duration);
		left.addFrame(new Image("res/playersprite/left3.png"), duration);
		left.setAutoUpdate(false);

		Animation right = new Animation();
		//right.addFrame(new Image("res/spritetest/dot.png"), duration);
		right.addFrame(new Image("res/playersprite/right2.png"), duration);
		right.addFrame(new Image("res/playersprite/right1.png"), duration);
		right.addFrame(new Image("res/playersprite/right2.png"), duration);
		right.addFrame(new Image("res/playersprite/right3.png"), duration);
		right.setAutoUpdate(false);
		
		player = new Player(up, down, left, right);
		player.setDirection(SpriteView.Direction.RIGHT);
    	//System.out.println("coords: "+x+", "+y);
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		createPlayer();
		
		addState(new MainGameState(this));
		addState(new InventoryState(this));
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer appgc = new AppGameContainer(new HarvestFullMoon("Harvest Full Moon"));
			appgc.setDisplayMode(640, 480, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException ex) {
			System.out.println("ERROR: "+ex);
		}
	}
}
