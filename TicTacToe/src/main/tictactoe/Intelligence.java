package main.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.content.Context;

/**
 * Default game intelligence that checks game progress. 
 * 
 * Holds certain basic functions and variables that are used by inherited classes
 * 
 * @author Stavros
 * 
 */
public class Intelligence {

	/**
	 * Top priority weight for block
	 */
	private static final int TOP = 100;

	/**
	 * Medium priority weight for block
	 */
	private static final int MEDIUM = 5;

	/**
	 * Low priority weight for block
	 */
	private static final int LOW = 1;

	/**
	 * Number of Blocks
	 */
	protected static final int BLOCKS = 9;

	/**
	 * Number of winning sets
	 */
	private static final int SETS = 8;

	/**
	 * Number of elements per set
	 */
	private static final int ELEMENTS = 3;

	/**
	 * List of the winning sets
	 */
	private final int[][] winningSets = { { 1, 2, 3 }, { 4, 5, 6 },
			{ 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 },
			{ 3, 5, 7 } };

	/**
	 * random number generator
	 */
	protected Random rand = new Random();

	/**
	 * list to hold open blocks
	 */
	protected List<Block> unflagged = new ArrayList<Block>();

	/**
	 * flag for winner id
	 */
	private Flag winner;

	/**
	 * Array to hold the blocks, also used as association of Block and Button as
	 * per position in array
	 */
	protected static Block[] blocks = new Block[BLOCKS];

	/**
	 * Context of the belonging activity
	 */
	protected Context context;

	/**
	 * Counter for number of turns, used by AI
	 */
	protected static int counter;

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public Intelligence(Context context) {
		super();

		this.context = context;
		restart();
	}

	/**
	 * Set default values
	 */
	public void restart() {
		counter = 0;
		winner = Flag.CLOSED;
		unflagged.clear();
		for (int i = 0; i < BLOCKS; i++) {
			blocks[i] = new Block(i + 1, GameActivity.getButtonId(i));
			unflagged.add(blocks[i]);
		}
	}

	/**
	 * Checks if there is a winner
	 * 
	 * @return TRUE/FALSE
	 */
	private boolean checkWinningCondition(Flag player) {
		int i = 0;
		boolean flag = false;
		while (i < SETS && !flag) {
			if ((blocks[winningSets[i][0] - 1].getFlag() == player)
					&& (blocks[winningSets[i][1] - 1].getFlag() == player)
					&& (blocks[winningSets[i][2] - 1].getFlag() == player)) {
				GameActivity.print("Player " + player.getID() + " wins");
				winner = player;
				flag = true;
			}
			i++;
		}
		return flag;
	}

	/**
	 * Checks if there is a draw
	 * 
	 * @return TRUE/FALSE
	 */
	private boolean checkDraw() {
		if (unflagged.size() == 0) {
			GameActivity.print("draw");
			winner = Flag.CLOSED;
			return true;
		}
		return false;
	}

	/**
	 * checks all end of game conditions
	 * 
	 * @param player
	 */
	protected void checkEndOfGame(Flag player) {
		if (checkWinningCondition(player))
			GameActivity.setEndOfGame(true);
		else if (checkDraw())
			GameActivity.setEndOfGame(true);
	}

	/**
	 * Checks if the pressed block is a valid play and sets it accordingly
	 * 
	 * @param i
	 * @param player
	 */
	public void PlayerPlay(int i, Flag player) {
		if (blocks[i].getFlag() == Flag.CLOSED) {
			blocks[i].setFlag(player);
			GameActivity.setPlay(i, player);
			GameActivity.nextTurn();
			unflagged.remove(blocks[i]);
			GameActivity.print(Integer.toString(unflagged.size()));
			checkEndOfGame(player);
			if (Settings.getInstance(context).getPlayerMode() == 1
					&& !GameActivity.isEndOfGame()) {
				AIPlay();
				checkEndOfGame(Flag.PLAYER_2);
				GameActivity.nextTurn();
				GameActivity.print(Integer.toString(unflagged.size()));
				GameActivity.print("Counter: "+Integer.toString(counter));
			}
		}
	}

	/**
	 * Reset weights of the blocks
	 */
	protected void resetAll() {
		for (int i = 0; i < BLOCKS; i++) {
			blocks[i].resetWeight();
		}
	}

	/**
	 * Assign weights on blocks based on current progress of game
	 */
	protected void assignWeights() {
		for (int i = 0; i < SETS; i++) {
			int counter[] = { 0, 0, 0 };
			for (int j = 0; j < ELEMENTS; j++)
				counter[blocks[winningSets[i][j] - 1].getFlag().getID()]++;
			if (counter[0] == 1 && counter[2] == 2) {
				for (int j = 0; j < ELEMENTS; j++) {
					blocks[winningSets[i][j] - 1].increaseWeight(TOP);
				}
			} else if (counter[0] == 1 && counter[1] == 2) {
				for (int j = 0; j < ELEMENTS; j++) {
					blocks[winningSets[i][j] - 1].increaseWeight(MEDIUM);
				}
			} 
			else {
				if (Settings.getInstance(context).getDifficulty() == 1) {
					if (counter[0] == 2) {
						for (int j = 0; j < ELEMENTS; j++) {
							blocks[winningSets[i][j] - 1].increaseWeight(LOW);
						}
					}
				}
				if (Settings.getInstance(context).getDifficulty() == 2) {
					if (counter[0] == 2) {
						for (int j = 0; j < ELEMENTS; j++) {
							blocks[winningSets[i][j] - 1].increaseWeight(LOW);
						}
					} else if ((counter[1] == 1 && counter[2] == 1)
							&& counter[0] == 1) {
						for (int j = 0; j < ELEMENTS; j++) {
							if (blocks[winningSets[i][j] - 1].getWeight() < 5) {
								blocks[winningSets[i][j] - 1].setWeight(0);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Chooses a valid block to play
	 */
	protected void play() {
		int id = 0;
		final List<Integer> candidates = new ArrayList<Integer>();
		candidates.add(id);
		for (int i = 1; i < unflagged.size(); i++) {
			if (unflagged.get(i).getWeight() > unflagged.get(id).getWeight()) {
				candidates.clear();
				id = i;
				candidates.add(i);
			} else if (unflagged.get(i).getWeight() == unflagged.get(id)
					.getWeight()) {
				candidates.add(i);
			}
		}

		final int r = rand.nextInt(candidates.size());
		setMove(unflagged.get(candidates.get(r)));
	}

	/**
	 * Turn of the AI on 1-Player mode
	 */
	protected void AIPlay() {}

	/**
	 * Sets properties of chosen block
	 * 
	 * @param move
	 *            chosen block
	 */
	protected void setMove(Block move) {
		move.setFlag(Flag.PLAYER_2);
		unflagged.remove(move);
		GameActivity.setPlay(move.getID() - 1, Flag.PLAYER_2);
	}

	/**
	 * Returns the winner
	 * 
	 * @return winner
	 */
	public Flag getWinner() {
		return winner;
	}

}
