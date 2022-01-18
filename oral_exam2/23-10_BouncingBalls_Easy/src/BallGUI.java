import javax.swing.*;
import java.awt.*;

public class BallGUI extends JPanel {
    private int x; //x location
    private int y; //y location
    private int radius=50; //radius

    /**
     * Constructor the BallGUI
     * @param x takes in the x location
     * @param y takes in the y location
     * @param radius radius of the ball
     */
    public BallGUI(int x, int y, int radius){
        super();
        this.x=x;
        this.y=y;
        this.radius=radius;
    }


    @Override
    /**
     * Paints the ball to be added to the JFrame
     */
    public void paintComponent(Graphics g){ //figure 13.5 Java How to Program helped
        super.paintComponent(g);
        this.setBackground(Color.yellow);
        g.setColor(new Color(0,100,155));
        g.fillOval(x,y,radius,radius);

    }



}
