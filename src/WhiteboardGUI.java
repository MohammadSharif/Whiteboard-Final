import javax.swing.*;
import java.awt.*;

/**
 * Created by Momo on 5/7/16.
 */
public class WhiteboardGUI extends JFrame {
    public static void main(String[] args) {
        new WhiteboardGUI();
    }

    public WhiteboardGUI(){
        this.setPreferredSize(new Dimension(800, 400));
        this.setSize(this.getPreferredSize());
        this.setLocationRelativeTo(null);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
