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

    HitPointsModel hitPointsModel;
    HitPointsController hitpoints;
    GoldModel goldModel;
    GoldController goldController;
    UnitButtonModel unitButtonModel;
    UnitButtonController unitButtonController;
    //This boolean is for the pause/unpause button, when its true its unpaused, if its false its paused
    boolean pauseBoolean = true;


    public GameJMenuController(GameBodyController gameBodyController) {

        this.gameBodyController = gameBodyController;
        gameBodyModel = this.gameBodyController.gameBodyModel;
        gameBodyFrame = this.gameBodyController.gameBodyFrame;

        gameTimerController = this.gameBodyController.gameTimerController;

        // todo: temporary hp changer
        hitPointsModel = this.gameBodyController.hitPointsModel;
        hitpoints = this.gameBodyController.hitPointsController;

        // todo: temporary gold changer
        goldModel = this.gameBodyController.goldController.goldModel;
        goldController = this.gameBodyController.goldController;

        // todo: temporary unit button changer
        unitButtonController = this.gameBodyController.unitButtonController;

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

                // todo: TEMPORARY!!!
                hitpoints.HitpointsPercentage(-20, GameBoardModel.player.PLAYER_1);
                goldController.UpdateGoldValue(-1, GameBoardModel.player.PLAYER_1);
                unitButtonController.disableButtons(GameBoardModel.player.PLAYER_1);

                                break;
            }
            case "highscore": {
                System.out.println("Highscore...");
                highscoreView = new HighscoreView();
                break;
            }
            case "saveGame": {
                System.out.println("Saved game...");
                String saveName = JOptionPane.showInputDialog(null, "New highscore, enter name: ");
                gameBodyModel.setHighScore(saveName + ": Time: " + gameTimerController.minutes + ":" + gameTimerController.seconds + "\n");
                break;
            }
            case "openGame": {
                System.out.println("Open game...");
                // todo: TEMPORARY!!
                hitpoints.HitpointsPercentage(-20, GameBoardModel.player.PLAYER_2);
                goldController.UpdateGoldValue(-1, GameBoardModel.player.PLAYER_2);
                unitButtonController.disableButtons(GameBoardModel.player.PLAYER_2);
                break;
            }
            case "pauseGame": {
                System.out.println("Paused game...");
                if (pauseBoolean == true) {
                    gameTimerController.playerTurnTimer.stop();
                    pauseBoolean = false;
                } else {
                    gameTimerController.playerTurnTimer.start();
                    pauseBoolean = true;
                }
                break;
            }
            case "startTimer": {
                System.out.println("Started timer");
                gameTimerController.playerTurnTimer.start();
                pauseBoolean = true;
                break;
            }
            case "resetTimer": {
                System.out.println("Timer reset...");
                gameTimerController.playerTurnTimer.stop();
                gameTimerController.currentTime = 29;
                gameTimerController.seconds = 0;
                gameTimerController.minutes = 0;
                //gameBodyFrame.totalTimer.setText("Total time: 0:0");
                gameTimerController.timer.setText("30");
                pauseBoolean = true;
                break;
            }
            case "restartGame": {
                System.out.println("Restarted game...");

                // todo: TEMPORARY!!!
                hitpoints.HitpointsPercentage(20, GameBoardModel.player.PLAYER_1);
                hitpoints.HitpointsPercentage(20, GameBoardModel.player.PLAYER_2);
                goldController.UpdateGoldValue(1, GameBoardModel.player.PLAYER_1);
                goldController.UpdateGoldValue(1, GameBoardModel.player.PLAYER_2);
                unitButtonController.disableButtons(GameBoardModel.player.PLAYER_1);
                break;
            }
        }
    }
}
