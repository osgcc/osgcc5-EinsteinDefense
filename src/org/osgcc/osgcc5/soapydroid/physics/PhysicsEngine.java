package org.osgcc.osgcc5.soapydroid.physics;

import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

public class PhysicsEngine implements CollisionHandler{
	
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
		float numerator = vel_1*(mass2 + mass1)+2*vel_2*mass2;
		float denominator = vel_1 + mass2;
		return numerator/denominator;
	}
	
	private float findNewOrientation(CollidableThing thing)
	{
		return 0;
	}
	
	private boolean haveCollided(CollidableThing thing1, CollidableThing thing2)
	{
		boolean collided = false;
		if( (thing1.getY()+thing1.getHeight() == thing2.getY()) || (thing2.getY()+thing2.getHeight() == thing1.getY()))
		{
			collided = true;
		}
		else if ( (thing1.getX()+thing1.getHeight() == thing2.getX()) || (thing2.getX()+thing2.getHeight() == thing1.getX()))
		{
			collided = true;
		}
		return collided;
	}
	
	/*
	 * Changes the velocities of two collidable objects whenever a collision occurs 
	 *  
	 */
	
	public void collision(CollidableThing thing1, CollidableThing thing2) {
		if(haveCollided(thing1, thing2))
		{
			//First gets new Dy for each object
			float totalMomentum = thing1.getDy()*thing1.getMass() + thing2.getDy()*thing2.getMass();
			thing1.setDy(findNewVel(thing1.getDy(), thing1.getMass(), thing2.getDy(), thing2.getMass()));
			thing2.setDy((totalMomentum - thing1.getMass()*thing1.getDy())/thing2.getMass());
		
			//Gets new Dx for each object
			totalMomentum = thing1.getDx()*thing1.getMass() + thing2.getDx()*thing2.getMass();
			thing1.setDx(findNewVel(thing1.getDx(), thing1.getMass(), thing2.getDx(), thing2.getMass()));
			thing2.setDx((totalMomentum - thing1.getMass()*thing1.getDx())/thing2.getMass());
		}
		
		
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
		thing.setY(thing.getX()+thing.getDy());
	}
	
	

	

}
