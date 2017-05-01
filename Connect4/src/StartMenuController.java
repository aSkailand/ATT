import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thanu on 20.04.2017.
 */
public class StartMenuController implements ActionListener{



    StartMenu View;




    public StartMenuController(){

        View = new StartMenu(this);


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

                System.out.println("Trakk på Solo");
                break;
            }

            case "Versus": {

                View.SoloButton.setEnabled(false);
                View.PlayButton.setEnabled(true);

                System.out.println("Trakk på Versus");
                break;
            }

            case "Play": {


                View.SoloButton.setEnabled(true);
                View.VersusButton.setEnabled(true);

                System.out.println("Trakk på Play");
                break;
            }


            case "Numbers of Columns": {

                String ColumnNumer = JOptionPane.showInputDialog(null, "Add how many Columns:");
                JOptionPane.showMessageDialog(null, "You chose " +ColumnNumer +" columns");
                System.out.println("Trakk på Numbers of Columns");
                break;

            }

            case "Numbers of rows": {

                String RowNumber = JOptionPane.showInputDialog(null, "Add how many Rows:");
                JOptionPane.showMessageDialog(null,"You chose "+RowNumber +" rows");
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
