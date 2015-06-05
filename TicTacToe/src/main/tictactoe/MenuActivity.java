package main.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * Menu Activity
 * 
 * @author Stavros
 * 
 */
public class MenuActivity extends Activity {

	/**
	 * Settings instance
	 */
	private Settings settings;
	
	/**
	 * Scoreboard instance
	 */
	private Scoreboard score;

	/**
	 * Database for settings and highscores
	 */
	private Data data;
	
	/**
	 * Custon font for the application
	 */
	private static Typeface font;
	
	/**
	 * onCreate implementation,
	 * 
	 * Creates menu with buttons through XML layout for New Game, Settings, Exit
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		data = Data.getInstance(getApplicationContext());
		font = Typeface.createFromAsset(getAssets(), "fonts/LucidaHandwriting.ttf");
		setContentView(R.layout.activity_menu);
		settings = Settings.getInstance(getApplicationContext());
		score = Scoreboard.getInstance(getApplicationContext());
	}

	/**
	 * onClick Implementation,
	 * 
	 * Handles Touch Event on Buttons
	 * 
	 * @param v
	 *            View
	 */
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.newGame_button:
			i = new Intent(this, GameActivity.class);
			startActivity(i);
			break;
		case R.id.settings_button:
			i = new Intent(this, SettingsActivity.class);
			startActivity(i);
			break;
		case R.id.score_button:
			i = new Intent(this, ScoreboardActivity.class);
			startActivity(i);
			break;
		case R.id.exit:
			super.finish();
			break;
		}
	}

	/**
	 * Returns the custom font
	 * 
	 * @return font
	 */
	public static Typeface getApplicationFont(){
		return MenuActivity.font;
	}
	
	/**
	 * Save current settings and highscores on database
	 */
	@Override
	public void onStop(){
		super.onStop();
		Data.getInstance(getApplicationContext()).saveData(settings, score);
	}
}
