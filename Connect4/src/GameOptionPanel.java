import javax.swing.*;
import java.awt.*;

/**
 * Created by Trong on 12/04/2017.
 */
public class GameOptionPanel extends JPanel{

    GameOptionPanel(GameBoardController controller){

        this.setLayout(new GridLayout(1,GameBoardModel.numCol));

        for (int i = 0; i < GameBoardModel.numCol; i++) {
            JButton tempButton = new JButton();
            tempButton.setText("[ "+i+" ]");
            tempButton.setActionCommand(""+i);
            tempButton.addActionListener(controller);
            this.add(tempButton);
        }
    }
}
