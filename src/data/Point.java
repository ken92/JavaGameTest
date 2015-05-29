package data;

public class Point {
	public final int x, y;
	public final float xFloat, yFloat;

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
		xFloat = -1;
		yFloat = -1;
	}
	public Point(final float xFloat, final float yFloat) {
		x = -1;
		y = -1;
		this.xFloat = xFloat;
		this.yFloat = yFloat;
	}

	public boolean equals(Point p) {
		return (x == p.x && y == p.y && xFloat == p.xFloat && yFloat == p.yFloat);
	}
	public boolean equals(int x, int y) {
		return (x == this.x && y == this.y);
	}
	public boolean equals(float xFloat, float yFloat) {
		return (xFloat == this.xFloat && yFloat == this.yFloat);
	}
}
