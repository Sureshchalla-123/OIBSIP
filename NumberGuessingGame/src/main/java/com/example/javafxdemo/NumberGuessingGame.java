package com.example.javafxdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class NumberGuessingGame extends Application {

	private int targetNumber;
	private int remainingGuesses;
	private int score;

	private Label promptLabel;
	private TextField guessTextField;
	private Button guessButton;
	private Label resultLabel;
	private Label scoreLabel;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Number Guessing Game");

		// Initialize game
		initializeGame();

		// Create UI elements
		promptLabel = new Label("Guess a number between 1 and 100:");
		guessTextField = new TextField();
		guessButton = new Button("Guess");
		resultLabel = new Label();
		scoreLabel = new Label();

		// Apply styling to UI elements
		promptLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		guessButton.setStyle("-fx-font-weight: bold");

		// Handle guess button click
		guessButton.setOnAction(e -> checkGuess());

		VBox root = new VBox(10);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(20));
		root.getChildren().addAll(promptLabel, guessTextField, guessButton, resultLabel, scoreLabel);

		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);

		// Set minimum and maximum width and height
		primaryStage.setMinWidth(300);
		primaryStage.setMinHeight(250);
		primaryStage.setMaxWidth(400);
		primaryStage.setMaxHeight(300);

		primaryStage.show();
	}

	private void initializeGame() {
		targetNumber = (int) (Math.random() * 100) + 1;
		remainingGuesses = 5;
		score = 0;
	}

	private void checkGuess() {
		try {
			int guess = Integer.parseInt(guessTextField.getText());

			if (guess < 1 || guess > 100) {
				resultLabel.setText("Please enter a number between 1 and 100.");
				return;
			}

			remainingGuesses--;

			if (guess == targetNumber) {
				score = 5 - remainingGuesses;
				resultLabel.setText("Congratulations! You guessed the number.");
				guessButton.setDisable(true);
				scoreLabel.setText("Your score: " + score);
			} else if (remainingGuesses == 0) {
				resultLabel.setText("Game over.The number was: " + targetNumber);
				guessButton.setDisable(true);
				scoreLabel.setText("Your score: 0");
			} else if (guess < targetNumber) {
				resultLabel.setText("Try a higher number. Guesses remaining: " + remainingGuesses);
			} else {
				resultLabel.setText("Try a lower number. Guesses remaining: " + remainingGuesses);
			}

			guessTextField.clear();
		} catch (NumberFormatException e) {
			resultLabel.setText("Please enter a valid number.");
		}
	}
}
