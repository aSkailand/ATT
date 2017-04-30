import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Trong on 30/04/2017.
 */
public class GamePieceKnight extends GamePiece implements ActionListener{

    int x;
    int y;

    private GameBoardController gameBoardController;

    public GamePieceKnight(GamePieceSlot gamePieceSlot) {

        gameBoardController = gamePieceSlot.gameBoardController;

        // JButton Setup
        this.setText("Knight");
        this.setEnabled(false);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.setEnabled(false);
        gameBoardController.gameBoardModel.getSlotCombined(x,y).setEnabled(false);

        System.out.println("I'm a Knight!");
        System.out.println("x: "+x+", y: "+y);

        temp_x = this.x;
        temp_y = this.y - 1;

        knightAnimationTimer.start();

    }

    int temp_x;
    int temp_y;
    boolean colored = false;

    Timer knightAnimationTimer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (y != 0) {
                if (temp_y != -1 && !colored) {
                    gameBoardController.gameBoardPanel.getSlot(temp_x, temp_y).setPieceColor(Color.GREEN);
                    gameBoardController.gameBoardPanel.revalidate();
                    gameBoardController.gameBoardPanel.repaint();
                    temp_y--;
                } else if (!colored) {
                    colored = true;
                    temp_x = x;
                    temp_y = y - 1;
                } else {
                    gameBoardController.gameBoardModel.getSlotCombined(temp_x, temp_y).clearInfo();
                    gameBoardController.gameBoardModel.loadSlotListFromCombinedList(gameBoardController.gameBoardPanel);
                    gameBoardController.gameBoardPanel.revalidate();
                    gameBoardController.gameBoardPanel.repaint();
                    temp_y--;

                    if (temp_y == -1) {
                        knightAnimationTimer.stop();

                        colored = false;
                        temp_x = x;
                        temp_y = y - 1;

                        gameBoardController.gameBoardModel.loadOccupancyListFromCombinedList();
                        gameBoardController.boardGravityController.runGravity();
                    }
                }

            }
        }
    });
}
