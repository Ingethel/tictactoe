package main.tictactoe;

import android.content.Context;

/**
 * Medium difficulty level of game intelligence. 
 * 
 * Child class of Intelligence.class
 * 
 * @author Stavros
 *
 */
public class I_Medium extends Intelligence{
	
	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public I_Medium(Context context) {
		super(context);
	}
	
	@Override
	protected void AIPlay(){
		if (counter == 0) {
			int move;
			move = rand.nextInt(unflagged.size());
			setMove(unflagged.get(move));
			counter++;
		} else {
			assignWeights();
			play();
		}
		resetAll();
	}
	
}
