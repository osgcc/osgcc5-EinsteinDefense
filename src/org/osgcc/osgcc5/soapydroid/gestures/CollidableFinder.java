package org.osgcc.osgcc5.soapydroid.gestures;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.view.MotionEvent;

public interface CollidableFinder {
	
	/**
	 * On a ACTION_DOWN MotionEvent, an implementation of this method 
	 * should be called in order to identify the piece which should
	 * be activates (if any)
	 * 
	 * @param collidables list of collidable pieces which may have been selected
	 * @param event the ACTION_DOWN event
	 * @return the CollidableThing which is selected
	 */
	public CollidableThing findSelectedThing(List<CollidableThing> collidables, MotionEvent event);

}
