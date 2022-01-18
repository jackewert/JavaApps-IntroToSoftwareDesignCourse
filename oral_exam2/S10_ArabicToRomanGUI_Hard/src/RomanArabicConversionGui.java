import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class RomanArabicConversionGui {
    private int value;
    private boolean leftoverRoman;
    private static String[] thousandsAToR = {"","M","MM","MMM"};
    private static String[] hundredsAToR = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
    private static String[] tensAToR= {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
    private static String[] onesAToR = {"","I","II","III","IV","V","VI","VII","VIII","IX"};


    @FXML
    private TextField arabicExplanation;

    @FXML
    private TextField romanNumberField;

    @FXML
    private TextField arabicNumberField;

    @FXML
    private TextField romanExplanation;

    @FXML
    private TextField title;

    /**
     * arabicToRoman is Triggered when someone uses the textField. It converts the arabic numeral into a roman numeral and sends it to
     * the gui display
     * @param event is from the GUI and triggers the update
     */
    public void arabicToRoman (ActionEvent event){
        int total=0;
        String arabicNumber=arabicNumberField.getCharacters().toString();
        total=Integer.parseInt(arabicNumber);
        value=total;
        if(value<4000 && value>0) {
            updateNumberFields();
        }
        else{
            romanNumberField.setText("Please input value under 4000 and greater than 0");
        }
    }

    /**
     * romanToArabic converts properly formatted roman numerals to arabic numerals given it's smaller than 4000
     * @param event comes from the GUI and triggers the conversion
     */
    public void romanToArabic (ActionEvent event){
        String romanNumber=romanNumberField.getCharacters().toString().toUpperCase();
        value=arrayToNumberAToR(translationRToA(romanNumber));
        updateNumberFields();
    }

    /**
     * translationAtor is the actual conversion from Arabic to Roman. It uses a digit seperation system and comparison to
     * static array of roman numerals to convert between the two types of numbers
     * @param number is the Arabic integer representation to be converted into roman numeral
     * @return is a String that is the roman Numeral equivalent of the number input
     */
    public String translationAToR(int number){
        String returnVal="";
        returnVal +=thousandsAToR[number/1000];
        returnVal +=hundredsAToR[(number%1000/100)];
        returnVal +=tensAToR[(number%100/10)];
        returnVal +=onesAToR[number%10];
        return returnVal;
    }

    /**
     * translationRToA converts Roman Numeral to Arabic numeral equivalent given the roman numeral is properly formatted
     * @param numeral the numeral being coverted
     * @return integer array of the arabic digits. Meant to be ran through arrayToNumberAToR
     */
    public int[] translationRToA(String numeral){
        int[] digitValues={0,0,0,0};
        for(int i=0;i<thousandsAToR.length;i++){
            if(numeral.startsWith(thousandsAToR[i])){
                digitValues[0]=i;
            }
        }
        numeral=numeral.substring(thousandsAToR[digitValues[0]].length());

        for(int i=0;i<hundredsAToR.length;i++){
            if(numeral.startsWith(hundredsAToR[i])){
                digitValues[1]=i;
            }
        }
        numeral=numeral.substring(hundredsAToR[digitValues[1]].length());

        for(int i=0;i<tensAToR.length;i++){
            if(numeral.startsWith(tensAToR[i])){
                digitValues[2]=i;
            }
        }
        numeral=numeral.substring(tensAToR[digitValues[2]].length());

        for(int i=0;i<onesAToR.length;i++){
            if(numeral.startsWith(onesAToR[i])){
                digitValues[3]=i;
            }
        }
        numeral=numeral.substring(onesAToR[digitValues[3]].length());
        if(numeral.length()!=0){
            leftoverRoman=true;
        }
        return digitValues;
    }

    /**
     * arrayToNumberAToR converts array of digits made by translationAToR into an Arabic Integer
     * @param digitArray array of digits from highest power of 10 to lowest 4 digit range
     * @return integer equivalent of digitArray input
     */
    public int arrayToNumberAToR(int[] digitArray){
        int total=0;
        int position=0;
        for(int i=digitArray.length;i>0;i--){
            total+=digitArray[position]*Math.pow(10,i-1);
            position++;
        }
        return total;
    }

    /**
     * updateNumberFields checks if Roman Numeral was read correctly and updates the Numeral Fields to represent the most current
     * input and output that was given and calculated.
     */
    public void updateNumberFields(){
        if(leftoverRoman!=true) {//leftoverRoman being true means that it wasn't read properly
            arabicNumberField.setText(Integer.toString(value));
            romanNumberField.setText(translationAToR(value));
        }
        else{
            String temp=Integer.toString(value);
            temp += " Are you sure the numerals are input correctly?";
            romanNumberField.setText(temp);
        }
    }
}
