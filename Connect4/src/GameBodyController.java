import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyController implements ActionListener {

    GameBodyFrame gameBodyFrame;


    public GameBodyController(GameBodyFrame gFrame) {

        gameBodyFrame = gFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Main menu action listener
        if (e.getSource().equals(gameBodyFrame.mainMenu)) {
            System.out.println("Main menu...");
        }

        //Save game action listener
        if (e.getSource().equals(gameBodyFrame.saveGame)) {
            System.out.println("Saved game...");
        }

        //Open game action listener
        if (e.getSource().equals(gameBodyFrame.openGame)) {
            System.out.println("Open game...");
        }

        //Pause game action listener
        if (e.getSource().equals(gameBodyFrame.pauseGame)) {
            System.out.println("Paused game...");
        }

        //Restart game action listener
        if (e.getSource().equals(gameBodyFrame.restartGame)) {
            System.out.println("Restart game...");
        }

        //Highscore action listener
        if (e.getSource().equals(gameBodyFrame.highscore)) {
            System.out.println("Highscore");
        }


    }
}
