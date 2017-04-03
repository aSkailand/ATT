import javax.swing.*;
import java.awt.*;

/**
 * Created by TrongDT on 03/04/2017.
 */
public class GameBoard extends JFrame{


    GameBoard(){
        this.setSize(1000,1000);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel button_holder = new JPanel();
        button_holder.setBackground(Color.RED);
        this.add(button_holder, gbc);

        JPanel JP = new JPanel();
        JP.setLayout(new GridLayout(6,7,5,5));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JPanel JJ = new JPanel();
                JJ.setToolTipText("ROW:"+i+",COL:"+j);
                JJ.setBackground(Color.gray);

                JP.add(JJ);
            }
        }
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 1;

        this.add(JP, gbc);
        this.setVisible(true);

    }
}
