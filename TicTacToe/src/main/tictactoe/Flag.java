package main.tictactoe;

/**
 * Enum class, used for assigning and checking labels
 * 
 * @author Stavros
 * 
 */
public enum Flag {

	/**
	 * Flag for Player 1
	 */
	PLAYER_1(1),
	
	/**
	 * Flag for Player 2
	 */
	PLAYER_2(2),
	
	/**
	 * Flag for unassigned
	 */
	CLOSED(0);

	/**
	 * Integer label
	 */
	private int id;

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	private Flag(int id) {
		this.id = id;
	}

	/**
	 * Return integer id
	 * 
	 * @return 0 -> Closed, 1 -> Player 1, 2 -> Player 2
	 */
	public int getID() {
		return this.id;
	}

}
