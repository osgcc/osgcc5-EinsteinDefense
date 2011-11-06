package org.osgcc.osgcc5.soapydroid.gestures;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.EinsteinDefensePanel;
import org.osgcc.osgcc5.soapydroid.R;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class EinsteinGestureListener implements GestureDetector.OnGestureListener, 
GestureDetector.OnDoubleTapListener {
	
	public static final float DAMPENING_FACTOR = 0.7f;

	/**
	 * reference to main panel
	 */
	EinsteinDefensePanel  mainView     ;
	List<CollidableThing> collidables  ;
	List<CollidableThing> activeThings ;
	List<CollidableThing> invaders ;
	CollidableThing       movingItem   ;
	float                 maxY         ;
	long                  deltaTime    ;
	float                 pastX        ;
	float                 pastY        ;
	
	public static final String DEBUG_TAG = "Gesture Listener" ;
	public EinsteinGestureListener(EinsteinDefensePanel mainView, List<CollidableThing> collidables, 
			List<CollidableThing> activeList,  float maxY, List<CollidableThing> invaders) {
		this.mainView    = mainView    ;
		this.collidables = collidables ;
		this.maxY        = maxY        ;
		this.activeThings = activeList ;
		this.invaders = invaders;
	}
	
	 
	public boolean onDoubleTap(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		CollidableThing tempCow = null ;
		for(CollidableThing item: activeThings)
		{
			if(item.getType() == "cow")
			{
				tempCow = item ;
				break ;
			}
		}
		if(tempCow != null) {
			synchronized(activeThings)
			{
				activeThings.remove(tempCow) ;
			}
			
			// find nearby targets
			float x = tempCow.getX()+tempCow.getWidth()/2;
			float y = tempCow.getY()+tempCow.getHeight()/2;
			synchronized (invaders) {
				for (CollidableThing invader : invaders) {
					
					float invaderX = invader.getX()+invader.getWidth()/2;
					float invaderY = invader.getY()+invader.getHeight()/2;
					if (Math.abs(invaderX - x) < 80) {
						float invaderDx = invader.getDx();
						float invaderMass = invader.getMass();
						if (invaderX < x) {
							invader.setDx(invaderDx-10/invaderMass);
						} else if (x < invaderX) {
							invader.setDx(invaderDx+10/invaderMass);
						}
					}
					if (Math.abs(invaderY - y) < 80) {
						float invaderDy = invader.getDy();
						float invaderMass = invader.getMass();
						if (invaderY < y) {
							invader.setDy(invaderDy-10/invaderMass);
						} else if (y < invaderY) {
							invader.setDy(invaderDy+10/invaderMass);
						}
					}
					
				}
			}
			
			mainView.addExplosion(tempCow);
			
			
		}
		return true ;
	}

	
	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.v(DEBUG_TAG, "onDown");
		movingItem = findSelectedThing(e) ;
		return true;
	}

	
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		Log.v(DEBUG_TAG, "onFling: "+velocityX+", "+velocityY)  ;
		synchronized(activeThings)
		{
		if(movingItem != null)
		{
			float velX = velocityX * .03F * DAMPENING_FACTOR;
			if (velX > 20) {
				velX = 20;
			}
			float velY = velocityY * .03F * DAMPENING_FACTOR;
			if (velY > 20) {
				velY = 20;
			}
		movingItem.setDx(velX) ;
		movingItem.setDy(velY) ;
		activeThings.add(movingItem) ;
		}
		}
		synchronized(activeThings)
		{
			mainView.setHeldOutCollidable(null) ;
		}
		
		movingItem      = null       ;
		return true;
	}

	
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	
	public boolean onScroll(MotionEvent e1, MotionEvent e2,
			float distanceX, float distanceY) {
		
		if(e2.getY() < maxY)
		{
			deltaTime = System.currentTimeMillis() - deltaTime ;
			
			//float velocityX = ((e2.getX() - pastX) / deltaTime); 
			//float velocityY = ((e2.getY() - pastY) / deltaTime);
			float velocityX = (e2.getX() - pastX) * DAMPENING_FACTOR; 
			float velocityY = (e2.getY() - pastY) * DAMPENING_FACTOR;
			
			if (velocityX > 20) {
				velocityX = 20;
			}
			if (velocityY > 20) {
				velocityY = 20;
			}
			
			synchronized(activeThings)
			{
				if (movingItem != null) {
					movingItem.setDx(velocityX) ;
					movingItem.setDy(velocityY) ;
					activeThings.add(movingItem) ;
				}
			}
			synchronized(activeThings)
			{
				mainView.setHeldOutCollidable(null) ;
			}
			movingItem = null            ;
			
		}
		if(movingItem != null)
		{
			movingItem.setX(e2.getX() - (movingItem.getWidth()/2)) ;
			movingItem.setY(e2.getY() - (movingItem.getHeight()/2));
		}
		Log.v(DEBUG_TAG, "OnScroll: Y: " + e2.getY() + " maxY: " + maxY) ;
		pastX = e2.getX();
		pastY = e2.getY();
		deltaTime = System.currentTimeMillis() ;
		return true;
	}

	
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	public CollidableThing findSelectedThing(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX() ;
		float y = event.getY() ;
		pastX   = x            ;
		pastY   = y            ;
		
		boolean isTouch = false ;
		for(CollidableThing i: collidables)
		{
			float xObj = i.getX();
			float yObj = i.getY();
			float height = i.getHeight();
			float width = i.getWidth();
			if(x >= xObj && x <= (xObj + width) && y >= maxY)
				if(y >= yObj && y <= (yObj + height))
						{
								deltaTime = System.currentTimeMillis() ;
							synchronized(collidables)
							{
							collidables.remove(i) ;
							mainView.setHeldOutCollidable(i) ;
							isTouch = true ;
							}
							
							Log.v(DEBUG_TAG, "Is touching: " + isTouch) ;
							return i ; 
						}
		}
		
		return null;
	}






}