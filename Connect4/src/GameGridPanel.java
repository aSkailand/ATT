import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by TrongDT on 03/04/2017.
 */
public class GameGridPanel extends JPanel{



    GameGridPanel(ArrayList<ArrayList<JPanel>> listJPanelGameBoardSlots, ArrayList<ArrayList<Boolean>> listBoolOccupiedSlots){

        // JPanel Setup
        this.setLayout(new GridLayout(Model.numRow, Model.numCol));


        // todo: fix all these three loop and merge them into two/one.
        // Grid panels Setup

//        for (int i = 0; i < Model.numRow; i++) {
//            for (int j = 0; j < Model.numCol; j++) {
//
//
//
//            }
//        }

        ArrayList<JPanel> tempy = new ArrayList<>();
        for (int i = 0; i < Model.numRow*Model.numCol; i++) {
            JPanel panelTemp = new JPanel(new GridLayout(1,1));
            tempy.add(panelTemp);
            this.add(tempy.get(i));
        }

        // Creating the ArrayLists for ArrayList of panels.
        for (int i = 0; i < Model.numCol; i++) {
            listJPanelGameBoardSlots.add(new ArrayList<>());
            for (int j = 0; j < Model.numRow; j++) {

                int currentSquareIndex = ((Model.numRow-1)*Model.numCol)+i-(Model.numCol*j);
                listJPanelGameBoardSlots.get(i).add(tempy.get(currentSquareIndex));
                JButton tempButton = new JButton("( "+i+" , "+j+" )");
                tempButton.setEnabled(false);
                listJPanelGameBoardSlots.get(i).get(j).add(tempButton);

            }
        }


    }
}
