package main.tictactoe;

/**
 * Block Class,
 * 
 * Holds id, flag, weight and button id
 * 
 * @author Stavros
 * 
 */
public class Block {

	/**
	 * ID
	 */
	private int id;

	/**
	 * weight
	 */
	private int weight;

	/**
	 * Button ID
	 */
	private int button_id;

	/**
	 * Flag
	 */
	private Flag flag;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param button_id
	 */
	public Block(int id, int button_id) {
		super();
		this.id = id;
		this.weight = 0;
		this.flag = Flag.CLOSED;
		this.button_id = button_id;
	}

	/**
	 * Sets Flag
	 * 
	 * @param flag
	 */
	public void setFlag(Flag flag) {
		this.flag = flag;
	}

	/**
	 * Returns Flag
	 * 
	 * @return flag
	 */
	public Flag getFlag() {
		return this.flag;
	}

	/**
	 * Returns ID
	 * 
	 * @return id
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Increases weight
	 * 
	 * @param add
	 *            weight
	 */
	public void increaseWeight(int add) {
		this.weight += add;
	}

	/**
	 * Resets weight
	 */
	public void resetWeight() {
		this.weight = 0;
	}

	/**
	 * Returns weight
	 * 
	 * @return weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Sets weight
	 * 
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Returns the view id of the button associated with
	 * 
	 * @return button_id
	 */
	public int getButton_id() {
		return button_id;
	}

}
