
import java.awt.*;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Rectangle object
 */
public class DRect extends DShape{
//    DShapeModel rectModel = new DRectModel();

    public DRect(){
        model = new DRectModel();
    }

    public void draw(Graphics g){
        g.fillRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());
        Point[] corners = getKnobs();
        g.setColor(Color.black);
        for(Point corner: corners) {
            g.fillRect(corner.x, corner.y, 9, 9);
        }
    }

    public void setX(int x){
        model.setX(x);
    }

    public void setY(int y){
        model.setY(y);
    }

    public void setHeight(int height){
        model.setHeight(height);
    }

    public void setWidth(int width){
        model.setWidth(width);
    }

    public void setColor(Color color){
        model.setColor(color);
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

//    public Point[] getKnobs(){
//        Point topLeft = new Point(rectModel.getX(), rectModel.getY());
//        Point bottomLeft = new Point(rectModel.getX(), rectModel.getY() + rectModel.getHeight());
//        Point topRight = new Point(rectModel.getX() + rectModel.getWidth(), rectModel.getY());
//        Point bottomRight = new Point(rectModel.getX() + rectModel.getWidth(), rectModel.getY() + rectModel.getHeight());
//        Point[] knobs = {topLeft, bottomLeft, topRight, bottomRight};
//        return knobs;
//    }


    public Color getColor(){
        return model.getColor();
    }

    public Rectangle getBounds(){
        return model.getBounds();
    }


}
