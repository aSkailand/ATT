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

        // Adds buttons to the left player unitpanel
        gameBodyFrame.leftPlayerUnitPanel.add(unitButtonView.CreateUnitButtonView(
                this,
                unitButtonModel.unitButtonsPlayer1,
                unitButtonModel.unitLabelPlayer1,
                unitButtonModel.testImg,
                Color.red,
                Color.ORANGE));

        // adds buttons to the right player unitpanel
        gameBodyFrame.rightPlayerUnitPanel.add(unitButtonView.CreateUnitButtonView(
                this,
                unitButtonModel.unitButtonsPlayer2,
                unitButtonModel.unitLabelPlayer2, unitButtonModel.testImg,
                Color.blue,
                Color.ORANGE));

        gameBodyFrame.revalidate();
        gameBodyFrame.repaint();
    }

    // This alternates the unit buttons for each player, diables the buttons of the opposing player when its the current players turn.
    public void disableButtons(GameBoardModel.player currentPlayer) {

        if (currentPlayer.equals(GameBoardModel.player.PLAYER_1)) {
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer1.get(i).setEnabled(true);
                unitButtonModel.unitButtonsPlayer2.get(i).setEnabled(false);
            }
        }
        if (currentPlayer.equals(GameBoardModel.player.PLAYER_2)) {
            for (int i = 0; i < 6; i++) {
                unitButtonModel.unitButtonsPlayer2.get(i).setEnabled(true);
                unitButtonModel.unitButtonsPlayer1.get(i).setEnabled(false);
            }
        }
    }

    public void checkAffordability(GameBoardModel.player currentPlayer) {

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
