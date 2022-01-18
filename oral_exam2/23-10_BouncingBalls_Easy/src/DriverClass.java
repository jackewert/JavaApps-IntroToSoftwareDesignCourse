import javax.swing.JFrame;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DriverClass {
    public static void main(String[] args) {
        JFrame frame=new JFrame("Bouncing Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
        BouncingBall test=new BouncingBall(3,4,50,frame);
        ExecutorService executorService= Executors.newCachedThreadPool();
        executorService.execute(test);
    }
}
