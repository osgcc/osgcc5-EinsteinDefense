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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//setContentView(R.layout.main);
		Log.d(DEBUG_TAG, "starting view initialization...");
		setContentView(new EinsteinDefensePanel(this));
	}













}