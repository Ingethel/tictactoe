package main.tictactoe;

import android.content.Context;

/**
 * Hard difficulty level of game intelligence. 
 * 
 * Child class of Intelligence.class
 * 
 * @author Stavros
 *
 */
public class I_Hard extends Intelligence {

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public I_Hard(Context context) {
		super(context);
	}

	/**
	 * Checks special condition of game progress where the AI has chosen centre
	 * block and player two diagonal ones
	 * 
	 * @return TRUE/FALSE
	 */
	private boolean sameDiagonal() {
		if (((blocks[0].getFlag() == blocks[8].getFlag()) && blocks[8]
				.getFlag() != Flag.CLOSED)
				|| ((blocks[2].getFlag() == blocks[6].getFlag()) && blocks[6]
						.getFlag() != Flag.CLOSED))
			return true;
		else
			return false;
	}

	/**
	 * Checks special condition of game progress where the player has centre
	 * block and a corner one and the AI has its diagonal
	 * 
	 * @return TRUE/FALSE
	 */
	private boolean differentDiagonal() {
		if (((blocks[0].getFlag() != blocks[8].getFlag())
				&& blocks[0].getFlag() != Flag.CLOSED && blocks[8].getFlag() != Flag.CLOSED)
				|| ((blocks[2].getFlag() != blocks[6].getFlag())
						&& blocks[2].getFlag() != Flag.CLOSED && blocks[6]
						.getFlag() != Flag.CLOSED))
			return true;
		else
			return false;
	}

	/**
	 * Checks a special condition of game progress
	 * 
	 * @return TRUE/FALSE
	 */
	private boolean checkTrap() {
		if (blocks[4].getFlag() == Flag.PLAYER_2 && sameDiagonal()) {
			unflagged.clear();
			unflagged.add(blocks[1]);
			unflagged.add(blocks[3]);
			unflagged.add(blocks[5]);
			unflagged.add(blocks[7]);
			return true;
		} else if (blocks[4].getFlag() == Flag.PLAYER_1 && differentDiagonal()) {
			unflagged.clear();
			if (blocks[0].getFlag() == Flag.CLOSED)
				unflagged.add(blocks[0]);
			if (blocks[2].getFlag() == Flag.CLOSED)
				unflagged.add(blocks[2]);
			if (blocks[6].getFlag() == Flag.CLOSED)
				unflagged.add(blocks[6]);
			if (blocks[8].getFlag() == Flag.CLOSED)
				unflagged.add(blocks[8]);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Initialises weights. Used by AI in hard difficulty
	 */
	private void setInitialWeights() {
		blocks[0].setWeight(50);
		blocks[1].setWeight(0);
		blocks[2].setWeight(50);
		blocks[3].setWeight(0);
		blocks[4].setWeight(100);
		blocks[5].setWeight(0);
		blocks[6].setWeight(50);
		blocks[7].setWeight(0);
		blocks[8].setWeight(50);
	}

	@Override
	protected void AIPlay() {
		if (counter == 0) {
			setInitialWeights();
			play();
		}
		else if (counter == 1 && checkTrap()) {
			int move;
			move = rand.nextInt(unflagged.size());
			setMove(unflagged.get(move));
			unflagged.clear();
			for (int i = 0; i < BLOCKS; i++)
				if (blocks[i].getFlag() == Flag.CLOSED)
					unflagged.add(blocks[i]);
		} else {
			assignWeights();
			play();
		}
		resetAll();
		counter++;
	}

}
