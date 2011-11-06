package org.osgcc.osgcc5.soapydroid.levels;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.things.CollidableCow;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

public class LevelInitializerSample extends LevelInitializer {

	public LevelInitializerSample(List<CollidableThing> invaders,
			List<CollidableThing> projectilesActive,
			List<CollidableThing> projectilesInactive) {
		super(invaders, projectilesActive, projectilesInactive);
	}

	@Override
	public void initializeLists(int level) {
		switch (level) {
		case 0:
			CollidableThing thing1 = new CollidableCow();
			thing1.setX(10);
			thing1.setY(400);
			projectilesInactive.add(thing1);
			
			CollidableThing thing2 = new CollidableCow();
			thing2.setX(600);
			thing2.setY(400);
			projectilesInactive.add(thing2);
			
			CollidableThing thing3 = new CollidableCow();
			thing3.setX(400);
			thing3.setY(100);
			invaders.add(thing3);
			
			break;
		case 1:

			break;
		default:

		}

	}

}
