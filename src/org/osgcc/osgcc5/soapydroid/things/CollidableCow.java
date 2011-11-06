package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableCow extends CollidableThing {
	
	public CollidableCow() {
		super();
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.cow));
		isEnemy = false;
	}
	
}
