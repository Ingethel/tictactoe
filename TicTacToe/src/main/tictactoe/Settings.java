package main.tictactoe;

import android.content.Context;

/**
 * Settings singleton instance to pass settings preferences to other classes.
 * 
 * Associated with SettingsActivity
 * 
 * @author Stavros
 *
 */
public class Settings {

	/**
	 * Settings instance
	 */
	private static Settings instance = null;

	/**
	 * Player mode
	 */
	private static int player_mode;
	
	/**
	 * Dificulty level
	 */
	private static int difficulty;
	
	/**
	 * Constructor,
	 * Retrieves settings from database
	 * 
	 * @param context
	 */
	protected Settings(Context context){
		Data data = Data.getInstance(context);
		Settings.player_mode = data.sharedpreferences.getInt(data.player_mode, 0);
		Settings.difficulty = data.sharedpreferences.getInt(data.difficulty, 0);
	}
	
	/**
	 * Returns instance of Settings
	 * 
	 * @param context
	 * @return instance
	 */
	public static Settings getInstance(Context context){
		if(Settings.instance == null){
			Settings.instance = new Settings(context);
		}
		return Settings.instance;
	}
	
	/**
	 * Returns player mode
	 * 
	 * @return player_mode
	 */
	public int getPlayerMode(){
		return Settings.player_mode;
	}
	
	/**
	 * Sets player mode
	 * 
	 * @param mode
	 */
	public void setPlayerMode(int mode){
		Settings.player_mode = mode;
	}
	
	/**
	 * Returns difficulty level
	 * 
	 * @return difficulty
	 */
	public int getDifficulty(){
		return Settings.difficulty;
	}
	
	/**
	 * Sets difficulty level
	 * 
	 * @param level
	 */
	public void setDifficulty(int level){
		Settings.difficulty = level;
	}
	
	/**
	 * Destroy instance
	 */
	public void kill(){
		Settings.instance = null;
	}
	
}
