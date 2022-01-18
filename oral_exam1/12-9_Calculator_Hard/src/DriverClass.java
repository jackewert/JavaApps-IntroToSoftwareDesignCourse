import javax.swing.JFrame;

public class DriverClass {
    public static void main(String[] args){
        Calculator calculator01= new Calculator();
        calculator01.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator01.setSize(450,450);
        calculator01.setVisible(true);
    }
}
