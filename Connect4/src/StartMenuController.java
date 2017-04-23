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
                System.out.println("Settingss");
                break;
            }

            case "Exit Game": {

                System.out.println("Trykket på Exit");

                break;
            }

            case "Save": {
                System.out.println("Trykket på save");
                break;

            }

            case "Reset": {
                System.out.println("Trykket på reset");
                break;
            }

            case "Back": {

                System.out.println("Trykket på back");
                break;
            }

            case "Solo": {

                System.out.println("Trykket på Solo");
                break;
            }

            case "Versus": {

                System.out.println("Trykket på Versus");
                break;
            }

            case "Back to Menu":{

                System.out.println("Trykket på back to menu");
                break;
            }

            default: {
                System.out.println("Error");
                break;
            }
        }



    }
}
