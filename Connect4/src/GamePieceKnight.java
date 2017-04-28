import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 27/04/2017.
 */
public class GamePieceKnight extends GamePiece implements ActionListener {

    int x;
    int y;

    private GameBoardController gameBoardController;

    GamePieceKnight(GamePieceSlot gamePieceSlot) {

        this.gameBoardController = gamePieceSlot.gameBoardController;

        this.setText("Knight");
        this.setEnabled(true);
        this.setActionCommand("Knight");
        this.addActionListener(this);
    }

    // KNIGHT ACTION
    @Override
    public void actionPerformed(ActionEvent e) {

        this.setEnabled(false);
        System.out.println("I'm a knight!");

        if (0 <= x - 1)
            if(!gameBoardController.gameBoardModel.getSlotOccupancy(x-1,y).equals(GameBoardModel.player.PLAYER_NONE))
                gameBoardController.gameBoardPanel.getSlot(x - 1, y).setPieceColor(Color.GREEN);
        if (x + 1 <= GameBoardModel.numCol - 1)
            if(!gameBoardController.gameBoardModel.getSlotOccupancy(x+1,y).equals(GameBoardModel.player.PLAYER_NONE))
                gameBoardController.gameBoardPanel.getSlot(x + 1, y).setPieceColor(Color.GREEN);

        // Activate Knight Animations
        knightTimer.start();

    }

    // KNIGHT ANIMATIONS
    private boolean hasAttacked = false;
    private Timer knightTimer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!hasAttacked) {
                System.out.println("First");

                if (0 <= x - 1)
                    if(!gameBoardController.gameBoardModel.getSlotOccupancy(x-1,y).equals(GameBoardModel.player.PLAYER_NONE))
                        gameBoardController.gameBoardModel.getSlotCombined(x - 1, y).clearInfo();
                if (x + 1 <= GameBoardModel.numCol - 1)
                    if(!gameBoardController.gameBoardModel.getSlotOccupancy(x-1,y).equals(GameBoardModel.player.PLAYER_NONE))
                        gameBoardController.gameBoardModel.getSlotCombined(x + 1, y).clearInfo();

                gameBoardController.gameBoardModel.loadOccupancyListFromCombinedList();
                gameBoardController.gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);

                hasAttacked = true;
            } else {
                knightTimer.stop();
                System.out.println("Second");

                hasAttacked = false;

                gameBoardController.boardGravityController.runGravity();
            }
        }
    });
}
