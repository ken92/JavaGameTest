package data;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMap;

public class MapData extends TiledMap {
	private boolean[][] blocked;
	private Point[][] centerCoords; //where to place the sprite when it moves into this position
	public int tileWidth, tileHeight;

	private Player player;
	
	public MapData(String mapPath) throws SlickException {
		super(mapPath);
		
		// populate blocked grid with blocked terrain and collision layer
		// also populate coords grid
		// also also populate objects grid
		blocked = new boolean[getWidth()][getHeight()];
		centerCoords = new Point[getWidth()][getHeight()];
		int terrainLayer = getLayerIndex("Terrain");
		int collideLayer = getLayerIndex("Collide");
		tileWidth = getTileWidth();
		tileHeight = getTileHeight();
		//float xCenter, yCenter;
		//xCenter = tileWidth/2;
		for (int xAxis=0; xAxis<getWidth(); xAxis++) {
			//yCenter = tileHeight/2;
			for (int yAxis=0;yAxis<getHeight(); yAxis++) {
				int tileID = getTileId(xAxis, yAxis, terrainLayer);
				String value = getTileProperty(tileID, "blocked", "false");
				if ("true".equals(value))
					blocked[xAxis][yAxis] = true;
				
				tileID = getTileId(xAxis, yAxis, collideLayer);
				if (tileID != 0)
					blocked[xAxis][yAxis] = true;

				float xCenter = ((xAxis+1) * tileWidth) - (tileWidth/2);
				float yCenter = ((yAxis+1) * tileHeight) - (tileHeight/2);
				centerCoords[xAxis][yAxis] = new Point((int)xCenter, (int)yCenter);
				System.out.println("centerCoords "+xAxis+", "+yAxis+": "+xCenter+", "+yCenter);
				//yCenter += tileHeight;
			}
			//xCenter += tileWidth;
		}
	}
	
	public boolean validBlock(int x, int y) {
		if (x < 0 || y < 0)
			return false;
		else if (x > width() || y > height())
			return false;
		return true;
	}
	
	public Point[] getSpriteStart() {
		String prop = getMapProperty("Start", "0,0");
		int x, y;
		String[] coords = prop.split(",");
		coords[0] = coords[0].trim();
		coords[1] = coords[1].trim();
		x = Integer.parseInt(coords[0]);
		y = Integer.parseInt(coords[1]);
		Point blockCoords = new Point(x, y);
		Point[] pointArr = {blockCoords, centerCoords[x][y]};
		return pointArr;
	}

	public int width() {
		return getWidth();
	}
	public int height() {
		return getHeight();
	}

	public void render(int x, int y) {
		render(x, y);
	}
	public void renderLayer(int x, int y, String layerName) {
		int layer = getLayerIndex(layerName);
		render(x, y, layer);
	}

	public Point getCenterCoords(int x, int y) {
		return centerCoords[x][y];
	}
	
	public boolean isBlocked(float x, float y) {
		int width = width();
		int height = height();
        int xBlock = (int)x / width;
        int yBlock = (int)y / height;
        return blocked[xBlock][yBlock];
    }
	public boolean isBlockBlocked(int x, int y) {
        return blocked[x][y];
    }
	public void setBlocked(int x, int y, boolean b) {
		blocked[x][y] = b;
	}
	public void setBlocked(float x, float y, boolean b) {
		int width = width();
		int height = height();
        int xBlock = (int)x / width;
        int yBlock = (int)y / height;
        blocked[xBlock][yBlock] = b;
    }
	
	public void setSprite(Player player) {
		this.player = player;
	}
	
	public void renderMap() {
		render(0, 0, getLayerIndex("Terrain"));
		render(0, 0, getLayerIndex("No-Collide"));
		render(0, 0, getLayerIndex("Collide"));
		
		Layer over = (Layer) layers.get(getLayerIndex("1 Over Player"));
		for (int ty = 0; ty < getHeight(); ty++) {
			over.render(0, 0, 0, 0, getWidth(), ty, true, tileWidth, tileHeight);
		}
		
		render(0, 0, getLayerIndex("Over Player"));
	}
	
	@Override
	public void renderedLine(int visualY, int mapY, int layerIndex) {
		if(player.yBlock > mapY) {
			Point drawHere = player.getDrawPoint(player.getX(), player.getY());
			Animation sprite = player.getSprite();
			sprite.draw(drawHere.x, drawHere.y);
		}
	}
}

