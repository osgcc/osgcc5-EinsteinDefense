package org.osgcc.osgcc5.soapydroid.levels;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.things.CollidableCow;
import org.osgcc.osgcc5.soapydroid.things.CollidableEinstein;
import org.osgcc.osgcc5.soapydroid.things.CollidableIceberg;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;
import org.osgcc.osgcc5.soapydroid.things.CollidableTree;

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
			thing1.setY(600);
			thing1.setMass(3);
			projectilesInactive.add(thing1);
			
			CollidableThing thing2 = new CollidableCow();
			thing2.setX(600);
			thing2.setY(640);
			thing2.setMass(3);
			projectilesInactive.add(thing2);
			
			CollidableThing thing4 = new CollidableTree();
			thing4.setX(400);
			thing4.setY(610);
			thing4.setMass(3);
			projectilesInactive.add(thing4);
			
			CollidableThing thing5 = new CollidableIceberg();
			thing5.setX(900);
			thing5.setY(590);
			thing5.setMass(3);
			projectilesInactive.add(thing5);
			
			CollidableThing thing6 = new CollidableIceberg();
			thing6.setX(1050);
			thing6.setY(640);
			thing6.setMass(3);
			projectilesInactive.add(thing6);

			CollidableThing thing3 = new CollidableEinstein();
			thing3.setX(400);
			thing3.setY(-100);
			thing3.setMass(1);
			thing3.setDy(2);
			invaders.add(thing3);
			
			CollidableThing thing7 = new CollidableEinstein();
			thing7.setX(700);
			thing7.setY(-150);
			thing7.setMass(1);
			thing7.setDy(2);
			invaders.add(thing7);
			
			break;
		case 1:

			break;
		default:

		}

	}

}
