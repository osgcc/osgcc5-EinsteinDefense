package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableSuperEinstein extends CollidableThing {

	public CollidableSuperEinstein() {
		super();
		setType("supereinstein");
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_super));
		isEnemy = true;
	}
	
}
