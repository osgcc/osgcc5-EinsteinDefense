package org.osgcc.osgcc5.soapydroid.gestures;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.view.MotionEvent;

public interface CollidableFinder {
	
	public CollidableThing findSelectedThing(List<CollidableThing> collidables, MotionEvent event);

}
