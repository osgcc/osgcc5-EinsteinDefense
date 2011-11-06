package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableIceberg extends CollidableThing {

	public CollidableIceberg(String size) {
		super();
		if(size.equals("small"))
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.iceberg_small));
		else if(size.equals("medium"))
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.iceberg));
		else
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.iceberg_large));
		
		setType("iceberg");
		isEnemy = false;
	}
	
}
