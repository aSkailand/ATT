import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thanu on 20.04.2017.
 */
public class StartMenuController implements ActionListener{



    StartMenuView View;
    StartMenuModel Model;

    public StartMenuController(){

        View = new StartMenuView(this);
        Model = new StartMenuModel();

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){

            case "Play Game": {
                View.jPanel.removeAll();
                Model.PlayGame(this);
                View.jPanel.validate();
                View.jPanel.repaint();

                System.out.println("Trakk på play game");
                break;
            }

            case "Settings": {
                View.jPanel.removeAll();
                Model.Settings(this);
                View.jPanel.validate();
                View.jPanel.repaint();
                System.out.println("Settingss");
                break;
            }

            case "Exit Game": {
                System.out.println("Trakk på exit");

                break;
            }

            case "Save": {
                System.out.println("Trykket på save");
                break;

            }

            default: {
                System.out.println("Error");
                break;
            }
        }






    }
}
