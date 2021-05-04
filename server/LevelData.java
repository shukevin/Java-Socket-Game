import java.util.ArrayList;

/**
 * Class used to organize pre-created game-data objects server side. Acts as a pseudo-database for the Server.
 * 
 * @author Kevin Shu
 *
 */
public class LevelData {
	ArrayList<GameData> levels;
	ArrayList<ArrayList<Double>> scores;

	/**
	 * Default class constructor which initiates all the GameData variables.
	 */
	public LevelData() {
		levels = new ArrayList<GameData>();
		scores = new ArrayList<ArrayList<Double>>();
		/**
		 * ADD NEW LEVEL DATA BELOW:
		 * SIZE: 20 Format for GameData (int level number, char[] level letters,
		 * string[] level words)
		 */
		// Level 1
		levels.add(new GameData(1, new char[] { 'c', 'a', 't', 'h', 'w' },
				new String[] { "watch", "chat",  "tach", "thaw", "what", "act", "cat", "caw", "hat", "at", }));
		// Level 2
		levels.add(new GameData(2, new char[] { 'e', 't', 'b', 'a' },
				new String[] { "abet", "beat", "beta", "ate", "bat", "bet", "eat", "tea", "tab", "at" }));
		// Level 3
		levels.add(new GameData(3, new char[] { 'm', 'n', 'o', 'l', 'w' },
				new String[] { "lown", "mown", "low", "mow", "now", "owl", "own", "won", "no", "on" }));
		// Level 4
		levels.add(new GameData(4, new char[] { 'g', 'y', 'z', 'e', 't', 'o' },
				new String[] { "zygote", "oyez", "toy", "yet", "ego", "got", "toe", "go", "to", "tye" }));
		// Level 5
		levels.add(new GameData(5, new char[] { 'b', 'e', 'r', 'n', 's', 't' },
				new String[] { "stern", "bent", "best", "nest", "rent", "rest", "sent", "bet", "net", "ten" }));
		for (int i = 0; i < levels.size(); i++) {
			scores.add(new ArrayList<Double>());
		}

	}

	/**
	 * Getter for the levels (GameData objects)
	 * @return - a list of gamedata objects.
	 */
	public ArrayList<GameData> getLevels() {
		return levels;
	}

	/**
	 * Getters for the scores (Highscore values)
	 * @param level - the level of the scores.
	 * @return - arraylist<Double> scores.
	 */
	public ArrayList<Double> getScores(int level) {
		return scores.get(level);
	}

}
