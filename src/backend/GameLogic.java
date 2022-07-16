package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.Node;
import unlimitedwordle.Grid;

/**
 * GameLogic is an abstract class that allows the concrete strategies (Easy, Medum, Hard Game) to read and
 * choose words depending upon the difficulty level. It also provides a framework for the concrete strategies to follow.
 * */
public abstract class GameLogic {
	
	Grid newGrid = new Grid();
	String type = "";

	public ArrayList<String> words = new ArrayList<String>();

/** 
 * readWord() is used to extract the words from the file
 * */	
	public void readWord(String filepath) throws FileNotFoundException {
		
		String chosenWord = new String();
		Scanner fileReader = new Scanner( new File (filepath));
		
		while(fileReader.hasNextLine()) {
			String newWord = fileReader.nextLine();
			//adding checks to prevent empty lines from being added accidentally
			if(!newWord.isEmpty())
			words.add(newWord);
		}
	}

	/** 
	 * chooseWord() is used to randomly choose the words from the file
	 * @return a randonly chosen word each time the function is called
	 * */		
	public String chooseWord() {
		
		String chosenWord = new String();
		Random randWord = new Random();
		
		chosenWord = words.get(randWord.nextInt(words.size()));
				
		return chosenWord;
	}
	
	/**
	 * getWords() is used to return the list of words read from the file
	 * @return an arraylist of words*/
	public ArrayList<String> getWords() {
		return words;
	}
	
	/**
	 * getGrid() is used to return the grid 
	 * */
	public Grid getGrid() {
		return newGrid;
	}

	/**
	 * type() is used to return the type of difficulty level*/
	public String type() {
		return type;
	}
}

