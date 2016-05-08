import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            ShapeButtons shapeButtons = new ShapeButtons();
            TextStyleButtons textStyleButtons = new TextStyleButtons();
            FrontOrBackButtons frontOrBackButtons = new FrontOrBackButtons();
            frontOrBackButtons.setAlignmentX(CENTER_ALIGNMENT);
            textStyleButtons.setAlignmentX(CENTER_ALIGNMENT);
            shapeButtons.setAlignmentX(CENTER_ALIGNMENT);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JButton setColor = new JButton("Set Color");
            setColor.setAlignmentX(CENTER_ALIGNMENT);
            setColor.addActionListener(new ListenForButton());

            this.setPreferredSize(new Dimension(400, 400));
            this.setSize(this.getPreferredSize());
            this.setBackground(Color.black);
            this.add(shapeButtons);
            this.add(setColor);
            this.add(textStyleButtons);
            this.add(frontOrBackButtons);


        }
    }

    private class ShapeButtons extends JPanel{
        public ShapeButtons(){
            ListenForButton listenForButton = new ListenForButton();
            JButton[] buttons = {new JButton("Rect"), new JButton("Oval"), new JButton("Line"), new JButton("Text")};
            this.setLayout(new FlowLayout());
            this.add(new JLabel("Add"));
            for(JButton button: buttons){
                this.add(button);
                button.addActionListener(listenForButton);
            }
            this.setPreferredSize(new Dimension(400, 10));
            this.setSize(this.getPreferredSize());
        }
    }

    private class TextStyleButtons extends JPanel{
        public TextStyleButtons(){
            this.setLayout(new FlowLayout());
            this.add(new TextField("Whiteboard!", 15));
            this.add(new JButton("Temp Placeholder"));
        }
    }

    private class FrontOrBackButtons extends JPanel{
        public FrontOrBackButtons(){
            JButton toFront = new JButton("Move To Front");
            JButton toBack = new JButton("Move To Back");
            JButton remove = new JButton("Remove Shape");
            toFront.addActionListener(new ListenForButton());
            toBack.addActionListener(new ListenForButton());
            remove.addActionListener(new ListenForButton());
            this.setLayout(new FlowLayout());
            this.add(toFront);
            this.add(toBack);
            this.add(remove);

        }
    }



    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
