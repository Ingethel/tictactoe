package main.tictactoe;

import android.content.Context;

/**
 * Easy difficulty level of game intelligence. 
 * 
 * Child class of Intelligence.class
 * 
 * @author Stavros
 *
 */
public class I_Easy extends Intelligence{

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public I_Easy(Context context) {
		super(context);
	}
	
	@Override
	protected void AIPlay(){
		int move;
		move = rand.nextInt(unflagged.size());
		setMove(unflagged.get(move));
	}
}
