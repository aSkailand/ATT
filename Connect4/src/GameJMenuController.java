import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 18.04.17.
 */
public class GameJMenuController implements ActionListener {


    GameBodyController gameBodyController;
    GameBodyFrame gameBodyFrame;


    GameJMenu gameJMenu;
    GameTimerController gameTimerController;
    GameBodyModel gameBodyModel;
    HighscoreView highscoreView;

    //This boolean is for the pause/unpause button, when its true its unpaused, if its false its paused
    boolean pauseBoolean = true;


    public GameJMenuController(GameBodyController gameBodyController) {

        this.gameBodyController = gameBodyController;
        gameBodyModel = this.gameBodyController.gameBodyModel;
        gameBodyFrame = this.gameBodyController.gameBodyFrame;

        gameTimerController = this.gameBodyController.gameTimerController;

        gameJMenu = new GameJMenu(this);
        gameBodyFrame.setJMenuBar(gameJMenu);

        // todo: have common repaint in GameBodyController
        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();


    }

    // ActionListener for the JMenuBar
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "mainMenu": {
                System.out.println("Main menu...");


                break;
            }
            case "highscore": {
                System.out.println("Highscore...");
                highscoreView = new HighscoreView();
                break;
            }
        }
    }
}
