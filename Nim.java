/**
 * This program runs my interpretation of the game Nim. Similar to the original game, this code prompts 
 * for the names of the two players and the two participants must take turns picking up sticks in a 
 * number of piles. On any turn, a player must choose a pile and remove one or more sticks from that 
 * pile (up to the number of sticks in the pile). Once that player has provided valid input, the program 
 * adjusts piles such that the piles will be arranged from least to greatest. The object of the game is 
 * to force your opponent to pick up the last stick in the last pile. When one player has won the game, 
 * a congratulatory note is printed. At the end of each game, the players are about to state whether they 
 * want to start a new game or to end playing the game. If a new game is started, the player that went 
 * second in the last game gets to start first. Otherwise, if the players state that they are done, the 
 * games ends by printing the number of games won by each player. 
 * 
 * Date Last Modified: 12 January 2019
 * Author: Niruthieka Velalakan
 * Course: ICS4U1
 * School: Lester B. Pearson Collegiate Institute
 */

import java.util.Random;
import java.util.Scanner;

public class Nim {

	// Creates global variables
	public static Scanner input = new Scanner (System.in);
	public static String start; //used to see if the player wants instructions or not
	public static String player1, player2; //stores player names
	public static String newGame = "yes";
	public static int gamesWon1 = 0, gamesWon2 = 0, gamesTotal = 1;
	public static int pileChoice, numSticks;
	public static int playerTurn = 2;
	public static int pile [] = new int [5];

	public static void main(String [] args) {

		// prints title and instructions
		System.out.println("Welcome to Nim\nBy: Niruthieka Velalakan\n");
		System.out.println("Instructions: " 
				+ "\nNim is a two-player game in which players take turns picking up sticks. The game begins "
				+ "\nwith various numbers of sticks in a number of piles. On any turn, a player must choose a "
				+ "\npile and remove one or more sticks from that pile (up to the number of sticks in the pile). "
				+ "\nThe object of the game is to force your opponent to pick up the last stick in the last pile."
				+ "\nAt the end of each game, the players are about to state whether they want to start a new game"
				+ "\nor to end playing the game. If a new game is started, the player that went second in the last"
				+ "\ngame gets to start first. Otherwise, if the players state that they are done, the games ends "
				+ "\nby printing the number of games won by each player.\n");
		// prompts to start new game
		System.out.print("Do you want to start a new game? (Yes/No): "); 
		start = input.nextLine();
		menu();

		// Only starts game when player states for new game
		while (newGame.equalsIgnoreCase("yes") && start.equalsIgnoreCase("yes")) {
			// Generates piles with random number of sticks
			pile [1] = new Random().nextInt(5)+4;
			pile [2] = new Random().nextInt(5)+4;
			pile [3] = new Random().nextInt(5)+4;
			pile [4] = new Random().nextInt(5)+4;
			game(); // starts game
		}

		gameOver(); // game ending

	}

	public static void menu() {
		do {
			if (start.equalsIgnoreCase("no")){ // end games if no
				System.out.println("\nThere are no games played to present the winner.");
				System.out.println("Game Over.");
			} else if (start.equalsIgnoreCase("yes")) { // gets names 
				System.out.print("\nWhat is player 1's name? ");  //asks for player 1's name
				player1 = input.nextLine(); //stores player 1's name
				System.out.print("What is player 2's name? "); //asks for player 2's name
				player2 = input.nextLine(); //stores player 2's name
			} else if (!start.equalsIgnoreCase("yes") && !start.equalsIgnoreCase("no")){ // loops if invalid input
				System.out.println("Sorry, your answer is invalid. Please try again.\n"); //outputs if answer is invalid
				System.out.print("Do you want to start a new game? (Yes/No): "); // prompts to start new game
				start = input.nextLine();
				if (start.equalsIgnoreCase("yes")) {
					System.out.print("\nWhat is player 1's name? ");  
					player1 = input.nextLine(); 
					System.out.print("What is player 2's name? ");
					player2 = input.nextLine();
				} else if (start.equalsIgnoreCase("no")){
					System.out.println("\nThere are no games played to present the winner.");
					System.out.println("Game Over.");
				}
			} 
		} while (!start.equalsIgnoreCase("yes") && !start.equalsIgnoreCase("no")); // loops if input is invalid
	}
	// controls turns
	public static void turns () {
		if (playerTurn == 1){ // switches to player 2 if player 1 went
			playerTurn = 2;
			System.out.println("\nIt is " + player2 + "'s turn\n"); // prints player 2's turn
		} else {
			playerTurn = 1; // switches to player 1 if player 2 went
			System.out.println("\nIt is " + player1 + "'s turn\n"); // prints player 1's turn
		}
	}
	// creates and rearranges piles
	public static void piles () {
		// Creates P I L E 1
		if (pile [1] <= pile [2] && pile [1] <= pile [3] && pile [1] <= pile [4]) {
			if (pile [2] <= pile [3] && pile [2] <= pile [4]) {
				if (pile [3] <= pile [4]) {
					System.out.println("Pile 1\tPile 2\tPile 3\tPile 4");
					System.out.println("  " + pile [1] + "\t  " + pile [2] + "\t  " + pile [3] + "\t  " + pile [4] );
				} else {
					System.out.println("Pile 1\tPile 2\tPile 4\tPile 3");
					System.out.println("  " + pile [1] + "\t  " + pile [2] + "\t  " + pile [4] + "\t  " + pile [3] );
				}
			} else if (pile [3] <= pile [2] && pile [3] <= pile [4]){
				if (pile [2] <= pile [4]) {
					System.out.println("Pile 1\tPile 3\tPile 2\tPile 4");
					System.out.println("  " + pile [1] + "\t  " + pile [3] + "\t  " + pile [2] + "\t  " + pile [4] );
				} else {
					System.out.println("Pile 1\tPile 3\tPile 4\tPile 2");
					System.out.println("  " + pile [1] + "\t  " + pile [3] + "\t  " + pile [4] + "\t  " + pile [2] );
				}
			} else if (pile [4] <= pile [2] && pile [4] <= pile [3]) {
				if (pile [2] <= pile [3]) {
					System.out.println("Pile 1\tPile 4\tPile 2\tPile 3");
					System.out.println("  " + pile [1] + "\t  " + pile [4] + "\t  " + pile [2] + "\t  " + pile [3] );
				} else {
					System.out.println("Pile 1\tPile 4\tPile 3\tPile 2");
					System.out.println("  " + pile [1] + "\t  " + pile [4] + "\t  " + pile [3] + "\t  " + pile [2] );
				}
			}
			// P I L E 2
		} else if (pile [2] <= pile [1] && pile [2] <= pile [3] && pile [2] <= pile [4]) {
			if (pile [1] <= pile [3] && pile [1] <= pile [4]) {
				if (pile [3] <= pile [4]) {
					System.out.println("Pile 2\tPile 1\tPile 3\tPile 4");
					System.out.println("  " + pile [2] + "\t  " + pile [1] + "\t  " + pile [3] + "\t  " + pile [4] );
				} else {
					System.out.println("Pile 2\tPile 1\tPile 4\tPile 3");
					System.out.println("  " + pile [2] + "\t  " + pile [1] + "\t  " + pile [4] + "\t  " + pile [3] );
				}
			} else if (pile [3] <= pile [1] && pile [3] <= pile [4]){
				if (pile [1] <= pile [4]) {
					System.out.println("Pile 2\tPile 3\tPile 1\tPile 4");
					System.out.println("  " + pile [2] + "\t  " + pile [3] + "\t  " + pile [1] + "\t  " + pile [4] );
				} else {
					System.out.println("Pile 2\tPile 3\tPile 4\tPile 1");
					System.out.println("  " + pile [2] + "\t  " + pile [3] + "\t  " + pile [4] + "\t  " + pile [1] );
				}
			} else if (pile [4] <=pile [1] && pile [4] <= pile [3]) {
				if (pile [1] <= pile [3]) {
					System.out.println("Pile 2\tPile 4\tPile 1\tPile 3");
					System.out.println("  " + pile [2] + "\t  " + pile [4] + "\t  " + pile [1] + "\t  " + pile [3] );
				} else {
					System.out.println("Pile 2\tPile 4\tPile 3\tPile 1");
					System.out.println("  " + pile [2] + "\t  " + pile [4] + "\t  " + pile [3] + "\t  " + pile [1] );
				}
			}
			// P I L E 3 TOP
		} else if (pile [3] <= pile [1] && pile [3] <= pile [2] && pile [3] <= pile [4]) {
			if (pile [2] <= pile [3] && pile [2] <= pile [4]) {
				if (pile [1] <= pile [4]) {
					System.out.println("Pile 3\tPile 2\tPile 1\tPile 4");
					System.out.println("  " + pile [3] + "\t  " + pile [2] + "\t  " + pile [1] + "\t  " + pile [4] );
				} else {
					System.out.println("Pile 3\tPile 2\tPile 4\tPile 1");
					System.out.println("  " + pile [3] + "\t  " + pile [2] + "\t  " + pile [4] + "\t  " + pile [1] );
				}

			} else if (pile [1] <= pile [2] && pile [1] <= pile [4]){
				if (pile [2] <= pile [4]) {
					System.out.println("Pile 3\tPile 1\tPile 2\tPile 4");
					System.out.println("  " + pile [3] + "\t  " + pile [1] + "\t  " + pile [2] + "\t  " + pile [4] );
				} else {
					System.out.println("Pile 3\tPile 1\tPile 4\tPile 2");
					System.out.println("  " + pile [3] + "\t  " + pile [1] + "\t  " + pile [4] + "\t  " + pile [2] );
				}
			} else if (pile [4] <= pile [2] && pile [4] <= pile [1]) {
				if (pile [2] <= pile [1]) {
					System.out.println("Pile 3\tPile 4\tPile 2\tPile 1");
					System.out.println("  " + pile [3] + "\t  " + pile [4] + "\t  " + pile [2] + "\t  " + pile [1] );
				} else {
					System.out.println("Pile 3\tPile 4\tPile 1\tPile 2");
					System.out.println("  " + pile [3] + "\t  " + pile [4] + "\t  " + pile [1] + "\t  " + pile [2] );
				}
			}
			// P I L E 4
		} else if (pile [4] <= pile [1] && pile [4] <= pile [2] && pile [4] <= pile [3]){
			if (pile [2] <= pile [3] && pile [2] <= pile [1]) {
				if (pile [3] <= pile [1]) {
					System.out.println("Pile 4\tPile 2\tPile 3\tPile 1");
					System.out.println("  " + pile [4] + "\t  " + pile [2] + "\t  " + pile [3] + "\t  " + pile [1] );
				} else {
					System.out.println("Pile 4\tPile 2\tPile 1\tPile 3");
					System.out.println("  " + pile [4] + "\t  " + pile [2] + "\t  " + pile [1] + "\t  " + pile [3] );
					System.out.println();
				}
			} else if (pile [3] <= pile [2] && pile [3] <= pile [1]){
				if (pile [2] <= pile [1]) {
					System.out.println("Pile 4\tPile 3\tPile 2\tPile 1");
					System.out.println("  " + pile [4] + "\t  " + pile [3] + "\t  " + pile [2] + "\t  " + pile [1] );
				} else {
					System.out.println("Pile 4\tPile 3\tPile 1\tPile 2");
					System.out.println("  " + pile [4] + "\t  " + pile [3] + "\t  " + pile [1] + "\t  " + pile [2] );
				}
			} else if (pile [1] <= pile [2] && pile [1] <= pile [3]) {
				if (pile [2] <= pile [3]) {
					System.out.println("Pile 4\tPile 1\tPile 2\tPile 3");
					System.out.println("  " + pile [4] + "\t  " + pile [1] + "\t  " + pile [2] + "\t  " + pile [3] );
				} else {
					System.out.println("Pile 4\tPile 1\tPile 3\tPile 2");
					System.out.println("  " + pile [4] + "\t  " + pile [1] + "\t  " + pile [3] + "\t  " + pile [2] );
				}
			}
		}
		System.out.println();
	}
	// gets user's input for pile and sticks
	public static void pick () {
		do {
			System.out.print("What pile do you want to take from? (1-4): ");
			pileChoice = input.nextInt(); // gets pile choice
		} while (pileChoice < 1 || pileChoice > 4); // loops if answer is invalid

		do {
			System.out.print("How many sticks do you want to take?: ");
			numSticks = input.nextInt(); // gets number of sticks
		} while (numSticks < 1 || numSticks > pile [pileChoice]); // loops if answer is invalid

		pile [pileChoice] -= numSticks; // if answer is valid, subtracts sticks from said pile
	}
	// announces winner at end of each game
	public static void determineWinner () {
		if (playerTurn == 1){ // if player 1 picked up stick, player 2 wins
			System.out.println("\n" + player2 + " is the Winner!\n");
			gamesWon2++; // adds one to total games won by player 2
		} else { // if player 2 picked up stick, player 1 wins
			System.out.println("\n" + player1 + " is the Winner!\n");
			gamesWon1++; // adds one to total games won by player 1
		}
	}
	// creates game
	public static void game () {
		do { 
			turns();
			piles();
			pick();
		} while (pile [1] > 0 || pile [2] > 0 || pile [3] > 0 || pile [4] > 0); // loops game until all piles are at zero

		determineWinner(); // prints winner of game

		do { 
			System.out.print("Do you want to play again? (Yes/No): ");
			newGame = input.nextLine();
			newGame = input.nextLine();
			System.out.println();
		} while (!newGame.equalsIgnoreCase("yes") && !newGame.equalsIgnoreCase("no")); // loops for new game until answer is valid

		gamesTotal++; // adds one to number of total games

		// switches player turns for new game
		if (gamesTotal % 2 == 0){
			playerTurn = 1;
		} else {
			playerTurn = 2;
		}
	}
	// prints if there is no new game
	public static void gameOver () {
		if (newGame.equalsIgnoreCase("no")) {
			if (gamesWon1 > gamesWon2) {
				System.out.println("\nCongratulations! " + player1 + " won " + gamesWon1 + " game(s)!"); // prints player 1 as winner
				System.out.println("While " + player2 + " won " + gamesWon2 + " game(s)!");
			} else if (gamesWon2 > gamesWon1) {
				System.out.println("\nCongratulations! " + player2 + " won " + gamesWon2 + " game(s)!"); // prints player 2 as winner
				System.out.println("While " + player1 + " won " + gamesWon1 + " game(s)!");
			} else {
				System.out.println("\nOops! It's a tie!"); // prints tie
			}
		}
	}
}

