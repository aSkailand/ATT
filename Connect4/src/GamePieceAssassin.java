import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 27/04/2017.
 */
public class GamePieceAssassin extends GamePiece implements ActionListener {

    int x;
    int y;

    private GameBoardController gameBoardController;

    GamePieceAssassin(GamePieceSlot gamePieceSlot) {

        // Declare MVC
        this.gameBoardController = gamePieceSlot.gameBoardController;

        // JButton Setup
        this.setText("Assassin");
        this.setEnabled(true);
        this.addActionListener(this);
    }

    // ASSASSIN ACTION
    @Override
    public void actionPerformed(ActionEvent e) {

        this.setEnabled(false);
        gameBoardController.gameBoardModel.getSlotCombined(x,y).setEnabled(false);

        System.out.println("I'm an Assassin!");

        if (0 <= x - 1)
            if (!gameBoardController.gameBoardModel.getSlotOccupancy(x - 1, y).equals(GameBoardModel.player.PLAYER_NONE))
                gameBoardController.gameBoardPanel.getSlot(x - 1, y).setPieceColor(Color.GREEN);
        if (x + 1 <= GameBoardModel.numCol - 1)
            if (!gameBoardController.gameBoardModel.getSlotOccupancy(x + 1, y).equals(GameBoardModel.player.PLAYER_NONE))
                gameBoardController.gameBoardPanel.getSlot(x + 1, y).setPieceColor(Color.GREEN);

        // Activate Assassin Animations
        AssassinAnimationTimer.start();

    }

    // ASSASSIN ANIMATIONS
    private boolean hasAttacked = false;
    private Timer AssassinAnimationTimer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!hasAttacked) {
                System.out.println("First");

                if (0 <= x - 1)
                    if (!gameBoardController.gameBoardModel.getSlotOccupancy(x - 1, y).equals(GameBoardModel.player.PLAYER_NONE))
                        gameBoardController.gameBoardModel.getSlotCombined(x - 1, y).clearInfo();
                if (x + 1 <= GameBoardModel.numCol - 1)
                    if (!gameBoardController.gameBoardModel.getSlotOccupancy(x + 1, y).equals(GameBoardModel.player.PLAYER_NONE))
                        gameBoardController.gameBoardModel.getSlotCombined(x + 1, y).clearInfo();

                gameBoardController.gameBoardModel.loadOccupancyListFromCombinedList();
                gameBoardController.gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);

                hasAttacked = true;
            } else {
                AssassinAnimationTimer.stop();
                System.out.println("Second");

                hasAttacked = false;

                gameBoardController.boardGravityController.runGravity();
            }
        }
    });
}
