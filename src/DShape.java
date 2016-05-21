import java.awt.*;
import java.awt.event.MouseEvent;

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

    public void resize(Point movingPoint, Point anchorPoint, MouseEvent e){

        int xChange = e.getX() - (int) movingPoint.getX();
        int yChange = e.getY() - (int) movingPoint.getY();
        movingPoint.move(e.getX(), e.getY());
        int newHeight = this.getHeight() - yChange;
        int newWidth = this.getWidth() - xChange;
        int x = this.getX() - xChange;
        int y = this.getY() - yChange;
        if (movingPoint.getY() < anchorPoint.getY()) {
            if (movingPoint.getX() < anchorPoint.getX()) {
                this.setX(x + 2 * xChange);
                this.setY(y + 2 * yChange);
                this.setHeight(newHeight);
                this.setWidth(newWidth);
            } else if (movingPoint.getX() > anchorPoint.getX()) {
                newWidth = this.getWidth() + xChange;
                this.setY(y + 2 * yChange);
                this.setHeight(newHeight);
                this.setWidth(newWidth);
            }
        } else {
            if (movingPoint.getX() < anchorPoint.getX()) {
                this.setX(x + 2 * xChange);
                newHeight = this.getHeight() + yChange;
                this.setHeight(newHeight);
                this.setWidth(newWidth);
            } else if (movingPoint.getX() > anchorPoint.getX()) {
                newWidth = this.getWidth() + xChange;
                newHeight = this.getHeight() + yChange;
                this.setHeight(newHeight);
                this.setWidth(newWidth);
            }

        }
    }

    public void move(int pressPointX, int pressPointY, MouseEvent e, int x, int y){
        int xDif = e.getX() - pressPointX;
        int yDif = e.getY() - pressPointY;
        this.setX(x + xDif);
        this.setY(y + yDif);
    }

    public Rectangle getBounds(){
        return model.getBounds();
    }

    @Override
    public void modelChanged(DShapeModel model) {

    }
}
