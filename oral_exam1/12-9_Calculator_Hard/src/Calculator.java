import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//536 of Java how to program helped with basic layout
public class Calculator extends JFrame implements ActionListener {
    private double number1; //first number input that's equivalent to the result if someone clicks an action without inputting a new number
    private double number2; //second number that's being actioned to the first number
    private double result; //stores answer that displays
    private int decimalCount; //stores what power of *10^(-decimalCount) used for decimal places
    private int actionChosen; // stores which action has been clicked
    private boolean actionClicked; //stores if an action has been clicked (used for switching to the second number)
    private boolean decimalPoint; //stores if someone clicked the decimal point during the current number
    private final JTextField textField1; //calculator's display
    private final JPanel buttonJPanel; //panel that stores the buttons
    private final JButton[] buttons; //array that helps constructing and defining the buttons
    private static final String[] buttonNames = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};

    /**
     * Calculator uses a JButton array to keep the buttons organized that are added onto a buttonJPanel with a gridlayout and a
     * textField on top for the calculator display. The button's order can be found on buttonNames string array for position of
     */
    public Calculator() {
        super("Calculator");
        textField1 = new JTextField("0.0", 100);
        textField1.setPreferredSize(new Dimension(200, 50));
        textField1.setEditable(false);
        textField1.setFont(new Font("Serif", Font.PLAIN, 30));
        add(textField1, BorderLayout.NORTH);
        buttonJPanel = new JPanel();
        buttonJPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttons = new JButton[buttonNames.length];
        for (int count = 0; count < buttonNames.length; count++) {
            buttons[count] = new JButton(buttonNames[count]);
            buttons[count].setFont(new Font("Serif", Font.PLAIN, 30));
            buttons[count].addActionListener(this);
            buttonJPanel.add(buttons[count]);
        }
        add(buttonJPanel);
        this.number1=0;
        this.number2=0;
        this.decimalCount=1;
        this.actionClicked=false;
        this.decimalPoint=false;
    }


    //note buttons are sorted by what the represent not by their position in buttons[] in actionPerformed

    /**
     * actionPerformed is split into if statements to accurately respond to which button was clicked. Their responses are split
     * into 4 major categories: numberClick, actionClicked, decimalPoint, and equal button. numberClick is its own function with
     * each number button containing its own integer input for numberClick. actionClicked is for *, /, +, or - and are symbolized by
     * an integer input specific for each button that essentially symbolizes a switch statement. Decimal point and equals are obviously for
     * those specific buttons
     * @param e is the event heard by action listeners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //note:These are organized by what the button represents and not their position in buttons[]
        //0
        if (e.getSource() == buttons[12]) {
            numberClick(0);
        }
        //1
        else if (e.getSource() == buttons[8]) {
            numberClick(1);
        }
        //2
        else if (e.getSource() == buttons[9]) {
            numberClick(2);
        }
        //3
        else if (e.getSource() == buttons[10]) {
            numberClick(3);
        }
        //4
        else if (e.getSource() == buttons[4]) {
            numberClick(4);
        }
        //5
        else if (e.getSource() == buttons[5]) {
            numberClick(5);
        }
        //6
        else if (e.getSource() == buttons[6]) {
            numberClick(6);
        }
        //7
        else if (e.getSource() == buttons[0]) {
            numberClick(7);
        }
        //8
        else if (e.getSource() == buttons[1]) {
            numberClick(8);
        }
        //9
        else if (e.getSource() == buttons[2]) {
            numberClick(9);
        }
        //(/)
        else if (e.getSource() == buttons[3]) {
            actionClicked(1);
        }
        //*
        else if (e.getSource() == buttons[7]) {
            actionClicked(2);
        }
        //-
        else if (e.getSource() == buttons[11]) {
            actionClicked(3);
        }
        //+
        else if (e.getSource() == buttons[15]) {
            actionClicked(4);
        }
        //.
        else if (e.getSource() == buttons[13]) {
            this.decimalPoint=true;
        }
        //=
        else if (e.getSource() == buttons[14]) {
            equalsButton();
        }

    }
    //notes: actionClicked resets decimalCount and decimalPoint to the default of 1 and false
    public void numberClick(double buttonNumber){
        if(this.actionClicked==false) {
            //actionClicked=false and decimalpoint=false
            if (this.decimalPoint == false) {
                this.number1 = (this.number1 * 10) + buttonNumber;
            }
            //actionClicked=false decimalPoint=true
            else {//note:Math.pow doesn't work with negative exponents so have to take inverse
                this.number1=this.number1+(buttonNumber*(1/Math.pow(10,decimalCount)));
                decimalCount++;
            }
            textField1.setText(Double.toString(number1));
        }
        //if actionclicked==true
        else{
            //actionclicked=true and decimalpoint=false
            if(this.decimalPoint==false){
                this.number2=(this.number2*10)+buttonNumber;
            }
            //actionclicked=true and decimal point=true
            else{
                this.number2=this.number2+(buttonNumber*(1/Math.pow(10,decimalCount)));
                decimalCount++;
            }
            textField1.setText(Double.toString(number2));
        }
    }

    /**
     * actionClicked acts to switch the number being edited on the calculator display to number2 while resetting decimal private variables
     * @param actionChosen is the action to be performed with 1=/ 2=* 3=- 4=+
     */
    public void actionClicked(int actionChosen){
        if(number1==0 && result!=0){//when user attempts to edit result
            this.number1=this.result;
        }
            this.actionClicked = true; //notifies other functions you're using number2
            this.actionChosen = actionChosen; //stores the action chosen until you have the second number
            decimalCount = 1; //has to be 1 otherwise you'll be adding to the ones place and not the tenths
            decimalPoint = false; //as you're moving to number2 decimal point has to be reset
            textField1.setText(Double.toString(number2));
    }

    /**
     * equalsButtons performs actionChosen as finally both number1 and number2 have been chosen
     * actionChosen is stored as an int with 1=/ 2=* 3=- 4=+
     * equals button also resets the private variables to their defaults with exception of result in case the user intends
     * on editing result as if it were number1  it also prints the result of the action to the calculator's display
     */
    public void equalsButton(){
        if(actionChosen==1){    //division
            this.result=this.number1/this.number2;
        }
        if(actionChosen==2){    //multiplication
            this.result=this.number1*this.number2;
        }
        if(actionChosen==3){    //subtraction
            this.result=this.number1-this.number2;
        }
        if(actionChosen==4){    //addition
            this.result=this.number1+this.number2;
        }
        this.number1=0;
        this.number2=0;
        actionClicked=false;
        actionChosen=0;
        decimalPoint=false;
        decimalCount=1;

        textField1.setText(Double.toString(this.result));

    }

}

