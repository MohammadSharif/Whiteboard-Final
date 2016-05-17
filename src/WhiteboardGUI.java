import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Momo on 5/7/16.
 */
public class WhiteboardGUI extends JFrame {
    Integer[][] tableData = new Integer[0][4];
    Canvas canvas;
    public static void main(String[] args) {
        new WhiteboardGUI();
    }

    //This constructs the complete user interface
    public WhiteboardGUI(){
        canvas = new Canvas();
        Controls controls = new Controls();
        this.setPreferredSize(new Dimension(800, 400));
        this.pack();
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(this.getPreferredSize());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);
        this.add(controls, BorderLayout.WEST);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * This class represents the canvas upon which shapes can be drawn and moved around
     */
    private class Canvas extends JPanel{
        ArrayList<DShape> shapes = new ArrayList<DShape>();
        DShape selected;
        public Canvas(){
            this.setPreferredSize(new Dimension(400, 400));
            this.setMinimumSize(this.getPreferredSize());
            this.setBackground(Color.white);
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for(int i = shapes.size() - 1; i >= 0; i--){
                        if(shapes.get(i).getBounds().contains(new Point(e.getX(), e.getY()))){
                            selected = shapes.get(i);
                            System.out.println(shapes.get(i).getClass().getName());
                            break;
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        public void addShape(DShape shape){
            shapes.add(shape);
        }

        public void selectShape(DShape shape){
            selected = shape;
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(DShape shape: shapes){
                g.setColor(shape.getColor());
                shape.draw(g);
            }
        }
    }

    /**
     * This class represents the panel that hold all of the buttons necessary for creating/deleting shapes as well as moving them around
     */
    private class Controls extends JPanel{
        public Controls(){
            ShapeButtons shapeButtons = new ShapeButtons();
            TextStyleButtons textStyleButtons = new TextStyleButtons();
            FrontOrBackButtons frontOrBackButtons = new FrontOrBackButtons();
            JButton setColor = new JButton("Set Color");
            TablePanel tablePanel = new TablePanel();
            JPanel setColorPanel = new JPanel();
            setColorPanel.add(setColor);


            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setColor.addActionListener(new ListenForButton());

            this.setSize(this.getPreferredSize());
            this.add(shapeButtons);
            this.add(setColorPanel);
            this.add(textStyleButtons);
            this.add(frontOrBackButtons);
            this.add(tablePanel);



        }
    }

    /**
     * This is the panel that holds the buttons for selecting different shapes
     */
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

        }
    }

    /**
     * This is the panel that allows for styling of the text, font and content
     */
    private class TextStyleButtons extends JPanel{
        public TextStyleButtons(){
            this.setLayout(new FlowLayout());
            this.add(new TextField("Whiteboard!", 15));
            this.add(new JButton("Temp Placeholder"));

        }
    }

    /**
     * This is the panel that holds the buttons that can move the shape to the front, back, or delete it
     */
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

    /**
     * This is the panel at the bottom of the controls which will hold a table of the coordinates of shapes and their sizes
     */
    private class TablePanel extends JPanel{
        public TablePanel(){
            this.setLayout(new BorderLayout());

            String[] names = {"X", "Y", "Width", "Height"};
//            DefaultTableModel dataModel = new DefaultTableModel() {
//                public int getColumnCount() { return 4; }
//                public int getRowCount() { return 10;}
//                public Object getValueAt(int row, int col) { return null; }
//
//
//                @Override
//                public String getColumnName(int index) {
//                    return names[index];
//                }
//            };
            JTable table = new JTable(tableData, names);
            TableCellRenderer rendererFromHeader = table.getTableHeader().getDefaultRenderer();
            JLabel headerLabel = (JLabel) rendererFromHeader;
            headerLabel.setHorizontalAlignment(JLabel.CENTER);
            JScrollPane tableScroll = new JScrollPane(table);
            tableScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.add(tableScroll);
            this.setPreferredSize(new Dimension(400, 300));
            this.setMaximumSize(this.getPreferredSize());
        }


    }


    /**
     * This class is a listener that will know which button is clicked and execute the proper function
     **/
    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Random randomGenerator = new Random();
            JButton clicked = (JButton)e.getSource();
            if(clicked.getText().equals("Rect")){
                DRect rect = new DRect();
                rect.setX(randomGenerator.nextInt(300));
                rect.setY(randomGenerator.nextInt(300));
                rect.setHeight(randomGenerator.nextInt(200));
                rect.setWidth(randomGenerator.nextInt(200));
                WhiteboardGUI.this.canvas.addShape(rect);
                WhiteboardGUI.this.repaint();
                WhiteboardGUI.this.revalidate();
            } else if(clicked.getText().equals("Oval")){
                DOval oval = new DOval();
                oval.setX(randomGenerator.nextInt(300));
                oval.setY(randomGenerator.nextInt(300));
                oval.setHeight(randomGenerator.nextInt(200));
                oval.setWidth(randomGenerator.nextInt(200));
                WhiteboardGUI.this.canvas.addShape(oval);
                WhiteboardGUI.this.repaint();
                WhiteboardGUI.this.revalidate();
            }

        }

    }
}
