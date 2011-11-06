package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableEinstein extends CollidableThing {

	public CollidableEinstein() {
		super();
		setType("einstein");
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein));
		isEnemy = true;
	}
	
}
