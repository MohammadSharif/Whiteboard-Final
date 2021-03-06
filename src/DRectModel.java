import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Momo on 5/16/16.
 * This is the model for the DRect class
 */
public class DRectModel extends DShapeModel{
    ArrayList<ModelListener> listeners = new ArrayList<>();
    public DRectModel(){
        super();
    }

    public DRectModel(int x, int y, int height, int width, Color color){
        super(x, y, height, width, color);
    }

    public void addListener(ModelListener listener){
        listeners.add(listener);
    }

    public void removeListener(ModelListener listener){
        listeners.remove(listener);
    }

    public void notifyListeners(){
        for(ModelListener listener: listeners){
            listener.modelChanged(DRectModel.this);
        }
    }
}
