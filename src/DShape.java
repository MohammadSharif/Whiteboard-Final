import java.awt.*;

/**
 * Created by Momo on 5/11/16.
 * This class represents the overall shape class
 */
public class DShape {
    DShapeModel model = new DShapeModel();
    public DShape(){

    }

    public void draw(Graphics g){

    }

    public Color getColor(){
        return model.getColor();
    }
}
