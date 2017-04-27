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


    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){

            case "Play Game": {
                View.leftPanel.removeAll();
                View.PlayGame(this);
                View.MainPanel.validate();
                View.MainPanel.repaint();

                System.out.println("Trakk på play game");
                break;
            }

            case "How To Play": {
                View.rightPanel.removeAll();
                View.leftPanel.removeAll();
                View.HowToPlay(this);
                View.MainPanel.validate();
                View.MainPanel.repaint();

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
                View.MainPanel.validate();
                View.MainPanel.repaint();

                System.out.println("Trakk på Credits");
                break;

            }


            case "Start Game": {

                System.out.println("Trakk på start game");
                break;

            }


            case "Game Mode": {

                View.rightPanel.removeAll();
                View.GameMode(this);
                View.MainPanel.validate();
                View.MainPanel.repaint();

                break;

            }


            case "Settings": {
                View.rightPanel.removeAll();
                View.Settings(this);
                View.MainPanel.validate();
                View.MainPanel.repaint();

                System.out.println("Trakk på Settings");
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


            case "Save Game": {
                System.out.println("Trakk på save");
                break;

            }

            case "Reset Game": {
                System.out.println("Trakk på reset");
                break;
            }


            case "Solo": {

                System.out.println("Trakk på Solo");
                break;
            }

            case "Versus": {

                System.out.println("Trakk på Versus");
                break;
            }

            case "Back to Menu":{

                View.leftPanel.removeAll();
                View.rightPanel.removeAll();
                View.BackToMenu(this);
                View.MainPanel.validate();
                View.MainPanel.repaint();

                System.out.println("Trakk på back to menu");

                break;
            }

            default: {
                System.out.println("Error");
                break;
            }
        }



    }
}
