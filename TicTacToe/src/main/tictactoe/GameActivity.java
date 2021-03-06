package main.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Game Activity,
 * 
 * Creates board through XML layout, Handles Touch Input
 * 
 * @author Stavros
 * 
 */
public class GameActivity extends Activity {

	/**
	 * Activity Context
	 */
	public final Context context = this;

	/**
	 * Buttons of the game
	 */
	private CButton CB1, CB2, CB3, CB4, CB5, CB6, CB7, CB8, CB9;
	
	/**
	 * Turn indicator
	 */
	private static CTextView TURN_INDICATOR;
	private static CTextView TURN_TEXT;
	
	/**
	 * Number of Blocks/Buttons
	 */
	private static int BLOCKS = 9;

	/**
	 * Array to hold the buttons, also used as association of Block and Button
	 * as per position in array
	 */
	private static final CButton[] buttons = new CButton[BLOCKS];

	/**
	 * Flag to indicate the Player turn
	 */
	private static Flag turn;

	/**
	 * Flag to indicate status of the game
	 */
	private static boolean end_of_game;

	/**
	 * Instance of the game's intelligence
	 */
	private static Intelligence AI;
	
	/**
	 * onCreate Implementation Runs upon start of the activity
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
/*
		int id = R.id.button1;
		for(int i = 0; i < 9; i++){
			buttons[i] = (CButton) findViewById(id);
			id++;
		}
*/	
		int size;
		if(MenuActivity.getWidth() < MenuActivity.getHeight())
			size = MenuActivity.getWidth();
		else
			size = MenuActivity.getHeight();
		size = 2*size/9;
		
		CB1 = (CButton) findViewById(R.id.button1);
		buttons[0] = CB1;
		buttons[0].setWidth(size);
		buttons[0].setHeight(size);
		
		CB2 = (CButton) findViewById(R.id.button2);
		buttons[1] = CB2;
		buttons[1].setWidth(size);
		buttons[1].setHeight(size);
		
		CB3 = (CButton) findViewById(R.id.button3);
		buttons[2] = CB3;
		buttons[2].setWidth(size);
		buttons[2].setHeight(size);
		
		CB4 = (CButton) findViewById(R.id.button4);
		buttons[3] = CB4;
		buttons[3].setWidth(size);
		buttons[3].setHeight(size);
		
		CB5 = (CButton) findViewById(R.id.button5);
		buttons[4] = CB5;
		buttons[4].setWidth(size);
		buttons[4].setHeight(size);
		
		CB6 = (CButton) findViewById(R.id.button6);
		buttons[5] = CB6;
		buttons[5].setWidth(size);
		buttons[5].setHeight(size);
		
		CB7 = (CButton) findViewById(R.id.button7);
		buttons[6] = CB7;
		buttons[6].setWidth(size);
		buttons[6].setHeight(size);
		
		CB8 = (CButton) findViewById(R.id.button8);
		buttons[7] = CB8;
		buttons[7].setWidth(size);
		buttons[7].setHeight(size);
		
		CB9 = (CButton) findViewById(R.id.button9);
		buttons[8] = CB9;
		buttons[8].setWidth(size);
		buttons[8].setHeight(size);
		
		TURN_TEXT = (CTextView) findViewById(R.id.cTextView1);
		TURN_TEXT.setValues(null, 0, 20);
		TURN_INDICATOR = (CTextView) findViewById(R.id.cTextView2);
		TURN_INDICATOR.setValues(null, Color.BLUE, 20);
		TURN_INDICATOR.setText(R.string.Player_1_label);
		
		if (Settings.getInstance(getApplicationContext()).getPlayerMode() == 2){
			AI = new Intelligence(context);
			TURN_INDICATOR.setVisibility(0);
			TURN_TEXT.setVisibility(0);
		} else {
			TURN_INDICATOR.setVisibility(8);
			TURN_TEXT.setVisibility(8);
			if (Settings.getInstance(getApplicationContext()).getDifficulty() == 0)
				AI = new I_Easy(context);
			if (Settings.getInstance(getApplicationContext()).getDifficulty() == 1)
				AI = new I_Medium(context);
			if (Settings.getInstance(getApplicationContext()).getDifficulty() == 2)
				AI = new I_Hard(context);
		}
		
		end_of_game = true;
		
		StartGame();
	}

	/**
	 * onClick function for buttons, serves as player turn/play
	 * 
	 * @param v
	 *            View
	 */
	public void onClickButton(View v) {
		if (!end_of_game)
			for (int i = 0; i < BLOCKS; i++)
				if (v.getId() == buttons[i].getId())
					if ((Settings.getInstance(getApplicationContext()).getPlayerMode() == 2)
							|| (Settings.getInstance(getApplicationContext()).getPlayerMode() == 1 && turn == Flag.PLAYER_1))
						AI.PlayerPlay(i, turn);
		if (end_of_game) {
			endOfGameMessage();
		}
	}

	/**
	 * onClick function for exit/menu button
	 * 
	 * @param v
	 */
	public void onClickExit(View v) {
		if (v.getId() == R.id.menu_button)
			Kill();
	}
	
	/**
	 * Resets game variables and start AI thread
	 */
	public void StartGame() {
		if (isEndOfGame()) {
			for (int i = 0; i < BLOCKS; i++)
				buttons[i].setText("");

			turn = Flag.PLAYER_1;
			AI.restart();

			setEndOfGame(false);

			print("Player 1 turn");
		}
	}

	/**
	 * Stops activity
	 */
	public void Kill() {
		super.finish();
	}

	/**
	 * Log print method
	 */
	public static void print(String msg) {
		Log.w("CHECK", msg);
	}

	/**
	 * Returns Player turn
	 * 
	 * @return turn
	 */
	public static Flag getTurn() {
		return GameActivity.turn;
	}

	/**
	 * Sets the turn to the next player's
	 */
	public static void nextTurn() {
		GameActivity.turn = (turn == Flag.PLAYER_1) ? Flag.PLAYER_2
				: Flag.PLAYER_1;
	}

	/**
	 * Sets play on Screen
	 * 
	 * @param pos
	 *            Block Position on List
	 * @param player
	 *            Player Flag
	 */
	public static void setPlay(int pos, Flag player) {
		if(player == Flag.PLAYER_1){
			buttons[pos].setTextColor(Color.BLUE);
			buttons[pos].setText(R.string.Player_1_label);
			TURN_INDICATOR.setTextColor(Color.RED);
			TURN_INDICATOR.setText(R.string.Player_2_label);
		}
		else{			
			buttons[pos].setTextColor(Color.RED);
			buttons[pos].setText(R.string.Player_2_label);
			TURN_INDICATOR.setTextColor(Color.BLUE);
			TURN_INDICATOR.setText(R.string.Player_1_label);
		}		
	}

	/**
	 * Returns game status
	 * 
	 * @return end_of_game
	 */
	public static boolean isEndOfGame() {
		return GameActivity.end_of_game;
	}

	/**
	 * Sets game status
	 * 
	 * @param flag
	 */
	public static void setEndOfGame(boolean flag) {
		GameActivity.end_of_game = flag;
	}

	/**
	 * Returns the ID of the given button
	 * 
	 * @param i
	 *            button position in list
	 * @return ID
	 */
	public static int getButtonId(int i) {
		return GameActivity.buttons[i].getId();
	}

	/**
	 * Outputs message for end of game, Announces winner or draw, Exit/Replay
	 * buttons
	 */
	private void endOfGameMessage() {
		GameActivity.setEndOfGame(true);
		if (Settings.getInstance(getApplicationContext()).getPlayerMode() == 1)
			Scoreboard.getInstance(getApplicationContext()).updateScore(AI.getWinner());

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.message_layout, null);

		final AlertDialog dialog = new AlertDialog.Builder(context).create();

		CTextView _message = (CTextView) view.findViewById(R.id.end_message);
		_message.setTextSize(30);
		if (AI.getWinner().getID() == 0)
			_message.setText("Draw");
		else
			_message.setText("Player " + Integer.toString(AI.getWinner().getID())
					+ " Wins!");
		_message.setGravity(Gravity.CENTER_HORIZONTAL);

		Button _exit = (Button) view.findViewById(R.id.exit_game);
		_exit.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dialog.dismiss();
				Kill();
		    }
		});
		
		Button _replay = (Button) view.findViewById(R.id.replay);
		_replay.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dialog.dismiss();
				StartGame();
		    }
		});
		
		dialog.setView(view);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
