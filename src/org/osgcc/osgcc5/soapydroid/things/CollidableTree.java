package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableTree extends CollidableThing {

	public CollidableTree() {
		super();
		setType("tree");
		setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.tree));
		isEnemy = false;
	}
	
}
