public class PlayerClassBase {
    private boolean[] played;
    private final int playerNo;

    /**
     *Generic Constructor
     */
    public PlayerClassBase(){
        playerNo=0;
    }

    /**
     * Ideal Constuctor. Saves the player's number and initializes the player's array that stores where they played using
     * integer representations.
     * @param playerNumber
     */
    public PlayerClassBase(int playerNumber) {
        boolean [] played= new boolean[9];
        for(int i=0;i<played.length;i++){
            played[i]=false;
        }
        this.played=played;
        playerNo=playerNumber;
    }


    public boolean[] getPlayed() {
        return played;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayed(int position, boolean status) {
        this.played[position]=status;
    }

    /**
     * checkForWin uses the array of integer representations of locations in Tic Tac Toe the player has played to check if they
     * have won the game by getting three in a row
     * @return
     */
    public boolean checkForWin(){
        boolean returnVal=false;
        if(played[0]==true && played[1]==true && played[2]==true){
            returnVal=true;
        }
        if(played[0]==true && played[3]==true && played[6]==true){
            returnVal=true;
        }
        if(played[0]==true && played[4]==true && played[8]==true){
            returnVal=true;
        }
        if(played[1]==true && played[4]==true && played[7]==true){
            returnVal=true;
        }
        if(played[2]==true && played[5]==true && played[8]==true){
            returnVal=true;
        }
        if(played[2]==true && played[4]==true && played[6]==true){
            returnVal=true;
        }
        if(played[3]==true && played[4]==true && played[5]==true){
            returnVal=true;
        }
        if(played[6]==true && played[7]==true && played[8]==true){
            returnVal=true;
        }
        return returnVal;
    }
}
