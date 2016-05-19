import java.awt.*;

/**
 * Created by Momo on 5/11/16.
 * This class represents the overall shape class
 */
public class DShape implements ModelListener{
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

    public void setX(int x){
        model.setX(x);
    }

    public void setY(int y){
        model.setX(y);
    }

    public void setHeight(int height){
        model.setHeight(height);
    }

    public void setWidth(int width){
        model.setWidth(width);
    }

    public Rectangle getBounds(){
        return model.getBounds();
    }

    @Override
    public void modelChanged(DShapeModel model) {

    }
}
