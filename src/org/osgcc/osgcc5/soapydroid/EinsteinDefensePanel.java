package org.osgcc.osgcc5.soapydroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgcc.osgcc5.soapydroid.gestures.EinsteinGestureListener;
import org.osgcc.osgcc5.soapydroid.levels.LevelInitializer;
import org.osgcc.osgcc5.soapydroid.levels.LevelInitializerSample;
import org.osgcc.osgcc5.soapydroid.physics.PhysicsEngine;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.SoundPool;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EinsteinDefensePanel extends SurfaceView implements SurfaceHolder.Callback {
	

	public static final String DEBUG_TAG = "EinsteinDefenseActivity";

	/**
	 * Reference to image cache.
	 */
	private static Map<Integer, Bitmap> imageCache = EinsteinDefenseActivity.getImageCache();
	
	/**
	 * Reference to sound cache.
	 */
	private static SoundPool soundCache = EinsteinDefenseActivity.getSoundCache();
	
	/**
	 * Main game thread; handles collisions, etc.
	 */
	private EinsteinDefenseThread gameThread;
	
	/**
	 * Listener for "fling" gestures.
	 */
	private GestureDetector gestureListener;
	
	/**
	 * Object held out for scrolling by user's finger.
	 */
	private CollidableThing heldOutCollidable;
	
	private LevelInitializer levelInitializer;
	
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
	
	/**
	 * Physics calculator.
	 */
	private PhysicsEngine physicsEngine;
	
	/**
	 * Ceiling beyond which user should have no fling control.
	 */
	private float flingCeiling;
	
	public EinsteinDefensePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		
		invaders = new ArrayList<CollidableThing>();
		projectilesActive = new ArrayList<CollidableThing>();
		projectilesInactive = new ArrayList<CollidableThing>();
		heldOutCollidable = null;
		physicsEngine = new PhysicsEngine();
		
		// for test: no ceiling
		flingCeiling = 0;
		
		levelInitializer = new LevelInitializerSample(invaders, projectilesActive, projectilesInactive);
		levelInitializer.initializeLists(0);
		
		gestureListener = new GestureDetector(context, 
				new EinsteinGestureListener(this, projectilesInactive, projectilesActive, flingCeiling));
		gameThread = new EinsteinDefenseThread(this, invaders, projectilesActive, projectilesInactive, physicsEngine);
		setFocusable(true);
		
		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.d(DEBUG_TAG, "drawing canvas...");
		// draw test
		//canvas.drawBitmap(imageCache.get(R.drawable.cow), 50, 500, null);
		
		// draw background
		canvas.drawBitmap(imageCache.get(R.drawable.background), 0, 0, null);
		
		// draw collidable objects
		// NOTE: must figure out how to draw with rotation!
		
		// draw inactive projectiles
		synchronized (projectilesInactive) {
			for (CollidableThing thing : projectilesInactive) {
				Matrix matrix = new Matrix();
				float x = thing.getX();
				float y = thing.getY();
				float rotation = thing.getOrientation();
				matrix.postTranslate(x, y);
				matrix.postRotate(rotation);
				canvas.drawBitmap(thing.getBitmap(), matrix, null);
			}
		}
		
		// draw invaders
		synchronized (invaders) {
			for (CollidableThing thing : invaders) {
				Matrix matrix = new Matrix();
				float x = thing.getX();
				float y = thing.getY();
				float rotation = thing.getOrientation();
				matrix.postTranslate(x, y);
				matrix.postRotate(rotation);
				canvas.drawBitmap(thing.getBitmap(), matrix, null);
			}
		}
		
		// draw active projectiles and held-out item
		synchronized (projectilesActive) {
			for (CollidableThing thing : projectilesActive) {
				Matrix matrix = new Matrix();
				float x = thing.getX();
				float y = thing.getY();
				float rotation = thing.getOrientation();
				matrix.postTranslate(x, y);
				matrix.postRotate(rotation);
				canvas.drawBitmap(thing.getBitmap(), matrix, null);
			}
			if (heldOutCollidable != null) {
				CollidableThing thing = heldOutCollidable;
				Matrix matrix = new Matrix();
				float x = thing.getX();
				float y = thing.getY();
				float rotation = thing.getOrientation();
				matrix.postTranslate(x, y);
				matrix.postRotate(rotation);
				canvas.drawBitmap(thing.getBitmap(), matrix, null);
			}
		}
		
	}

	
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	
	public void surfaceCreated(SurfaceHolder arg0) {
		if (!gameThread.isAlive()) {
			gameThread = new EinsteinDefenseThread(this, invaders, 
					projectilesActive, projectilesInactive, physicsEngine);
		}
		gameThread.setRunning(true);
		gameThread.start();
		
	}

	
	public void surfaceDestroyed(SurfaceHolder arg0) {
		boolean retry = true;
		gameThread.setRunning(false);
		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// keep trying to end the thread
			}
		}
		Log.i(DEBUG_TAG, "gameThread terminated...");
		
	}
	
	public void setHeldOutCollidable(CollidableThing heldOutCollidable) {
		this.heldOutCollidable = heldOutCollidable; 
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureListener.onTouchEvent(event);
	}
	
	
}