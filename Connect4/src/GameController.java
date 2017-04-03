import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by TrongDT on 03/04/2017.
 */
public class GameController implements ActionListener{

    GameBoard GB = new GameBoard(this);

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e.getActionCommand());

    }
}
