package org.osgcc.osgcc5.soapydroid.levels;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import org.osgcc.osgcc5.soapydroid.things.CollidableCow;
import org.osgcc.osgcc5.soapydroid.things.CollidableEinstein;
import org.osgcc.osgcc5.soapydroid.things.CollidableIceberg;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;
import org.osgcc.osgcc5.soapydroid.things.CollidableTree;

public class LevelData extends LevelInitializer{

	Scanner scanner;
	public LevelData(List<CollidableThing> invaders,
			List<CollidableThing> projectilesActive,
			List<CollidableThing> projectilesInactive) throws FileNotFoundException {
		super(invaders, projectilesActive, projectilesInactive);
		// TODO Auto-generated constructor stub
		FileReader reader = new FileReader("LevelData.txt");
		scanner = new Scanner(reader);
	}

	@Override
	public void initializeLists(int level) {
		
		int numberOfThings = scanner.nextInt();
		CollidableThing[] thingsArray = new CollidableThing[numberOfThings];
		String thingType = "";
		for(int k = 0; k < thingsArray.length; k++ )
		{
			thingType = scanner.next();
			if(thingType.equals("Tree"))
				thingsArray[k] = new CollidableTree();
			else if(thingType.equals("Rock"))
				thingsArray[k] = new CollidableTree();
			else if(thingType.equals("Cow"))
				thingsArray[k] = new CollidableCow();
			else
				thingsArray[k] = new CollidableEinstein();
			
			thingsArray[k].setX(scanner.nextInt());
			thingsArray[k].setY(scanner.nextInt());
			thingsArray[k].setMass(scanner.nextInt());
			
			if(thingType.equals("Einstein"))
				thingsArray[k].setDy(scanner.nextInt());
			projectilesInactive.add(thingsArray[k]);
		}
		
	}
	
	

}
