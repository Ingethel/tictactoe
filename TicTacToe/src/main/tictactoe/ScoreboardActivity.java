package main.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Score board activity class. Creates a layout to show scores
 * 
 * @author Stavros
 *
 */
public class ScoreboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoreboard);
		
		CTextView e_wins = (CTextView)findViewById(R.id.e_wins);
		e_wins.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getEasyScores().getWins()));
		CTextView m_wins = (CTextView)findViewById(R.id.m_wins);
		m_wins.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getMediumScores().getWins()));
		CTextView h_wins = (CTextView)findViewById(R.id.h_wins);
		h_wins.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getHardScores().getWins()));
		
		CTextView e_loses = (CTextView)findViewById(R.id.e_loses);
		e_loses.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getEasyScores().getLoses()));
		CTextView m_loses = (CTextView)findViewById(R.id.m_loses);
		m_loses.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getMediumScores().getLoses()));
		CTextView h_loses = (CTextView)findViewById(R.id.h_loses);
		h_loses.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getHardScores().getLoses()));

		CTextView e_draws = (CTextView)findViewById(R.id.e_draws);
		e_draws.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getEasyScores().getDraws()));
		CTextView m_draws = (CTextView)findViewById(R.id.m_draws);
		m_draws.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getMediumScores().getDraws()));
		CTextView h_draws = (CTextView)findViewById(R.id.h_draws);
		h_draws.setText(Integer.toString(Scoreboard.getInstance(getApplicationContext()).getHardScores().getDraws()));
		
	}
	
	/**
	 * Handles button click for exit/menu
	 * 
	 * @param v
	 */
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.back:
			super.finish();
		}
	}
}
