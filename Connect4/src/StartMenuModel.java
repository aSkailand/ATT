import javax.swing.*;
import java.awt.*;

/**
 * Created by Thanu on 20.04.2017.
 */
public class StartMenuModel {


    JButton Button4;
    JButton Button5;
    JButton Button6;

    JButton Button7;
    JButton Button8;
    JButton Button9;

    JPanel jPanel = new JPanel();




    public void Settings(StartMenuController C){


        JPanel JPANEL = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();


        JPANEL.setSize(600,600);

        JPANEL.setLayout(new GridBagLayout());
        JPANEL.setBackground(Color.GREEN);

        JLabel JLA1;
        JLabel JLA2;



        Button4 = new JButton("Save");
        gbc.insets = new Insets(20,0,0,20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        JPANEL.add(Button4,gbc);
        Button4.addActionListener(C);

        Button5 = new JButton("Reset");
        gbc.insets = new Insets(20,0,0,20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        JPANEL.add(Button5, gbc);
        Button5.addActionListener(C);

        Button6 = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        JPANEL.add(Button6, gbc);
        Button6.addActionListener(C);


        JLA1 = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.VERTICAL;
        JPANEL.add(JLA1, gbc);

        JLA2 = new JLabel("");
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.VERTICAL;
        JPANEL.add(JLA2, gbc);

        jPanel.add(JPANEL);
        jPanel.setVisible(true);



    }



    public void PlayGame(StartMenuController C){

        JPanel JPan = new JPanel();
        GridBagConstraints gbcon = new GridBagConstraints();



        JPan.setSize(300,300);
        JPan.setLayout(new GridBagLayout());
        JPan.setBackground(Color.magenta);

        Button7 = new JButton("Solo");
        gbcon.insets = new Insets(20,50,5,50);
        gbcon.gridx = 0;
        gbcon.gridy = 0;
        gbcon.weightx = 0.1;
        gbcon.weighty = 0.4;
        gbcon.fill = GridBagConstraints.BOTH;
        JPan.add(Button7,gbcon);
        Button7.addActionListener(C);


        Button8 = new JButton("Versus");
        gbcon.insets = new Insets(20,50,5,50);
        gbcon.gridx = 0;
        gbcon.gridy = 1;
        gbcon.weightx = 0.1;
        gbcon.weighty = 0.4;
        gbcon.fill = GridBagConstraints.BOTH;
        JPan.add(Button8,gbcon);
        Button8.addActionListener(C);

        Button9 = new JButton("Back to Menu");
        gbcon.insets = new Insets(20,50,5,50);
        gbcon.gridx = 0;
        gbcon.gridy = 2;
        gbcon.weightx = 0.1;
        gbcon.weighty = 0.1;
        gbcon.fill = GridBagConstraints.BOTH;
        JPan.add(Button9,gbcon);
        Button9.addActionListener(C);


        jPanel.add(JPan);
        jPanel.setVisible(true);

    }






}
