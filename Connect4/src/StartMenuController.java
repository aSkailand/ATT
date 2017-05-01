import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thanu on 20.04.2017.
 */
public class StartMenuController implements ActionListener{



    StartMenu View;

    StartMenuModel startMenuModel;



    public StartMenuController(){

        // MVC declare
        View = new StartMenu(this);

        startMenuModel = new StartMenuModel();

    }

    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){

            case "Play Game": {
                View.leftPanel.removeAll();
                View.PlayGame(this);
                View.leftPanel.validate();
                View.leftPanel.repaint();

                System.out.println("Trakk på play game");
                break;
            }

            case "How To Play": {
                View.rightPanel.removeAll();
                View.leftPanel.removeAll();
                View.HowToPlay(this);

                View.rightPanel.validate();
                View.rightPanel.repaint();

                View.leftPanel.validate();
                View.leftPanel.repaint();

                System.out.println("Trakk på How to Play");
                break;
            }

            case "Exit Game": {

                View.ExitGame(this);
                System.out.println("Trakk på Exit");

                break;
            }

            case "Credits": {

                View.rightPanel.removeAll();
                View.Credits(this);
                View.rightPanel.validate();
                View.rightPanel.repaint();

                System.out.println("Trakk på Credits");
                break;

            }

            case "Back": {

                View.leftPanel.removeAll();
                View.rightPanel.removeAll();
                View.BackToMenu(this);
                View.MainPanel.validate();
                View.MainPanel.repaint();

                System.out.println("Trakk på Back");

                break;

            }


            case "Game Mode": {

                View.rightPanel.removeAll();
                View.GameMode(this);
                View.rightPanel.validate();
                View.PlayButton.setEnabled(false);
                View.rightPanel.repaint();

                break;

            }


            case "Settings": {
                View.rightPanel.removeAll();
                View.Settings(this);
                View.rightPanel.validate();
                View.rightPanel.repaint();

                System.out.println("Trakk på Settings");
                break;

            }

            case "Solo": {

                View.VersusButton.setEnabled(false);
                View.PlayButton.setEnabled(true);

                startMenuModel.setAI_Bool(true);

                System.out.println("Trakk på Solo");
                break;
            }

            case "Versus": {

                View.SoloButton.setEnabled(false);
                View.PlayButton.setEnabled(true);

                startMenuModel.setAI_Bool(false);


                System.out.println("Trakk på Versus");
                break;
            }

            case "Play": {


                View.SoloButton.setEnabled(true);
                View.VersusButton.setEnabled(true);


                new GameBodyController(startMenuModel);


                System.out.println("Trakk på Play");
                break;
            }


            case "Numbers of Columns": {

                String ColumnNumber = JOptionPane.showInputDialog(null, "Add how many Columns:");
                int Column = Integer.parseInt(ColumnNumber);
                JOptionPane.showMessageDialog(null, "You chose " +Column +" columns.");
                startMenuModel.setColumns(Column);



                System.out.println("Trakk på Numbers of Columns");
                break;

            }

            case "Numbers of rows": {

                String RowNumber = JOptionPane.showInputDialog(null, "Add how many Rows:");
                int Row = Integer.parseInt(RowNumber);
                JOptionPane.showMessageDialog(null,"You chose "+Row +" rows");
                startMenuModel.setRows(Row);

                System.out.println("Trakk på Numbers of rows");
                break;
            }

            default: {
                System.out.println("Error");
                break;
            }
        }



    }
}
