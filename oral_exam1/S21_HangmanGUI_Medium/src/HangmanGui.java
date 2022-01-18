import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

//536 of Java how to program helped with basic layout
public class HangmanGui extends JFrame implements ActionListener {
    private final JPasswordField wordInput; //Where the word to be guessed is input only visible until words been input
    private final JTextField letterInput; //Where guessed letters are input, only first character is registered
    private final JTextField lettersUsedField;//Where lettersUsed are print out
    private final JTextField guessesLeftField;//Where guessesLeft are print out
    private final JTextField hangmanWordField;//Where the classic HangMan _ _ _ _ _ _ _letter spots and correct guesses show H a n g _ a n
    private final JTextField wordInputExplanation; //explains what to do with wordInput textfield
    private final JTextField letterInputExplanation;//explains what to do with letterInput textfield
    private final JTextField hangmanWordFieldExplanation;//explains hangman field
    private int guessesLeft; //how many guesses are left
    private int guessesUsed; //how many guesses have been used
    private final JPanel textGrid;//used to add GUIS
    private final JTextField[] textArray; //used to keep the TextFields together and loopable in a for loop
    private char [] word; //original word that was input saved as a character array all capitalized to make letter comparison easier
    private char [] hangmanWord; //the word in the HangMan format of _ _ _ _ and T o a _ it stores the current progress in hangman format
    private char[] lettersUsed; //a character array of letters that have been used

    /**
     * HangmanGui's constructor defines all the private variables and gives them a mostly uniform text size and font. the GUI
     * is a gridLayout of JTextFields and a JPasswordField attached to a JPanel.  GuessesTotal is a temp int that is used for
     * guessesleft, and lettersUsed array's length. By making it a temporary integer it's easier to change guessesTotal without editing
     * multiple areas
     */
    HangmanGui(){
        int GuessesTotal=8;//change this to change how many guesses the player gets

        textGrid = new JPanel();
        textGrid.setLayout(new GridLayout(4,2));

        textArray= new JTextField[7];
        //these are stored into the textfield array in a certain order for a reason as their added to JPanel and thus the GUI in this order
        wordInputExplanation=new JTextField("Insert Word Here (no _ symbol): ",40);
        wordInputExplanation.setEditable(false);
        wordInputExplanation.setPreferredSize(new Dimension(200, 50));
        wordInputExplanation.setFont(new Font("Serif", Font.PLAIN, 25));
        wordInputExplanation.setVisible(true);
        textArray[0]=wordInputExplanation;

        letterInputExplanation=new JTextField("Insert Letter Here :",40);
        letterInputExplanation.setEditable(false);
        textArray[1]=letterInputExplanation;

        //wordInput and letterInput are the only editable fields
        letterInput= new JTextField("");
        letterInput.addActionListener(this);
        letterInput.setEditable(true);
        letterInput.setVisible(false);
        textArray[2]=letterInput;

        hangmanWordFieldExplanation=new JTextField("Your Progress on the Word :",40);
        hangmanWordFieldExplanation.setEditable(false);
        textArray[3]=hangmanWordFieldExplanation;

        hangmanWordField= new JTextField("");
        hangmanWordField.setEditable(false);
        textArray[4]= hangmanWordField;

        guessesLeftField= new JTextField("Guesses Left: ");
        guessesLeftField.setEditable(false);
        textArray[5]=guessesLeftField;

        lettersUsedField= new JTextField("Letters Used: ");
        lettersUsedField.setEditable(false);
        textArray[6]=lettersUsedField;

        //wordInput and letterInput are the only editable fields
        wordInput = new JPasswordField("",40);
        wordInput.setEditable(true);
        wordInput.addActionListener(this);
        wordInput.setPreferredSize(new Dimension(200, 50));
        wordInput.setFont(new Font("Serif", Font.PLAIN, 25));
        wordInput.setVisible(true);

        textGrid.add(wordInputExplanation);
        textGrid.add(wordInput);
        //note wordInputExplanation is added seperately because wordInput is a PasswordField and needs to be added after wordInputExplanation
        for(int i=1; i<7; i++){ //thus the loop starts at 1, however wordInput is added into this array to be used for when the game is over
            textArray[i].setPreferredSize(new Dimension(200, 50)); //loop was to make less repeated code
            textArray[i].setFont(new Font("Serif", Font.PLAIN, 25));
            if(i!=2) { //don't want letterInput visible until word has been input
                textArray[i].setVisible(true);
            }
            textGrid.add(textArray[i]);
        }
        add(textGrid);

        //effects of the int GuessesTotal thats equal to 8 at the top of constructor
        lettersUsed = new char [GuessesTotal];
        for(int i=0; i<GuessesTotal;i++){
            lettersUsed[i]=' '; //saved as spaces for now as the loops get complicated with lettersUsed and don't want to accidentally
                                //print something that would look weird or off
        }
        guessesUsed=0; //0 is the default just being clear it starts at 0
        guessesLeft=GuessesTotal;
    }


    /**
     * actionPerformed is the function triggered by actionlisteners that only wordInput and letterInput have
     * Each situation have a function that is triggered after the input is saved into the class'es private variables
     * @param e action being listened for by actionlisteners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wordInput) { //when word is first input
            //makes char arr of uppercase only version of input
            char[] tempWord = wordInput.getPassword(); //used to store the input temporarily before converting each individual character
            for(int i=0; i<tempWord.length;i++){        // then send the capitalized version to this.word to be stored
               tempWord[i]=Character.toUpperCase(tempWord[i]);
            }
            this.word=tempWord;
            wordInputSent(); //action to be completed now that word has been made
        }
            if (e.getSource() == letterInput) { //a letter was input/guessed
                char[] letterArray = letterInput.getText().toUpperCase().toCharArray(); //comparison is to fully uppercase word
                letterInputSent(letterArray[0]); //first character is the only thing looked at when a "letter" is guessed
            }

        }


    /**
     * wordInputSent is the response to after a word is input to be guessed. It could be jammed into action performed but
     * it wouldn't be as organized so put into its own function. It handles visibility of wordInput and letterInput as well as
     * the display of wordInputExplanation. Creates hangmanWord initially
     */
    public void wordInputSent(){
        hangmanWord= new char[word.length*2]; //due to _ _ looking like __ without a space dividing them
        for(int i=0; i<(word.length*2);i+=2){
            if(this.word[i/2]!=' ') {//if word doesn't have a space
                hangmanWord[i] = '_';
                hangmanWord[i + 1] = ' ';
            }
            else{//if word does have a space at that position do a double space to show theres a difference
                hangmanWord[i] = ' ';
                hangmanWord[i + 1] = ' ';
            }
        }
        String hangmanString = new String(hangmanWord);//conversion of the character array made to a string
        hangmanWordField.setText(hangmanString); //needed the string to setText
        wordInput.setVisible(false); //after wordInput has been put in no reason for this field to stay visible
        letterInput.setVisible(true); //now players can guess letters
        wordInputExplanation.setText("Word Has Been Input"); //show that they did it correctly and explain why that field is no longer visible
    }

    /**
     * letterInputSent is the function designed for after a letter is input. It's its own function to keep action performed readable
     * letterInputSent  handles guessesUsed, guessesLeft,hangmanWord after initial creation,lettersUsed array, and contains the call
     * to the game over functions that's eventually indirectly called to DriverClass via actionPerformed. It's similar to a loop that runs the game
     * @param input is the capitalized character that has been guessed it's prepared in actionPerformed
     */
    public void letterInputSent(char input) {
        boolean CorrectLetter = false;  //symbolizes whether the letter is correct or better thought process would be if the player doesn't deserve to lose a guess
        for (int i = 0; i < this.word.length; i++) { //rotates through word and compares if the letter is ever equal
            if (this.word[i] == input) {
                CorrectLetter = true;
            }
            if (input == ' ') {//players shouldn't be penalized for guessing space even though it's given
                CorrectLetter = true;
            }
        }
        if (CorrectLetter == true) { //aka not being penalized
            for (int i = 0; i < this.word.length; i++) {
                if (this.word[i] == input) {    //shouldn't be punished for using space but no reward as space is already given
                    if (i != 0) { //if the letter isnt in the first position and shouldnt be capitalized
                        this.hangmanWord[i * 2] = Character.toLowerCase(input);
                    } else {//if it should be capitalized as it is in the first position
                        this.hangmanWord[i * 2] = input;
                    }
                }
            }
            String hangmanString = new String(hangmanWord);//conversion to string to setText output
            this.hangmanWordField.setText(hangmanString);
        } else {
            if (usedLetterChecker(input) == true) { //letterChecker checks if that letter was already guessed incorrectly
                //letters thats been guessed has already been guessed and was wrong
                //nothing happens as they shouldn't be penalized nor rewarded for messing up
            }
            else {
                //assign the array before increasing guessesused because the indexes start at 0
                this.lettersUsed[this.guessesUsed] = input;
                this.guessesLeft--;
                this.guessesUsed++;
                //guesses used must be incremented before this array otherwise length starts at 0*2
                //made lettersused start filled with spaces so doesn't print anything weird
                char[] lettersUsedOutput = new char[this.guessesUsed * 2];
                int current = 0;
                for (int i = 0; i < (this.guessesUsed * 2); i += 2) {  //they're spaced out to make them clearer to read
                    lettersUsedOutput[i] = this.lettersUsed[current];
                    lettersUsedOutput[i + 1] = ' ';
                    current++;
                }
                String lettersUsedOutputString = new String(lettersUsedOutput); //string conversion for setText
                String lettersUsedOutputStringComplete= "Letters Used: "+ lettersUsedOutputString;
                lettersUsedField.setText(lettersUsedOutputStringComplete);
                String guessLeftOutputStringComplete= "Guesses Left: " + Integer.toString(this.guessesLeft);
                this.guessesLeftField.setText(guessLeftOutputStringComplete);
            }
        }
        letterInput.setText(""); //clears to show that it's been registered
        if(guessesLeft==0){ //note guesses left ultimately the losing decider
            gameOverLose();
        }
        if(checkForWin()==true){
            gameOverWin();
        }
    }

    //true symbolizes that the letter is not in the word being guessed, but has already been used

    /**
     * usedLetterChecker checks if the input has already been guessed incorrectly in which case it will return a boolean
     * false if the letter hasn't been guessed, true if the letter has been guessed before
     * @param input is the character being checked if it's been guessed incorrectly in the past
     * @return returnVal which is a boolean false if the letter hasn't been guessed incorrectly yet, true if it has been guessed incorrectly in the past
     */
    public boolean usedLetterChecker(char input){
        boolean returnVal=false;
        for(int i=0; i<this.lettersUsed.length;i++){ //checks lettersUsed to see if it's been used before
            if(input==lettersUsed[i]){
                returnVal=true;
            }
        }
        return returnVal;
    }

    /**
     * gameOverLose is the action performed when the game is lost. It focuses on notifying the player is game over by
     * changing the text on the textfields, making them not editable, and posts the correct answer
     */
    public void gameOverLose(){ //what happens when the player loses
        for(int i=0; i<7; i++){
            if(i!=4) {
                textArray[i].setText("You Lose, Game Over");
            }
            else{
                String PityAnswer= new String(word);
                textArray[i].setText(PityAnswer);
            }
            textArray[i].setEditable(false);
        }
    }

    /**
     * gameOverWin is the action that is performed when the game is over because the player won. It focuses on locking in and
     * notifying the player via the textFields
     */
    public void gameOverWin(){ //when the game is over because the player won
        for(int i=0; i<7; i++){
            if(i!=4) {
                textArray[i].setText("You Win, Nicely Done");
            }
            else{

            }
            textArray[i].setEditable(false);
        }
    }

    /**
     * checkForWin checks hangmanWord to see if theres any letters left to guess
     * @return returnVal which symbolizes if the player won, true for they won or false if they haven't won
     */
    public boolean checkForWin(){ //checks to see if the player won
        boolean returnVal=true;
        for(int i=0; i<hangmanWord.length; i++){ //note the game check hangmanWord for '_' using the logic that if
            if(hangmanWord[i]=='_'){                //the player finished filling in blank spots he won
            returnVal=false;                        // however if someone uses '_' in their word against what was defined there's no way the player can win
                                                    //to counter this the correct word is printed to players can't cheeat their friends
            }
        }
        return returnVal;
    }

}






