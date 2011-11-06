package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableRock extends CollidableThing {

	public CollidableRock() {
		super();
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.rock));
		isEnemy = false;
	}
	
}