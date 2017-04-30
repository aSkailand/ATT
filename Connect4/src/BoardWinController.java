import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 25/04/2017.
 */
public class BoardWinController implements ActionListener {

    private GameBoardController gameBoardController;
    private GameBoardModel gameBoardModel;
    private GameBoardPanel gameBoardPanel;

    private Timer timerBlink;

    private boolean lightOn = false;

    private int blinks;

    BoardWinController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        gameBoardModel = gameBoardController.gameBoardModel;
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
        // If there exist none, and it was still phase 1
        else if(gameBoardModel.phase_1){

            gameBoardModel.phase_1 = false;

            // Open all pieces for use.
            System.out.println("PHASE 1 OVER");
            gameBoardModel.loadOccupancyListFromCombinedList();
            for (int x = 0; x < GameBoardModel.numCol; x++) {
                for (int y = 0; y < GameBoardModel.numRow; y++) {
                    if(gameBoardModel.getSlotOccupancy(x,y).equals(gameBoardModel.getWaitingPlayer()))
                        gameBoardPanel.getSlot(x,y).getPiece().setEnabled(false);
                    else if(gameBoardModel.getSlotOccupancy(x,y).equals(gameBoardModel.getCurrentPlayer()))
                        gameBoardPanel.getSlot(x,y).getPiece().setEnabled(true);
                }
            }

            // Run Summoning Sickness
            int indexOfLowestNone = gameBoardModel.getListOccupancy().get(gameBoardModel.currentPlayedColumn).indexOf(GameBoardModel.player.PLAYER_NONE);
            if(indexOfLowestNone != -1){
                gameBoardPanel.getSlot(gameBoardModel.currentPlayedColumn, indexOfLowestNone - 1).getPiece().setEnabled(false);
            }
            else gameBoardPanel.getSlot(gameBoardModel.currentPlayedColumn, GameBoardModel.numRow-1).getPiece().setEnabled(false);

        }

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
