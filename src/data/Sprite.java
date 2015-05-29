package data;

import org.newdawn.slick.Animation;

import ui.SpriteView;

public class Sprite implements Comparable<Sprite> {
	public int xBlock, yBlock;
	private SpriteView sprite;
	
	public Sprite(SpriteView sprite) {
		this.sprite = sprite;
	}
	public Sprite(Animation sprite) {
		this.sprite = new SpriteView(sprite);
	}
	public Sprite(Animation up, Animation down, Animation left, Animation right) {
		sprite = new SpriteView(up, down, left, right);
	}
	
	public void update(int delta) {
		sprite.update(delta);
	}
	
	public Animation getSprite() {
		return sprite.getSprite();
	}
	public SpriteView getSpriteView() {
		return sprite;
	}
	public void setSprite(SpriteView sprite) {
		this.sprite = sprite;
	}
	
	public float getX() {
		return sprite.x;
	}
	public float getY() {
		return sprite.y;
	}
	public void setX(float x) {
		sprite.x = x;
	}
	public void setY(float y) {
		sprite.y = y;
	}
	
	public void setUp(Animation up) {
		sprite.setUp(up);
	}
	public void setDown(Animation down) {
		sprite.setDown(down);
	}
	public void setLeft(Animation left) {
		sprite.setLeft(left);
	}
	public void setRight(Animation right) {
		sprite.setRight(right);
	}
	public void setDirection(SpriteView.Direction d) {
		sprite.setDirection(d);
	}
	
	public Point getDrawPoint(float x, float y) {
		return sprite.getDrawPoint(x, y);
	}
	
	@Override
	public int compareTo(Sprite s) {
		return yBlock - s.yBlock;
	}
}
