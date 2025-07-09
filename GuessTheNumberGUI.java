import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberGUI extends JFrame {
    private int numberToGuess;
    private int totalScore = 0;
    private int round = 1;
    private int attempts = 0;
    private final int totalRounds = 3;
    private final int maxAttempts = 7;

    private JLabel messageLabel, scoreLabel, roundLabel;
    private JTextField guessField;
    private JButton guessButton;
    private Random rand = new Random();

    public GuessTheNumberGUI() {
        setTitle("Guess The Number");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        roundLabel = new JLabel("Round: 1 / " + totalRounds);
        messageLabel = new JLabel("Guess a number between 1 and 100:");
        scoreLabel = new JLabel("Score: 0");

        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");

        add(roundLabel);
        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(scoreLabel);

        startNewRound();

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        setVisible(true);
    }

    private void startNewRound() {
        numberToGuess = rand.nextInt(100) + 1;
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100:");
        guessField.setText("");
        roundLabel.setText("Round: " + round + " / " + totalRounds);
    }

    private void handleGuess() {
        String input = guessField.getText();
        int guess;

        try {
            guess = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            return;
        }

        if (guess < 1 || guess > 100) {
            JOptionPane.showMessageDialog(this, "Enter a number between 1 and 100!");
            return;
        }

        attempts++;

        if (guess == numberToGuess) {
            int points = (maxAttempts - attempts + 1) * 10;
            totalScore += points;
            scoreLabel.setText("Score: " + totalScore);
            JOptionPane.showMessageDialog(this, "Correct! You earned " + points + " points.");
            nextRoundOrEnd();
        } else if (guess < numberToGuess) {
            messageLabel.setText("Too low! Try again:");
        } else {
            messageLabel.setText("Too high! Try again:");
        }

        if (attempts >= maxAttempts && guess != numberToGuess) {
            JOptionPane.showMessageDialog(this, " Out of attempts! The number was: " + numberToGuess);
            nextRoundOrEnd();
        }

        guessField.setText("");
    }

    private void nextRoundOrEnd() {
        if (round < totalRounds) {
            round++;
            startNewRound();
        } else {
            JOptionPane.showMessageDialog(this, "Game Over!\nYour total score: " + totalScore + " points");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new GuessTheNumberGUI();
    }
}
