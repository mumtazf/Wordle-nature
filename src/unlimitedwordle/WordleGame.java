package unlimitedwordle;

import backend.EasyGame;
import backend.GameLogic;
import backend.HardGame;
import backend.MediumGame;

import java.awt.event.KeyEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
//new 
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;


/**
 * WordleGame maintains the UI of the application
 * Sets up the interface with all the required components
 * @param gameScene sets the game scene with grid and keyboard
 * @param gameControl vertically sets the music button and score option along with additional controls
 * @param additionalControls sets the newgame and exit buttons in a horizontal aspect
 * @param difficultyLevel passes the value of difficulty
 * @param playGame initializes the backend for changing difficulty level and chosing words based on difficulty level
 * @param welcomeUser contains start scene display
 * @param musicButton starts the music
 * @param startGame button allows the user to move from start scene to game scene
 * @param gameTitle displays "Wordle Game"
 * @param newGame allows users to choose difficulty levels
 * @param exitButton allows users to exit the game
 * @param guessingGrid initializes the grid based on the difficulty level
 * @param keyboard for creating the keyboard.
 */
public class WordleGame extends Application {
	// container variables to set up the stage and animation
	private Scene scene;
	private Stage stage;
	private VBox gameScene;
	private VBox gameControl;
	private HBox additionalControls; 
	private HBox displayGameScene;
	private StackPane stackingPane;

    //variable to store the chosen word
	private String chosenWord = new String();
	GameLogic playGame;
	
	private VBox welcomeUser;
	boolean isWin = false;

	//default difficulty level
	String difficultyLevel;
	
	// component variables
	private Button musicButton;
	private Button startGame;
	private Label gameTitle;
	private Button newGame;
	private ComboBox<String> difficultyButton; 
	private Button exitButton;

	//grid variable
	private Grid guessingGrid;
	
	//Variable for accessing image and music elements
	private Defines DEF = new Defines();
	private Boolean toggleValue = true;
	
	//variable for keyboard
	private Keyboard keyboard;
	
	//variable for initializing new game scenes
	HBox root1;
	
	/**
	 * Event handler for keyboard 
	 * when the keys are pressed, the events that should happen!
	 */
	int rowPos = 0; 
	int colPos = 0; 
	boolean pressedEnter = false;
	HashMap<Button, Integer> pressedButton = new HashMap();
	
	//score variables
	private TextField scoreField;
	private int score;
	
	//variables for different game scenes
	private Button startButton;
	private String userName;
	private EasyGame easyGameObj;
	
	//list of valid words
	private ArrayList<String> validWords;

	//Alerts for the game 
	Alert noDifficultyChosen = new Alert(AlertType.ERROR, "Please choose a difficulty level", ButtonType.OK);
	Alert lost = new Alert(AlertType.NONE, "You Lost :( Choose New Game or Exit", ButtonType.OK);
	Alert won = new Alert(AlertType.NONE, "Congratulations! You Won! Choose New Game or Exit", ButtonType.OK);
	Alert invalid = new Alert(AlertType.ERROR, "Not a valid word length, guess a different word", ButtonType.OK);


	public static void main(String[] args) {
		
		launch(args);		
	}
	
	
	/** createGameLevels() is used to create game scenes depending on the difficulty levels chosen by the user
	 * It also chooses the word based on the difficulty level chosen
	 * @throws FileNotFoundException 
	 */
	public Grid createGameLevels(String difficultyLevel) { //try-catch
		
		try {
		
		if(difficultyLevel.equals("Easy")) {
			playGame = new EasyGame();
		}
		else
			if(difficultyLevel.equals("Medium"))
				playGame = new MediumGame();
			if(difficultyLevel.equals("Hard"))
				playGame = new HardGame();
		} catch (Exception e) {
			System.err.print(e.getMessage());
		}
		
		chosenWord = playGame.chooseWord();
		System.out.println("word choosing in createGameLevels: "+ chosenWord);
		validWords = playGame.getWords();

		return playGame.getGrid();
	}
	

	
	/**
	 * Sets up the beginning scene of the application
	 */
	@Override
	public void start(Stage primaryStage) {
		
		// initialize scene graphs and UIs
		startGameScene();
     
		HBox startScene = new HBox();
    	
    	//inline style setting
    	
    	VBox.setMargin(welcomeUser, new Insets(0,0,0,15));
    	startScene.getChildren().addAll(welcomeUser);
		//changes the positioning of our buttons
    	startScene.setAlignment(Pos.CENTER); 
		scene = startGameScene();
		
		//stylesheet for styling
		scene.getStylesheets().add("resources/style.css");
    
        // finalize and show the stage
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle(DEF.STAGE_TITLE);
        primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/** @method startGameScene() initializes the first scene of the game where users can choose the difficulty level 
	 * using the parameter @param difficultyLevel
	 * */
	 public Scene startGameScene() {
	 		
		 	soundEffects("start");
	 		startButton = new Button();
	 		
	 		//a hbox that welcomes the user
	 		welcomeUser = new VBox();
	 		
	    	startButton = new Button("Play Game");
	    	startButton.setGraphic(DEF.IMVIEW.get("play"));
	    	startButton.setLayoutX(500);
	    	startButton.setLayoutY(500);	    	
    		startButton.setOnAction(this::playGame);
	 
	 		//Scene designing
	 		gameTitle = new Label("Wordle : Garden Edition");
	 		gameTitle.applyCss();

	     	welcomeUser.setStyle("-fx-background-image: url(resources/images/flower_garden.jpg);" + "-fx-background-size:cover;");
	 		
	     	welcomeUser.setStyle("-fx-background-image: url(resources/images/flower_scene6.jpg);" + "-fx-background-size:cover;");

	     	welcomeUser.getChildren().addAll(gameTitle);

	     	welcomeUser.setAlignment(Pos.CENTER);
	     	
	     	//Setting up Toggle for game difficulty
	        
	        difficultyButton = new ComboBox();
	        ObservableList<String> difficulty_level = FXCollections.observableArrayList("Easy", "Medium", "Hard");
	        difficultyButton.setItems(difficulty_level);
	        difficultyButton.setPromptText("Difficulty Levels");
	        
	        difficultyButton.setOnAction(this::difficultyClick);
	        
	        welcomeUser.setSpacing(100);
	     	welcomeUser.getChildren().addAll(difficultyButton, startButton);
	     	scene = new Scene(welcomeUser, DEF.SCENE_WIDTH, DEF.SCENE_WIDTH);
	 		scene.getStylesheets().add("resources/style.css");

	 		return scene;
		}
	    
     
	/**
	 * Resets the gameplay graph
	 * @return 
	 * @throws FileNotFoundException 
	 */
	public VBox resetGameScene() {
		gameScene = new VBox();  
		//styling the gameScene
	 	//gameScene.setStyle("-fx-background-image: url(resources/images/flower_garden.jpg);" + "-fx-background-size: cover;");
	 	
	 	//Calling the createGameLevels to create the grid and initialize word
		guessingGrid = createGameLevels(difficultyLevel);
		//adding the grid to the gameScene
		gameScene.getChildren().addAll(guessingGrid.getGrid()); //I want to add this to playGame()
		
		//Creating the keyboard and adding it to the scene
		keyboard = new Keyboard();
		gameScene.getChildren().add(keyboard.getKeyboard());
		gameScene.setAlignment(Pos.CENTER);
		gameScene.setSpacing(60);
	
		//Event handling for the keyboard
		for(int i = 0; i < keyboard.getRowNumber(); i++) {
			for(int j = 0; j < keyboard.getColumnNumber(i); j++) {
				keyboard.getKey(i, j).setOnAction(this::buttonClickKey);
			}
		}
		return gameScene;
	}
	/**
	 * @method resetGameControl() resets the game control. This method is useful for reseting the buttons
	 * when new game scene is initialized.
	 */
	public Scene resetGameControl() {
		musicButton = new Button();
		
		//Setting the size of the button
		musicButton.setPrefSize(DEF.BLOB_WIDTH, DEF.BLOB_WIDTH);
		
	    //Setting a graphic to the button
		musicButton.setGraphic(DEF.IMVIEW.get("music"));
		musicButton.setOnMouseClicked(this::playMusic);
		//startButton.setOnMouseClicked(this::makeTrophyDance);
		
		//Add button to game control
        gameControl = new VBox();
        gameControl.setPadding(new Insets(20,0,0,0));
        gameControl.setSpacing(10);
        gameControl.setAlignment(Pos.TOP_RIGHT);
    	gameControl.setLayoutX(150);
    	gameControl.setLayoutY(150);
        gameControl.getChildren().addAll(musicButton); //how does this work?
		gameControl.setStyle("-fx-background-image: url(resources/images/flower_garden.jpg);" + "-fx-background-size: cover;");
              
        //Create a new Horizontal box to have exit game and new game options
        additionalControls = new HBox();
                
        //New Game Button
        newGame = new Button("New Game");
        newGame.setOnAction(this::newGame);
        additionalControls.getChildren().addAll(newGame);
        additionalControls.setAlignment(Pos.TOP_RIGHT);
        
    	//Exit button
    	exitButton = new Button("Exit");
    	exitButton.setOnAction(this::exitGame);
        additionalControls.getChildren().addAll(exitButton);
        
        //Score
        scoreField = new TextField("Score: " + score);
        scoreField.setMinWidth(150);
        scoreField.setAlignment(Pos.TOP_RIGHT);
        gameControl.getChildren().addAll(scoreField);
        
        gameControl.getChildren().addAll(additionalControls);
        setStyleGameControl();

    	scene = new Scene(gameControl, DEF.SCENE_WIDTH, DEF.SCENE_WIDTH);
 		scene.getStylesheets().add("resources/style.css");
 		
        return scene;
	}
    

	/**playGame() initializes the first game scene right after the user presses play game from the entry scene.
	 * @see startGameScene() for entry scene
	 * */
	private void playGame(ActionEvent e) {
		
		if(validateDifficultyClick()) {
			
	 		pressedEnter = false;
	 		displayGameScene = new HBox();
			System.out.println("Hi from playGame");
			
			//Turn off start scene music
			DEF.startSound.stopClip();

			//get the stage
			stage = (Stage)(welcomeUser.getScene().getWindow()); 
			
			//get the scene from reset game control
			resetGameScene();
			resetGameControl();
			
			//Add these scenes to the root
			stackingPane = new StackPane();
			displayGameScene.getChildren().addAll(gameScene, gameControl);
			displayGameScene.setAlignment(Pos.TOP_RIGHT); //changes the positioning of our buttons
			setStyleGameScene();
	     
			//stackingPane is used to show confetti for when the user wins the game
			stackingPane.getChildren().add(displayGameScene);
			scene = new Scene(stackingPane, DEF.APP_HEIGHT, DEF.APP_WIDTH);
	 		scene.getStylesheets().add("resources/style.css");
			
			//set the stage and show the scene
	 		
			stage.setScene(scene);
			stage.show();
		}		
	}
	
     /**
      * newGame() is used to begin the game once the user picks a difficulty level
      * */
     private void newGame(ActionEvent e) {
    	guessingGrid.clearGrid();
    	guessingGrid.enableGrid();
    	keyboard.clearKeys();
 		chosenWord = playGame.chooseWord();
 		rowPos = 0; 
 		colPos = 0; 
 		pressedEnter = false;
 		System.out.println("chosenWord in newGame:" + chosenWord);
		musicButton.setOnMouseClicked(this::playMusic);
 		
		stage = (Stage) (gameControl.getScene().getWindow());

		stackingPane = new StackPane();
		root1 = new HBox();
		root1.getChildren().addAll(gameScene,gameControl);
		root1.setAlignment(Pos.TOP_RIGHT);
   		setStyleRoot();
     	stackingPane.getChildren().add(root1);
		scene = new Scene(stackingPane, DEF.APP_HEIGHT, DEF.APP_WIDTH);
 		scene.getStylesheets().add("resources/style.css");
		stage.setScene(scene);
		stage.show();
		
     }
     
     /**
      * exitGame() is the event handler for ending the game
      * */
     private void exitGame(ActionEvent e) {
    	 endGameScene();
     }
   
  /**
   * endGameScene() is used to end the game and display the score of the user
   * */
	private void endGameScene() {
	 	soundEffects("end");

		VBox root = new VBox();
		stage = (Stage) (gameControl.getScene().getWindow());
     	root.setStyle("-fx-background-image: url(resources/images/rose.png);" + "-fx-background-size: cover;");

		Label scoreValue = new Label("Your Total "+ scoreField.getText());
		//setId is used in order to specify the label for CSS styling
		scoreValue.setId("scoreValue");
						
		gameTitle.applyCss();
		
		//configuring sizes for flowerfromcat gif
		ImageView flowerFromCat = new ImageView("resources/images/flower-gentleman.gif");
		flowerFromCat.setFitHeight(350);
		flowerFromCat.setFitWidth(350);
		
		root.getChildren().addAll(gameTitle,flowerFromCat,scoreValue);
		root.setSpacing(75);
		root.setAlignment(Pos.CENTER);

		scene = new Scene(root, DEF.APP_HEIGHT, DEF.APP_WIDTH);
		scene.getStylesheets().add("resources/style.css");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 *  buttonClickKey() is used for event handling of the keyboard and prevent the user from going back to 
	 * the previous row if evaluation of the grid was done.
	 * */
	public void buttonClickKey(ActionEvent e) {
		Button clicked = (Button) e.getSource();		
		
		if (clicked.getText() != "ENTER" && clicked.getText() != "DEL") {
				if(rowPos == (guessingGrid.getWordlen()-1) && colPos == guessingGrid.getWordlen()) { //edge case
					
				}else if (colPos == guessingGrid.getgridList().get(0).size() && pressedEnter == true) {
					pressedEnter = false;
					rowPos += 1; 
					colPos = 0;
					
					guessingGrid.getgridList().get(rowPos).get(colPos).setText(clicked.getText());
					colPos += 1;
				}
				else if (colPos < guessingGrid.getWordlen()){
					guessingGrid.getgridList().get(rowPos).get(colPos).setText(clicked.getText());
					colPos += 1;
				}
			}

		/**
		 * clear the field that has been added
		 */
		else if(clicked.getText() == "DEL") {
			if ((colPos <= 0) || (pressedEnter == true)) {} //edge case
			else {
				colPos -= 1;
				guessingGrid.getgridList().get(rowPos).get(colPos).clear();
			}
			
		}
		/**
		 * evaluate guess and show hints
		 */
		else if (clicked.getText() == "ENTER") {
			boolean validWord = guessingGrid.checkValidWord(rowPos);
			if (validWord == false) {
				pressedEnter = false;
				invalid.showAndWait();
			}
			else {
			String guess = guessingGrid.checkGuess(chosenWord, rowPos);
			keyboard.checkLetters(guess, chosenWord);
			isWin = guessingGrid.checkWin(chosenWord, guess);
			boolean isWin = guessingGrid.checkWin(chosenWord, guess);
			boolean isFull = guessingGrid.gridFull(rowPos);
			
			//if the user correctly guesses the word, then we show confetti and give the sound effects
			if (isWin) {
				showConfetti();	
				score += (6-(rowPos+1));
				scoreField.setText("Score: " + score);
				//do confetti
				guessingGrid.disableGrid();
				//winning sound effects
				soundEffects("won");
				won.showAndWait();
				return;

			}
			//this condition is for when the user loses the game
			if (isFull) {
				System.out.println("Grid full");
				soundEffects("lost");
				lost.showAndWait();
			}
			pressedEnter = true;
			}

		}
	}
	
	/**
	 * difficultyClickEqual() receives information from the
	 * combobox and stores the value of the button click
	 * 
	 * */
     public void difficultyClick(ActionEvent e) {
		difficultyLevel = ((String)((ComboBox) e.getSource()).getSelectionModel().getSelectedItem());

     }
     
	/** validateDifficultyClick() is used to validate if the user has picked a difficulty level and throw an error
	 * if they haven't picked one before pressing play game.
	 * */
     private boolean validateDifficultyClick() {
    	 if(difficultyLevel == null) {
    	 	noDifficultyChosen.showAndWait();
    	 	return false;
    	 }
    	 
    	 return true;
  }
	
	/**
	 * showConfetti() is used to show the confetti when the user wins the game by correctly guessing 
	 * the word.
	 * */
	public void showConfetti() {
		
		Random random = new Random();
		
		int numCircle = 2000;
		 Circle c[] = new Circle[numCircle];
	        for (int i = 0; i < numCircle; i++) {
	        	int r = random.nextInt(256);
	    		int g = random.nextInt(256);
	    		int b = random.nextInt(256);
	    		
	            c[i] = new Circle(-DEF.SCENE_WIDTH, 10, 10);
	            c[i].setRadius(random.nextDouble() * 2);
	            Color color = Color.rgb(r, g, b, 1);
	            c[i].setFill(color);
	            stackingPane.getChildren().add(c[i]);
	            rainingConfetti(c[i]);   
	        }
	}
	
	/**
	 * rainingConfetti() is used for the animation of the confetti and ensuring that the confetti is correctly
	 * positioned in the middle of the scene.
	 * */
	public void rainingConfetti(Circle c) {
	    Random random = new Random();
	    int time = 3000 + random.nextInt(500);
		c.setCenterX(Math.pow(-1,  random.nextInt(2))* (random.nextInt(DEF.SCENE_WIDTH) - DEF.SCENE_WIDTH));
		 TranslateTransition fall = new TranslateTransition(Duration.millis(time),c);
		   
	        fall.setFromY(-DEF.SCENE_WIDTH/2);
	        fall.setFromX(0);
	        fall.setToY(DEF.SCENE_WIDTH);
	        fall.setToX(random.nextDouble() * c.getCenterX()*Math.pow(-1, random.nextInt()));
	        fall.setOnFinished(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle (ActionEvent t) {
	        		rainingConfetti(c);
	        	}
	        });
	        fall.play();
	}
	
	/**
	 * setStyleGameControl() is used to set the background for the game control (music, score, newgame button, etc.) section
	 * */
	public void setStyleGameControl() {
		if (playGame.type().equals("easy")) {
			gameControl.setStyle("-fx-background-image: url(resources/images/flower_garden.jpg);" + "-fx-background-size: cover;");
		}
		if (playGame.type().equals("medium")) {
			gameControl.setStyle("-fx-background-image: url(resources/images/flower_scene1.jpg);" + "-fx-background-size: cover;");
		}
		if (playGame.type().equals("hard")) {
			gameControl.setStyle("-fx-background-image: url(resources/images/flower_scene2.jpg);" + "-fx-background-size: cover;");	
		}
	}
	
	/**
	 * setStyleGameScene() is used to set the background for the game scenes where the grid and keyboard is present
	 * */
	public void setStyleGameScene() {
		if (playGame.type().equals("easy")) {
			displayGameScene.setStyle("-fx-background-image: url(resources/images/flower_garden.jpg);" + "-fx-background-size: cover;");
		}
		if (playGame.type().equals("medium")) {
			displayGameScene.setStyle("-fx-background-image: url(resources/images/flower_scene1.jpg);" + "-fx-background-size: cover;");
		}
		if (playGame.type().equals("hard")) {
			displayGameScene.setStyle("-fx-background-image: url(resources/images/flower_scene2.jpg);" + "-fx-background-size: cover;");	
		}
	}
	
	/**
	 * setStyleRoot() is used to set the background for the new game scenes 
	 * */
	public void setStyleRoot() {
		if (playGame.type().equals("easy")) {
			root1.setStyle("-fx-background-image: url(resources/images/flower_garden.jpg);" + "-fx-background-size: cover;");
		}
		if (playGame.type().equals("medium")) {
			root1.setStyle("-fx-background-image: url(resources/images/flower_scene1.jpg);" + "-fx-background-size: cover;");
		}
		if (playGame.type().equals("hard")) {
			root1.setStyle("-fx-background-image: url(resources/images/flower_scene2.jpg);" + "-fx-background-size: cover;");	
		}
	}
	
    /** playMusic() allows user to play music on the basis of their preference of having music on or off.
     * */
 private void playMusic(MouseEvent e) {
    //Background music
	 if(toggleValue) {
		toggleValue = false; 
		musicButton.setGraphic(DEF.IMVIEW.get("pause"));
	 	DEF.backgroundMusic.playClip();
	 }
	 
	 else {
		toggleValue = true;
			musicButton.setGraphic(DEF.IMVIEW.get("music"));
			DEF.backgroundMusic.stopClip();
	 }
}
 
 /**
  * soundEffects is used to set the sound effects for when the user has won the game or when they've lost the game. 
  * It is also used to set the sound effects for the entry and exit scene.
  * */
 
 private void soundEffects(String gameStatus) {
	 
	 switch(gameStatus) {
	 
	 case "won" : {    	
						DEF.backgroundMusic.stopClip();
    		 			DEF.clappingSound.playClip();
    		 			break;
	 			  }
	 
	 case "lost" : {
		 				DEF.backgroundMusic.stopClip();
		 				DEF.losingSound.playClip();
		 				break;
	 			   }
	 
	 case "start" : {
		 				DEF.startSound.playClip();
		 				break;
	 				}
	 
	 case "end" :   {
						DEF.backgroundMusic.stopClip();
						DEF.endSound.playClip();
						break;
					}
	 	}
 	}
}


	