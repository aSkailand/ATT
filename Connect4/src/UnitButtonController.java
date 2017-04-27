import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 27.04.17.
 */
public class UnitButtonController implements ActionListener {

    GameBodyFrame gameBodyFrame;
    UnitButtonView unitButtonView;
    UnitButtonModel unitButtonModel;

    public UnitButtonController(GameBodyController gameBodyController) {

        // Declare MVC
        gameBodyFrame = gameBodyController.gameBodyFrame;
        unitButtonModel = new UnitButtonModel();
        unitButtonView = new UnitButtonView();

        gameBodyFrame.rightPlayerUnitPanel.add(unitButtonView.UnitButtonView(this,true,Color.blue));

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();
    }

    public void disableButtons(GameBoardModel.player currentPlayer) {

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {

        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "button0": {

                System.out.println("button0");
                break;
            }
            case "button1": {

                System.out.println("button1");
                break;
            }
            case "button2": {

                System.out.println("button2");
                break;
            }
            case "button3": {

                System.out.println("button3");
                break;
            }
            case "button4": {

                System.out.println("button4");
                break;
            }
            case "button5": {

                System.out.println("button5");
                break;
            }
            case "button6": {

                System.out.println("button6");
                break;
            }
        }
    }
}
