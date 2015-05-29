package data;

import org.newdawn.slick.Animation;
import ui.SpriteView;

public class Player extends Sprite {
	private Inventory inventory;
	
	public Player(SpriteView sprite) {
		super(sprite);
	}
	public Player(Animation sprite) {
		super(sprite);
	}
	public Player(Animation up, Animation down, Animation left, Animation right) {
		super(up, down, left, right);
	}
}
