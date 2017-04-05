import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class Controller implements ActionListener{

    Model model;
    GameFrame gameFrame;
    GameGridPanel gameGridPanel;

    public Controller(){
        model = new Model();
        gameFrame = new GameFrame(this);

        // Initiating a GameGridPanel Object
        gameGridPanel = new GameGridPanel(model.listJPanelGameBoardSlots,model.listBoolOccupiedSlots);

        // Adding it to gameFrame's centerPanel.
        gameFrame.centerPanel.add(gameGridPanel);
        gameFrame.centerPanel.repaint();
        gameFrame.centerPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Find first index of the slot that is not occupied in the chosen column
        int indexOfNotOccupied = model.listBoolOccupiedSlots.get(chosenCol).indexOf(false);

        // Place a button (piece) on chosen column
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).removeAll();
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).add(new JButton("APP"));
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).repaint();
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).revalidate();

        model.listBoolOccupiedSlots.get(chosenCol).set(indexOfNotOccupied,true);



    }
}
