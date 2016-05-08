import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Momo on 5/7/16.
 */
public class WhiteboardGUI extends JFrame {
    Integer[][] tableData = new Integer[0][4];
    public static void main(String[] args) {
        new WhiteboardGUI();
    }

    public WhiteboardGUI(){
        Canvas canvas = new Canvas();
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

    private class Canvas extends JPanel{
        public Canvas(){
            this.setPreferredSize(new Dimension(400, 400));
            this.setMinimumSize(this.getPreferredSize());
            this.setBackground(Color.white);
        }
    }

    private class Controls extends JPanel{
        public Controls(){
            ShapeButtons shapeButtons = new ShapeButtons();
            TextStyleButtons textStyleButtons = new TextStyleButtons();
            FrontOrBackButtons frontOrBackButtons = new FrontOrBackButtons();
            JButton setColor = new JButton("Set Color");
            TablePanel tablePanel = new TablePanel();
            JPanel setColorPanel = new JPanel();
            setColorPanel.add(setColor);

            frontOrBackButtons.setAlignmentX(CENTER_ALIGNMENT);
            textStyleButtons.setAlignmentX(CENTER_ALIGNMENT);
            shapeButtons.setAlignmentX(CENTER_ALIGNMENT);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setColor.setAlignmentX(CENTER_ALIGNMENT);
            setColor.addActionListener(new ListenForButton());

            this.setSize(this.getPreferredSize());
            this.add(shapeButtons);
            this.add(setColorPanel);
            this.add(textStyleButtons);
            this.add(frontOrBackButtons);
            this.add(tablePanel);


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

        }
    }

    private class TextStyleButtons extends JPanel{
        public TextStyleButtons(){
            this.setLayout(new FlowLayout());
            this.add(new TextField("Whiteboard!", 15));
            this.add(new JButton("Temp Placeholder"));
//            this.setBackground(controlBackground);

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



    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }
}
