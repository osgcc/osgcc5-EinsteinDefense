package org.osgcc.osgcc5.soapydroid;

import java.util.List;

import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.graphics.Canvas;

public class EinsteinDefenseThread extends Thread {

	/**
	 * Reference to main panel.
	 */
	EinsteinDefensePanel mainView;

	/**
	 * Indicates whether the thread should currently be running.
	 */
	private boolean running;

	
	
	

	/**
	 * List of active invaders.
	 * NOTE: this will be used by multiple threads. Make sure to synchronize!
	 */
	private List<CollidableThing> invaders;
	
	/**
	 * List of active projectiles.
	 * NOTE: this will be used by multiple threads. Make sure to synchronize!
	 */
	private List<CollidableThing> projectilesActive;
	
	/**
	 * List of inactive projectiles (waiting on the ground to be flung).
	 * NOTE: this will be used by multiple threads. Make sure to synchronize!
	 */
	private List<CollidableThing> projectilesInactive;
	
	public EinsteinDefenseThread(EinsteinDefensePanel mainView, 
			List<CollidableThing> invaders, 
			List<CollidableThing> projectilesActive,
			List<CollidableThing> projectilesInactive) {
		this.mainView = mainView;
		
		this.invaders = invaders;
		this.projectilesActive = projectilesActive;
		this.projectilesInactive = projectilesInactive;
		
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {
		Canvas canvas;
		while (running) {
			canvas = null;
			try {
				// don't let anything else interfere while we do canvas-y stuff
				canvas = mainView.getHolder().lockCanvas();
				synchronized (mainView.getHolder()) {
					// detect and handle collisions
					// this is currently very inefficient, fix later
					for (CollidableThing invader : invaders) {
						for (CollidableThing projectile : projectilesActive) {
							
						}
					}
					
					// update positions
					for (CollidableThing invader : invaders) {
						
					}
					for (CollidableThing projectile : projectilesActive) {
						
					}

					// update images
					mainView.onDraw(canvas);

					// check level status? remove pieces outside of bounds?

				}
			}  finally {

				if (canvas != null) {
					mainView.getHolder().unlockCanvasAndPost(canvas);
				}

			}

		}


	}


}