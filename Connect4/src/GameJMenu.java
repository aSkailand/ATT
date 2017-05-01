import javax.swing.*;

/**
 * Created by aslak on 18.04.17.
 */
public class GameJMenu extends JMenuBar {

    //JMenu items
    JMenu fileMenu = new JMenu("File");
    JMenuItem mainMenu = new JMenuItem("Main menu");
    JMenuItem highscore = new JMenuItem("Highscore");

    /**
     * This is the JMenuBar
     *
     * @param controller
     */
    public GameJMenu(GameJMenuController controller) {

        //JMenu setup
        this.add(fileMenu);
        fileMenu.add(mainMenu);
        fileMenu.addSeparator();
        fileMenu.add(highscore);

        // addActionListener and setActionListener setup
        mainMenu.addActionListener(controller);
        highscore.addActionListener(controller);

        mainMenu.setActionCommand("mainMenu");
        highscore.setActionCommand("highscore");
    }
}
