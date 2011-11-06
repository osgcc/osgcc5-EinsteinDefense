package org.osgcc.osgcc5.soapydroid.things;

import android.graphics.Bitmap;

public abstract class CollidableThing {

	// position
	protected float x;
	protected float y;

	// velocity
	protected float dx;
	protected float dy;

	// acceleration
	protected float accx;
	protected float accy;

	// mass
	protected float mass;

	// size
	protected float height;
	protected float width;

	// rotation
	protected float orientation;
	
	// bitmap
	protected Bitmap bitmap;
	
	// is invader?
	protected boolean isEnemy;
	
	// point value
	protected int points;
	
	public CollidableThing() {
		x = 0;
		y = 0;
		dx = 0;
		dy = 0;
		accx = 0;
		accy = 0;
		orientation = 0;
		points = 1;
		mass = 1;
		height = 1;
		width = 1;
		bitmap = null;
		isEnemy = false;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public float getOrientation() {
		return orientation;
	}

	public void setOrientation(float orientation) {
		this.orientation = orientation;
	}

	public boolean isEnemy() {
		return isEnemy;
	}

	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
		height = bitmap.getHeight();
		width = bitmap.getWidth();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public float getAccx() {
		return accx;
	}

	public void setAccx(float accx) {
		this.accx = accx;
	}

	public float getAccy() {
		return accy;
	}

	public void setAccy(float accy) {
		this.accy = accy;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}
}
