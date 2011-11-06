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
    
    
    
    
    
    
    class EinsteinDefensePanel extends SurfaceView implements SurfaceHolder.Callback {
    	
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
			gestureListener = new GestureDetector(EinsteinDefenseActivity.this, 
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

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			if (!gameThread.isAlive()) {
				gameThread = new EinsteinDefenseThread(this);
			}
			gameThread.setRunning(true);
			gameThread.start();
			
		}

		@Override
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
    
    class EinsteinDefenseThread extends Thread {
    	
    	/**
    	 * reference to main panel
    	 */
    	EinsteinDefensePanel mainView;
    	
    	boolean running;

    	public EinsteinDefenseThread(EinsteinDefensePanel mainView) {
    		this.mainView = mainView;
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
    					// update physics
    					
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
    
    class EinsteinGestureListener implements GestureDetector.OnGestureListener, 
			GestureDetector.OnDoubleTapListener {
    	
    	/**
    	 * reference to main panel
    	 */
    	EinsteinDefensePanel mainView;
    	
    	public EinsteinGestureListener(EinsteinDefensePanel mainView) {
    		this.mainView = mainView;
    	}
    	
		@Override
		public boolean onDoubleTap(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
    	
    	
    	
    	
    	
    	
    }
    
    
}