import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Trong on 24/04/2017.
 */
public class BoardGravityController implements ActionListener{

    private GameBoardModel gameBoardModel;
    private GameBoardPanel gameBoardPanel;

    private BoardWinController boardWinController;

    private Timer gravityTimer;

    // Amount of times to gravityPull
    private int ticks;

    BoardGravityController(GameBoardController gameBoardController) {
        gameBoardPanel = gameBoardController.gameBoardPanel;
        gameBoardModel = gameBoardController.gameBoardModel;

        boardWinController = gameBoardController.boardWinController;

        // Delay will determine speed of blinks.
        gravityTimer = new Timer(30, this);
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
    private void calcMaxTicks(){
        int indexOfLowestEmpty = 0;
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            int currentRowLowestEmpty = gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE);
            if(currentRowLowestEmpty < indexOfLowestEmpty)
                indexOfLowestEmpty = currentRowLowestEmpty;
        }
        this.ticks = GameBoardModel.numRow - 1 - indexOfLowestEmpty;
    }

    /**
     * Pull down column x once
     * @param x: The column x
     */
    private void gravityPullOnce(int x){
        int indexOfLowestEmpty = gameBoardModel.getListOccupancy().get(x).indexOf(GameBoardModel.player.PLAYER_NONE);
        if (indexOfLowestEmpty != -1) {

            for (int i = indexOfLowestEmpty; i < GameBoardModel.numRow - 1; i++) {
                if (gameBoardModel.getSlotOccupancy(x, i + 1).equals(GameBoardModel.player.PLAYER_NONE)) {
                    gameBoardPanel.getSlot(x, i).setEmpty();
                } else {
                    gameBoardPanel.getSlot(x, i).setPiece(gameBoardPanel.getSlot(x, i + 1).piece);
                }
            }

            gameBoardPanel.getSlot(x, GameBoardModel.numRow - 1).setEmpty();

            gameBoardModel.getListOccupancy().get(x).remove(indexOfLowestEmpty);
            gameBoardModel.getListOccupancy().get(x).add(GameBoardModel.player.PLAYER_NONE);

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ticks > 0) {

            for (int x = 0; x < GameBoardModel.numCol; x++) {
                gravityPullOnce(x);
                gameBoardPanel.revalidate();
                gameBoardPanel.repaint();
            }

            ticks--;
        }
        else {
            gravityTimer.stop();
            boardWinController.runWinCheck();

        }



    }
}