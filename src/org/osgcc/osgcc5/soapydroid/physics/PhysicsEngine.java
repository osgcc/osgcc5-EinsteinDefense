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
	private int findNewDy(int vel_1, int mass1, int vel_2, int mass2)
	{
		int numerator = vel_1*(mass2 + mass1)+2*vel_2*mass2;
		int denominator = vel_1 + mass2;
		return numerator/denominator;
	}
	private int findNewDx(int vel_1, int mass1, int vel_2, int mass2)
	{
		int numerator = vel_1*(mass2 + mass1)+2*vel_2*mass2;
		int denominator = vel_1 + mass2;
		return numerator/denominator;
	}
	

	/*
	 * Changes the velocities of two collidable objects whenever a collision occurs 
	 *  
	 */
	
	public void collision(CollidableThing thing1, CollidableThing thing2,
			int onTop, int onLeft) {

		//First gets new Dy for each object
		int totalMomentum = thing1.getDy()*thing1.getMass() + thing2.getDy()*thing2.getMass();
		thing1.setDy(findNewDy(thing1.getDy(), thing1.getMass(), thing2.getDy(), thing2.getMass()));
		thing2.setDy((totalMomentum - thing1.getMass()*thing1.getDy())/thing2.getMass());
		
		//Gets new Dx for each object
		totalMomentum = thing1.getDx()*thing1.getMass() + thing2.getDx()*thing2.getMass();
		thing1.setDx(findNewDx(thing1.getDx(), thing1.getMass(), thing2.getDx(), thing2.getMass()));
		thing2.setDx((totalMomentum - thing1.getMass()*thing1.getDx())/thing2.getMass());
		
		
	}

}
