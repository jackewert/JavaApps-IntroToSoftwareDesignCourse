import javax.swing.*;

public class BouncingBall implements Runnable {
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private int xFrame;
    private int yFrame;
    private int radius;
    private BallGUI ball;
    private final JFrame jFrame;

    /**
     * This constructor takes in the necessary information for the ball to run
     * @param xSpeed    speed in the x direction the ball moves
     * @param ySpeed    speed in the y direction the ball moves
     * @param radius    radius of the ball
     * @param jFrame    jFrame the ball will be added to, contains Frame size information as well;
     */
    public BouncingBall(int xSpeed,int ySpeed,int radius,JFrame jFrame){
        this.x=jFrame.getWidth()/2;//start near center
        this.y=jFrame.getHeight()/2;//start near center
        this.speedX=xSpeed;
        this.speedY=ySpeed;
        this.yFrame=jFrame.getHeight();
        this.xFrame=jFrame.getWidth();
        this.radius=radius;
        this.jFrame=jFrame;
    }

    /**
     * run contains the calculations for speed in x and y directions,
     * as well as consistent updating on location in x and y directions.
     */
    public void run(){
        while (true){
            if(x+speedX+radius+(radius/2)-5<=xFrame && x+speedX+5>0){
                x+=speedX;
            }
            else{//hits edge of windows
                speedX=-speedX;
                x+=speedX;
            }
            if(y+speedY+(radius*2)<yFrame && y+speedY+5>0){
                y+=speedY;
            }
            else{//hits edge of window
                speedY=-speedY;
                y+=speedY;
            }
            ball=new BallGUI(x,y,radius);
            jFrame.add(ball);
            jFrame.setVisible(true);

            try {
                Thread.sleep(33);//how to limit update rate
            }
            catch(InterruptedException exception){
                exception.printStackTrace();
                Thread.currentThread().interrupt();
            }

            

        }
    }
}
