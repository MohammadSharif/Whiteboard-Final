import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Oval object
 */
public class DOval extends DShape{

    DShapeModel ovalModel = new DOvalModel();

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

    public Color getColor(){
        return ovalModel.getColor();
    }

}
