import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TrongDT on 03/04/2017.
 */

/**
 * This Class creates a JPanel, acting as a frame for a grid system making up the in-game board.
 *
 * Parameter:   Model
 * Output:      Sorted ArrayList of GamePieceSlot
 *
 */

public class GameGridPanel extends JPanel {

    GameGridPanel(GameGridModel M) {

        // JPanel Setup
        this.setLayout(new GridLayout(GameGridModel.numRow, GameGridModel.numCol));


        // Add JPanels to ArrayList
        ArrayList<GamePieceSlot> tempListPanels = new ArrayList<>();
        for (int i = 0; i < GameGridModel.numCol * GameGridModel.numRow; i++) {
            tempListPanels.add(new GamePieceSlot());
        }


        // Add JPanels from ArrayList to Grid
        for (int i = 0; i < GameGridModel.numCol * GameGridModel.numRow; i++) {
            this.add(tempListPanels.get(i));
        }


        // Add ArrayList of JPanels to a common ArrayList
        for (int i = 0; i < GameGridModel.numCol; i++) {
            M.listJPanelGameBoardSlots.add(new ArrayList<>());
            for (int j = 0; j < GameGridModel.numRow; j++) {

                int currentSquareIndex = (((GameGridModel.numRow - 1) * GameGridModel.numCol) + i) - (GameGridModel.numCol * j);

                tempListPanels.get(currentSquareIndex).picture.setText("( " + i + " , " + j + " )");
                tempListPanels.get(currentSquareIndex).picture.setEnabled(false);

                M.listJPanelGameBoardSlots.get(i).add(tempListPanels.get(currentSquareIndex));

            }
        }

    }
}
