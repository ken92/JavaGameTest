package states;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ui.SpriteView;
import data.MapData;
import data.ObjectBatch;
import data.Player;
import data.Point;

public class MainGameState extends BasicGameState {
	public static final int ID = 1;
	private final Player player;
	private ObjectBatch objects;
	private final HarvestFullMoon sbg;
	
	// for movement animation
	private SpriteView.Direction dir;
	private boolean moveInProgress = false;
	private Point destination;
	
	private MapData mapData;

	public MainGameState(HarvestFullMoon sbg) {
		this.sbg = sbg;
		player = sbg.player;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		mapData = new MapData("res/crapmap.tmx");
		objects = new ObjectBatch();

		dir = SpriteView.Direction.RIGHT;
		Point[] startPos = mapData.getSpriteStart();
		Point blockCoords = startPos[0];
		Point coords = startPos[1];
		player.setX(coords.x); player.setY(coords.y);
		player.xBlock = blockCoords.x; player.yBlock = blockCoords.y;
		mapData.setSprite(player);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g)
			throws SlickException {
		mapData.renderMap();
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_I) {
			sbg.enterState(InventoryState.ID);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		if (moveInProgress) {
			if (dir == SpriteView.Direction.UP || dir == SpriteView.Direction.DOWN) {
				if (dir == SpriteView.Direction.UP && player.getY() > destination.y)
					player.setY(player.getY() - delta * .2f);
				else if (dir == SpriteView.Direction.DOWN && player.getY() < destination.y)
					player.setY(player.getY() + delta * .2f);
				else
					moveInProgress = false;
			}
			else if (dir == SpriteView.Direction.LEFT || dir == SpriteView.Direction.RIGHT) {
				if (dir == SpriteView.Direction.LEFT && player.getX() > destination.x)
					player.setX(player.getX() - delta * .2f);
				else if (dir == SpriteView.Direction.RIGHT && player.getX() < destination.x)
					player.setX(player.getX() + delta * .2f);
				else
					moveInProgress = false;
			}
			player.update(delta);
		}
		else
			playerReadInput(container, delta);
		
	}

	@Override
	public int getID() {
		return ID;
	}

	private void readyPlayerForMove(int delta, SpriteView.Direction d) {
		player.update(delta);
		dir = d;
		moveInProgress = true;
		if (d == SpriteView.Direction.UP)
			player.yBlock--;
		else if (d == SpriteView.Direction.DOWN)
			player.yBlock++;
		else if (d == SpriteView.Direction.LEFT)
			player.xBlock--;
		else if (d == SpriteView.Direction.RIGHT)
			player.xBlock++;
		destination = mapData.getCenterCoords(player.xBlock, player.yBlock);
	}
	
	private void playerReadInput(GameContainer container, int delta) {
		// The lower the delta the slower the sprite will animate.
		Input input = container.getInput();
		if (input.isKeyPressed(Input.KEY_UP)) {
			player.setDirection(SpriteView.Direction.UP);
			if (mapData.validBlock(player.xBlock, player.yBlock-1) && !mapData.isBlockBlocked(player.xBlock, player.yBlock-1))
				readyPlayerForMove(delta, SpriteView.Direction.UP);
		}
		else if (input.isKeyPressed(Input.KEY_DOWN)) {
			player.setDirection(SpriteView.Direction.DOWN);
			if (mapData.validBlock(player.xBlock, player.yBlock+1) && !mapData.isBlockBlocked(player.xBlock, player.yBlock+1))
				readyPlayerForMove(delta, SpriteView.Direction.DOWN);
		}
		else if (input.isKeyPressed(Input.KEY_LEFT)) {
			player.setDirection(SpriteView.Direction.LEFT);
			if (mapData.validBlock(player.xBlock-1, player.yBlock) && !mapData.isBlockBlocked(player.xBlock-1, player.yBlock))
				readyPlayerForMove(delta, SpriteView.Direction.LEFT);
		}
		else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			player.setDirection(SpriteView.Direction.RIGHT);
			if (mapData.validBlock(player.xBlock+1, player.yBlock) && !mapData.isBlockBlocked(player.xBlock+1, player.yBlock))
				readyPlayerForMove(delta, SpriteView.Direction.RIGHT);
		}
	}
}