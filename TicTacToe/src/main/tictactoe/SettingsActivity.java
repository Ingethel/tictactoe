package main.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Settings activity class. Creates a layout to set game preferences
 * 
 * @author Stavros
 *
 */
public class SettingsActivity extends Activity implements OnClickListener {

	/**
	 * Radio Group that defines the player mode (1-Player or 2-Player)
	 */
	private RadioGroup _playerMode;

	/**
	 * Seek Bar that defines the AI level (Easy/Medium/hard)
	 */
	private SeekBar _difficultyBar;

	/**
	 * Button to exit activity
	 */
	private View _confirm;

	/**
	 * temporary player mode
	 */
	private static int temp_playerMode;
	
	/**
	 * temporary difficulty
	 */
	private static int temp_difficulty;
	
	/**
	 * onCreate Implementation,
	 * 
	 * Creates an XML layout to set player mode and game difficulty
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		temp_playerMode = Settings.getInstance(getApplicationContext()).getPlayerMode();
		temp_difficulty = Settings.getInstance(getApplicationContext()).getDifficulty();
		
		_playerMode = (RadioGroup) findViewById(R.id.Player_mode);
		if (Settings.getInstance(getApplicationContext()).getPlayerMode() == 1)
			_playerMode.check(R.id.player1);
		else
			_playerMode.check(R.id.player2);

		_difficultyBar = (SeekBar) findViewById(R.id.difficulty_level);
		_difficultyBar.setProgress(Settings.getInstance(getApplicationContext()).getDifficulty());
		_difficultyBar.setEnabled(Settings.getInstance(getApplicationContext()).getPlayerMode() == 1);
		_difficultyBar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onProgressChanged(SeekBar arg0, int level,
							boolean arg2) {
						temp_difficulty = level;
					}

					@Override
					public void onStartTrackingTouch(SeekBar arg0) {
					}

					@Override
					public void onStopTrackingTouch(SeekBar arg0) {
					}

				});

		_confirm = findViewById(R.id.confirm);
		_confirm.setOnClickListener(this);

		CTextView text = (CTextView)findViewById(R.id.difficulty_text);
		text.setValues(null, 0, 24.5f);
	}

	/**
	 * Click handler for the confirm button
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.confirm:
			Settings.getInstance(getApplicationContext()).setDifficulty(temp_difficulty);
			Settings.getInstance(getApplicationContext()).setPlayerMode(temp_playerMode);
			super.finish();
		}
	}

	/**
	 * Click handler for the radio buttons
	 * 
	 * @param v
	 *            View
	 */
	public void onRadioButtonClick(View v) {
		boolean checked = ((RadioButton) v).isChecked();
		switch (v.getId()) {
		case R.id.player1:
			if (checked){
				temp_playerMode = 1;
				_difficultyBar.setEnabled(true);
				break;
			}
		case R.id.player2:
			if (checked){
				temp_playerMode = 2;
				_difficultyBar.setEnabled(false);
				break;
			}
		}
	}
}
