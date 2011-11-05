package org.osgcc.osgcc5.soapydroid.physics;

public interface GravityHandler {
	
	/**
	 * Modifies velocity of object based on gravity constant, current 
	 * orientation of tablet. 
	 * 
	 * @param thing1 mobile object on screen which will have its velocity altered
	 * @param orientation current orientation of tablet, in degrees (need to verify this)
	 */
	public void gravityTick(CollisionHandler thing1, float orientation);
	
}
