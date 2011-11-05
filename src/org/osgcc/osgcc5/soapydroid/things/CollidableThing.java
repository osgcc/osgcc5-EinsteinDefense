package org.osgcc.osgcc5.soapydroid.things;

public abstract class CollidableThing {
	
	// position
	private int x;
	private int y;
	
	// velocity
	private int dx;
	private int dy;
	
	// acceleration
	private int accx;
	private int accy;
	
	
	
	
	
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public int getAccx() {
		return accx;
	}
	public void setAccx(int accx) {
		this.accx = accx;
	}
	public int getAccy() {
		return accy;
	}
	public void setAccy(int accy) {
		this.accy = accy;
	}
	
}
