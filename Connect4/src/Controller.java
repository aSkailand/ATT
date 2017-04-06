import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class Controller implements ActionListener {

    Model model;
    GameFrame gameFrame;
    GameGridPanel gameGridPanel;

    public Controller() {
        model = new Model();
        gameFrame = new GameFrame(this);

        // Initiating a GameGridPanel Object
        gameGridPanel = new GameGridPanel(model.listJPanelGameBoardSlots);

        // Adding it to gameFrame's centerPanel.
        gameFrame.centerPanel.add(gameGridPanel);
        gameFrame.centerPanel.repaint();
        gameFrame.centerPanel.revalidate();

        //JMenu action listeners
        gameFrame.mainMenu.addActionListener(this);
        gameFrame.saveGame.addActionListener(this);
        gameFrame.openGame.addActionListener(this);
        gameFrame.pauseGame.addActionListener(this);
        gameFrame.restartGame.addActionListener(this);
        gameFrame.highscore.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //TODO: Maybe we should move all the MenuBar Action listeners to a new controller? (E.g. JMenuBar controller?)

        //Main menu action listener
        if (e.getSource().equals(gameFrame.mainMenu)) {
            System.out.println("Main menu...");
        }

        //Save game action listener
        if (e.getSource().equals(gameFrame.saveGame)) {
            System.out.println("Saved game...");
        }

        //Open game action listener
        if (e.getSource().equals(gameFrame.openGame)) {
            System.out.println("Open game...");
        }

        //Pause game action listener
        if (e.getSource().equals(gameFrame.pauseGame)) {
            System.out.println("Paused game...");
        }

        //Restart game action listener
        if (e.getSource().equals(gameFrame.restartGame)) {
            System.out.println("Restart game...");
        }

        //Highscore action listener
        if (e.getSource().equals(gameFrame.highscore)) {
            System.out.println("Highscore");
        }


        // Check the number on the clicked button
        int chosenCol = Integer.parseInt(e.getActionCommand());

        // Find first index of the slot that is not occupied in the chosen column
        int indexOfNotOccupied = model.listBoolOccupiedSlots.get(chosenCol).indexOf(false);

        // Place a button (piece) on chosen column
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).removeAll();
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).add(new JButton("APP"));
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).repaint();
        model.listJPanelGameBoardSlots.get(chosenCol).get(indexOfNotOccupied).revalidate();

        model.listBoolOccupiedSlots.get(chosenCol).set(indexOfNotOccupied, true);


    }
}
