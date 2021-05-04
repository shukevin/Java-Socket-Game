import java.io.Serializable;

/**
 * Class that specifies game data for a level. (Used both in client/server)
 * @author Kevin Shu
 *
 */
public class GameData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int level;
	private char[] letters;
	private String[] words;
	
	/**
	 * Default constructor for creating game data for a level.
	 * @param level - integer value for the game's level.
	 * @param letters - letter array for the game's level.
	 * @param words - word array for the game's level.
	 */
	public GameData(int level, char[] letters, String[] words) {
		this.level = level;
		this.letters = letters;
		this.words = words;
	}
	
	/**
	 * Getter for integer representing the level of the game data.
	 * @return Integer representing the level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Getter for char array representing letters of the game data.
	 * @return Char array of letters.
	 */
	public char[] getLetters() {
		return letters;
	}
	
	/**
	 * Getter for String array representing words of the game data.
	 * @return String array of words.
	 */
	public String[] getWords() {
		return words;
	}
	
	/**
	 * Helper method designed to output GameData contents.
	 */
	public String toString() {
		System.out.print("\nLevel: " + this.getLevel());
		System.out.print("\nLetters: ");
		for(char c : this.getLetters()) {
			System.out.print(c + ", ");
		}
		System.out.print("\nWords: ");
		for(String s : this.getWords()) {
			System.out.print(s + ", ");
		}
		
		
		return null;
	}
	

}
