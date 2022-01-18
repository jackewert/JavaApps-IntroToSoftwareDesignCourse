public class HumanPlayer extends PlayerClassBase {

    public HumanPlayer(int playerNumber) {
        super(playerNumber);
    }
    /**
     * playersTurn takes an integer representation of the location of the button pressed, saves the integer in an array
     * this information is then used to check if the player has won the game using the array of integer locations
     * @param buttonPressed
     * @return boolean whether the player has won
     */
    public boolean playersTurn(int buttonPressed){
        this.setPlayed(buttonPressed, true);
        return this.checkForWin();
    }

}
