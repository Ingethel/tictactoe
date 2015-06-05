package main.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Data Singleton.
 * Creates and saves database for settings and highscores.
 * Used on start and end of application to access and store data from and to database.
 * Data is manipulated from Setting and Scoreboard classes during application run time.
 * 
 * @author Stavros
 *
 */
public class Data {

	/**
	 * Data instance
	 */
	private static Data instance = null;
	
	/**
	 * Application context
	 */
	private static Context context;
	
	/**
	 * Database name
	 */
	private final String Database = "Data";
	
	/**
	 * Player mode field name on database
	 */
	public final String player_mode = "player_mode";
	
	/**
	 * Player mode default value
	 */
	private static final int player_mode_default = 1;
	
	/**
	 * Difficulty mode field name on database
	 */
	public final String difficulty = "difficulty";
	
	/**
	 * Difficulty default value
	 */
	private static final int difficulty_default = 1;
	
	/**
	 * Easy wins field name on database
	 */
	public final String easy_wins = "easy_wins"; 
	
	/**
	 * Easy loses field name on database
	 */
	public final String easy_loses = "easy_loses"; 
	
	/**
	 * Easy draws field name on database
	 */
	public final String easy_draws = "easy_draws"; 

	/**
	 * Medium wins field name on database
	 */
	public final String medium_wins = "medium_wins"; 
	
	/**
	 * Medium loses field name on database
	 */
	public final String medium_loses = "medium_loses"; 
	
	/**
	 * Medium draws field name on database
	 */
	public final String medium_draws = "medium_draws"; 

	/**
	 * Hard wins field name on database
	 */
	public final String hard_wins = "hard_wins"; 
	
	/**
	 * Hard loses field name on database
	 */
	public final String hard_loses = "hard_loses"; 
	
	/**
	 * Hard draws field name on database
	 */
	public final String hard_draws = "hard_draws";
	
	/**
	 * Default value for for game progress on any difficulty
	 */
	private static final int _default = 0;
	
	/**
	 * SharedPreference instance -- database
	 */
	public SharedPreferences sharedpreferences;
	
	/**
	 * Database editor
	 */
	public Editor editor;
	
	/**
	 * Constructor,
	 * Retrieves data from database.
	 * Creates database with default values on first run.
	 * 
	 * @param context
	 */
	protected Data(Context context) {
		Data.context = context;
		sharedpreferences = Data.context.getSharedPreferences(Database, Context.MODE_PRIVATE);
		editor = sharedpreferences.edit();
		if(!sharedpreferences.contains(player_mode)){
			editor.putInt(player_mode, player_mode_default);
			editor.putInt(difficulty, difficulty_default);
			editor.putInt(easy_wins, _default);
			editor.putInt(easy_loses, _default);
			editor.putInt(easy_draws, _default);
			editor.putInt(medium_wins, _default);
			editor.putInt(medium_loses, _default);
			editor.putInt(medium_draws, _default);
			editor.putInt(hard_wins, _default);
			editor.putInt(hard_loses, _default);
			editor.putInt(hard_draws, _default);
			editor.commit();
		}
	}
	
	/**
	 * Returns instance of Data
	 * 
	 * @param context
	 * @return instance
	 */
	public static Data getInstance(Context context){
		if(Data.instance == null){
			Data.instance = new Data(context);
		}
		return Data.instance;
	}
	
	/**
	 * Save settings and score board
	 * 
	 * @param settings
	 * @param score
	 */
	public void saveData(Settings settings, Scoreboard score){
		editor.putInt(player_mode, settings.getPlayerMode());
		editor.putInt(difficulty, settings.getDifficulty());
		editor.putInt(easy_wins, score.getEasyScores().getWins());
		editor.putInt(easy_loses, score.getEasyScores().getLoses());
		editor.putInt(easy_draws, score.getEasyScores().getDraws());
		editor.putInt(medium_wins, score.getMediumScores().getWins());
		editor.putInt(medium_loses, score.getMediumScores().getLoses());
		editor.putInt(medium_draws, score.getMediumScores().getDraws());
		editor.putInt(hard_wins, score.getHardScores().getWins());
		editor.putInt(hard_loses, score.getHardScores().getLoses());
		editor.putInt(hard_draws, score.getHardScores().getDraws());
		editor.commit();
	}
	
}
