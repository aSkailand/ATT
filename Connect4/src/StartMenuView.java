import javax.swing.*;
import java.awt.*;

/**
 * Created by Thanu on 20.04.2017.
 */
public class StartMenuView extends JFrame {

    JButton Button1;
    JButton Button2;
    JButton Button3;

    JLabel JL;
    JPanel jPanel = new JPanel();
    JPanel JP = new JPanel();



    public StartMenuView(StartMenuController C){

        //this. er JFramen
        this.setSize(600,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        jPanel.setLayout(new BorderLayout());
        this.add(jPanel);


        JP.setLayout(new GridBagLayout());
        JP.setBackground(Color.CYAN);


        GridBagConstraints GBC = new GridBagConstraints();


        //Fra nå av jobber jeg på JPanel og ikke JFrame

        Button1 = new JButton("Play Game");
        GBC.insets = new Insets(10,0,0,0);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.1;
        GBC.weighty = 0.4;
        GBC.fill = GridBagConstraints.BOTH;
        JP.add(Button1, GBC);
        Button1.addActionListener(C);


        Button2 = new JButton("Settings");
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;
        JP.add(Button2,GBC);
        Button2.addActionListener(C);


        Button3 = new JButton("Exit Game");
        GBC.insets = new Insets(0,0,10,0);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;
        JP.add(Button3, GBC);
        Button3.addActionListener(C);

        JL = new JLabel("");
        GBC.gridx = 1;
        GBC.gridy = 0;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.VERTICAL;
        JP.add(JL, GBC);



        jPanel.add(JP);
        this.setVisible(true);





    }




}
