import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by aslak on 03.04.17.
 */
public class GameBodyController implements ActionListener {

    // todo: Convert to local: Possible if in actionPerformed: e.getSource -> e.getActionCommand
    // todo: if changing to e.getActionCommand,
    GameBodyFrame gameBodyFrame;

    // todo: Convert over to local?
    GameBoardController gameBoardController;

    public GameBodyController() {

        gameBodyFrame = new GameBodyFrame();
        gameBoardController = new GameBoardController(gameBodyFrame);

        // todo: add this to own class or GameBoardFrame?
        //JMenu action listeners
        gameBodyFrame.mainMenu.addActionListener(this);
        gameBodyFrame.saveGame.addActionListener(this);
        gameBodyFrame.openGame.addActionListener(this);
        gameBodyFrame.pauseGame.addActionListener(this);
        gameBodyFrame.restartGame.addActionListener(this);
        gameBodyFrame.highscore.addActionListener(this);

        // todo: add this to GameBodyFrame?
        //Window listener
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
