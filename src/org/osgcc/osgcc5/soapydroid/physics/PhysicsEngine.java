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
		float numerator = vel_1*(mass2 - mass1)+2*vel_2*mass2;
		float denominator = mass1 + mass2;
		return numerator/denominator;
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
	
	private boolean haveCollided(CollidableThing thing1, CollidableThing thing2)
	{
		boolean collided = false;
		//checks to see if they collided by top or bottom of squares
		float y1 = thing1.getY();
		float x1 = thing1.getX();
		float y2 = thing2.getY();
		float x2 = thing2.getX();
		float height1 = thing1.getHeight();
		float height2 = thing2.getHeight();
		float width1 = thing1.getWidth();
		float width2 = thing2.getWidth();
		
		
		if( (thing1.getY()+thing1.getHeight() >= thing2.getY()) || (thing2.getY()+thing2.getHeight() >= thing1.getY()))
		{
			if( ((thing2.getX() + thing2.getWidth() - thing1.getX()) <= (thing1.getWidth() + thing2.getWidth())) ||
					( (thing1.getX() + thing1.getWidth() - thing2.getX()) <= (thing1.getWidth() + thing2.getWidth()) ))
				collided = true;
		}
		//checks to see if they collide on either sides
		
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
			thing1.setDy(findNewVel(thing1.getDy(), thing1.getMass(), thing2.getDy(), thing2.getMass())/50);
			thing2.setDy((totalMomentum - thing1.getMass()*thing1.getDy())/thing2.getMass()/50);
		
			//Gets new Dx for each object
			totalMomentum = thing1.getDx()*thing1.getMass() + thing2.getDx()*thing2.getMass();
			thing1.setDx(findNewVel(thing1.getDx(), thing1.getMass(), thing2.getDx(), thing2.getMass())/50);
			thing2.setDx((totalMomentum - thing1.getMass()*thing1.getDx())/thing2.getMass()/50);
			
			//sets the new orientations 
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
			thing.setY(thing.getY()+thing.getDy());
			
			//keeps and object from going past the left side of the screen and bounces it the opposite direction
			if(thing.getX() <= 0)
			{
				thing.setX(0);
				collisionWall(thing);
			}
			//keeps objects from going past the right side of the screen and bounces it to the left
			if(thing.getX() + thing.getWidth() >= 1280)//1980 should be width of screen
			{
				thing.setX(1280 - thing.getWidth());
				collisionWall(thing);
			}
	}
	
	public void gravity(CollidableThing thing)
	{
		//add to dy's to slow down and eventually reverse direction
		//only affects the player's projectiles
		if(!thing.isEnemy())
			thing.setDy((float)(thing.getDy() + .5));
		
	}
	

	

}
