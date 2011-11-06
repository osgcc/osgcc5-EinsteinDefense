package org.osgcc.osgcc5.soapydroid.physics;

import java.util.HashMap;
import java.util.Map;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.sensors.RotationDetector;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class PhysicsEngine implements CollisionHandler {

	public static final String DEBUG_TAG = "EinsteinDefenseActivity";
	
	/**
	 * Class for obtaining current rotation value from orientation sensors.
	 */
	private RotationDetector rotationDetector;
	private static SoundPool soundPool = EinsteinDefenseActivity.getSoundPool()               ;
	private static Map<Integer, Integer> soundCache = EinsteinDefenseActivity.getSoundCache() ;
	
	public PhysicsEngine(RotationDetector rotationDetector) {
		this.rotationDetector = rotationDetector;
	}
	
	//gets the two objects that are colliding to return the velocity of thing1
	/*
	 * The equation used here is as follow:
	 * m = mass, u = velocity before collision, v = velocity after collision
	 * v1 = u1(m1-m2)+2*m2*u2
	 *      -----------------
	 *           m1+m2
	 */
	private float findNewVel(float vel_1, float mass1, float vel_2, float mass2)
	{
		///*
		float numerator = vel_1*(mass2 - mass1)+2*vel_2*mass2;
		float denominator = mass1 + mass2;
		return numerator/denominator;
		//*/
		//return 0;
		
	}
	
	private float findNewOrientation(CollidableThing thing1, CollidableThing thing2)
	{
		float orientation = 0;
		//will run through this, so long as neither of the objects is more than twice the weight of the other
		if (thing1.getMass() * 2 > thing2.getMass() && thing2.getMass() * 2 > thing1.getMass()) {
			if ((thing1.getY() + thing1.getHeight()) / 2 < thing2.getY()) {
				orientation = 1;
			} else if ((thing1.getY() + thing1.getHeight()) / 2 > thing2.getX()
					+ thing2.getHeight()) {
				orientation = -1;
			} else if ((thing1.getX() + thing1.getWidth()) / 2 < thing2.getX()) {
				orientation = -1;
			} else if ((thing1.getX() + thing1.getWidth()) / 2 > thing2.getX()){
				return 1;
			}
		}
		return orientation;
	}
	
	public boolean haveCollided(CollidableThing thing1, CollidableThing thing2)
	{
		boolean collided = false;
		//checks to see if they collided by top or bottom of squares
		float top1 = thing1.getY();
		float left1 = thing1.getX();
		float top2 = thing2.getY();
		float left2 = thing2.getX();
		float height1 = thing1.getHeight();
		float height2 = thing2.getHeight();
		float width1 = thing1.getWidth();
		float width2 = thing2.getWidth();
		float bot1 = top1 + height1;
		float right1 = left1 + width1;
		float bot2 = top2 + height2; 
		float right2 = left2 + width2;
		
		if (((top1<=top2 && top2<=bot1) || (top1<=bot2 && bot2<=bot1) || (top2<=top1 && top1<=bot2) || (top2<=bot1 && bot1<=bot2)) && 
				((left1<=left2 && left2<=right1) || (left1<=right2 && right2<=right1) || (left2<=left1 && left1<=right2) || (left2<=right1 && right1<=right2))) {
			soundPool.play(soundCache.get(1), 1F, 1F, 0, 0, 1F) ;
			collided = true;
		}
		
		/*
		if( (thing1.getY()+thing1.getHeight() >= thing2.getY()) || (thing2.getY()+thing2.getHeight() >= thing1.getY()))
		{
			if( ((thing2.getX() >= thing1.getDx()) && ((thing2.getX() + thing2.getWidth() - thing1.getX()) <= (thing1.getWidth() + thing2.getWidth()))) ||
					((thing1.getX() >= thing2.getX()) && ( (thing1.getX() + thing1.getWidth() - thing2.getX()) <= (thing1.getWidth() + thing2.getWidth()) )))
				collided = true;
		}
		*/
		//checks to see if they collide on either sides
		
		return collided;
	}
	
	/*
	 * Changes the velocities of two collidable objects whenever a collision occurs 
	 *
	 */
	
	public void collision(CollidableThing thing1, CollidableThing thing2) {
		//if(haveCollided(thing1, thing2))
		//{
			//First gets new Dy for each object
			/*
			float totalMomentum = thing1.getDy()*thing1.getMass() + thing2.getDy()*thing2.getMass();
			thing1.setDy(findNewVel(thing1.getDy(), thing1.getMass(), thing2.getDy(), thing2.getMass()));
			thing2.setDy((totalMomentum - thing1.getMass()*thing1.getDy())/thing2.getMass()/25);
				thing1.setDy(thing1.getDy()/25);
		
			//Gets new Dx for each object
			totalMomentum = thing1.getDx()*thing1.getMass() + thing2.getDx()*thing2.getMass();
			thing1.setDx(findNewVel(thing1.getDx(), thing1.getMass(), thing2.getDx(), thing2.getMass()));
			thing2.setDx((totalMomentum - thing1.getMass()*thing1.getDx())/thing2.getMass()/25);
				thing1.setDy(thing1.getDy()/25);
			//*/
			
			/*
			float mass1 = thing1.getMass();
			float mass2 = thing2.getMass();
			float massSum = mass1 + mass2;
			float dx1 = thing1.getDx();
			float dx2 = thing2.getDx();
			float dy1 = thing1.getDy();
			float dy2 = thing2.getDy();
			float v1x = (dx1*(mass1-mass2)+2*mass2*dx2)/massSum;
			float v2x = (dx2*(mass2-mass1)+2*mass1*dx1)/massSum;
			float v1y = (dy1*(mass1-mass2)+2*mass2*dy2)/massSum;
			float v2y = (dy2*(mass2-mass1)+2*mass1*dy1)/massSum;
			
			thing1.setDx(v1x);
			thing1.setDx(v1y);
			thing2.setDx(v2x);
			thing2.setDx(v2y);
			//*/
			
			///*
			float mass1 = thing1.getMass();
			float mass2 = thing2.getMass();
			
			float force1x = thing1.getDx() * mass1;
			float force2x = thing2.getDx() * mass2;
			float force1y = thing1.getDy() * mass1;
			float force2y = thing2.getDy() * mass2;
			
			thing1.setDx(force2x / mass1);
			thing1.setDy(force2y / mass1);
			
			thing2.setDx(force1x / mass2);
			thing2.setDy(force1y / mass2);
			//*/
			
			//sets the new orientations
		//}
		
		
	}

	public void collisionWall(CollidableThing thing) {
		//may need to add that only "non-hit" enemies and player's objects bounce, already 
		//hit enemies should go off of screen
		
		//changes direction sideways direction(dx) of object if it hits wall
		thing.setDx(thing.getDx()*-1);
	}

	public void updatePosition(CollidableThing thing)
	{
			thing.setX(thing.getX()+thing.getDx());
			thing.setY(thing.getY()+thing.getDy());
			
			//keeps and object from going past the left side of the screen and bounces it the opposite direction
			if(thing.getX() <= 0 && !thing.isEnemy())
			{
				thing.setX(0);
				collisionWall(thing);
			}
			//keeps objects from going past the right side of the screen and bounces it to the left
			if(thing.getX() + thing.getWidth() >= 1280 && !thing.isEnemy())//1280 should be width of screen
			{
				thing.setX(1280 - thing.getWidth());
				collisionWall(thing);
			}
	}
	
	public void gravity(CollidableThing thing)
	{
		// add to dy's to slow down and eventually reverse direction
		// only affects the player's projectiles
		// compute with respect to current orientation
		if(!thing.isEnemy()) {
			
			//thing.setDy((float)(thing.getDy() + .5));
			
			float xWeight = rotationDetector.getXWeight();
			float yWeight = rotationDetector.getYWeight();
			float gravStrength = 1.5f;
			// figure out relative portions of gravStrength which belong 
			//  to x and y 
			thing.setDx(thing.getDx() + gravStrength*xWeight);
			thing.setDy(thing.getDy() + gravStrength*yWeight);
		}
		
	}
	

	

}
