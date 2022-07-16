package backend;

import java.io.FileNotFoundException;

import unlimitedwordle.Defines;
import unlimitedwordle.Grid;

/**
 * HardGame is used to initialize the game when user picks the hard difficulty option. 
 * It contains @param hardGrid that creates a new Grid based on the set number of columns for hard level
 * and contains getter for getting the grid
 * */
public class HardGame extends GameLogic {

	Defines DEF = new Defines();
	Grid hardGrid;
	
	/** @const HardGame() reads in the words assigned for easy category. In this case, we've chosen 7 letter words as hard
	 * It then initializes value for hardGrid
	 * */
	public HardGame() throws FileNotFoundException{
		readWord("src/backend/sevenLetterWords.txt");
		hardGrid = new Grid(DEF.getNUM_GUESS(), DEF.getHARD());		
	}

	/**
	 * @return the hardGrid object*/
	public Grid getGrid() {
		return hardGrid;
	}
	
	/**
	 * @return the type of Level it is*/
	public String type() {
		return "hard";
	}
}
