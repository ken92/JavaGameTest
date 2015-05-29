package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Image;

public class ObjectBatch {
	private List<Sprite> objects;
	
	public ObjectBatch() {
		objects = new ArrayList<Sprite>();
	}
	
	public void addSprite(Sprite s) {
		objects.add(s);
		Collections.sort(objects);
	}
	public List<Sprite> getObjectList() {
		return objects;
	}
	
	public void removeObject(Sprite sprite) {
		Image image = sprite.getSprite().getImage(0);
		int x = sprite.xBlock; int y = sprite.yBlock;
		for (int i = 0; i < objects.size(); i++) {
			Sprite s = objects.get(i);
			if (x == s.xBlock && y == s.yBlock && image == s.getSprite().getImage(0))
				objects.remove(i);
		}
	}
	public void removeObjectAt(int x, int y) {
		for (int i = 0; i < objects.size(); i++) {
			Sprite s = objects.get(i);
			if (x == s.xBlock && y == s.yBlock)
				objects.remove(i);
		}
	}
}
