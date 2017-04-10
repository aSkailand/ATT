import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TrongDT on 03/04/2017.
 */

/**
 *  ROLE:
 *      View
 *  WHAT:
 *      This Class creates a JPanel, acting as a frame for a grid system making up the in-game board.
 *  USAGE:
 *      Outputs a sorted ArrayList of GamePieceSlot.
 */
public class GameBoardPanel extends JPanel {

    GameBoardPanel(GameBoardModel M) {

        // JPanel Setup
        this.setLayout(new GridLayout(GameBoardModel.numRow, GameBoardModel.numCol));


        // Add JPanels to ArrayList
        ArrayList<GamePieceSlot> tempListPanels = new ArrayList<>();
        for (int i = 0; i < GameBoardModel.numCol * GameBoardModel.numRow; i++) {
            tempListPanels.add(new GamePieceSlot());
        }


        // Add JPanels from ArrayList to Grid
        for (int i = 0; i < GameBoardModel.numCol * GameBoardModel.numRow; i++) {
            this.add(tempListPanels.get(i));
        }


        // Add ArrayList of JPanels to a common ArrayList
        for (int i = 0; i < GameBoardModel.numCol; i++) {
            M.listJPanelGameBoardSlots.add(new ArrayList<>());
            for (int j = 0; j < GameBoardModel.numRow; j++) {

                int currentSquareIndex = (((GameBoardModel.numRow - 1) * GameBoardModel.numCol) + i) - (GameBoardModel.numCol * j);

                tempListPanels.get(currentSquareIndex).piece.setText("( " + i + " , " + j + " )");
                tempListPanels.get(currentSquareIndex).piece.setEnabled(false);

                M.listJPanelGameBoardSlots.get(i).add(tempListPanels.get(currentSquareIndex));

            }
        }

    }
}
