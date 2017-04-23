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

    // JPanel list holding all the JPanels on the board in sorted fashion.
    private ArrayList<ArrayList<GamePieceSlot>> listJPanelGameBoardSlots = new ArrayList<>();

    GameBoardPanel() {

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
        for (int x = 0; x < GameBoardModel.numCol; x++) {
            listJPanelGameBoardSlots.add(new ArrayList<>());
            for (int y = 0; y < GameBoardModel.numRow; y++) {

                // corresponding index from linked list to x-y-index
                int currentSquareIndex = (((GameBoardModel.numRow - 1) * GameBoardModel.numCol) + x) - (GameBoardModel.numCol * y);

                listJPanelGameBoardSlots.get(x).add(tempListPanels.get(currentSquareIndex));
                getSlot(x,y).setCoordinates(x,y);
                getSlot(x,y).initializeEmpty();
                getSlot(x,y).setEmpty();

            }
        }

    }

    // Getter-Wrapper for the slots inside GameBoardPanel
    GamePieceSlot getSlot(int x, int y) {
        return listJPanelGameBoardSlots.get(x).get(y);
    }
}
