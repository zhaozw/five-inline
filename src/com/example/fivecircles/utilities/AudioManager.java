package com.example.fivecircles.utilities;

import org.andengine.audio.sound.Sound;

import android.content.SharedPreferences;

import com.example.fivecircles.ResourcesManager;
import com.example.fivecircles.activities.GameActivity;

public class AudioManager {
	
	public static final String PREFS_NAME = "FiveNeighborPreferences";
	
	//Singleton Pattern
    private static final AudioManager instance = new AudioManager();
    
    private AudioManager(){}
    
    public static AudioManager getInstance(){
    	return instance;
    }
    
    public boolean isSoundEnable(){
    	//Check if the sound is enable
    	//We store sound state inside a Android Preference
    	GameActivity gameActivity = ResourcesManager.getInstance().getActivity();
		SharedPreferences settings = gameActivity.getSharedPreferences(PREFS_NAME, 0);
		return settings.getBoolean("isSoundEnable", true); 
    
    }

    public void turnOnSound(){
		//Set Preference
		GameActivity gameActivity = ResourcesManager.getInstance().getActivity();
		SharedPreferences settings = gameActivity.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("isSoundEnable", true);
		editor.commit();
	}
	
	public void turnOffSound(){
		//Set Preference
		GameActivity gameActivity = ResourcesManager.getInstance().getActivity();
		SharedPreferences settings = gameActivity.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("isSoundEnable", false);
		editor.commit();
	}
    
    //Sounds which We need in this game
    public synchronized void soundSelectPlayer(){
    	if(this.isSoundEnable()){
    		Sound sound = ResourcesManager.getInstance().getSelectPlayerSound();
        	sound.play();
    	}
    	
    }
    
    public synchronized void soundRemovePlayers(){
    	if(this.isSoundEnable()){
    		Sound sound = ResourcesManager.getInstance().getRemovePlayersSound();
    		sound.play();
    	}	
    }
    
    public synchronized void soundAppearPlayers(){
    	if(this.isSoundEnable()){
    		Sound sound = ResourcesManager.getInstance().getAppearPlayersSound();
    		sound.play();
    	}	
    }
    
    public synchronized void soundGameOver(){
    	if(this.isSoundEnable()){
    		Sound sound = ResourcesManager.getInstance().getGameOverPlayersSound();
    		sound.play();
    	}	
    }
    
}
