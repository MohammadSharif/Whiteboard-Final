

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Momo on 5/7/16.
 */
public class WhiteboardGUI extends JFrame{
    Integer[][] tableData;
    Canvas canvas;
    TextStyleButtons textStyleButtons;
    OurTableModel ourTableModel;
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
    private class Canvas extends JPanel implements ModelListener{
        ArrayList<DShape> shapes = new ArrayList<DShape>();
        DShape selected;
        public Canvas(){
            this.setPreferredSize(new Dimension(400, 400));
            this.setMinimumSize(this.getPreferredSize());
            this.setBackground(Color.white);
            DragListener dragListener = new DragListener();
            this.addMouseListener(dragListener);
            this.addMouseMotionListener(dragListener);
        }

        public void moveToFront(){
            shapes.remove(selected);
            shapes.add(selected);
            WhiteboardGUI.this.repaint();
            WhiteboardGUI.this.revalidate();
        }

        public void moveToBack(){
            shapes.remove(selected);
            shapes.add(0, selected);
            WhiteboardGUI.this.repaint();
            WhiteboardGUI.this.revalidate();
        }

        public void addShape(DShape shape){
            shapes.add(shape);
            shape.model.addListener(canvas);
            selected = shape;
            tableData = new Integer[shapes.size()][4];

            WhiteboardGUI.this.repaint();
            WhiteboardGUI.this.revalidate();
        }

        public void deleteShape(){
//            ourTableModel.removed(selected);
            selected.model.removeListener(canvas);
            selected.model.removeListener(ourTableModel);
            shapes.remove(selected);
            selected = null;
//            tableData = new Integer[shapes.size()][4];
            WhiteboardGUI.this.repaint();
            WhiteboardGUI.this.revalidate();
        }

        public void deleteAllShapes(){
            shapes = new ArrayList<>();
        }

        public void saveAsImage(){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Save your file");
            int userSelection = jFileChooser.showSaveDialog(WhiteboardGUI.this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = jFileChooser.getSelectedFile();
                    BufferedImage image = (BufferedImage) createImage(canvas.getWidth(), canvas.getHeight());
                    Graphics g = image.getGraphics();
                    paintAll(g);
                    g.dispose();
                    javax.imageio.ImageIO.write(image, "PNG", file);
                } catch(Exception exc){
                    exc.printStackTrace();
                }
            }
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
            if(selected == null){
                textStyleButtons.disablePanel();
            }
            else if(selected.getClass().isInstance(new DText())){
                textStyleButtons.enablePanel();
            } else{
                textStyleButtons.disablePanel();
            }

        }

        @Override
        public void modelChanged(DShapeModel model) {
            WhiteboardGUI.this.repaint();
            WhiteboardGUI.this.revalidate();
        }
    }

    /**
     * This class is key to providing a large variety of the functionalities that happen on the canvas,
     * this listener listens for clicks on the canvas and moves shapes around if they have been clicked, or resizes them if
     * the knob has been clicked.
     */

    private class DragListener implements MouseListener, MouseMotionListener{

        int x = 0;
        int y = 0;
        int pressPointX;
        int pressPointY;
        Point movingPoint;
        Point anchorPoint;
        boolean move = false;
        boolean resize = false;


        int p1X = 0;
        int p1Y = 0;
        int p2X = 0;
        int p2Y = 0;


        @Override
        public void mouseClicked(MouseEvent e) {
            //This can check if a shape was clicked, if it was it gets stored as the selected shape in the canvas
            //It also will pick the topmost shape if two shapes overlap
            for(int i = canvas.shapes.size() - 1; i >= 0; i--){
                if(canvas.shapes.get(i).getBounds().contains(new Point(e.getX(), e.getY()))){
    //                if(canvas.selected != null){
    //                    canvas.selected.setColor(canvas.selected.getColor());
    //                }
                    canvas.selectShape(canvas.shapes.get(i));
                    System.out.println(canvas.shapes.get(i).getClass().getName());
                    x = canvas.selected.getX();
                    y = canvas.selected.getY();
                    if(canvas.selected.getClass().isInstance(new DLine())){
                        DLine temp = (DLine)canvas.selected;
                        DLineModel tempModel = (DLineModel)temp.model;
                        p1X = (int)temp.getP1X();
                        p1Y = (int)temp.getP1Y();
                        p2X = (int)temp.getP2X();
                        p2Y = (int)temp.getP2Y();
                    }
                    move = true;
                    break;
                }
            }
            Point[] knobs = canvas.selected.getKnobs();
            //When a knob is clicked it indicates that the shape will be resized
            //This gets the knob that was clicked and stores it as a moving knob, while it's diagonal
            //Is going to be treated as the anchor
            for(Point knob: knobs) {
                System.out.println(e.getX() + " " + e.getY());
                Rectangle temp = new Rectangle(knob, new Dimension(9, 9));
                if(knobs.length == 4) {
                    if (temp.getBounds().contains(new Point(e.getX(), e.getY()))) {
                        System.out.println("Resize");
                        movingPoint = knob;
                        if (knob.equals(knobs[0])) {
                            anchorPoint = knobs[3];
                        } else if (knob.equals(knobs[1])) {
                            anchorPoint = knobs[2];
                        } else if (knob.equals(knobs[3])) {
                            anchorPoint = knobs[0];
                        } else {
                            anchorPoint = knobs[1];
                        }
                        System.out.println(movingPoint);
                        System.out.println(anchorPoint);
                        move = false;
                        resize = true;
                        break;
                    }
                } else {
                    if(temp.getBounds().contains(new Point(e.getX(), e.getY()))) {
                        movingPoint = knob;
                        System.out.println("RESIZE");
                        if (knob.equals(knobs[0])) {
                            anchorPoint = knobs[1];
                        } else {
                            anchorPoint = knobs[0];
                        }
                        move = false;
                        resize = true;
                        break;
                    }
                }
            }


    }

        @Override
        public void mousePressed(MouseEvent e) {
            pressPointX = e.getX();
            pressPointY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            anchorPoint = null;
            movingPoint = null;
            move = false;
            resize = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

    }

        @Override
        public void mouseExited(MouseEvent e) {

    }

        @Override
        public void mouseDragged(MouseEvent e) {

//                if (e.getX() > x) {
//                    xDif = e.getX() - x;
//                    canvas.selected.setX(canvas.selected.getX() + xDif);
//
//                } else if (e.getY() > y) {
//                    yDif = e.getY() - y;
//                    canvas.selected.setY(canvas.selected.getY() + yDif);
//
//                } else {
//                    xDif = x - e.getX();
//                    yDif = y - e.getY();
//                    canvas.selected.setX(canvas.selected.getX() - xDif);
//                    canvas.selected.setY(canvas.selected.getY() - yDif);
//
//                }

            //If the shape was clicked and needs to be moved this will move the shape around the canvas as well as the knobs
            if(move) {
//                System.out.println("MOVE");
                if(canvas.selected.getClass().isInstance(new DLine())){
                    DLine temp = (DLine)canvas.selected;
                    temp.move(pressPointX, pressPointY, e, p1X, p1Y, p2X, p2Y);

                } else {
                    canvas.selected.move(pressPointX, pressPointY, e, x, y);
                }

            }
            //If a knob was clicked, this code takes care of properly resizing the shape
            if(resize) {
                canvas.selected.resize(movingPoint, anchorPoint, e);
            }


        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    /**
     * This class represents the panel that hold all of the buttons necessary for creating/deleting shapes as well as moving them around
     */
    private class Controls extends JPanel{
        public Controls(){
            ShapeButtons shapeButtons = new ShapeButtons();
            textStyleButtons = new TextStyleButtons();
            FrontOrBackButtons frontOrBackButtons = new FrontOrBackButtons();
            JButton setColor = new JButton("Set Color");
            TablePanel tablePanel = new TablePanel();
            JPanel setColorPanel = new JPanel();
            setColorPanel.add(setColor);
            SaveAndOpen saveAndOpen = new SaveAndOpen();



            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setColor.addActionListener(new ListenForButton());

            this.setSize(this.getPreferredSize());
            this.add(shapeButtons);
            this.add(setColorPanel);
            this.add(textStyleButtons);
            this.add(frontOrBackButtons);
            this.add(tablePanel);
            this.add(saveAndOpen);


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
        JComboBox<String> fontDropDown;
        TextField textField;
        public TextStyleButtons(){
            String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            this.setLayout(new FlowLayout());
            textField = new TextField("Hello", 15);
            textField.setMaximumSize(textField.getSize());
            textField.setMaximumSize(new Dimension());
            textField.addKeyListener(new KeyListener() {


                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    DText temp = (DText)canvas.selected;
                    temp.setText(textField.getText());
                    temp.model.notifyListeners();
                }

            });


            this.add(textField);

            fontDropDown = new JComboBox<String>(fonts);
            fontDropDown.setSelectedItem("Dialog");
            this.add(fontDropDown);

        }

        public String getFontString(){
            return (String)fontDropDown.getSelectedItem();
        }

        public String getMessageText(){
            return textField.getText();
        }

        public void enablePanel(){
            DText temp = null;
            if(new DText().getClass().isInstance(canvas.selected)) {
               temp =(DText) canvas.selected;
                textField.setText(temp.getText());
                fontDropDown.setSelectedItem(temp.getFont());
                fontDropDown.setEnabled(true);
                textField.setEnabled(true);
            }

        }

        public void disablePanel(){
            textField.setText("Hello");
            fontDropDown.setSelectedItem("Dialog");
            fontDropDown.setEnabled(false);
            textField.setEnabled(false);
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


        JTable table;
        String[] names;

        public TablePanel(){
            ourTableModel = new OurTableModel(this);
            this.setLayout(new BorderLayout());
            names = new String[]{"X", "Y", "Width", "Height"};

            tableData = new Integer[4][4];

            table = new JTable(ourTableModel.ourData, names);
            table.setModel(ourTableModel);

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

    private class OurTableModel extends AbstractTableModel implements ModelListener{
        private Integer[][] ourData;
        TablePanel tablePanel;
        public OurTableModel(TablePanel tablePanel){
            this.tablePanel = tablePanel;
            ourData = new Integer[0][4];
        }

        @Override
        public int getRowCount() {
            return canvas.shapes.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

                DShape temp = canvas.shapes.get(canvas.shapes.size() - rowIndex - 1);
                if (columnIndex == 0) {
                    return temp.getX();
                } else if (columnIndex == 1) {
                    return temp.getY();
                } else if (columnIndex == 2) {
                    return temp.getWidth();
                } else {
                    return temp.getHeight();
                }

        }

        public void fillTable(){
            ourData = new Integer[getRowCount()][getColumnCount()];
            for(int i = 0; i < getRowCount(); i++){
                for(int j = 0; j < getColumnCount(); j++) {
                    ourData[i][j] = (int)getValueAt(i, j);
                }
            }
        }

        public void removed(){
            fillTable();
            fireTableDataChanged();
        }

        public void added(){
            fillTable();
            fireTableDataChanged();
        }

        @Override
        public void modelChanged(DShapeModel model) {
            int index = 0;
            for(DShape shape: canvas.shapes){
                if(shape.model.equals(model)){
                    index = canvas.shapes.indexOf(shape);
                }
            }
            fireTableRowsUpdated(index, index);

        }
    }



    private class SaveAndOpen extends JPanel{
        public SaveAndOpen(){
            this.setLayout(new FlowLayout());
            JButton save = new JButton("Save");
            save.addActionListener(new ListenForButton());
            JButton open = new JButton("Open");
            open.addActionListener(new ListenForButton());
            JButton saveImage = new JButton("Save Image");
            saveImage.addActionListener(new ListenForButton());
            this.add(save);
            this.add(open);
            this.add(saveImage);
        }
    }


    /**
     * This class is a listener that will know which button is clicked and execute the proper function
     **/
    private class ListenForButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked = (JButton)e.getSource();
            String text = clicked.getText();
            if(text.equals("Rect")){
                DRect rect = new DRect();
                rect.setX(10);
                rect.setY(10);
                rect.setHeight(20);
                rect.setWidth(20);
                rect.model.addListener(canvas);
                rect.model.addListener(ourTableModel);
                WhiteboardGUI.this.canvas.addShape(rect);
                WhiteboardGUI.this.ourTableModel.added();
            } else if(text.equals("Oval")){
                DOval oval = new DOval();
                oval.setX(10);
                oval.setY(10);
                oval.setHeight(20);
                oval.setWidth(20);
                oval.model.addListener(canvas);
                oval.model.addListener(ourTableModel);

                WhiteboardGUI.this.canvas.addShape(oval);
                WhiteboardGUI.this.ourTableModel.added();

            } else if(text.equals("Line")){
                DLine line = new DLine();
                line.setX(10);
                line.setY(10);
                line.setHeight(0);
                line.setWidth(20);
                line.setPoints();
                line.model.addListener(canvas);
                line.model.addListener(ourTableModel);
                WhiteboardGUI.this.canvas.addShape(line);
                WhiteboardGUI.this.ourTableModel.added();

            } else if(text.equals("Text")){
                DText dText = new DText();
                dText.setX(10);
                dText.setY(10);
                dText.setHeight(30);
                dText.setWidth(20);
                dText.setFont(textStyleButtons.getFontString());
                dText.setText(textStyleButtons.getMessageText());
                dText.model.addListener(canvas);
                dText.model.addListener(ourTableModel);

                WhiteboardGUI.this.canvas.addShape(dText);
                WhiteboardGUI.this.ourTableModel.added();


            }
            else if(text.equals("Set Color")){
                if(canvas.selected != null) {
                    canvas.selected.setColor(JColorChooser.showDialog(null, "Set Color", canvas.selected.getColor()));
                }
            } else if(text.equals("Remove Shape")){
                canvas.deleteShape();
                ourTableModel.removed();

            } else if(text.equals("Move To Front")){
                canvas.moveToFront();
            } else if(text.equals("Move To Back")){
                canvas.moveToBack();
            } else if(text.equals("Save")){
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Save your file");
                DShapeModel[] models = new DShapeModel[canvas.shapes.size()];
                for(DShape shape: canvas.shapes){
                    models[canvas.shapes.indexOf(shape)] = shape.model;
                }
                int userSelection = jFileChooser.showSaveDialog(WhiteboardGUI.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = jFileChooser.getSelectedFile();
                        WhiteboardFile.save(file.getName(), models);
                    } catch(Exception exc){
                        exc.printStackTrace();
                    }
                }

            } else if(text.equals("Open")){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open a file");
                int userSelection = fileChooser.showOpenDialog(WhiteboardGUI.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try{
                        canvas.removeAll();
                        DShapeModel[] temp = WhiteboardFile.read(file.getName());
                        ArrayList<DShape> tempList = new ArrayList<>();
                        for(DShapeModel model: temp){
                            DShape dShape = new DShape();
                            dShape.model = model;
                            canvas.addShape(dShape);
                        }

                    } catch (Exception exc){
                        exc.printStackTrace();
                    }
                }
            } else if(text.equals("Save Image")){
                canvas.saveAsImage();
            }

        }

    }
}
