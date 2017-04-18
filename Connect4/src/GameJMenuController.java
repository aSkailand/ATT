import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * Created by aslak on 18.04.17.
 */
public class GameJMenuController implements ActionListener {



    GameJMenu gameJMenu;
    GameBodyFrame gameBodyFrame;
    Timer roundTimer;


    public GameJMenuController(GameBodyFrame gbFrame) {


        gameJMenu = new GameJMenu(this);
        gameBodyFrame = gbFrame;
        gameBodyFrame.setJMenuBar(gameJMenu);
        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();
    }

    // ActionListener for the JMenuBar
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(roundTimer)) {

        }

        switch (e.getActionCommand()) {
            case "mainMenu": {
                System.out.println("Main menu...");
                break;
            }
            case "highscore": {
                System.out.println("Highscore...");
                break;
            }
            case "saveGame": {
                System.out.println("Saved game...");
                break;
            }
            case "openGame": {
                System.out.println("Open game...");
                break;
            }
            case "pauseGame": {
                System.out.println("Paused game...");
                break;
            }
            case "restartGame": {
                System.out.println("Restarted game...");
            }
            case "startTimer": {
                System.out.println("Started timer");
            }
        }
    }
}
