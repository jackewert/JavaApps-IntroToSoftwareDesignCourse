import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class S20_TicTacToe {

    private boolean gameStarted;
    private HumanPlayer hPlayer1;
    private HumanPlayer hPlayer2;
    private ComputerPlayer cPlayer1;
    private ComputerPlayer cPlayer2;
    private boolean player1;//true means human player1
    private boolean player2;//false mean CPU player2
    private boolean turn;//true means first players turn
    private boolean hPlayersTurn; //true means tic tac toe buttons work

    @FXML
    private Button twoButt;

    @FXML
    private Button fourButt;

    @FXML
    private Button resetAndPvP;

    @FXML
    private Button fiveButt;

    @FXML
    private Button eightButt;

    @FXML
    private Button compGame;

    @FXML
    private Button nineButt;

    @FXML
    private Button sixButt;

    @FXML
    private Button threeButt;

    @FXML
    private Button playerCompGame;

    @FXML
    private Button sevenButt;

    @FXML
    private Button oneButt;

    /**
     * resetButt acts as both the button to announce a player vs player game and to reset the game
     * It's part of the gui and is trigger through interaction with its button
     * @param event
     */
    public void resetButt(ActionEvent event) {
        if (gameStarted == true) {
            oneButt.setDisable(true);
            oneButt.setText("Open");
            twoButt.setDisable(true);
            twoButt.setText("Open");
            threeButt.setDisable(true);
            threeButt.setText("Open");
            fourButt.setDisable(true);
            fourButt.setText("Open");
            fiveButt.setDisable(true);
            fiveButt.setText("Open");
            sixButt.setDisable(true);
            sixButt.setText("Open");
            sevenButt.setDisable(true);
            sevenButt.setText("Open");
            eightButt.setDisable(true);
            eightButt.setText("Open");
            nineButt.setDisable(true);
            nineButt.setText("Open");
            gameStarted = false;

            playerCompGame.setText("Player Vs CPU");
            playerCompGame.setDisable(false);
            compGame.setText("CPU Vs CPU");
            compGame.setDisable(false);
            resetAndPvP.setText("Player Vs Player");

        } else {
            hPlayer1 = new HumanPlayer(0);
            player1 = true;
            hPlayersTurn = true;
            hPlayer2 = new HumanPlayer(1);
            player2 = true;
            cPlayer1 = null;
            cPlayer2 = null;
            startGameHelper();
        }
    }

    /**
     * startGamePvCPU stands for start game player vs computer. After game has started it turns into CPU Turn button that must
     * be pressed for the computer to make their move.
     * @param event As this is part of the gui an event such as clicking this button will trigger the function
     */
    public void startGamePvCPU(ActionEvent event) {
        if (gameStarted != true) {
            hPlayer1 = new HumanPlayer(0);
            player1 = true;
            hPlayersTurn = true;
            cPlayer2 = new ComputerPlayer(1);
            player2 = false;
            hPlayer2 = null;
            cPlayer1 = null;
            startGameHelper();
        } else {
            if (hPlayersTurn == false) {
                while (cpuButtonPressed(ComputerPlayer.cpuButtonAttempt()) == false) ;
            }
        }
    }

    /**
     * startGameCPUvCPU is a button on the GUI that is used to start computer vs computer games
     * @param event sent via GUI by pressing the button starts the function
     */
    public void startGameCPUvCPU(ActionEvent event) {
        cPlayer1 = new ComputerPlayer(0);
        player1 = false;
        cPlayer2 = new ComputerPlayer(1);
        player2 = false;
        hPlayer1 = null;
        hPlayer2 = null;
        startGameHelper();
    }

    public void startGameHelper() {
        gameStarted = true;
        resetAndPvP.setText("Reset");
        compGame.setDisable(true);
        if (!player1 || !player2) {
            playerCompGame.setText("CPU Turn");
        } else {
            playerCompGame.setText("");
            playerCompGame.setDisable(true);
        }
        compGame.setText("");
        oneButt.setDisable(false);
        twoButt.setDisable(false);
        threeButt.setDisable(false);
        fourButt.setDisable(false);
        fiveButt.setDisable(false);
        sixButt.setDisable(false);
        sevenButt.setDisable(false);
        eightButt.setDisable(false);
        nineButt.setDisable(false);
        turn = true;
    }

    /**
     * buttOne through buttNine are the main TicTacToe buttons in a normal game. They all rely on the GUI and won't function
     * unless its a human player's turn
     * @param event
     */
    public void buttOne(ActionEvent event) {
        if (hPlayersTurn) {
            buttonClicked(0, oneButt);
        }
    }

    public void buttTwo(ActionEvent event) {
        if (hPlayersTurn) {
            buttonClicked(1, twoButt);
        }
    }

    public void buttThree(ActionEvent event) {
        if (hPlayersTurn) {
            buttonClicked(2, threeButt);
        }
    }

    public void buttFour(ActionEvent event) {

        if (hPlayersTurn) {
            buttonClicked(3, fourButt);
        }
    }

    public void buttFive(ActionEvent event) {

        if (hPlayersTurn) {
            buttonClicked(4, fiveButt);
        }
    }

    public void buttSix(ActionEvent event) {

        if (hPlayersTurn) {
            buttonClicked(5, sixButt);
        }
    }

    public void buttSeven(ActionEvent event) {

        if (hPlayersTurn) {
            buttonClicked(6, sevenButt);
        }
    }

    public void buttEight(ActionEvent event) {

        if (hPlayersTurn) {
            buttonClicked(7, eightButt);
        }
    }

    public void buttNine(ActionEvent event) {

        if (hPlayersTurn) {
            buttonClicked(8, nineButt);
        }
    }

    /**
     * buttClicked is part of the full buttonClicked code. It focuses on the GUI's appearance when a button is clicked.
     * @param button it intakes the button so it could be called by buttonClicked directly
     * @param playerNumber it needs the playerNumber to know which shape to output onto the gui
     */
    public void buttClicked(Button button, int playerNumber) {
        if (playerNumber == 0) {//players must be numbered 0 or 1
            button.setText("X");
        }
        if (playerNumber == 1) {
            button.setText("O");
        }
        button.setDisable(true);
    }

    /**
     * buttonClicked is the full response to a button on the TicTacToe Board being clicked with exception of a check to make sure
     * that its a human player's turn. buttonClicked also handles checking for Ties, and ending the game in general
     * @param buttonPressed intakes an integer representation of the button pressed from the button's handler to make it easier to send information to PlayerClasses
     * @param button button is input so the button's display can be changed
     */
    private void buttonClicked(int buttonPressed, Button button) {
        // boolean won=false;
        if (turn) {//player1 turn
            if (player1) {//hplayer1
                buttClicked(button, hPlayer1.getPlayerNo());
                hPlayer1.playersTurn(buttonPressed);
                if (!player2) {
                    hPlayersTurn = false;
                }
            } else {//cplayer1
                buttClicked(button, cPlayer1.getPlayerNo());
                cPlayer1.playersTurn(buttonPressed);
            }
            turn = false;
        } else {
            if (player2) {//hplayer2
                buttClicked(button, hPlayer2.getPlayerNo());
                hPlayer2.playersTurn(buttonPressed);
            } else {//cplayer2
                buttClicked(button, cPlayer2.getPlayerNo());
                cPlayer2.playersTurn(buttonPressed);
                if (player1) {
                    hPlayersTurn = true;
                }
            }
            turn = true;
        }
        if (checkForCatsGame()){
            catsGame();
        }
        if (checkGameWon()) {
            gameWon();
        }
    }

    /**
     * checkGamewon checks if the game has been won by somebody
     * @return boolean representation of whether the game has been won
     */
    public boolean checkGameWon() {
        boolean returnVal = false;
        if (player1) {
            if (hPlayer1.checkForWin()) {
                returnVal = true;
            }
        } else {
            if (cPlayer1.checkForWin()) {
                returnVal = true;
            }
        }
        if (player2) {
            if (hPlayer2.checkForWin()) {
                returnVal = true;
            }
        } else {
            if (cPlayer2.checkForWin()) {
                returnVal = true;
            }
        }
        return returnVal;
    }

    /**
     * gameWon is what is triggered if the game is won during buttonClicked. It locks up the board and announces the winner in the GUI.
     */
    public void gameWon() {
        String winPhrase = "Has Won";
        oneButt.setDisable(true);
        twoButt.setDisable(true);
        threeButt.setDisable(true);
        fourButt.setDisable(true);
        fiveButt.setDisable(true);
        sixButt.setDisable(true);
        sevenButt.setDisable(true);
        eightButt.setDisable(true);
        nineButt.setDisable(true);
        playerCompGame.setDisable(true);
        if (!turn) {
            if (player1) {
                winPhrase = "Real Player1";
            } else {
                winPhrase = "CPU Player1";
            }
        } else {
            if (player2) {
                winPhrase = "Real Player2";
            } else {
                winPhrase = "CPU Player2";
            }
        }
        compGame.setText("Has Won");
        playerCompGame.setText(winPhrase);

    }

    /**
     * cpuButtonPressed handles all computer interaction with the GUI and ComputerPlayer Class from the GUI. It is called after the game has started via
     * playerCompGame button.
     * @param buttonPressed
     * @return
     */
    public boolean cpuButtonPressed(int buttonPressed) {//false means buttons been pressed, for computers
        boolean returnVal = false;
        switch (buttonPressed) {
            case 1:
                if (!oneButt.isDisabled()) {
                    buttonClicked(0, oneButt);
                    returnVal = true;
                }
                break;
            case 2:
                if (!twoButt.isDisabled()) {
                    buttonClicked(1, twoButt);
                    returnVal = true;
                }
                break;
            case 3:
                if (!threeButt.isDisabled()) {
                    buttonClicked(2, threeButt);
                    returnVal = true;
                }
                break;
            case 4:
                if (!fourButt.isDisabled()) {
                    buttonClicked(3, fourButt);
                    returnVal = true;
                }
                break;
            case 5:
                if (!fiveButt.isDisabled()) {
                    buttonClicked(4, fiveButt);
                    returnVal = true;
                }
                break;
            case 6:
                if (!sixButt.isDisabled()) {
                    buttonClicked(5, sixButt);
                    returnVal = true;
                }
                break;
            case 7:
                if (!sevenButt.isDisabled()) {
                    buttonClicked(6, sevenButt);
                    returnVal = true;
                }
                break;
            case 8:
                if (!eightButt.isDisabled()) {
                    buttonClicked(7, eightButt);
                    returnVal = true;
                }
                break;
            case 9:
                if (!nineButt.isDisabled()) {
                    buttonClicked(8, nineButt);
                    returnVal = true;
                }
                break;
        }
        return returnVal;
    }

    /**
     * checkForCatsGame checks for a cats game, aka all fields on the board are disabled
     * @return boolean of whether its a cats game or not
     */
    public boolean checkForCatsGame() {
        boolean returnVal = true;
        if (!oneButt.isDisabled()) {
            returnVal = false;
        }
        if (!twoButt.isDisabled()) {
            returnVal = false;
        }
        if (!threeButt.isDisabled()) {
            returnVal = false;
        }
        if (!fourButt.isDisabled()) {
            returnVal = false;
        }
        if (!fiveButt.isDisabled()) {
            returnVal = false;
        }
        if (!sixButt.isDisabled()) {
            returnVal = false;
        }
        if (!sevenButt.isDisabled()) {
            returnVal = false;
        }
        if (!eightButt.isDisabled()) {
            returnVal = false;
        }
        if (!nineButt.isDisabled()) {
            returnVal = false;
        }
        return returnVal;
    }

    /**
     * catsGame is triggered in buttonClicked if it is a Cats Game and announces the Cats Game
     */
    public void catsGame(){
        playerCompGame.setDisable(true);
        playerCompGame.setText("Cats Game");

    }
}