import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Momo on 5/16/16.
 * This is the model for the DText class
 */
public class DTextModel extends DShapeModel{
    ArrayList<ModelListener> listeners = new ArrayList<>();
    String text;
    String font;
    public DTextModel(){
        super();
        text = "Hello";
        font = "Dialog";
    }

    public void setText(String name){
        text = name;
    }

    public void setFont(String name){
        font = name;
    }

    public String getFont(){
        return font;
    }

    public String getText(){
        return text;
    }

    public void addListener(ModelListener listener){
        listeners.add(listener);
    }

    public void removeListener(ModelListener listener){
        listeners.remove(listener);
    }

    public void notifyListeners(){
        for(ModelListener listener: listeners){
            listener.modelChanged(DTextModel.this);
        }
    }


}
