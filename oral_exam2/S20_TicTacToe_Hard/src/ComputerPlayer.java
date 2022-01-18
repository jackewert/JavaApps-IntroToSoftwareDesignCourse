public class ComputerPlayer extends PlayerClassBase {

    public ComputerPlayer(int playerNumber) {
        super(playerNumber);
    }

    /**
     * playersTurn takes an integer representation of the location of the button pressed, saves the integer in an array
     * this information is then used to check if the player has won the game using the array of integer locations
     * @param buttonPressed
     * @return
     */
    public boolean playersTurn(int buttonPressed) {
        this.setPlayed(buttonPressed, true);
        return this.checkForWin();
    }

    /**
     * cpuButtonAttempt is essentially a random number generator that represents the Computer's tic tac toe abilities
     * @return a random number between 9 and 1 that is the bots tic tac toe choice
     */
    public static int cpuButtonAttempt(){
        int returnVal=(int)(Math.random() * 9 + 1);
        return returnVal;

    }
}
