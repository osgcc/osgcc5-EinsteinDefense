package org.osgcc.osgcc5.soapydroid.gestures;

import org.osgcc.osgcc5.soapydroid.EinsteinDefensePanel;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class EinsteinGestureListener implements GestureDetector.OnGestureListener, 
GestureDetector.OnDoubleTapListener {

	/**
	 * reference to main panel
	 */
	EinsteinDefensePanel mainView;

	public EinsteinGestureListener(EinsteinDefensePanel mainView) {
		this.mainView = mainView;
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
		return false;
	}

	
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	
	public boolean onScroll(MotionEvent e1, MotionEvent e2,
			float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}






}