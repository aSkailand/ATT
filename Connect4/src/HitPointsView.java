import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aslak on 26.04.17.
 */
class HitPointsView {

    JPanel createAHeartJPanel(ArrayList<JLabel> listOfHeartLabels, Color playerColor) {

        // JPanel to hold JPanel that again holds Label
        JPanel heartsJPanel = new JPanel();
        heartsJPanel.setLayout(new GridLayout(1,5));
        heartsJPanel.setBackground(playerColor);

        for (int i = 0; i < 5; i++) {

            // Panel to hold Label Setup
            JPanel tempPanel = new JPanel();
            tempPanel.setLayout(new BorderLayout());
            tempPanel.setBackground(new Color(0,0,0,0));
            tempPanel.add(listOfHeartLabels.get(i));
            heartsJPanel.add(tempPanel);
        }
        return heartsJPanel;
    }

}
