package org.osgcc.osgcc5.soapydroid;

import android.graphics.Canvas;

public class EinsteinDefenseThread extends Thread {

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