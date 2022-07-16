package backend;

import java.io.FileNotFoundException;

import unlimitedwordle.Defines;
import unlimitedwordle.Grid;

/**
 * MediumGame is used to initialize the game when user picks the hard difficulty option. 
 * It contains @param hardGrid that creates a new Grid based on the set number of columns for hard level
 * and contains getter for getting the grid
 * */
public class MediumGame extends GameLogic {
	Defines DEF = new Defines();
	Grid mediumGrid;
	
	/** @const MediumGame reads in the words assigned for easy category. In this case, we've chosen 6 letter words as easy
	 * It then initializes value for mediumGrid
	 * */
	public MediumGame() throws FileNotFoundException{
		readWord("src/backend/sixLetterWords.txt");
		mediumGrid = new Grid(DEF.getNUM_GUESS(),DEF.getMEDIUM());		
	}
	
	/**
	 * @return a mediumGrid object*/
	public Grid getGrid() {
		return mediumGrid;
	}
	
	/**
	 * @return the type of Level it is*/
	public String type() {
		return "medium";
	}

}
