import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Class the controls the client-side actions.
 * @author Kevin Shu
 *
 */
public class Client {

	/**
	 * Runs the client commands.
	 * @param args
	 */
	public static void main(String[] args) {
		GameData data;
		InetAddress ip;
		Socket s;
		ObjectInputStream in;
		ObjectOutputStream out;
		int level = 0;
		double finalTime = 69;
		final int MAX_LEVEL = 5;

		try {
			String ipAddress = "localhost"; // Where a valid ipaddress would go
			ip = InetAddress.getByName(ipAddress);
			s = new Socket(ip, 25565);
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			int option = 1;
			while(level < MAX_LEVEL) {
				// Option for user 0 = leaderboard, 1 = next level
				if(option == 0) {
					// write the option to the server (0)
					out.writeInt(0);
					out.flush();
					// write the level to the server
					out.writeInt(level);
					out.flush();
					// read leaderboard information from the server
					@SuppressWarnings("unchecked")
					ArrayList<Double> scores = (ArrayList<Double>)in.readObject();
					LevelLoad.displayScores(scores, finalTime);
					option = 1;
				} else {
					// write the option to the server (1)
					out.writeInt(1);
					out.flush();
					
					// requests the level data of the specified 'level' integer
					out.writeInt(level);
					out.flush();
					
					// recieve the level data from the server.
					data = (GameData)in.readObject();
					
					// Temporary toString method to display valid level data.
					data.toString();
					
					// start time after loading in ui elements code
					long startTime = System.nanoTime();
					
					// Code area for checking if level is complete.
					LevelLoad load = new LevelLoad(data);
					while(!load.isComplete()) {
						Thread.sleep(10);
					}
					// Calculate endtime
					long endTime = System.nanoTime();
					finalTime = ((double) endTime - startTime) / 1_000_000_000;
					System.out.println("\n" + finalTime + " seconds");
					// send time to server
					out.writeDouble(finalTime);
					out.flush();
					
					level++;
					option = LevelLoad.displayOptions();
				}
			} // end of while
			
			// Edge-case fix for displaying score on final level.
			if(option == 0) {
				// write the option to the server (0)
				out.writeInt(0);
				out.flush();
				// write the level to the server
				out.writeInt(MAX_LEVEL - 1);
				out.flush();
				// read leaderboard information from the server
				@SuppressWarnings("unchecked")
				ArrayList<Double> scores = (ArrayList<Double>)in.readObject();
				LevelLoad.displayScores(scores, finalTime);
			}
			
			System.out.println("Congrats! you finished all the levels");
			LevelLoad.winScreen();

			
		} catch (Exception ex) {
			System.out.println("Server closed with pending client request");
		}
	}
	
}
