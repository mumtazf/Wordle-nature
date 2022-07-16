package unlimitedwordle;
import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Maintains and initializes all the fixed parameters that control the application
 * 
 */
public class Defines {
	
	// coefficients related to the application & scene
	//game scene
	final int APP_HEIGHT = 700;
    final int APP_WIDTH = 750;
    final int SCENE_HEIGHT = 700;
    final int SCENE_WIDTH = 700;
   
    // coefficients related to the image blobs
    final int BLOB_WIDTH = 40;
    final int BLOB_HEIGHT = 40;
    
    // coefficients related to time
    //animation
    final double TRANSITION_DURATION = 4;
    final double TRANSITION_TIME = 0.5;
    final double TRANSITION_DELAY = 0.5;
    final int TRANSITION_CYCLE = 20;
   
    final String STAGE_TITLE = "Unlimited Wordle";
   
    // variables related to image files
    //sets path of directory where all the images are
    private final String IMAGE_DIR = "../resources/images/";
    //array of image files
    private final String[] IMAGE_FILES = {"play", "music", "pause","trampoline","rose", "doraemon", "confetti"};
    
    //Music variables
    final String MUSIC_DIR = "../resources/music";
    final Sound backgroundMusic;
    final Sound clappingSound;
    final Sound losingSound;
    final Sound startSound;
    final Sound endSound;

    
    // hashmap to access the images & their container image views by their file names directly
    final HashMap<String, ImageView> IMVIEW = new HashMap<String, ImageView>();
    final HashMap<String, Image> IMAGE = new HashMap<String, Image>();
    
    //number of guesses
    private final int NUM_GUESS = 5;
    
    private final int EASY = 5;
    private final int MEDIUM = 6;
    private final int HARD = 7;
     
    
    public Defines(){
    	
    	//creating images components for each of the image files
    	for(int i=0; i<IMAGE_FILES.length; i++) {
    		if(i<=1) {
    			Image img = new Image(pathImage(IMAGE_FILES[i]), BLOB_WIDTH, BLOB_HEIGHT, false, false);
    			IMAGE.put(IMAGE_FILES[i],img);
    		} else if (i==2) {
    			Image img = new Image(pathImage(IMAGE_FILES[i]), BLOB_WIDTH, BLOB_HEIGHT, false, false);
    			IMAGE.put(IMAGE_FILES[i],img);
    		}  else {
    			Image img = new Image(pathImage(IMAGE_FILES[i]), 8*BLOB_WIDTH, 8*BLOB_HEIGHT, false, false);
    			IMAGE.put(IMAGE_FILES[i],img);
    		}
    	}
    	
    	//creating image views containing each of the images
    	for(int i=0; i<IMAGE_FILES.length; i++) {
    		ImageView imgView = new ImageView(IMAGE.get(IMAGE_FILES[i]));
    		IMVIEW.put(IMAGE_FILES[i],imgView);
    	}
    	
    	//Initializing sound variables
    	//Music credits to BenSound
 		backgroundMusic = new Sound("../resources/music/backgroundMusic.wav");
 		clappingSound = new Sound("../resources/music/claps.wav");
 		losingSound = new Sound("../resources/music/gameOver.wav");
 		startSound = new Sound("../resources/music/ukulele-start.mp3");
 		endSound = new Sound("../resources/music/endMusic.mp3");

    }
    
    
    /**
     * Helper method to build complete URL of the image files from their names and locations
     * @param filepath relative filepath of image file
     * @return full path of the file.
     */
    public String pathImage(String filepath) {
    	String fullpath = getClass().getResource(IMAGE_DIR+filepath+".png").toExternalForm();
    	return fullpath;
    }


	public int getEASY() {
		return EASY;
	}


	public int getNUM_GUESS() {
		return NUM_GUESS;
	}


	public int getHARD() {
		return HARD;
	}


	public int getMEDIUM() {
		return MEDIUM;
	}
        

}
