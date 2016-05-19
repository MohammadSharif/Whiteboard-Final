import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Momo on 5/16/16.
 * This is the model for the DOval class
 */
public class DOvalModel extends DShapeModel{
    ArrayList<ModelListener> listeners = new ArrayList<>();

    public DOvalModel(){
        super();
    }

    public DOvalModel(int x, int y, int height, int width, Color color){
        super(x, y, height, width, color);
    }

    public void addListener(ModelListener listener){
        listeners.add(listener);
    }

    public void removeListener(ModelListener shape){
        listeners.remove(shape);
    }

    public void notifyListeners(){
        for(ModelListener listener: listeners){
            listener.modelChanged(DOvalModel.this);
        }
    }

}
