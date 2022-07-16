package backend;
import java.io.File;
import unlimitedwordle.Grid;
import unlimitedwordle.Defines;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

	/**
	 * EasyGame is used to initialize the game when user picks the easy difficulty option. 
	 * It contains @param easyGrid that creates a new Grid based on the set number of columns for easy level
	 * and contains getter for getting the grid
	 * */
	public class EasyGame extends GameLogic {
	
		Defines DEF = new Defines();
		Grid easyGrid;
		
		/** @const EasyGame reads in the words assigned for easy category. In this case, we've chosen 5 letter words as easy
		 * It then initializes value for easyGrid
		 * */
		public EasyGame() throws FileNotFoundException { 
			readWord("src/backend/fiveLetterWords.txt");
			easyGrid = new Grid(DEF.getNUM_GUESS(), DEF.getEASY());
		}
		
		/**
		 * @return a grid object*/
		public Grid getGrid() {
			return easyGrid;
		}
		
		/**
		 * @return the type of Level it is*/
		public String type() {
			return "easy";
		}
	
	}
