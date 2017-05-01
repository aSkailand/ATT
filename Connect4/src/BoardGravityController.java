import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Trong on 24/04/2017.
 */

/**
 * This controller manages the gravityPull on the pieces.
 */
public class BoardGravityController implements ActionListener {

    private GameBoardModel gameBoardModel;
    private GameBoardPanel gameBoardPanel;

    private BoardWinController boardWinController;

    // How fast to drag pieces down
    private Timer gravityTimer = new Timer(30, this);

    // Amount of times to gravityPull
    private int ticks;

    BoardGravityController(GameBoardController gameBoardController) {
        // Declare MVC
        gameBoardPanel = gameBoardController.gameBoardPanel;
        gameBoardModel = gameBoardController.gameBoardModel;

        // Declare other
        boardWinController = gameBoardController.boardWinController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ticks > 0) {

            gameBoardModel.loadSlotListFromCombinedList(gameBoardPanel);

            for (int x = 0; x < GameBoardModel.numCol; x++) {
                gravityPullOnce(x);
            }

            gameBoardModel.loadSlotListFromCombinedList(gameBoardPanel);

            gameBoardModel.printCombinedList();

            ticks--;

        } else {
            gravityTimer.stop();
            boardWinController.runWinCheck();
        }


    }

    /**
     * Main method, this is the one to run if one wants to initiate gravity.
     */
    void runGravity() {




        calcMaxTicks();

        gravityTimer.start();
    }

    /**
     * Calculate the minimum amount of needed Ticks.
     * More ticks than needed will result unnecessary GravityPulls
     */
    private void calcMaxTicks() {
        int indexOfLowestEmpty = 0;
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            int currentRowLowestEmpty = gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE);
            if (currentRowLowestEmpty < indexOfLowestEmpty)
                indexOfLowestEmpty = currentRowLowestEmpty;
        }
        this.ticks = GameBoardModel.numRow - 1 - indexOfLowestEmpty;
    }

    /**
     * Pull down column x once
     *
     * @param x: The column x
     */
    private void gravityPullOnce(int x) {

        int indexOfLowestEmpty = gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE);
        if (indexOfLowestEmpty != -1) {

            // gravityPull Column (listCombined)
            for (int i = indexOfLowestEmpty; i < GameBoardModel.numRow - 1; i++) {
                gameBoardModel.getSlotCombined(x, i).copyInfo(gameBoardModel.getSlotCombined(x, i + 1));
            }
            gameBoardModel.getSlotCombined(x, GameBoardModel.numRow - 1).clearInfo();

            // gravityPull Column (listOccupancy)
            gameBoardModel.getListOccupancy().get(x).remove(indexOfLowestEmpty);
            gameBoardModel.getListOccupancy().get(x).add(GameBoardModel.player.PLAYER_NONE);

        }

    }
}