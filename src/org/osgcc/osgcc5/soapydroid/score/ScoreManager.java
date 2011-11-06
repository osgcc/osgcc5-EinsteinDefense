package org.osgcc.osgcc5.soapydroid.score;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

public class ScoreManager {
	
	/**
	 * Current score.
	 */
	public int score;
	
	/**
	 * Starting life value?
	 */
	public static final int INIT_LIFE = 3;
	//public static final int INIT_LIFE = 1;
	
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

		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 10000);
			HttpConnectionParams.setSoTimeout(httpClient.getParams(), 10000);
			HttpPost httpPost = new HttpPost("http://www.cs.pitt.edu/~conrada/some_php_script.php");  
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
			nameValuePairs.add(new BasicNameValuePair("score", Integer.toString(score)));  
			//nameValuePairs.add(new BasicNameValuePair("name", "value2")); 
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs)); 
			HttpResponse response = httpClient.execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		}
		
		return false;
		
	}
	
}
