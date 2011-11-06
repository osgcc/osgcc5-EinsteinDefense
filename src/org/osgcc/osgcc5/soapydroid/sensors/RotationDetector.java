package org.osgcc.osgcc5.soapydroid.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.util.Log;



public class RotationDetector {

	public static final String DEBUG_TAG = "EinsteinDefenseActivity";
	
	private float yOrientation;
	private float xOrientation;
	
	private SensorManager sensorManager;
	private Sensor sensor;
	
	private Context context;
	
	public RotationDetector(Context context) {
		
		this.context = context;
		yOrientation = 0;
		xOrientation = 0;
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		sensorManager.registerListener(mListener, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
	
	public float getYWeight() {
		return yOrientation * 0.1f;
	}
	
	public float getXWeight() {
		return -xOrientation * 0.1f;
	}
	
	private final SensorEventListener mListener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
				//Log.v(DEBUG_TAG, "orientation changed to "+event.values[0]+","+event.values[1]);
				xOrientation = event.values[0];
				yOrientation = event.values[1];
			}
			
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};

}
