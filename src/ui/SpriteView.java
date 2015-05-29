package ui;

import org.newdawn.slick.Animation;

import data.Point;

public class SpriteView {
	public enum Direction {UP, DOWN, LEFT, RIGHT};
	protected Animation sprite;
	private Animation up, down, left, right;
	public float x, y;
	public final int width, height;
	
	public SpriteView(Animation sprite) {
		this.sprite = sprite;
		up = sprite;
		down = sprite;
		left = sprite;
		right = sprite;
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	public SpriteView(Animation up, Animation down, Animation left, Animation right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		sprite = right;
		width = sprite.getWidth();
		height = sprite.getHeight();
	}

	public void update(int delta) {
		sprite.update(delta);
	}
	public void draw(int x, int y) {
		sprite.draw(x, y);
	}
	
	public Animation getSprite() {
		return sprite;
	}
	
	public void setUp(Animation up) {
		this.up = up;
	}
	public void setDown(Animation down) {
		this.down = down;
	}
	public void setLeft(Animation left) {
		this.left = left;
	}
	public void setRight(Animation right) {
		this.right = right;
	}
	
	public void setDirection(Direction d) {
		switch (d) {
		case UP:
			sprite = up;
			break;
		case DOWN:
			sprite = down;
			break;
		case LEFT:
			sprite = left;
			break;
		case RIGHT:
			sprite = right;
			break;
		}
	}
	
	public Point getDrawPoint(float x, float y) {
		//System.out.println("draw: "+x+", "+y);
		float drawX = x - (width/2);
		float drawY = y - height;
		//System.out.println("draw point: "+drawX+", "+drawY);
		return new Point((int)drawX, (int)drawY);
	}
}
