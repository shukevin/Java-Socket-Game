import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server for CS462 Project.
 * 
 * @author Kevin Shu
 *
 */
public class Server implements Runnable {

	private Thread controlThread;
	private volatile boolean running;
	private LevelData levels;
	private ArrayList<GameData> data;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ServerSocket ss;
	private Socket s;
	private int level;
	private ArrayList<Double> scores;

	/**
	 * Main method for running the server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	/**
	 * Class constructor for the server.
	 */
	public Server() {
		// Initiate objects for use in Server logic.
		levels = new LevelData();
		data = levels.getLevels();
		try {
			ss = new ServerSocket(50026);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server is running...");
	}

	/**
	 * Thread for running the server.
	 */
	public void run() {

		// Connect to the client.
		try {
			s = ss.accept();
			System.out.println("Connection established...");
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// Error - rerun
			run();
		}

		// Workflow for server operations.
		while (running) {
			try {
				// Read in user request (0 = leaderboard, 1 = start next level)
				int choice = in.readInt();
				if (choice == 0) {
					// Get the scores from the specified level
					ArrayList<Double> scores = levels.getScores(in.readInt());
					// Write the scores to the client
					out.writeObject(scores);
				} else {
					// Initiates next level
					startLevel();
				}
			} catch (IOException e) {
				// Client most likely exited while the server expected writes/reads.
				// Reinitiates the run method.
				run();
			}

		}

	}

	/**
	 * Starts the controlThread on the server.
	 */
	public void start() {
		if (controlThread == null) {
			controlThread = new Thread(this);
			running = true;
			controlThread.start();
		}
	}

	/**
	 * Stops the server and closes various streams.
	 */
	@SuppressWarnings("unused")
	private void stop() {
		running = false;
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initiates the level
	 */
	private void startLevel() {
		try {
			// Reads in the level request
			level = (int) in.readInt();
			// Gets the corresponding score list from the level
			scores = levels.getScores(level);

			// Write the corresponding level GameData to out
			out.writeObject(data.get(level));
			out.flush();

			// Read in the score from the Client and add it into the highscore list.
			double score = in.readDouble();
			scores.add((double) score);

		} catch (Exception ex) {
			// connection ended (most likely), restart call to run.
			run();
		}
	}
}
