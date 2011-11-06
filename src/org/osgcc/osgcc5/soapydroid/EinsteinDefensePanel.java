package org.osgcc.osgcc5.soapydroid;

import java.util.HashMap;
import java.util.Map;

import org.osgcc.osgcc5.soapydroid.gestures.EinsteinGestureListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.SoundPool;
import android.util.Log;
import android.view.GestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class EinsteinDefensePanel extends SurfaceView implements SurfaceHolder.Callback {
	

	public static final String DEBUG_TAG = "EinsteinDefenseActivity";
	
	/**
	 * Cache for image files.
	 */
	private Map<Integer, Bitmap> imageCache = new HashMap<Integer, Bitmap>();
	
	/**
	 * Cache for all sound files.
	 */
	private SoundPool soundCache;
	
	/**
	 * Main game thread; handles collisions, etc.
	 */
	private EinsteinDefenseThread gameThread;
	
	/**
	 * Listener for "fling" gestures.
	 */
	private GestureDetector gestureListener;
	
	
	
	public EinsteinDefensePanel(Context context) {
		super(context);
		Log.d(DEBUG_TAG, "loading images...");
		loadBitmaps();
		Log.d(DEBUG_TAG, "loading sounds...");
		loadSounds();
		getHolder().addCallback(this);
		gestureListener = new GestureDetector(context, 
				new EinsteinGestureListener(this));
		gameThread = new EinsteinDefenseThread(this);
		setFocusable(true);
		
		
		
	}
	
	private void loadBitmaps() {
		imageCache.put(R.drawable.cow, BitmapFactory.decodeResource(getResources(), R.drawable.cow));
		imageCache.put(R.drawable.tree, BitmapFactory.decodeResource(getResources(), R.drawable.tree));
		
	}
	
	private void loadSounds() {
		
		
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.d(DEBUG_TAG, "drawing canvas...");
		// draw test
		canvas.drawBitmap(imageCache.get(R.drawable.cow), 50, 50, null);
		
		// draw background
		
		// draw collidable objects
		
		
		
	}

	
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}

	
	public void surfaceCreated(SurfaceHolder arg0) {
		if (!gameThread.isAlive()) {
			gameThread = new EinsteinDefenseThread(this);
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
	
	
	
	
	
	
}