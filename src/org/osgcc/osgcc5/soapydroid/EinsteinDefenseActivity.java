package org.osgcc.osgcc5.soapydroid;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


import org.osgcc.osgcc5.soapydroid.R ;
import org.osgcc.osgcc5.soapydroid.title.TitleScreen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
	private static SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100) ;
	private static Map<Integer, Integer> soundCache = new HashMap<Integer, Integer>() ;
	private MediaPlayer player ;
	private static Map<Integer, InputStream> textCache = new HashMap<Integer, InputStream>();
	
	/**
	 * Reference to title screen.
	 */
	private static TitleScreen titleScreen;
	
	/**
	 * Reference to activity object.
	 */
	private static EinsteinDefenseActivity activity;
	
	private MediaPlayer mediaPlayer;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.main);
		Log.d(DEBUG_TAG, "loading images...");
		loadBitmaps();
		Log.d(DEBUG_TAG, "loading sounds...");
		loadSounds();
		Log.d(DEBUG_TAG, "loading text...");
		loadText();
<<<<<<< HEAD
		 
		 Log.d(DEBUG_TAG, "starting view initialization...");
		 player = MediaPlayer.create(this, soundCache.get(3)) ;
		 player.start() ;
=======
		
		
		mediaPlayer = MediaPlayer.create(this, R.raw.robotsummer);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
		Log.d(DEBUG_TAG, "starting view initialization...");
		//soundPool.play(soundCache.get(3), 1F, 1F, 3, -1, 1F) ;
>>>>>>> 8a13933abe9ba02af570b35a63ee9b9ac84a590f
		 TitleScreen titleScreen = new TitleScreen(this) ;
		 setContentView(titleScreen) ;
		  
		 
	}

	
	private void loadBitmaps() {
		imageCache.put(R.drawable.okbutton, BitmapFactory.decodeResource(getResources(), R.drawable.okbutton));
		imageCache.put(R.drawable.helpbutton, BitmapFactory.decodeResource(getResources(), R.drawable.helpbutton));
		imageCache.put(R.drawable.startbutton, BitmapFactory.decodeResource(getResources(), R.drawable.startbutton));
		imageCache.put(R.drawable.cow, BitmapFactory.decodeResource(getResources(), R.drawable.cow));
		imageCache.put(R.drawable.tree, BitmapFactory.decodeResource(getResources(), R.drawable.tree));
		imageCache.put(R.drawable.rock, BitmapFactory.decodeResource(getResources(), R.drawable.rock));
		imageCache.put(R.drawable.iceberg, BitmapFactory.decodeResource(getResources(), R.drawable.iceberg));
		imageCache.put(R.drawable.background, BitmapFactory.decodeResource(getResources(), R.drawable.background));
		imageCache.put(R.drawable.logo, BitmapFactory.decodeResource(getResources(), R.drawable.logo));
		imageCache.put(R.drawable.einstein, BitmapFactory.decodeResource(getResources(), R.drawable.einstein));
		imageCache.put(R.drawable.help, BitmapFactory.decodeResource(getResources(), R.drawable.help));
		imageCache.put(R.drawable.background_lvl2, BitmapFactory.decodeResource(getResources(), R.drawable.background_lvl2));
		imageCache.put(R.drawable.background_lvl3, BitmapFactory.decodeResource(getResources(), R.drawable.background_lvl3));
		imageCache.put(R.drawable.background_lvl4, BitmapFactory.decodeResource(getResources(), R.drawable.background_lvl4));
		imageCache.put(R.drawable.einstein_tiny, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_tiny));
		imageCache.put(R.drawable.einstein_large, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_large));
		imageCache.put(R.drawable.einstein_huge, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_huge));
		imageCache.put(R.drawable.einstein_super_tiny, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_super_tiny));
		imageCache.put(R.drawable.einstein_super_large, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_super_large));
		imageCache.put(R.drawable.einstein_super_huge, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_super_huge));
		imageCache.put(R.drawable.einstein_super, BitmapFactory.decodeResource(getResources(), R.drawable.einstein_super));
		imageCache.put(R.drawable.cow_small, BitmapFactory.decodeResource(getResources(), R.drawable.cow_small));
		imageCache.put(R.drawable.tree_small, BitmapFactory.decodeResource(getResources(), R.drawable.tree_small));
		imageCache.put(R.drawable.rock_small, BitmapFactory.decodeResource(getResources(), R.drawable.rock_small));
		imageCache.put(R.drawable.iceberg_small, BitmapFactory.decodeResource(getResources(), R.drawable.iceberg_small));
		imageCache.put(R.drawable.cow_large, BitmapFactory.decodeResource(getResources(), R.drawable.cow_large));
		imageCache.put(R.drawable.tree_large, BitmapFactory.decodeResource(getResources(), R.drawable.tree_large));
		imageCache.put(R.drawable.rock_large, BitmapFactory.decodeResource(getResources(), R.drawable.rock_large));
		imageCache.put(R.drawable.iceberg_large, BitmapFactory.decodeResource(getResources(), R.drawable.iceberg_large));
		
	}
	
	private void loadSounds() {
		soundCache.put(0, soundPool.load(this, R.raw.explosion, 1)) ;
		soundCache.put(1, soundPool.load(this, R.raw.button3, 1)) ;
		soundCache.put(2, soundPool.load(this, R.raw.button10, 1)) ;
		soundCache.put(3, soundPool.load(this, R.raw.robotsummer, 3)) ;
		
		
	}
	
	
	private void loadText() {
		textCache.put(R.raw.leveldata, getResources().openRawResource(R.raw.leveldata));
	}
	
	public static Map<Integer, Bitmap> getImageCache() {
		return imageCache;
	}
	
	public static Map<Integer, Integer> getSoundCache() {
		return soundCache;
	}
	
	public static SoundPool getSoundPool()
	{
		return soundPool ;
	}
	
	public static Map<Integer, InputStream> getTextCache() {
		return textCache;
	}
	
	public static void loadTitleScreen() {
		activity.setContentView(titleScreen);
	}


}