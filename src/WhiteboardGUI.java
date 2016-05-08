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
        Canvas canvas = new Canvas();
        Controls controls = new Controls();
        this.setPreferredSize(new Dimension(800, 400));
        this.setSize(this.getPreferredSize());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(controls, BorderLayout.WEST);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class Canvas extends JPanel{
        public Canvas(){
            this.setPreferredSize(new Dimension(400, 400));
            this.setSize(this.getPreferredSize());
        }
    }

    private class Controls extends JPanel{
        public Controls(){
            this.setPreferredSize(new Dimension(400, 400));
            this.setSize(this.getPreferredSize());
            this.setBackground(Color.black);
        }
    }
}
