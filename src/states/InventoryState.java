package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import data.Player;

public class InventoryState extends BasicGameState {
	public static final int ID = 2;
	private final HarvestFullMoon sbg;
	private final Player player;
	
	public InventoryState(HarvestFullMoon sbg) {
		this.sbg = sbg;
		player = sbg.player;
	}
	
	public void keyReleased(int key, char c) {
		if (key == Input.KEY_I) {
			sbg.enterState(MainGameState.ID);
		}
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
