package org.osgcc.osgcc5.soapydroid.score;

import java.util.List;

public class ScoreManager {
	
	/**
	 * Current score.
	 */
	public int score;
	
	/**
	 * Starting life value?
	 */
	//public static final int INIT_LIFE = 3;
	public static final int INIT_LIFE = 1;
	
	/**
	 * Current life points.
	 */
	public int life;
	
	public ScoreManager() {
		score = 0;
		life = INIT_LIFE;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLife() {
		return life;
	}
	
	public void decrementLife() {
		life--;
	}
	
	public void incrementLife() {
		life++;
	}
	
	public void incrementScore(int increment) {
		score += increment;
	}
	
	public List<Integer> getHighScores() {
		
		// TODO
		
		
		return null;
		
	}
	
	public boolean submitScore() {
		
		// TODO
		
		return false;
		
	}
	
}
