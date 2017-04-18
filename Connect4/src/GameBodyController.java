import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyController {

    // todo: Convert to local: Possible if in actionPerformed: e.getSource -> e.getActionCommand
    // todo: if changing to e.getActionCommand,
    GameBodyFrame gameBodyFrame;

    // todo: Convert over to local?
    GameBoardController gameBoardController;
    GameJMenuController gameJMenuController;

    public GameBodyController() {

        gameBodyFrame = new GameBodyFrame();
        gameBoardController = new GameBoardController(gameBodyFrame);
        gameJMenuController = new GameJMenuController(gameBodyFrame);

        // todo: add this to GameBodyFrame?
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
