import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Thanu on 05.04.2017.
 */


public class StartMenu extends JFrame{


    JButton PlayGameButton;
    JButton SettingsButton;
    JButton ExitButton;

    JLabel JL;
    JPanel jPanel = new JPanel();
    JPanel JP = new JPanel();
    JLabel TitleLabel;

    JButton SaveButton;
    JButton ResetButton;
    JButton BackButton1;

    JButton SoloButton;
    JButton VersusButton;
    JButton BackButton2;


    GridBagConstraints GBC = new GridBagConstraints();
    

    public StartMenu(StartMenuController c){


        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        jPanel.setLayout(new BorderLayout());
        this.add(jPanel);

        JP.setLayout(new GridBagLayout());
        JP.setBackground(Color.CYAN);

        PlayGameButton = new JButton("Play Game");
        PlayGameButton.addActionListener(c);
        GBCPlayGameButton();
        JP.add(PlayGameButton, GBC);

        SettingsButton = new JButton("Settings");
        SettingsButton.addActionListener(c);
        GBCSettingsButton();
        JP.add(SettingsButton,GBC);

        ExitButton = new JButton("Exit Game");
        ExitButton.addActionListener(c);
        GBCExitButton();
        JP.add(ExitButton, GBC);

        JL = new JLabel("");
        GBCJL();
        JP.add(JL, GBC);

        TitleLabel = new JLabel("Welcome to Connect4");
        TitleLabel.setForeground(Color.BLACK);
        TitleLabel.setFont(new Font("", Font.BOLD, 22));
        GBCTitleLabel();
        JP.add(TitleLabel,GBC);

        jPanel.add(JP);
        this.setVisible(true);



    }


    public void PlayGame(StartMenuController c){

        JPanel JPan = new JPanel();

        JPan.setSize(300,300);
        JPan.setLayout(new GridBagLayout());
        JPan.setBackground(Color.magenta);

        SoloButton = new JButton("Solo");
        SoloButton.addActionListener(c);
        GBCSoloButton();
        JPan.add(SoloButton,GBC);

        VersusButton = new JButton("Versus");
        VersusButton.addActionListener(c);
        GBCVersusButton();
        JPan.add(VersusButton,GBC);

        BackButton1 = new JButton("Back to Menu");
        BackButton1.addActionListener(c);
        GBCBackButton1();
        JPan.add(BackButton1,GBC);

        jPanel.add(JPan);
        jPanel.setVisible(true);

    }




    public void Settings(StartMenuController c){

        JPanel JPANEL = new JPanel();

        JPANEL.setSize(600,600);

        JPANEL.setLayout(new GridBagLayout());
        JPANEL.setBackground(Color.GREEN);

        JLabel JLA1;
        JLabel JLA2;

        SaveButton = new JButton("Save");
        SaveButton.addActionListener(c);
        GBCSaveButton();
        JPANEL.add(SaveButton,GBC);

        ResetButton = new JButton("Reset");
        ResetButton.addActionListener(c);
        GBCResetButton();
        JPANEL.add(ResetButton, GBC);

        BackButton2 = new JButton("Back");
        BackButton2.addActionListener(c);
        GBCBackButton2();
        JPANEL.add(BackButton2, GBC);

        JLA1 = new JLabel("");
        GBCJLA1();
        JPANEL.add(JLA1, GBC);

        JLA2 = new JLabel("");
        GBCJLA2();
        JPANEL.add(JLA2, GBC);

        jPanel.add(JPANEL);
        jPanel.setVisible(true);


    }


    public void ExitGame(StartMenuController c){

        setVisible(false);
        dispose();

    }

    public void BackToMenu(StartMenuController c){

        PlayGameButton = new JButton("Play Game");
        PlayGameButton.addActionListener(c);
        GBCPlayGameButton();
        JP.add(PlayGameButton, GBC);

        SettingsButton = new JButton("Settings");
        SettingsButton.addActionListener(c);
        GBCSettingsButton();
        JP.add(SettingsButton,GBC);

        ExitButton = new JButton("Exit Game");
        ExitButton.addActionListener(c);
        GBCExitButton();
        JP.add(ExitButton, GBC);

        JL = new JLabel("");
        GBCJL();
        JP.add(JL, GBC);

        jPanel.add(JP);
        this.setVisible(true);

    }




    void GBCPlayGameButton(){

        GBC.insets = new Insets(10,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;


    }

    void GBCSettingsButton(){

        GBC.insets = new Insets(5,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;


    }

    void GBCExitButton(){

        GBC.insets = new Insets(5,5,10,5);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.1;
        GBC.weighty = 0.05;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCTitleLabel(){

        GBC.insets = new Insets(20,20,20,20);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.03;
        GBC.weighty = 0.03;
        GBC.fill = GridBagConstraints.VERTICAL;



    }

    void GBCSoloButton(){

        GBC.insets = new Insets(20,50,5,50);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.1;
        GBC.weighty = 0.4;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCVersusButton(){

        GBC.insets = new Insets(20,50,5,50);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.1;
        GBC.weighty = 0.4;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCBackButton1(){
        GBC.insets = new Insets(20,50,5,50);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCSaveButton(){
        GBC.insets = new Insets(20,0,0,20);
        GBC.gridx = 1;
        GBC.gridy = 0;
        GBC.weightx = 0.2;
        GBC.weighty = 0.4;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCResetButton(){
        GBC.insets = new Insets(20,0,0,20);
        GBC.gridx = 1;
        GBC.gridy = 1;
        GBC.weightx = 0.2;
        GBC.weighty = 0.4;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCBackButton2(){
        GBC.gridx = 1;
        GBC.gridy = 2;
        GBC.weightx = 0.2;
        GBC.weighty = 0.2;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCJL(){
        GBC.gridx = 1;
        GBC.gridy = 0;
        GBC.weightx = 0.1;
        GBC.weighty = 0.1;
        GBC.fill = GridBagConstraints.VERTICAL;
    }

    void GBCJLA1(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.1;
        GBC.weighty = 0.5;
        GBC.fill = GridBagConstraints.VERTICAL;
    }

    void GBCJLA2(){
        GBC.gridx = 2;
        GBC.gridy = 0;
        GBC.weightx = 0.1;
        GBC.weighty = 0.5;
        GBC.fill = GridBagConstraints.VERTICAL;
    }





}
