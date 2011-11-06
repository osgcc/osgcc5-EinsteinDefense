package org.osgcc.osgcc5.soapydroid.title;

import java.util.HashMap;
import java.util.Map;

import org.osgcc.osgcc5.soapydroid.EinsteinDefensePanel;
import org.osgcc.osgcc5.soapydroid.EinsteinDefenseThread;
import org.osgcc.osgcc5.soapydroid.R;
import org.osgcc.osgcc5.soapydroid.things.CollidableThing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.app.Activity;

public class TitleScreen extends View {

	private static final String DEBUG_TAG = "Title Screen";
	private static Map<Integer, Bitmap> imageCache = new HashMap<Integer, Bitmap>();
	public  static boolean isActive ;
	GestureDetector listener ;
	Context         context  ;
	public TitleScreen(Context context) {
		super(context);
		this.context = context ;
		isActive = true ;
		listener = new GestureDetector(context, new ButtonPush()) ;
		
		
	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		Log.d(DEBUG_TAG, "drawing canvas...");
		// draw test
		//canvas.drawBitmap(imageCache.get(R.drawable.cow), 50, 500, null);
		initImageCache() ;
		// draw background
		
		canvas.drawBitmap(imageCache.get(R.drawable.temptitle), 0, 0, null);
		canvas.drawBitmap(imageCache.get(R.drawable.startbutton), 573F, 500F,  null);
		canvas.drawBitmap(imageCache.get(R.drawable.helpbutton),  573F, 625F,  null);
		// draw collidable objects
		// NOTE: must figure out how to draw with rotation!
		
		
		// draw inactive projectiles
		
		}
		private void initImageCache()
		{
			imageCache.put(R.drawable.temptitle, BitmapFactory.decodeResource(getResources(), R.drawable.temptitle));
			imageCache.put(R.drawable.startbutton, BitmapFactory.decodeResource(getResources(), R.drawable.startbutton));
			imageCache.put(R.drawable.helpbutton, BitmapFactory.decodeResource(getResources(), R.drawable.helpbutton));
		}
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			return listener.onTouchEvent(event);
		}
		
		
		
		
	
	private class ButtonPush implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			float x = e.getX();
			float y = e.getY();
					Log.v("onDown", "") ;
			if(x >= 573F && x <= 673F)
			{
				if(y >= 500F && y <= 600F)
				{
					Log.v(DEBUG_TAG, "DETECTED") ;
					((Activity)context).setContentView(new EinsteinDefensePanel(context)) ;
				}
				if(y >= 625F && y <= 725F)
				{
					
				}
			}
			
			return true;
		}

		@Override
		public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
