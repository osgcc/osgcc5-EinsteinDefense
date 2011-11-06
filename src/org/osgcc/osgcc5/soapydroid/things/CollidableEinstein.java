package org.osgcc.osgcc5.soapydroid.things;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.R;

public class CollidableEinstein extends CollidableThing {
	
	public CollidableEinstein(int score) {
		super();
		if(score == 57)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_tiny));
		else if(score == 142)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein));
		else if(score == 368)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_large));
		else if(score == 519)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_huge));
		else if(score == 213)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_super_tiny));
		else if(score == 406)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_super));
		else if(score == 671)
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_super_large));
		else
			setBitmap(EinsteinDefenseActivity.getImageCache().get(R.drawable.einstein_super_huge));
		
		setType("einstein");
		isEnemy = true;
	}
	
}
