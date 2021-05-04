import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * Class that works with the client to provide a java gui.
 * @author Kevin Shu
 *
 */
public class LevelLoad {

	private int level;
	private char[] letters;
	private String[] words;
	private boolean complete;
	// Swing elements
	private JTextArea submitText;
	private JButton btnSubmit;
	private JButton btnCheat;
	private JButton btnShuffle;
	private JPanel gamePanel;
	private JFrame frame;
	private JTextArea text1;
	private JTextArea text2;
	private JTextArea text3;
	private JTextArea text4;
	private JTextArea text5;
	private JTextArea text6;
	private JTextArea text7;
	private JTextArea text8;
	private JTextArea text9;
	private JTextArea text10;
	private JTextArea[] text;
	private boolean found[];
	private JLabel responseLabel;
	private JLabel letterLabel;
	private static int cheatCount = 0;

	public LevelLoad(GameData gd) {
		this.level = gd.getLevel();
		this.letters = gd.getLetters();
		this.words = gd.getWords();
		
		start();
	}

	private void start() {
		this.found = new boolean[10];
		complete = false;
		// Main JFrame
		frame = new JFrame("Client-Game Implementation");
		frame.setTitle("Level: " + level);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(750, 500);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				complete = true;
			}
		});
		// Add components to frame
		gamePanelInit();
		frame.add(gamePanel);
		frame.setVisible(true);

	}
	
	private void gamePanelInit() {
		gamePanel = new JPanel(null);
		gamePanel.setSize(750, 500);
		gamePanel.setVisible(true);
		gamePanel.setBackground(new Color(240, 248, 255));


		// Text areas
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		// row1
		text1 = new JTextArea();
		text1.setEditable(false);
		text1.setBounds(200, 130, 100, 30);
		text1.setBorder(border);
		gamePanel.add(text1);

		text2 = new JTextArea();
		text2.setEditable(false);
		text2.setBounds(200, 165, 100, 30);
		text2.setBorder(border);
		gamePanel.add(text2);

		text3 = new JTextArea();
		text3.setEditable(false);
		text3.setBounds(200, 200, 100, 30);
		text3.setBorder(border);
		gamePanel.add(text3);

		text4 = new JTextArea();
		text4.setEditable(false);
		text4.setBounds(200, 235, 100, 30);
		text4.setBorder(border);
		gamePanel.add(text4);

		text5 = new JTextArea();
		text5.setEditable(false);
		text5.setBounds(200, 270, 100, 30);
		text5.setBorder(border);
		gamePanel.add(text5);

		// row 2
		text6 = new JTextArea();
		text6.setEditable(false);
		text6.setBounds(450, 130, 100, 30);
		text6.setBorder(border);
		gamePanel.add(text6);

		text7 = new JTextArea();
		text7.setEditable(false);
		text7.setBounds(450, 165, 100, 30);
		text7.setBorder(border);
		gamePanel.add(text7);

		text8 = new JTextArea();
		text8.setEditable(false);
		text8.setBounds(450, 200, 100, 30);
		text8.setBorder(border);
		gamePanel.add(text8);

		text9 = new JTextArea();
		text9.setEditable(false);
		text9.setBounds(450, 235, 100, 30);
		text9.setBorder(border);
		gamePanel.add(text9);

		text10 = new JTextArea();
		text10.setEditable(false);
		text10.setBounds(450, 270, 100, 30);
		text10.setBorder(border);
		gamePanel.add(text10);
		text = new JTextArea[] { text1, text2, text3, text4, text5, text6, text7, text8, text9, text10 };
		
		// Submit Button
		btnSubmit = new JButton();
		btnSubmit.setBounds(337, 425, 75, 25);
		btnSubmit.setText("Submit");
		btnSubmit.setFocusPainted(false);
		btnSubmit.setBackground(new Color(59, 89, 182));
		btnSubmit.setForeground(Color.WHITE);
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = submitText.getText();
				for (int i = 0; i < words.length; i++) {
					if (content.equalsIgnoreCase(words[i])) {
						text[i].setText(words[i]);
						submitText.setText("");
						found[i] = true;
						responseLabel.setText("You found a correct word!");
						break;
					} else {
						submitText.setText("");
						responseLabel.setText("Not a valid word :(");
					}
				}
				boolean finished = true;
				for(int i = 0; i < words.length; i++) {
					if(!found[i]) {
						finished = false;
					}
				}
				if(finished) {
					responseLabel.setText("You finished the level!");
					complete = true;
				}

			}

		});
		gamePanel.add(btnSubmit);
		
		// Cheat button
		btnCheat = new JButton();
		btnCheat.setBounds(650, 425, 75, 25);
		btnCheat.setText("dumbo");
		btnCheat.setFocusPainted(false);
		btnCheat.setBackground(new Color(59, 89, 182));
		btnCheat.setForeground(Color.WHITE);
		btnCheat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < words.length; i++) {
					if(!found[i]) {
						found[i] = true;
						text[i].setText(words[i]);
						responseLabel.setText("You 'found' a correct word!");
						cheatCount++;
						break;
					}
				}
				boolean finished = true;
				for(int i = 0; i < 10; i++) {
					if(!found[i]) {
						finished = false;
					}
				}
				if(finished) {
					responseLabel.setText("You finished the level!");
					complete = true;
				}
				
			}
			
		});
		gamePanel.add(btnCheat);
		
		btnShuffle = new JButton();
		btnShuffle.setBounds(10, 425, 75, 25);
		btnShuffle.setText("shuffle");
		btnShuffle.setFocusPainted(false);
		btnShuffle.setBackground(new Color(59, 89, 182));
		btnShuffle.setForeground(Color.WHITE);
		btnShuffle.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Random rnd = ThreadLocalRandom.current();
				for(int i = letters.length-1; i > 0; i--) {
					int index = rnd.nextInt(i + 1);
					char updatedCh = letters[index];
					letters[index] = letters[i];
					letters[i] = updatedCh;
				}
				String lettersText = "";
				for (char c : letters) {
					lettersText += c;
				}
				letterLabel.setText(lettersText);
				
			}
			
		});
		
		gamePanel.add(btnShuffle);

		// submit
		submitText = new JTextArea();
		submitText.setBounds(325, 395, 100, 25);
		submitText.setBorder(border);
		gamePanel.add(submitText);

		// JLabels
		responseLabel = new JLabel("", SwingConstants.CENTER);
		responseLabel.setBounds(275, 360, 200, 25);
		gamePanel.add(responseLabel);
		
		
		JLabel letterDesc = new JLabel("available letters:", SwingConstants.CENTER);
		letterDesc.setBounds(325, 20, 100, 25);
		gamePanel.add(letterDesc);

		letterLabel = new JLabel("test", SwingConstants.CENTER);
		String lettersText = "";
		for (char c : this.letters) {
			lettersText += c;
		}
		letterLabel.setText(lettersText);
		letterLabel.setBounds(295, 40, 160, 50);
		letterLabel.setFont(new Font("San-Serif", Font.BOLD, 30));
		gamePanel.add(letterLabel);


	}

	public boolean isComplete() {
		if(complete) {
			frame.setVisible(false);
			frame.dispose();
		}
		return complete;
	}
	
	public static int displayOptions() {
		String[] options = new String[] {"View Leaderboards", "Next Level"};
		return JOptionPane.showOptionDialog(null, "", "Choose Option", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}
	
	public static void displayScores(ArrayList<Double> scores, double userTime) {
		ArrayList<Double> sortedScores = scores;
		sortedScores.add(userTime);
		Collections.sort(sortedScores);
		String list = "";
		int lastIndexPrinted = 0;
		for(int i = 0; i < sortedScores.size(); i++) {
			if(userTime == sortedScores.get(i)) {
				list += (lastIndexPrinted + 1) + ". " + sortedScores.get(i) + " - YOUR SCORE\n";
				lastIndexPrinted++;
			} else {
				list += (lastIndexPrinted + 1) + ". " + sortedScores.get(i) + "\n";
				lastIndexPrinted++;
			}
			
		}
		JOptionPane.showMessageDialog(null, list);
		
		
	}
	
	public static void winScreen() {
		if(cheatCount == 0) {
			JOptionPane.showMessageDialog(null, "You win, good job", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "You won by cheating only " + cheatCount + " times!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	

}
