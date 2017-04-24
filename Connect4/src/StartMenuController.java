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
                View.jPanel.removeAll();
                View.PlayGame(this);
                View.jPanel.validate();
                View.jPanel.repaint();

                System.out.println("Trakk på play game");
                break;
            }

            case "Settings": {
                View.jPanel.removeAll();
                View.Settings(this);
                View.jPanel.validate();
                View.jPanel.repaint();
                System.out.println("Trakk på Settings");
                break;
            }

            case "Exit Game": {

                View.ExitGame(this);
                System.out.println("Trakk på Exit");

                break;
            }

            case "Save": {
                System.out.println("Trakk på save");
                break;

            }

            case "Reset": {
                System.out.println("Trakk på reset");
                break;
            }

            case "Back": {

                View.jPanel.removeAll();
                View.BackToMenu(this);
                View.jPanel.validate();
                View.jPanel.repaint();

                System.out.println("Trakk på back");

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

                View.jPanel.removeAll();
                View.BackToMenu(this);
                View.jPanel.validate();
                View.jPanel.repaint();


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
