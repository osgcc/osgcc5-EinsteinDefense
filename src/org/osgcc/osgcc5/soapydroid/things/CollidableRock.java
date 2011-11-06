package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableRock extends CollidableThing {
	
	public CollidableRock() {
		this("medium");
	}
	
	public CollidableRock(String size) {
		super();

		if(size.equals("small"))
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.tree_small));
		else if(size.equals("medium"))
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.tree));
		else
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.tree_large));

		setType("rock");
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.rock));
		isEnemy = false;
	}
	
}