package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableCow extends CollidableThing {
	
	public CollidableCow(String size) {
		super();
		if(size.equals("small"))
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.cow_small));
		else if(size.equals("medium"))
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.cow));
		else
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.cow_large));
		
		setType("cow");
		isEnemy = false;
	}
	
}
