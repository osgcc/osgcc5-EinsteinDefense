package org.osgcc.osgcc5.soapydroid.gestures;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.EinsteinDefensePanel;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class EinsteinGestureListener implements GestureDetector.OnGestureListener, 
GestureDetector.OnDoubleTapListener {

	/**
	 * reference to main panel
	 */
	EinsteinDefensePanel  mainView     ;
	List<CollidableThing> collidables  ;
	List<CollidableThing> activeThings ;
	CollidableThing       movingItem   ;
	float                 maxY         ;
	public static final String DEBUG_TAG = "Gesture Listener" ;
	public EinsteinGestureListener(EinsteinDefensePanel mainView, List<CollidableThing> collidables, List<CollidableThing> activeList, float maxY) {
		this.mainView    = mainView    ;
		this.collidables = collidables ;
		this.maxY        = maxY        ;
		this.activeThings = activeList ;
	}
	
	 
	public boolean onDoubleTap(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean onDoubleTapEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
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
		Log.v(DEBUG_TAG, "onFling")  ;
		synchronized(activeThings)
		{
		activeThings.add(movingItem) ;
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
		// TODO Auto-generated method stub
		
		if(e2.getY() < maxY)
		{
			synchronized(activeThings)
			{
			activeThings.add(movingItem) ;
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