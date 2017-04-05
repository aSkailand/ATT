import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TrongDT on 03/04/2017.
 */

/**
 * This Class creates a JPanel, acting as a frame for a grid system making up the in-game board.
 */
// todo: Decide what should go to GameModel or not. (Note: Possibility to move all over, should we or not?)
public class GameGridPanel extends JPanel{

    GameGridPanel(ArrayList<ArrayList<JPanel>> listJPanelGameBoardSlots){

        // JPanel Setup
        this.setLayout(new GridLayout(GameModel.numRow, GameModel.numCol));

        // Filling temporary ArrayLists with JPanels
        ArrayList<JPanel> tempListPanels = new ArrayList<>();
        for (int i = 0; i < GameModel.numRow* GameModel.numCol; i++) {
            JPanel panelTemp = new JPanel();
//            panelTemp.setLayout(new GridLayout(1,1));
            panelTemp.setLayout(new BorderLayout());
            panelTemp.setBorder(new EmptyBorder(10, 10, 10, 10));
            tempListPanels.add(panelTemp);
            this.add(tempListPanels.get(i));
        }

        // Creating the ArrayLists for ArrayList of panels, in a sorted fashion
        for (int i = 0; i < GameModel.numCol; i++) {

            // Making a subArrayList for the main ArrayList
            listJPanelGameBoardSlots.add(new ArrayList<>());

            for (int j = 0; j < GameModel.numRow; j++) {

                // Find the corresponding index from the temporary list
                int currentSquareIndex = ((GameModel.numRow-1)* GameModel.numCol)+i-(GameModel.numCol*j);

                // Add the current JPanel to corresponding slot
                listJPanelGameBoardSlots.get(i).add(tempListPanels.get(currentSquareIndex));

                // Adding a button to the current JPanel
                JButton tempButton = new JButton("( "+i+" , "+j+" )");
                tempButton.setEnabled(false);
                listJPanelGameBoardSlots.get(i).get(j).add(tempButton);

            }
        }

    }
}
