import java.awt.*;

/**
 * Created by Momo on 5/11/16.
 * This class represents the overall shape class
 */
public class DShape implements ModelListener{
    protected DShapeModel model;
    WhiteboardGUI gui;
    public DShape(){
        model = new DShapeModel();
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

    public int getX(){
        return model.getX();
    }

    public int getY(){
        return model.getY();
    }

    public int getHeight(){
        return model.getHeight();
    }

    public int getWidth(){
        return model.getWidth();
    }

    public Point[] getKnobs(){
        Point topLeft = new Point(model.getX() - (9/2), model.getY() - (9/2));
        Point bottomLeft = new Point(model.getX() - (9/2), model.getY() + model.getHeight() - (9/2));
        Point topRight = new Point(model.getX() + model.getWidth() - (9/2), model.getY() - (9/2));
        Point bottomRight = new Point(model.getX() + model.getWidth() - (9/2), model.getY() + model.getHeight() - (9/2));
        Point[] knobs = {topLeft, bottomLeft, topRight, bottomRight};
        System.out.println(topLeft);
        return knobs;
    }

    public Rectangle getBounds(){
        return model.getBounds();
    }

    @Override
    public void modelChanged(DShapeModel model) {

    }
}
