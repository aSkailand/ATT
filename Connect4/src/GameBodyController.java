import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyController implements MouseListener {

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

        gameBodyFrame.addMouseListener(this);


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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked: " + e.getX() + "," + e.getY());
        if (e.getX() >= 245 && e.getX() <= 340 ){
            System.out.println("slot1");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed at: " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released at: " + e.getX() + "," + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouse entered at: ( x = " + e.getX() + ", y = " + e.getY() + " 5)");
        //System.out.println();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
