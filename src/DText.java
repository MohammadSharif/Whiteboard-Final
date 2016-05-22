import java.awt.*;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Text object
 */
public class DText extends DShape {
    DTextModel thisModel;
    public DText(){
        model = new DTextModel();
        thisModel = (DTextModel)model;
    }

    public void draw(Graphics g){
        Font thisFont = computeFont();
        FontMetrics metrics = new FontMetrics(thisFont) {
            @Override
            public int getDescent() {
                return super.getDescent();
            }
        };

        Shape clip = g.getClip();
        g.setClip(clip.getBounds().createIntersection(getBounds()));
        g.setFont(thisFont);
        g.drawString(getText(), getX(), getY() + getHeight() - 2*metrics.getDescent());
        g.setClip(clip);
        Point[] corners = getKnobs();
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

    public String getFont(){
        return thisModel.getFont();
    }

    public String getText(){
        return thisModel.getText();
    }

    public void setText(String text){
        thisModel.setText(text);
    }

    public void setFont(String font){
        thisModel.setFont(font);
    }

    private Font computeFont(){
        double size = 1.0;
        double previousSize = 0;
        Font toCompute = new Font(getFont(), Font.PLAIN, (int)size);
        FontMetrics fontMetrics = new FontMetrics(toCompute) {
            @Override
            public int getHeight() {
                return super.getHeight();
            }
        };
        while(fontMetrics.getHeight() < getHeight()){
            previousSize = size;
            size = (size*1.10) + 1;
            toCompute = new Font(getFont(), Font.PLAIN, (int)size);
            fontMetrics = new FontMetrics(toCompute) {
                @Override
                public int getHeight() {
                    return super.getHeight();
                }
            };
        }

        return new Font(thisModel.font, Font.PLAIN, (int)previousSize);
    }
}
