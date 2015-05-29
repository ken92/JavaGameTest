package data;

public class ObjectData {
	private Sprite sprite;
	public boolean walkable;
	
	public ObjectData(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	public Sprite getSprite() {
		return sprite;
	}
}
