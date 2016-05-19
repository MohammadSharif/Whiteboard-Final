import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Oval object
 */
public class DOval extends DShape{

    public DOval(){
        model = new DOvalModel();
    }


    public void draw(Graphics g){
        g.fillOval(model.getX(), model.getY(), model.getWidth(), model.getHeight());
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


    public Rectangle getBounds(){
        return model.getBounds();
    }

    public Color getColor(){
        return model.getColor();
    }

}
