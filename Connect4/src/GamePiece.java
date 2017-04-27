import javax.swing.*;
import java.awt.*;

/**
 * Created by Trong on 23/04/2017.
 */
abstract class GamePiece extends JButton{

    GamePiece() {
        this.setBorderPainted(false);
        this.setForeground(Color.WHITE);
        this.setPreferredSize(new Dimension(50, 50));
        this.setMargin(new Insets(0,0,0,0)); // Make text fit button better

    }
}
