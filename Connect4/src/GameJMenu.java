import javax.swing.*;

/**
 * Created by aslak on 18.04.17.
 */
public class GameJMenu extends JMenuBar {

    //JMenu items
    JMenu fileMenu = new JMenu("File");
    JMenuItem mainMenu = new JMenuItem("Main menu");
    JMenuItem highscore = new JMenuItem("Highscore");
    JMenuItem saveGame = new JMenuItem("Save game");
    JMenuItem openGame = new JMenuItem("Open game");
    JMenuItem pauseGame = new JMenuItem("Pause game");
    JMenuItem restartGame = new JMenuItem("Restart game");

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
        fileMenu.addSeparator();
        fileMenu.add(saveGame);
        fileMenu.add(openGame);
        fileMenu.addSeparator();
        fileMenu.add(pauseGame);
        fileMenu.add(restartGame);

        // addActionListener and setActionListener setup
        mainMenu.addActionListener(controller);
        highscore.addActionListener(controller);
        saveGame.addActionListener(controller);
        openGame.addActionListener(controller);
        pauseGame.addActionListener(controller);
        restartGame.addActionListener(controller);

        mainMenu.setActionCommand("mainMenu");
        highscore.setActionCommand("highscore");
        saveGame.setActionCommand("saveGame");
        openGame.setActionCommand("openGame");
        pauseGame.setActionCommand("pauseGame");
        restartGame.setActionCommand("restartGame");
    }
}
