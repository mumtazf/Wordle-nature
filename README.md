# Unlimited Wordle - Nature Edition
unlimitedwordlex-eem created by GitHub Classroom


INTRODUCTION
-------------

Unlimited Wordle Nature Edition is a wordle game that is garden themed. It has three difficulty levels which are based on word lenght. The words and GUI are inspired by nature.

HOW TO PLAY THE GAME
--------------------
Guess a word with the theme garden to play the game. There are three levels: Easy, Medium and Hard. The length of the words increase with increasing difficulty level in the game but the number of tries remain the same. Once you type your guess, hit Enter and your guess is evaluated.

REQUIREMENTS
-------------
You must download Javafx on eclipe for this application: https://gluonhq.com/products/javafx/

CONFIGURATION
-------------
1. Select the project on the Package Explorer & Right-click :
Properties > Java Buildpath > Select Libraries tab
Choose Classpath > Click on Add Library > User Library > JavaFXLib > Finish
Apply & Close

2. Select the project on the Package Explorer :
Run > Run Configurations > Java Application >
Go to (x) = Arguments > Under VM Arguments > <path-to-javafx-sdk> & paste this :
--module-path <path-to-javafx-sdk>/lib --add-modules=javafx.controls,javafx.fxml,javafx.media
 
3. Uncheck the box for “Use the -XstartOnFirstThread” argument when launching with SWT”
(Probably only for MAC Users)

 FAQ
 ---
 Q: Why are some of the words so obscure?
 
 A: Because some are latin names of plants.
 
 Q: How do we exit the game completely?
 
 A: Click the x in the top right corner of the application
 
 
MAINTAINERS
-----------
Emma Roth-Wells (emmarw99)
 
Mumtaz Fatima (mumtazf)
 
Enam Dartey (abla-creator)
 
