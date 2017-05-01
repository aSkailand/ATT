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
    JButton Back1Button;
    JButton CreditButton;

    JLabel TitleWelcome;
    JLabel TitleHowToPlay;
    JLabel TitleSelect;
    JLabel TitleSettings;
    JTextArea TextArea;
    JLabel CreditText;

    JLabel JLAInMainMenu;
    JLabel JLAInHowToPlay;

    JButton GameModeButton;
    JButton SettingsButton;
    JButton Back2Button;

    JPanel MainPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    JButton SaveButton;
    JButton ResetButton;

    JButton SoloButton;
    JButton VersusButton;
    JButton PlayButton;

    Font JButtonFontSize = new Font("", Font.BOLD, 20);

    Color ButtonColor = new Color(0*65536+0*256+255);//RGB Color system

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
        leftPanel.setBackground(Color.LIGHT_GRAY);
        GBCleftPanel();
        MainPanel.add(leftPanel, GBC);

        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setBackground(Color.GRAY);
        GBCrightPanel();
        MainPanel.add(rightPanel, GBC);

        PlayGameButton = new JButton("Play Game");
        PlayGameButton.setFont(JButtonFontSize);
        PlayGameButton.setBackground(ButtonColor);
        PlayGameButton.addActionListener(c);
        GBCPlayGameButton();
        leftPanel.add(PlayGameButton, GBC);

        HowToPlayButton = new JButton("How To Play");
        HowToPlayButton.setFont(JButtonFontSize);
        HowToPlayButton.setBackground(ButtonColor);
        HowToPlayButton.addActionListener(c);
        GBCHowToPlayButton();
        leftPanel.add(HowToPlayButton,GBC);

        ExitButton = new JButton("Exit Game");
        ExitButton.setFont(JButtonFontSize);
        ExitButton.setBackground(ButtonColor);
        ExitButton.addActionListener(c);
        GBCExitButton();
        leftPanel.add(ExitButton, GBC);

        CreditButton = new JButton("Credits");
        CreditButton.setFont(JButtonFontSize);
        CreditButton.addActionListener(c);
        GBCCreditButton();
        rightPanel.add(CreditButton, GBC);

        TitleWelcome = new JLabel("WELCOME TO CONNECT4!");
        TitleWelcome.setForeground(Color.BLACK);
        TitleWelcome.setFont(new Font("", Font.BOLD, 25));
        GBCTitleWelcome();
        rightPanel.add(TitleWelcome,GBC);


        JLAInMainMenu = new JLabel("");
        GBCJLAInMainMenu();
        leftPanel.add(JLAInMainMenu,GBC);


        this.setVisible(true);



    }


    public void Credits(StartMenuController c){

        TitleWelcome = new JLabel("WELCOME TO CONNECT4!");
        TitleWelcome.setForeground(Color.BLACK);
        TitleWelcome.setFont(new Font("", Font.BOLD, 25));
        GBCTitleWelcome();
        rightPanel.add(TitleWelcome,GBC);

        CreditText = new JLabel("Made by:\n\n"
                                        +"Trong Duc Truong,\n\n"
                                        + "Aslak Frafjord Skailand,\n\n"
                                        + "Thanukan Jegatheeswaran\n");
        CreditText.setFont(new Font("", Font.BOLD,9));
        CreditText.setForeground(Color.BLACK);
        GBCCreditText();
        rightPanel.add(CreditText,GBC);

    }

    public void PlayGame(StartMenuController c){

        GameModeButton = new JButton("Game Mode");
        GameModeButton.setFont(JButtonFontSize);
        GameModeButton.addActionListener(c);
        GBCGameModeButton();
        leftPanel.add(GameModeButton,GBC);

        SettingsButton = new JButton("Settings");
        SettingsButton.setFont(JButtonFontSize);
        SettingsButton.addActionListener(c);
        GBCSettingsButton();
        leftPanel.add(SettingsButton,GBC);

        Back2Button = new JButton("Back");
        Back2Button.setFont(JButtonFontSize);
        Back2Button.addActionListener(c);
        GBCBack2Button();
        leftPanel.add(Back2Button,GBC);

    }

    public void GameMode(StartMenuController c) {

        rightPanel.setBackground(Color.GRAY);

        TitleSelect = new JLabel("Select Gaming Mode");
        TitleSelect.setFont(new Font("", Font.BOLD, 25));
        GBCTitleSelect();
        rightPanel.add(TitleSelect,GBC);

        SoloButton = new JButton("Solo");
        SoloButton.setFont(JButtonFontSize);
        SoloButton.addActionListener(c);
        GBCSoloButton();
        rightPanel.add(SoloButton, GBC);

        VersusButton = new JButton("Versus");
        VersusButton.setFont(JButtonFontSize);
        VersusButton.addActionListener(c);
        GBCVersusButton();
        rightPanel.add(VersusButton, GBC);

        PlayButton = new JButton("Play");
        PlayButton.setFont(JButtonFontSize);
        PlayButton.addActionListener(c);
        GBCPlayButton();
        rightPanel.add(PlayButton,GBC);


    }


    public void HowToPlay(StartMenuController c){

        TitleHowToPlay = new JLabel("How To Play");
        TitleHowToPlay.setForeground(Color.BLACK);
        TitleHowToPlay.setFont(new Font("", Font.BOLD, 25));
        GBCTitleHowToPlay();
        rightPanel.add(TitleHowToPlay,GBC);

        TextArea = new JTextArea();
        TextArea.setPreferredSize(new Dimension(200,200));
        TextArea.setLineWrap(true);
        TextArea.setRows(10);
        GBCTextArea();

        TextArea.setEditable(false);

        String HowToPlayConnect4 = (" Connect4 is a two-player game in which the players\n" +
                " choose a color and then take turns dropping colored\n" +
                " boxes from the top into a seven-colum, six-row grid.\n\n" +
                " The pieces fall straight down, occupying the next\n" +
                " available space within the column. The objectiv of the\n" +
                " game is to be the first to form a horizontal,\n" +
                " vertical, or diagonal line of four of one'a own boxes.\n\n" +
                " Try it out!\n");

        TextArea.setText(HowToPlayConnect4);

        rightPanel.add(TextArea,GBC);


        Back1Button = new JButton("Back");
        Back1Button.setFont(JButtonFontSize);
        Back1Button.addActionListener(c);
        GBCBack1Button();
        leftPanel.add(Back1Button, GBC);

        JLAInHowToPlay = new JLabel("");
        GBCJLAInHowToPlay();
        leftPanel.add(JLAInHowToPlay,GBC);


        leftPanel.setVisible(true);
        rightPanel.setVisible(true);

    }

    public void Settings(StartMenuController c){


        rightPanel.setBackground(Color.GRAY);

        SaveButton = new JButton("Save Game");
        SaveButton.setFont(JButtonFontSize);
        SaveButton.addActionListener(c);
        GBCSaveButton();
        rightPanel.add(SaveButton,GBC);

        ResetButton = new JButton("Reset Game");
        ResetButton.setFont(JButtonFontSize);
        ResetButton.addActionListener(c);
        GBCResetButton();
        rightPanel.add(ResetButton, GBC);

        TitleSettings = new JLabel("Settings");
        TitleSettings.setForeground(Color.BLACK);
        TitleSettings.setFont(new Font("", Font.BOLD, 25));
        GBCTitleSettings();
        rightPanel.add(TitleSettings,GBC);


    }


    public void ExitGame(StartMenuController c){

        setVisible(false);
        dispose();

    }


    public void BackToMenu(StartMenuController c){

        leftPanel.setBackground(Color.GRAY);
        rightPanel.setBackground(Color.GRAY);


        PlayGameButton = new JButton("Play Game");
        PlayGameButton.setFont(JButtonFontSize);
        PlayGameButton.setBackground(ButtonColor);
        PlayGameButton.addActionListener(c);
        GBCPlayGameButton();
        leftPanel.add(PlayGameButton, GBC);

        HowToPlayButton = new JButton("How To Play");
        HowToPlayButton.setFont(JButtonFontSize);
        HowToPlayButton.setBackground(ButtonColor);
        HowToPlayButton.addActionListener(c);
        GBCHowToPlayButton();
        leftPanel.add(HowToPlayButton,GBC);

        ExitButton = new JButton("Exit Game");
        ExitButton.setFont(JButtonFontSize);
        ExitButton.setBackground(ButtonColor);
        ExitButton.addActionListener(c);
        GBCExitButton();
        leftPanel.add(ExitButton, GBC);

        CreditButton = new JButton("Credits");
        CreditButton.setFont(JButtonFontSize);
        CreditButton.addActionListener(c);
        GBCCreditButton();
        rightPanel.add(CreditButton, GBC);

        TitleWelcome = new JLabel("WELCOME TO CONNECT4!");
        TitleWelcome.setForeground(Color.BLACK);
        TitleWelcome.setFont(new Font("", Font.BOLD, 25));
        GBCTitleWelcome();
        rightPanel.add(TitleWelcome,GBC);

        JLAInMainMenu = new JLabel("");
        GBCJLAInMainMenu();
        leftPanel.add(JLAInMainMenu,GBC);

    }






    void GBCleftPanel(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 1;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCrightPanel(){
        GBC.gridx = 1;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weighty = 1;
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

    void GBCJLAInMainMenu(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.VERTICAL;
    }

    void GBCTitleWelcome(){
        GBC.insets = new Insets(20,50,20,10);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.6;
        GBC.weighty = 0.75;
        GBC.fill = GridBagConstraints.VERTICAL;
    }

    void GBCCreditText(){
        GBC.insets = new Insets(100,10,10,10);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCCreditButton(){
        GBC.insets = new Insets(100,320,5,5);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCTitleHowToPlay(){
        GBC.insets = new Insets(20,140,30,10);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weightx = 0.25;
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

    void GBCBack1Button(){
        GBC.insets = new Insets(5,5,10,5);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.4;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCJLAInHowToPlay(){
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 0.75;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCGameModeButton(){
        GBC.insets = new Insets(10,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.4;
        GBC.weighty = 0.20;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCSettingsButton(){
        GBC.insets = new Insets(5,5,5,5);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.4;
        GBC.weighty = 0.20;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCBack2Button(){
        GBC.insets = new Insets(5,5,20,5);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.4;
        GBC.weighty = 0.10;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCTitleSelect(){
        GBC.insets = new Insets(20,100,20,20);
        GBC.gridx = 0;
        GBC.gridy = 0;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCSoloButton(){

        GBC.insets = new Insets(20,40,5,40);
        GBC.gridx = 0;
        GBC.gridy = 1;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCVersusButton(){

        GBC.insets = new Insets(20,40,5,40);
        GBC.gridx = 0;
        GBC.gridy = 2;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCPlayButton(){
        GBC.insets = new Insets(20,140,20,140);
        GBC.gridx = 0;
        GBC.gridy = 3;
        GBC.weightx = 0.6;
        GBC.weighty = 0.25;
        GBC.fill = GridBagConstraints.BOTH;
    }

    void GBCTitleSettings(){
        GBC.insets = new Insets(20,140,20,20);
        GBC.gridx = 0;
        GBC.gridy = 0;
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


}
