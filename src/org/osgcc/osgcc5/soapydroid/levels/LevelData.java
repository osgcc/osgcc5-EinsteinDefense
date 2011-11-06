package org.osgcc.osgcc5.soapydroid.levels;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.osgcc.osgcc5.soapydroid.things.CollidableCow;
import org.osgcc.osgcc5.soapydroid.things.CollidableEinstein;
import org.osgcc.osgcc5.soapydroid.things.CollidableIceberg;
import org.osgcc.osgcc5.soapydroid.things.CollidableRock;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;
import org.osgcc.osgcc5.soapydroid.things.CollidableTree;


import android.content.res.Resources;
import android.util.Log;

import org.osgcc.osgcc5.soapydroid.EinsteinDefenseActivity;
import org.osgcc.osgcc5.soapydroid.EinsteinDefensePanel;
import org.osgcc.osgcc5.soapydroid.R;

//Loads the data for each new level
public class LevelData extends LevelInitializer{

	Scanner scanner;
	public LevelData(List<CollidableThing> invaders,
			List<CollidableThing> projectilesActive,
			List<CollidableThing> projectilesInactive,
			EinsteinDefensePanel panel) throws FileNotFoundException {
		super(invaders, projectilesActive, projectilesInactive, panel);
		
		InputStream reader = EinsteinDefenseActivity.getTextCache().get(R.raw.leveldata);
		scanner = new Scanner(reader);
	}

	@Override
	public void initializeLists(int level) {
		
		int numberOfThings = scanner.nextInt();
		CollidableThing[] thingsArray = new CollidableThing[numberOfThings];
		String thingType = "";
		String size = "";
		int score = 0;
		for(int k = 0; k < thingsArray.length; k++ )
		{
			//loops through the text file one line at a time to get info for lvls
			
			/*
			 * Things to add:
			 * delay system so all Einsteins don't fall at once
			 * 
			 */
			
			// TODO set background
			
			thingType = scanner.next();
			if(!thingType.equals("Einstein"))
			{
				size = scanner.next();
				if(thingType.equals("Tree"))
					thingsArray[k] = new CollidableTree(size);
				else if(thingType.equals("Rock"))
					thingsArray[k] = new CollidableRock(size);
				else if(thingType.equals("Cow"))
					thingsArray[k] = new CollidableCow(size);
				else 
					thingsArray[k] = new CollidableIceberg(size);
			}
			else
			{
				score = scanner.nextInt();
				thingsArray[k] = new CollidableEinstein(score);
			}
			
			thingsArray[k].setX(scanner.nextInt());
			thingsArray[k].setY(scanner.nextInt());
			thingsArray[k].setMass(scanner.nextInt());
			
			if(thingType.equals("Einstein"))
			{
				thingsArray[k].setDy(scanner.nextInt());
				thingsArray[k].setPoints(score);
				invaders.add(thingsArray[k]);
			}
			else
				projectilesInactive.add(thingsArray[k]);
		}
		
	}
	
	

}
