package org.osgcc.osgcc5.soapydroid;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class EinsteinDefenseActivity extends Activity {

	/**
	 * tag for debugging data in output logfile
	 */
	public static final String DEBUG_TAG = "EinsteinDefenseActivity";

	/**
	 * Cache for image files.
	 */
	private static Map<Integer, Bitmap> imageCache = new HashMap<Integer, Bitmap>();
	
	/**
	 * Cache for all sound files.
	 */
	private static SoundPool soundCache;
	//private static SoundPool soundCache = new SoundPool(maxStreams, streamType, srcQuality);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.main);
		Log.d(DEBUG_TAG, "loading images...");
		loadBitmaps();
		Log.d(DEBUG_TAG, "loading sounds...");
		loadSounds();
		Log.d(DEBUG_TAG, "starting view initialization...");
		setContentView(new EinsteinDefensePanel(this));
	}

	
	private void loadBitmaps() {
		imageCache.put(R.drawable.cow, BitmapFactory.decodeResource(getResources(), R.drawable.cow));
		imageCache.put(R.drawable.tree, BitmapFactory.decodeResource(getResources(), R.drawable.tree));
		imageCache.put(R.drawable.rock, BitmapFactory.decodeResource(getResources(), R.drawable.rock));
		imageCache.put(R.drawable.iceberg, BitmapFactory.decodeResource(getResources(), R.drawable.iceberg));
		imageCache.put(R.drawable.background, BitmapFactory.decodeResource(getResources(), R.drawable.background));
	}
	
	private void loadSounds() {
		
		
		
	}
	
	public static Map<Integer, Bitmap> getImageCache() {
		return imageCache;
	}
	
	public static SoundPool getSoundCache() {
		return soundCache;
	}


}