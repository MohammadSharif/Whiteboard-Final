import java.awt.*;

/**
 * Created by Momo on 5/11/16.
 * This class represents the overall shape class
 */
public class DShape {
    DShapeModel model = new DShapeModel();
    WhiteboardGUI gui;
    public DShape(){

    }

    public DShape(WhiteboardGUI gui){
        this.gui = gui;
    }

    public void draw(Graphics g){

    }

    public Color getColor(){
        return model.getColor();
    }

    public void setColor(Color color){
        model.setColor(color);
    }

    public Rectangle getBounds(){
        return model.getBounds();
    }
}
