import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Trong on 24/04/2017.
 */
public class BoardGravityController implements ActionListener {

    Timer gravityTimer;
    GameBoardController gameBoardController;
    GameBoardModel gameBoardModel;
    GameBoardPanel gameBoardPanel;

    BoardWinController boardWinController;

    int ticks = 0;

    BoardGravityController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
        gameBoardPanel = gameBoardController.gameBoardPanel;
        gameBoardModel = gameBoardController.gameBoardModel;
        boardWinController = gameBoardController.boardWinController;

        gravityTimer = new Timer(50, this);
    }


    void startGravityTimer() {
        gravityTimer.start();
    }

    void stopGravityTimer() {
        gravityTimer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ticks < 6) {

            for (int x = 0; x < GameBoardModel.numCol; x++) {

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
                gameBoardPanel.revalidate();
                gameBoardPanel.repaint();
            }



            ticks++;
        }
        else {
            ticks = 0;
            stopGravityTimer();

            if(boardWinController.RUN()){
                startGravityTimer();
            }
            else {
                System.out.println("Gravity DONE???");
                gameBoardController.roundEnd();
            }

        }



    }
}