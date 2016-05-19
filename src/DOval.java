import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Oval object
 */
public class DOval extends DShape implements ModelListener{

    DShapeModel ovalModel = new DOvalModel();
    public DOval(){
        super();
    }


    public void draw(Graphics g){
        g.fillOval(ovalModel.getX(), ovalModel.getY(), ovalModel.getWidth(), ovalModel.getHeight());
    }

    public void setX(int x){
        ovalModel.setX(x);
    }

    public void setY(int y){
        ovalModel.setY(y);
    }

    public void setHeight(int height){
        ovalModel.setHeight(height);
    }

    public void setWidth(int width){
        ovalModel.setWidth(width);
    }

    public void setColor(Color color){
        ovalModel.setColor(color);
    }

    public Rectangle getBounds(){
        return ovalModel.getBounds();
    }

    public Color getColor(){
        return ovalModel.getColor();
    }

    @Override
    public void modelChanged(DShapeModel model) {
        
    }
}
