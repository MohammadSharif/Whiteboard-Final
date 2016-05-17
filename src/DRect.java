import java.awt.*;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Rectangle object
 */
public class DRect extends DShape {
    DShapeModel rectModel = new DRectModel();

    public void draw(Graphics g){
        g.fillOval(rectModel.getX(), rectModel.getY(), rectModel.getWidth(), rectModel.getHeight());
    }

    public void setX(int x){
        rectModel.setX(x);
    }

    public void setY(int y){
        rectModel.setY(y);
    }

    public void setHeight(int height){
        rectModel.setHeight(height);
    }

    public void setWidth(int width){
        rectModel.setWidth(width);
    }

    public void setColor(Color color){
        rectModel.setColor(color);
    }

    public Color getColor(){
        return rectModel.getColor();
    }
}
