import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aslak on 03.04.17.
 */
public class Controller implements ActionListener{
    GameFrame gameFrame;


    public Controller(){
        gameFrame = new GameFrame();
        gameFrame.button1.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
