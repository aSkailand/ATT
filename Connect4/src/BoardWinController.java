import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
        else if (gameBoardModel.phase_1) {


            gameBoardModel.phase_1 = false;

            // Open all pieces for use.
            System.out.println("PHASE 1 OVER");
            gameBoardModel.loadOccupancyListFromCombinedList();
            for (int x = 0; x < GameBoardModel.numCol; x++) {
                for (int y = 0; y < GameBoardModel.numRow; y++) {
                    if (gameBoardModel.getSlotOccupancy(x, y).equals(gameBoardModel.getWaitingPlayer())) {
                        gameBoardModel.getSlotCombined(x, y).setEnabled(false);
//                        gameBoardPanel.getSlot(x, y).getPieceUnit().setEnabled(false);
                    } else if (gameBoardModel.getSlotOccupancy(x, y).equals(gameBoardModel.getCurrentPlayer())) {
//                        gameBoardPanel.getSlot(x, y).getPieceUnit().setEnabled(true);
                        gameBoardModel.getSlotCombined(x, y).setEnabled(true);
                    }
                }
            }

            // Run Summoning Sickness
            int indexOfLowestNone = gameBoardModel.getListOccupancy().get(gameBoardModel.currentPlayedColumn).indexOf(GameBoardModel.player.PLAYER_NONE);

            if (0 < indexOfLowestNone) {
                gameBoardModel.getSlotCombined(gameBoardModel.currentPlayedColumn, indexOfLowestNone - 1).setEnabled(false);
//                gameBoardPanel.getSlot(gameBoardModel.currentPlayedColumn, indexOfLowestNone - 1).getPieceUnit().setEnabled(false);
            } else if (indexOfLowestNone == -1) {
                gameBoardModel.getSlotCombined(gameBoardModel.currentPlayedColumn, GameBoardModel.numRow - 1).setEnabled(false);
//                gameBoardPanel.getSlot(gameBoardModel.currentPlayedColumn, GameBoardModel.numRow - 1).getPieceUnit().setEnabled(false);
            }

            System.out.println("PHASE 2:");
            System.out.println("CURRENT BOARD:");
            gameBoardModel.loadSlotListFromCombinedList(gameBoardPanel);
            gameBoardModel.printCombinedList();

            gameBoardController.gameBodyController.gameTimerController.timer.setEnabled(true);

            gameBoardController.gameBodyController.unitButtonController.phaseDisableButtons(gameBoardModel.getCurrentPlayer(), false);
            gameBoardController.gameBodyController.unitButtonController.checkMagicAffordability(gameBoardModel.getCurrentPlayer(), gameBoardModel.phase_1);

            gameBoardModel.currentSelectedAction = null;

            gameBoardController.autoSwitchActionPanel();

            if (gameBoardModel.getStatusAI(gameBoardModel.getCurrentPlayer())) {
                gameBoardController.gameBodyController.gameTimerController.timer.doClick();
            }

        }

        // Check if anyone has won:

        // AI
        if (gameBoardModel.getStatusAI(GameBoardModel.player.PLAYER_2)) {

            if (gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_1Hitpoints() <= 0
                    && gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_2Hitpoints() <= 0) {
                System.out.println("DRAW against AI");
                JOptionPane.showMessageDialog(null, "It's a draw!");
                gameBoardController.gameBodyController.gameBodyFrame.dispose();
                gameBoardController.gameBodyController.startMenuController.View.Back2Button.doClick();
                gameBoardController.gameBodyController.startMenuController.View.setVisible(true);
            } else {
                // WIN against AI
                if (gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_2Hitpoints() <= 0) {
                    System.out.println("AI lost against you");
                    String winName = JOptionPane.showInputDialog(null, "You won! Enter you name: ");

                    winName += "Total time: " + gameBoardController.gameBodyController.gameTimerController.totalTime;


                    try {
                        FileWriter fr = new FileWriter("highscore.txt");


                        fr.write(winName);
                        fr.flush();
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gameBoardController.gameBodyController.gameTimerController.playerTurnTimer.stop();
                    gameBoardController.gameBodyController.gameBodyFrame.dispose();
                    gameBoardController.gameBodyController.startMenuController.View.Back2Button.doClick();
                    gameBoardController.gameBodyController.startMenuController.View.setVisible(true);
                }


                // DEFEAT against AI
                if (gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_1Hitpoints() <= 0) {
                    System.out.println("You lost against AI");

                    JOptionPane.showMessageDialog(null, "You lost agains AI!");

                    gameBoardController.gameBodyController.gameTimerController.playerTurnTimer.stop();
                    gameBoardController.gameBodyController.gameBodyFrame.dispose();
                    gameBoardController.gameBodyController.startMenuController.View.Back2Button.doClick();
                    gameBoardController.gameBodyController.startMenuController.View.setVisible(true);
                }


            }


        } else {

            if (gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_1Hitpoints() <= 0
                    && gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_2Hitpoints() <= 0) {
                System.out.println("DRAW");
                JOptionPane.showMessageDialog(null, "It's a draw!");
                gameBoardController.gameBodyController.gameBodyFrame.dispose();
                gameBoardController.gameBodyController.startMenuController.View.Back2Button.doClick();
                gameBoardController.gameBodyController.startMenuController.View.setVisible(true);
            } else {

                if (gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_2Hitpoints() <= 0) {
                    System.out.println("Player 1 WON");

                    JOptionPane.showMessageDialog(null, "Player 1 WON!");
                    gameBoardController.gameBodyController.gameBodyFrame.dispose();
                    gameBoardController.gameBodyController.startMenuController.View.Back2Button.doClick();
                    gameBoardController.gameBodyController.startMenuController.View.setVisible(true);

                    // todo: add winning screen then leave game.
                }


                if (gameBoardController.gameBodyController.hitPointsController.hitPointsModel.getPlayer_1Hitpoints() <= 0) {
                    System.out.println("Player 2 WON");

                    JOptionPane.showMessageDialog(null, "Player 2 WON!");
                    gameBoardController.gameBodyController.gameBodyFrame.dispose();
                    gameBoardController.gameBodyController.startMenuController.View.Back2Button.doClick();
                    gameBoardController.gameBodyController.startMenuController.View.setVisible(true);

                }


            }


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
