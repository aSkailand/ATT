import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Trong on 12/04/2017.
 */

/**
 *  Temporary Description: Adding buttons to a JPanel, which is placed on Top Panel of GameBodyFrame
 */
public class GameOptionPanel extends JPanel{

    private JPanel idlePanel;
    private JPanel optionPanel;
    private JPanel castSpellPanel;

    ArrayList<JButton> optionList = new ArrayList<>();

    GameBoardController gameBoardController;
    GameBoardModel gameBoardModel;

    GameOptionPanel(GameBoardController gameBoardController){

        this.gameBoardController = gameBoardController;
        gameBoardModel = gameBoardController.gameBoardModel;

        // Main JPanel Setup
        this.setLayout(new BorderLayout());

        // Initialize JPanels
        idlePanel = initializeIdlePanel();
        optionPanel = initializeOptionPanel(gameBoardController);
        castSpellPanel = initializeCastSpellPanel(gameBoardController);

        // Add panel to
        switchToIdlePanel();


    }

    /* Panel Switcher Methods */

    void switchToIdlePanel(){
        this.removeAll();
        idlePanel.setBackground(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()).darker());
        this.add(idlePanel);
    }

    void switchToOptionPanel(){
        this.removeAll();

        optionPanel.setBackground(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()).darker());

        for(JButton button : optionList){
            button.setBackground((gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer())));
        }

        this.add(optionPanel);
    }

    void switchToCastSpellPanel(){
        this.removeAll();
        castSpellPanel.setBackground(gameBoardModel.getPlayerColor(gameBoardModel.getCurrentPlayer()).darker());
        this.add(castSpellPanel);
    }

    /* Panel Initialize Methods */

    private JPanel initializeIdlePanel(){

        JPanel tempIdle = new JPanel();
        tempIdle.setLayout(new BorderLayout());


        JLabel label = new JLabel("Choose Action");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.white);

        tempIdle.add(label);

        return tempIdle;
    }

    private JPanel initializeOptionPanel(GameBoardController gameBoardController){
        // Option Panel Setup
        JPanel optionPanel = new JPanel();
        optionPanel.setBackground(Color.darkGray);

        optionPanel.setLayout(new GridBagLayout());

        // Adding buttons
        for (int i = 0; i < GameBoardModel.numCol; i++) {

            JButton tempButton = new JButton();
            tempButton.setText("[ "+i+" ]");
            tempButton.setActionCommand(""+i);
            tempButton.setBorderPainted(false);
            tempButton.addActionListener(gameBoardController);

            optionList.add(tempButton);
            optionPanel.add(tempButton, optionButtonGBC(i));
        }
        return optionPanel;
    }

    private JPanel initializeCastSpellPanel(GameBoardController gameBoardController){
        JPanel magicCastPanel = new JPanel();
        magicCastPanel.setLayout(new BorderLayout());

        JButton castSpellButton = new JButton("Cast Spell");
        magicCastPanel.add(castSpellButton);

        return magicCastPanel;
    }

    private GridBagConstraints optionButtonGBC(int x){
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = x;
        gbc.gridy = 0;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.ipadx = 0;
        gbc.ipady = 0;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.PAGE_END;

        return gbc;

    }

}
