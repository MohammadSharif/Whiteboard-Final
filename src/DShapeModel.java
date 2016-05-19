
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Momo on 5/16/16.
 * This is the model for the DShape class
 */
public class DShapeModel {
    private int x;
    private int y;
    private int height;
    private int width;
    private Color color;
    private ArrayList<ModelListener> listeners;

    public DShapeModel(){
        x = 0;
        y = 0;
        height = 0;
        width = 0;
        color = Color.gray;
    }
    public DShapeModel(int x, int y, int height, int width, Color color){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        listeners = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        for(ModelListener listener: listeners){
            listener.modelChanged(this);
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void addListener(ModelListener listener){
        listeners.add(listener);
    }

    public void removeListener(DShape shape){
        listeners.remove(shape);
    }

    public ArrayList<ModelListener> getListeners(){
        return listeners;
    }
}
