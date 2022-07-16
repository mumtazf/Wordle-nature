package unlimitedwordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import backend.EasyGame;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Grid {

	private int noofrows;
	private int wordlen;

	private ArrayList<ArrayList<TextField>> gridList;
	
	VBox overall_grid;
	
	//default constructor
	public Grid()
	{
	
	}
	/** constructor
	 * @param int r: number of rows in grid
	 * @param int c: number of columns in grid**/
	public Grid(int r, int c){
		noofrows = r;
		wordlen = c;
		overall_grid =  new VBox();
		gridList = new ArrayList<ArrayList<TextField>>();


		buildGrid();
	}
	/**creates a grid with cells of textFields
	 * @param none
	 * @return none**/
	public void buildGrid(){
		ArrayList<HBox> rowList_tf = new ArrayList<HBox>();

		for (int k=0; k<noofrows; k++) {
			ArrayList<TextField> gridRow = new ArrayList<TextField>();
			HBox row_tf = new HBox();
			for (int i=0; i<wordlen; i ++) {
				TextField txt = new TextField();
				gridRow.add(txt);

			}
			
			gridList.add(gridRow);
			//add grid row to HBox
			//add to HBox
			row_tf.getChildren().addAll(gridRow);
			rowList_tf.add(row_tf);
		}
		
		overall_grid.setAlignment(Pos.CENTER);
		overall_grid.setLayoutX(0);
		overall_grid.setLayoutY(200);
		overall_grid.getChildren().addAll(rowList_tf);
		overall_grid.setAlignment(Pos.CENTER_LEFT);
		
	}
	/**getter for grid
	 * @return vBox overall_grid
	 * **/
	public VBox getGrid() {
		return overall_grid;
	}
	/**getter for gridList
	 * @return gridList
	 */
	public ArrayList<ArrayList<TextField>> getgridList(){
		return gridList;
	}
	/**getter for gridList
	 * @return gridList
	 */
	public int getWordlen() {
		return wordlen;
	}
	
	/**clears entire grid
	 * @param none
	 * @return none**/
	public void clearGrid() {
		for (int row=0; row<5; row++ ) {
			for (int col=0; col<wordlen; col++ ) {
				gridList.get(row).get(col).setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
				gridList.get(row).get(col).clear();
			}
		}
	}
	
	/**check if user's entry is valid
	 * @param int guessNum: index of row of grid the user is guessing in
	 * @return boolean: true if word is valid length false if not **/
	public boolean checkValidWord(int guessNum)  {
		StringBuilder guess = new StringBuilder();
		for (int i=0; i< wordlen; i ++) {
			guess.append(gridList.get(guessNum).get(i).getText());
		}
		if (guess.length() != wordlen ) {
			return false;
		}
		else {
			return true;
		}

		}
	
	/**check user's entry into grid
	 * @param String chosenWord: word user is trying to guess
	 * @param int guess_num: index of row of grid the user is guessing in
	 * @return String: user's guess entry into grid**/
	public String checkGuess(String chosenWord, int guess_num) {
		//created a string in order to make the word lowercase as the stringbuilder wouldn't allow us to do so.
		StringBuilder guess = new StringBuilder();
		String guessString = new String(); 
	
		//get each letter in each text box of row i
		for (int i=0; i< wordlen; i ++) {
			//build a string from each text box
			guess.append(gridList.get(guess_num).get(i).getText());
		}
		
		chosenWord = chosenWord.toLowerCase();
		guessString = guess.toString().toLowerCase();
			for (int i=0; i< wordlen; i ++ ) {
				if (chosenWord.contains(guessString.subSequence(i, i+1))) {
					if (chosenWord.charAt(i) == (guessString.charAt(i))) {
						gridList.get(guess_num).get(i).setStyle("-fx-text-fill: green; -fx-font-size: 20px;");
	
					}
					else {
					    gridList.get(guess_num).get(i).setStyle("-fx-text-fill: #FDDA0D; -fx-font-size: 20px;");

					}
				}else  {
				    gridList.get(guess_num).get(i).setStyle("-fx-text-fill: grey; -fx-font-size: 20px;");
				}
			}
		return guessString;
	}
	/**checks if user has guessed correct word
	 * @param String chosenWord: word user is trying to guess
	 * @param String guessString: user's guess
	 * @returns boolean: true if user has won false if not**/
	
	public boolean checkWin(String chosenWord, String guessString) {
		
		if (guessString.equals(chosenWord.toLowerCase())) {
			return true;
		}else
			return false;
	}
	public boolean gridFull(int guess_num) {
		if ((guess_num) == 4) {
			return true;
		}
		return false;
	}
	public void disableGrid() {
		overall_grid.setDisable(true);	
	}
	public void enableGrid() {
		overall_grid.setDisable(false);
	}
}




