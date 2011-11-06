package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableIceberg extends CollidableThing {

	public CollidableIceberg() {
		super();
		setType("iceberg");
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.iceberg));
		isEnemy = false;
	}
	
}
