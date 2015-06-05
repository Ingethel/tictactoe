package main.tictactoe;

import android.content.Context;

/**
 * Scoreboard singleton instance to hold the scores achieved across all
 * difficulties.
 * 
 * Associated with ScoreboardActivity
 * 
 * @author Stavros
 * 
 */
public class Scoreboard {

	/**
	 * Rating class. Represents a difficulty set. Holds wins/loses/draws and
	 * update function for the score
	 * 
	 * @author Stavros
	 * 
	 */
	public class Rating {

		/**
		 * Wins of instanced difficulty
		 */
		private int wins;

		/**
		 * Loses of instanced difficulty
		 */
		private int loses;

		/**
		 * Draws of instanced difficulty
		 */
		private int draws;

		/**
		 * Constructor
		 */
		public Rating(int wins, int loses, int draws) {
			this.wins = wins;
			this.loses = loses;
			this.draws = draws;
		};

		/**
		 * Returns wins
		 * 
		 * @return wins
		 */
		public int getWins() {
			return wins;
		}

		/**
		 * Returns loses
		 * 
		 * @return loses
		 */
		public int getLoses() {
			return loses;
		}

		/**
		 * Returns draws
		 * 
		 * @return draws
		 */
		public int getDraws() {
			return draws;
		}

		/**
		 * Update score given the game winner
		 * 
		 * @param winner
		 */
		public void updateScore(Flag winner) {
			switch (winner.getID()) {
			case 1:
				wins++;
				break;
			case 2:
				loses++;
				break;
			case 0:
				draws++;
				break;
			default:
				break;
			}
		}
	};

	/**
	 * Instance of score for easy difficulty
	 */
	private static Rating Easy;

	/**
	 * Instance of score for medium difficulty
	 */
	private static Rating Medium;

	/**
	 * Instance of score for hard difficulty
	 */
	private static Rating Hard;

	/**
	 * Instance of Scoreboard
	 */
	private static Scoreboard instance;

	/**
	 * Application context
	 */
	private static Context context;

	/**
	 * Constructor,
	 * Retrieves score board from database
	 * 
	 * @param context
	 */
	protected Scoreboard(Context context) {
		Scoreboard.context = context;
		Data data = Data.getInstance(context);
		Scoreboard.Easy = new Rating(data.sharedpreferences.getInt(
				data.easy_wins, 0), data.sharedpreferences.getInt(
				data.easy_loses, 0), data.sharedpreferences.getInt(
				data.easy_draws, 0));
		Scoreboard.Medium = new Rating(data.sharedpreferences.getInt(
				data.medium_wins, 0), data.sharedpreferences.getInt(
				data.medium_loses, 0), data.sharedpreferences.getInt(
				data.medium_draws, 0));
		Scoreboard.Hard = new Rating(data.sharedpreferences.getInt(
				data.hard_wins, 0), data.sharedpreferences.getInt(
				data.hard_loses, 0), data.sharedpreferences.getInt(
				data.hard_draws, 0));
	};

	/**
	 * Returns instance of Scoreboard
	 * 
	 * @param context
	 * @return instance
	 */
	public static Scoreboard getInstance(Context context) {
		if (Scoreboard.instance == null) {
			Scoreboard.instance = new Scoreboard(context);
		}
		return Scoreboard.instance;
	}

	/**
	 * Returns the instance of the easy difficulty
	 * 
	 * @return easy
	 */
	public Rating getEasyScores() {
		return Scoreboard.Easy;
	}

	/**
	 * Returns the instance of the medium difficulty
	 * 
	 * @return medium
	 */
	public Rating getMediumScores() {
		return Scoreboard.Medium;
	}

	/**
	 * Returns the instance of the hard difficulty
	 * 
	 * @return hard
	 */
	public Rating getHardScores() {
		return Scoreboard.Hard;
	}

	/**
	 * Updates scores given the game winner
	 * 
	 * @param winner
	 */
	public void updateScore(Flag winner) {
		switch (Settings.getInstance(context).getDifficulty()) {
		case 0:
			Scoreboard.Easy.updateScore(winner);
			break;
		case 1:
			Scoreboard.Medium.updateScore(winner);
			break;
		case 2:
			Scoreboard.Hard.updateScore(winner);
			break;
		default:
			break;
		}
	}
}
