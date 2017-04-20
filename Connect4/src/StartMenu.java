import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thanu on 05.04.2017.
 */
/*

public class StartMenu extends JFrame implements ActionListener{



    JButton Button1;
    JButton Button2;
    JButton Button3;

    JLabel JL;
    JPanel jPanel = new JPanel();
    JPanel JP = new JPanel();

    JButton Button4;
    JButton Button5;
    JButton Button6;

    JButton Button7;
    JButton Button8;
    JButton Button9;



    public StartMenu(){


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
        Button1.addActionListener(this);


        Button2 = new JButton("Settings");
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;
        JP.add(Button2,GBC);
        Button2.addActionListener(this);


        Button3 = new JButton("Exit Game");
        GBC.insets = new Insets(0,0,10,0);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;
        JP.add(Button3, GBC);
        Button3.addActionListener(this);

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


    public void PlayGame(){

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
        Button7.addActionListener(this);


        Button8 = new JButton("Versus");
        gbcon.insets = new Insets(20,50,5,50);
        gbcon.gridx = 0;
        gbcon.gridy = 1;
        gbcon.weightx = 0.1;
        gbcon.weighty = 0.4;
        gbcon.fill = GridBagConstraints.BOTH;
        JPan.add(Button8,gbcon);
        Button8.addActionListener(this);

        Button9 = new JButton("Back to Menu");
        gbcon.insets = new Insets(20,50,5,50);
        gbcon.gridx = 0;
        gbcon.gridy = 2;
        gbcon.weightx = 0.1;
        gbcon.weighty = 0.1;
        gbcon.fill = GridBagConstraints.BOTH;
        JPan.add(Button9,gbcon);
        Button9.addActionListener(this);


        jPanel.add(JPan);
        jPanel.setVisible(true);

    }




    public void Settings(){


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
        Button4.addActionListener(this);

        Button5 = new JButton("Reset");
        gbc.insets = new Insets(20,0,0,20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.4;
        gbc.fill = GridBagConstraints.BOTH;
        JPANEL.add(Button5, gbc);
        Button5.addActionListener(this);

        Button6 = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        JPANEL.add(Button6, gbc);
        Button6.addActionListener(this);


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



    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){

            case "Play Game": {
                jPanel.removeAll();
                PlayGame();
                jPanel.validate();
                jPanel.repaint();

                System.out.println("Trakk på play game");
                break;
            }

            case "Settings": {
                jPanel.removeAll();
                Settings();
                jPanel.validate();
                jPanel.repaint();

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
*/
