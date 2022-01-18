import javax.swing.*;

public class DriverClass {
    public static void main(String[] args){
        HangmanGui hangman= new HangmanGui();
        hangman.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hangman.setSize(800,500);
        hangman.setVisible(true);
    }
}
