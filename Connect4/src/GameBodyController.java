import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyController {

    GameBodyFrame gameBodyFrame;
    GameBodyModel gameBodyModel;

    GameBoardController gameBoardController;
    GameJMenuController gameJMenuController;
    GameTimerController gameTimerController;

    HitPointsModel hitPointsModel;
    HitPointsController hitPointsController;
    GoldController goldController;
    UnitButtonController unitButtonController;

    public GameBodyController() {

        // Declare MVC
        gameBodyModel = new GameBodyModel();
        gameBodyFrame = new GameBodyFrame(this);

        // Instantiate classes
        gameBoardController = new GameBoardController(this);
        gameTimerController = new GameTimerController(this);
        hitPointsController = new HitPointsController(this);
        goldController = new GoldController(this);
        unitButtonController = new UnitButtonController(this);
        gameJMenuController = new GameJMenuController(this);



        // Window listener
        gameBodyFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {


                        //Prompts a JOptionPane
                        int option = JOptionPane.showConfirmDialog(
                                null,
                                "Quit game?",
                                "Quit game?",
                                JOptionPane.YES_NO_OPTION);

                        //If yes close game, else continue game
                        switch (option) {
                            case JOptionPane.YES_OPTION: {
                                System.out.println("Yes, closing game...");
                                gameBodyFrame.dispose();
                                break;
                            }
                            case JOptionPane.NO_OPTION: {
                                System.out.println("No, continue game... ");
                                break;
                            }
                            default: {
                                System.out.println("Default, continue game... ");
                                break;
                            }
                        }
                    }
                });
    }


}
