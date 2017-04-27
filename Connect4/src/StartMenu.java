import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * Created by Thanu on 05.04.2017.
 */


public class StartMenu extends JFrame{


    JButton PlayGameButton;
    JButton HowToPlayButton;
    JButton ExitButton;
    JButton Back1;
    JButton CreditButton;


    JLabel TitleWelcome;
    JLabel TitleHowToPlay;
    JLabel TitleSelect;
    JLabel TitleSettings;
    JTextArea TextArea;
    JTextArea CreditsText;

    JLabel JLABEL;
    JLabel jlabel;
    
    JButton StartGame;
    JButton GameMode;
    JButton Settings;
    JButton Back2;

    JPanel MainPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    JButton SaveButton;
    JButton ResetButton;


    JButton SoloButton;
    JButton VersusButton;
    JButton PlayButton;



    GridBagConstraints GBC = new GridBagConstraints();
    

    public StartMenu(StartMenuController c){


        //Jobber på JFrame
        this.setSize(600,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        //Jobber på MainPanel
        MainPanel.setLayout(new GridBagLayout());
        this.add(MainPanel);

        //Legger til panel over main, jobber ikke på main panel lenger
        //Jobber på leftPanel og rightPanel
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(Color.CYAN);
        GBCleftPanel();
        MainPanel.add(leftPanel, GBC);

        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.RED);
        GBCrightPanel();
        MainPanel.add(rightPanel, GBC);


        PlayGameButton = new JButton("Play Game");
        PlayGameButton.addActionListener(c);
        GBCPlayGameButton();
        leftPanel.add(PlayGameButton, GBC);

        HowToPlayButton = new JButton("How To Play");
        HowToPlayButton.addActionListener(c);
        GBCHowToPlayButton();
        leftPanel.add(HowToPlayButton,GBC);

        ExitButton = new JButton("Exit Game");
        ExitButton.addActionListener(c);
        GBCExitButton();
        leftPanel.add(ExitButton, GBC);

        CreditButton = new JButton("Credits");
        CreditButton.addActionListener(c);
        GBCCreditButton();
        rightPanel.add(CreditButton, GBC);

        TitleWelcome = new JLabel("WELCOME TO CONNECT4!");
        TitleWelcome.setForeground(Color.BLACK);
        TitleWelcome.setFont(new Font("", Font.BOLD, 22));
        GBCTitleWelcome();
        rightPanel.add(TitleWelcome,GBC);


        JLABEL = new JLabel("");
        GBCJLABEL();
        leftPanel.add(JLABEL,GBC);






        this.setVisible(true);



    }


    public void Credits(StartMenuController c){

        TitleWelcome = new JLabel("WELCOME TO CONNECT4!");
        TitleWelcome.setForeground(Color.BLACK);
        TitleWelcome.setFont(new Font("", Font.BOLD, 22));
        GBCTitleWelcome();
        rightPanel.add(TitleWelcome,GBC);

        CreditsText = new JTextArea();
        CreditsText.setPreferredSize(new Dimension(50,50));
        GBCCreditText();
        CreditsText.setEditable(false);
        CreditsText.setText("  Made by:\n\n"
                            + "  Trong\n"
                            + "  Aslak\n"
                            + "  Thanu\n");
        rightPanel.add(CreditsText,GBC);


    }

    public void PlayGame(StartMenuController c){




        StartGame = new JButton("Start Game");
        StartGame.addActionListener(c);
        GBCStartGame();
        leftPanel.add(StartGame,GBC);

        GameMode = new JButton("Game Mode");
        GameMode.addActionListener(c);
        GBCGameMode();
        leftPanel.add(GameMode,GBC);

        Settings = new JButton("Settings");
        Settings.addActionListener(c);
        GBCSettings();
        leftPanel.add(Settings,GBC);

        Back2 = new JButton("Back"); 
        Back2.addActionListener(c);  
        GBCBack2();                  
        leftPanel.add(Back2,GBC);

    }

    public void GameMode(StartMenuController c) {

        rightPanel.setBackground(Color.RED);

        TitleSelect = new JLabel("Select Gaming Mode");
        TitleSelect.setFont(new Font("",Font.BOLD, 22));
        GBCTitleSelect();
        rightPanel.add(TitleSelect,GBC);

        SoloButton = new JButton("Solo");
        SoloButton.addActionListener(c);
        GBCSoloButton();
        rightPanel.add(SoloButton, GBC);

        VersusButton = new JButton("Versus");
        VersusButton.addActionListener(c);
        GBCVersusButton();
        rightPanel.add(VersusButton, GBC);

        PlayButton = new JButton("Play");
        PlayButton.addActionListener(c);
        GBCPlayButton();
        rightPanel.add(PlayButton,GBC);


    }


    public void HowToPlay(StartMenuController c){



        TitleHowToPlay = new JLabel("How To Play");
        TitleHowToPlay.setForeground(Color.BLACK);
        TitleHowToPlay.setFont(new Font("", Font.BOLD, 22));
        GBCTitleHowToPlay();
        rightPanel.add(TitleHowToPlay,GBC);

        TextArea = new JTextArea();
        TextArea.setPreferredSize(new Dimension(200,200));
        GBCTextArea();
        //TextArea.setLineWrap(true);
        TextArea.setEditable(false);
        TextArea.setText(" This is how you play Connect4.\n\n"
                            +" Choose your level. \n\n"
                            + ""

                            + " Play!\n");

        rightPanel.add(TextArea,GBC);


        Back1 = new JButton("Back");
        Back1.addActionListener(c);
        GBCBack1();
        leftPanel.add(Back1, GBC);

        jlabel = new JLabel("");
        GBCjlabel();
        leftPanel.add(jlabel,GBC);


        leftPanel.setVisible(true);
        rightPanel.setVisible(true);



    }




    public void Settings(StartMenuController c){


        rightPanel.setBackground(Color.GREEN);

        SaveButton = new JButton("Save Game");
        SaveButton.addActionListener(c);
        GBCSaveButton();
        rightPanel.add(SaveButton,GBC);

        ResetButton = new JButton("Reset Game");
        ResetButton.addActionListener(c);
        GBCResetButton();
        rightPanel.add(ResetButton, GBC);

        TitleSettings = new JLabel("Settings");
        TitleSettings.setForeground(Color.BLACK);
        TitleSettings.setFont(new Font("", Font.BOLD, 22));
        GBCTitleSettings();
        rightPanel.add(TitleSettings,GBC);


        leftPanel.setVisible(true);
        rightPanel.setVisible(true);


    }




    public void ExitGame(StartMenuController c){

        setVisible(false);
        dispose();

    }

    public void BackToMenu(StartMenuController c){

        leftPanel.setBackground(Color.CYAN);
        rightPanel.setBackground(Color.RED);


        PlayGameButton = new JButton("Play Game");
        PlayGameButton.addActionListener(c);
        GBCPlayGameButton();
        leftPanel.add(PlayGameButton, GBC);

        HowToPlayButton = new JButton("How To Play");
        HowToPlayButton.addActionListener(c);
        GBCHowToPlayButton();
        leftPanel.add(HowToPlayButton,GBC);

        ExitButton = new JButton("Exit Game");
        ExitButton.addActionListener(c);
        GBCExitButton();
        leftPanel.add(ExitButton, GBC);

        CreditButton = new JButton("Credits");
        GBCCreditButton();
        rightPanel.add(CreditButton, GBC);

        TitleWelcome = new JLabel("WELCOME TO CONNECT4!");
        TitleWelcome.setForeground(Color.BLACK);
        TitleWelcome.setFont(new Font("", Font.BOLD, 22));
        GBCTitleWelcome();
        rightPanel.add(TitleWelcome,GBC);

        JLABEL = new JLabel("");
        GBCJLABEL();
        leftPanel.add(JLABEL,GBC);




    }




    void GBCleftPanel(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 1.0;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCrightPanel(){
        GBC.gridx = 1;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weighty = 1.0;
        GBC.fill = GridBagConstraints.BOTH;
    }




    void GBCPlayGameButton(){

        GBC.insets = new Insets(10,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;


    }

    void GBCHowToPlayButton(){

        GBC.insets = new Insets(5,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;


    }

    void GBCExitButton(){

        GBC.insets = new Insets(5,5,30,5);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCTitleWelcome(){

        GBC.insets = new Insets(20,50,20,10);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.6;
        GBC.weighty = 1.0;
        GBC.fill = GridBagConstraints.VERTICAL;



    }

    void GBCTitleHowToPlay(){
        GBC.insets = new Insets(20,20,30,10);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weightx = 0.25;
        GBC.fill = GridBagConstraints.VERTICAL;


    }

    void GBCBack1(){
        GBC.insets = new Insets(5,5,10,5);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;


    }

    void GBCStartGame(){
        GBC.insets = new Insets(10,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCGameMode(){
        GBC.insets = new Insets(5,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCSettings(){
        GBC.insets = new Insets(5,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCBack2(){
        GBC.insets = new Insets(5,5,10,5);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCTextArea(){
        GBC.insets = new Insets(20,20,20,20);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.6;
        GBC.weighty = 0.75;
        GBC.fill = GridBagConstraints.BOTH;



    }

    void GBCCreditText(){
        GBC.insets = new Insets(20,20,20,20);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCSoloButton(){

        GBC.insets = new Insets(20,20,5,50);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCVersusButton(){

        GBC.insets = new Insets(20,20,5,50);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCTitleSelect(){
        GBC.insets = new Insets(20,20,20,20);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCPlayButton(){
        GBC.insets = new Insets(20,110,20,140);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;

    }

    void GBCSaveButton(){
        GBC.insets = new Insets(20,20,0,50);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCResetButton(){
        GBC.insets = new Insets(20,20,20,50);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCTitleSettings(){
        GBC.insets = new Insets(20,200,20,20);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCCreditButton(){
        GBC.insets = new Insets(100,350,5,5);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.6;
        GBC.weighty = 0.20;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCJLABEL(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.VERTICAL;
    }

    void GBCjlabel(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 0.75;
        GBC.fill = GridBagConstraints.VERTICAL;



    }





}
