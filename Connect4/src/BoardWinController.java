import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 25/04/2017.
 */
public class BoardWinController implements ActionListener {

    private GameBoardController gameBoardController;
    private GameBoardPanel gameBoardPanel;

    private Timer timerBlink;

    private boolean lightOn = false;

    private int blinks;

    BoardWinController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        gameBoardPanel = gameBoardController.gameBoardPanel;

        timerBlink = new Timer(50, this);
    }


    /**
     * The main method here, used to check if there exist winning rows.
     * If there exist, blink it, kill it, and then run Gravity again.
     * If there is no winning rows, end Phase 1.
     */
    void runWinCheck() {

        // Clean all residues
        gameBoardController.cleanAllWinParts();

        // If there exist winning rows
        if (gameBoardController.checkWinAllConditions(GameBoardModel.player.PLAYER_1) |
                gameBoardController.checkWinAllConditions(GameBoardModel.player.PLAYER_2)) {
            blinks = 10;
            timerBlink.start();

        }
        // If there exist none
        else gameBoardController.roundEnd();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (blinks > 0) {
            if (lightOn) {
                gameBoardController.resetColorWinPieces();
                lightOn = false;
            } else {
                gameBoardController.colorWinPieces();
                lightOn = true;
            }

            gameBoardPanel.revalidate();
            gameBoardPanel.repaint();

            blinks--;
        } else {
            timerBlink.stop();

            gameBoardController.killWinPieces();

            gameBoardPanel.revalidate();
            gameBoardPanel.repaint();

            gameBoardController.boardGravityController.runGravity();

        }

    }
}
