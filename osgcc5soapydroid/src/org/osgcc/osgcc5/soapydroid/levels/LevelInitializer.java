package org.osgcc.osgcc5.soapydroid.levels;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

public abstract class LevelInitializer {
	
	/**
	 * List of active invaders.
	 * NOTE: this will be used by multiple threads. Make sure to synchronize!
	 */
	protected List<CollidableThing> invaders;
	
	/**
	 * List of active projectiles.
	 * NOTE: this will be used by multiple threads. Make sure to synchronize!
	 */
	protected List<CollidableThing> projectilesActive;
	
	/**
	 * List of inactive projectiles (waiting on the ground to be flung).
	 * NOTE: this will be used by multiple threads. Make sure to synchronize!
	 */
	protected List<CollidableThing> projectilesInactive;

	public LevelInitializer(List<CollidableThing> invaders,
			List<CollidableThing> projectilesActive,
			List<CollidableThing> projectilesInactive) {
		
		this.invaders = invaders;
		this.projectilesActive = projectilesActive;
		this.projectilesInactive = projectilesInactive;
		
		
		
	}
	
	public abstract void initializeLists(int level);
	
	
	
	
}
