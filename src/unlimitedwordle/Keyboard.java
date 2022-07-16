package unlimitedwordle;

import java.util.ArrayList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.util.HashMap;
import javafx.event.ActionEvent;

public class Keyboard {
	//declaring variables
	private ArrayList<HBox> keyRows = new ArrayList<HBox>(); //array list of array list of keys
	VBox keyGrid = new VBox();
	private HashMap<String, Boolean> letterMap; //hashmap that checks the value of the keys, value is false if the key has been grayed out
	private ArrayList<Button> keyboardKeys; 
	private ArrayList<ArrayList<Button>> keyboardKeysAll;
	private int numKeyRows = 3; 
	private int numCol;
	
	//array list of keys for each row
	ArrayList <String> firstRow = new ArrayList<String>(Arrays.asList("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P")); 
	ArrayList <String> secondRow = new ArrayList<String>(Arrays.asList("A", "S", "D", "F", "G", "H", "J", "K", "L")); 
	ArrayList <String> thirdRow = new ArrayList<String>(Arrays.asList("ENTER", "Z", "X", "C", "V", "B", "N", "M", "DEL")); 
	
	//keyboard constructor: build the keyboard and hashmap
	Keyboard(){
		buildKeyboard();
		buildHashMap();
	}
	
	/**
	 * builds the keyboard
	 * @param: none
	 * @return: none
	 */
	public void buildKeyboard() {
		
		//arraylists with keyboard letters 
		keyboardKeysAll = new ArrayList<ArrayList<Button>>();
		
		for (int i = 0; i < numKeyRows; i++) {
			 HBox row_key = new HBox(); 
			 ArrayList<Button> keyboardKeys = new ArrayList<Button>(); //initializing the keyboards to have 28 keys in the array list
			 if (i == 0) {
				 for (int j = 0; j < firstRow.size(); j++) {
					 Button key = new Button(firstRow.get(j));
					 keyboardKeys.add(key);
				 } 
			 }
			 if (i == 1) {
				 for (int j = 0; j < secondRow.size(); j++) {
					 Button key = new Button(secondRow.get(j));
					 keyboardKeys.add(key);
				 }
			 }
			 if (i == 2) {
				 for (int j = 0; j < thirdRow.size(); j++) {
					 Button key = new Button(thirdRow.get(j));
					 keyboardKeys.add(key);
				 }
			 }
			 
			 row_key.getChildren().addAll(keyboardKeys);
			 keyboardKeysAll.add(keyboardKeys);
			 row_key.setSpacing(2);
			 keyRows.add(row_key);
		 }
		 
		 keyGrid.setAlignment(Pos.BOTTOM_CENTER);
		 keyGrid.setLayoutY(300);
		 keyGrid.getChildren().addAll(keyRows);	
		 keyGrid.setSpacing(5);
	}

	/**
	 * returns the build keyboard
	 * @param: none
	 * @return: VBox
	 */
	public VBox getKeyboard() {
		return keyGrid;
	}
	
	/**
	 * returns the number of rows of the keyboard
	 * @param: none
	 * @return: integer
	 */
	public int getRowNumber() {
		return numKeyRows;
	}
	
	/**
	 * returns the number of columns of each row
	 * @param: int (position of row)
	 * @return: int (number of columns)
	 */
	public int getColumnNumber(int rowNumber) {
		if (rowNumber == 0) {
			return firstRow.size();
		}
		if (rowNumber == 1) {
			return secondRow.size();
		}
		if (rowNumber == 2) {
			return thirdRow.size();
		}
		return numCol;
	}

	/**
	 *builds the hash map of letters
	 *holds value of true or false to check if the letters not in the word are grayed out or not 
	 *@param: none
	 *@return: none
//	 */
	public void buildHashMap() {
		letterMap = new HashMap<String, Boolean>();
		ArrayList <String> alphabets = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
		for (int i = 0; i < alphabets.size(); i++) {
			letterMap.put(alphabets.get(i), true);
		}
	}
	
	/**
	 * finds the key which holds a letter
	 * @param: String (the letter)
	 * @return: Button (key for that letter)
	 */
	public Button findButton(String letter) {
		Button buttonFound = new Button();
		for (int i = 0; i < keyboardKeysAll.size(); i++) {
			for (int j = 0; j < keyboardKeysAll.get(i).size(); j++) {
				if (keyboardKeysAll.get(i).get(j).getText().equals(letter)) {
					return keyboardKeysAll.get(i).get(j);
				}
			}
		}
		
		return buttonFound;
	}
	
	/**
	 * check if the letter is used in a guess, is wrong and grays them out 
	 * @param: String (the guess), String (the chosenWord)
	 * @return: none
	 */
	public void checkLetters(String guess, String actualWord) {
		Button key = new Button();
		
		for (int i = 0; i < guess.length(); i++) {
			String letter = Character.toString(guess.charAt(i)).toUpperCase();
			//if the letter from the guess is not in the word and its value in the hashmap is true,
			if (!actualWord.toUpperCase().contains(letter) && letterMap.get(letter) == true) {
					//change its value to false in the hash map
					letterMap.replace(Character.toString(guess.charAt(i)), false);
					//set the color of the key to be grey
					key = findButton(letter);
					findButton(letter).setStyle("-fx-background-color: #808080, #808080, #000000");
			}
		}
	}

	
	/**
	 * @param: int (row position), int (column position)
	 * @return: button at this position
	 * the click event e
	 * 
	 */
	public Button getKey(int i, int j) {
		return keyboardKeysAll.get(i).get(j);
	}
	
	/**
	 * clears the grayed out keyboard and sets it to its original color
	 * @param: none
	 * @return: none
	 */
	public void clearKeys() {
		for (int i = 0; i < keyboardKeysAll.size(); i++) {
			for (int j = 0; j < keyboardKeysAll.get(i).size(); j++) {
				keyboardKeysAll.get(i).get(j).setStyle("-fx-background-color:#e0b1cffb, #48bfe3, #5390d9,  radial-gradient(center 50% 50%, radius 100%,#5390d9,#48bfe3)");
			}
		}
	}
	
	
}



