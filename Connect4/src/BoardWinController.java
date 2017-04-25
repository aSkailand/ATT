import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by Trong on 25/04/2017.
 */
public class BoardWinController implements ActionListener {

    GameBoardController gameBoardController;
    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;
    BoardGravityController boardGravityController;

    Timer timerBlink;

    boolean lightOn = false;

    int blinks = 10;

    BoardWinController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        gameBoardModel = gameBoardController.gameBoardModel;
        gameBoardPanel = gameBoardController.gameBoardPanel;
        boardGravityController = gameBoardController.boardGravityController;

        timerBlink = new Timer(50, this);
    }

    void startTimer() {
        timerBlink.start();
    }

    void RUN() {

        gameBoardController.cleanAllWinParts();

        if (gameBoardController.checkWinAllConditions(GameBoardModel.player.PLAYER_1) | gameBoardController.checkWinAllConditions(GameBoardModel.player.PLAYER_2)) {
            blinks = 10;
            timerBlink.start();

        } else gameBoardController.roundEnd();

    }

    void colorWinpieces() {
        gameBoardController.colorWinPieces();

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

            gameBoardController.boardGravityController.calcMaxTicks();
            gameBoardController.boardGravityController.startGravityTimer();

        }

    }
}
