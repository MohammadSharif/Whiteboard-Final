import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Momo on 5/16/16.
 * This is the model for the DLine class
 */
public class DLineModel extends DShapeModel{
    ArrayList<ModelListener> listeners = new ArrayList<>();
    Point p1 = new Point();
    Point p2 = new Point();
    int p1X = (int)p1.getX();
    int p1Y = (int)p1.getY();
    int p2X = (int)p2.getX();
    int p2Y = (int)p2.getY();

    public DLineModel(){
        super();
    }

    public void addListener(ModelListener listener){
        listeners.add(listener);
    }

    public void removeListener(ModelListener listener){
        listeners.remove(listener);
    }

    public void notifyListeners(){
        for(ModelListener listener: listeners){
            listener.modelChanged(DLineModel.this);
        }
    }

    public void setP1(int x, int y){
        p1.setLocation(x, y);
        p1X = x;
        p1Y = y;
    }

    public void setP2(int x, int y){
        p2.setLocation(x, y);
        p2X = x;
        p2Y = y;
    }

    public int getP1X(){
        return  p1X;
    }

    public int getP1Y(){
        return p1Y;
    }

    public int getP2X(){
        return p2X;
    }

    public int getP2Y(){
        return p2Y;
    }

    @Override
    public Rectangle getBounds(){
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;
        if(p1.getX() < p2.getX()){
            if(p1.getY() < p2.getY()){
                return new Rectangle((int)p1.getX(),(int)p1.getY(), (int)(p2.getX() - p1.getX()) + (9/2), (int)(p2.getY() - p1.getY()) + (9/2));
            } else if(p1.getY() > p2.getY()){
                return new Rectangle((int)p1.getX(),(int)p2.getY(), (int)(p2.getX() - p1.getX())+ (9/2), (int)(p1.getY() - p2.getY()) + (9/2));
            }
        }
        else if(p1.getX() > p2.getX()){
            if(p1.getY() < p2.getY()){
                return new Rectangle((int)p2.getX(),(int)p1.getY(), (int)(p1.getX() - p2.getX()) + (9/2), (int)(p2.getY() - p1.getY()) + (9/2));
            } else {
                return new Rectangle((int)p2.getX(),(int)p2.getY(), (int)(p1.getX() - p2.getX()) + (9/2), (int)(p1.getY() - p2.getY()) + (9/2));
            }
        }
        return new Rectangle((int)p1.getX(), (int)p1.getY() - 5, 20 + (9/2), 10 + (9/2));

    }


}
