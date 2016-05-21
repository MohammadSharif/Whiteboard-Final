import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Momo on 5/16/16.
 * This class represents the Line object
 */
public class DLine extends DShape {
    DLineModel thisModel;


    public DLine(){
        model = new DLineModel();
        thisModel = (DLineModel)model;
    }

    public void draw(Graphics g){
        Point[] corners = getKnobs();

//        if(p1X < p2X){
//            if(p1Y < p2Y){
//                g.drawLine(p1X, p1Y, p2X, p2Y);
//            } else {
//                g.drawLine(p2X, p2Y, p1X, p1Y);
//            }
//        } else{
//            if(p1Y < p2Y){
//                g.drawLine(p2X, p2Y, p1X, p1Y);
//            } else {
//                g.drawLine(p1X, p1Y, p2X, p2Y);
//            }
//        }
        g.drawLine(thisModel.p1X, thisModel.p1Y, thisModel.p2X, thisModel.p2Y);

        g.setColor(Color.black);
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

    public Point[] getKnobs(){
        Point p1 = new Point((int)thisModel.p1.getX() - (9/2), (int)thisModel.p1.getY() - (9/2));
        Point p2 = new Point((int)thisModel.p2.getX() - (9/2), (int)thisModel.p2.getY() - (9/2));
        Point[] knobs = {p1, p2};
        return knobs;
    }

    public void setPoints(){
        thisModel.setP1(thisModel.getX(), thisModel.getY());
        thisModel.setP2(thisModel.getX() + thisModel.getWidth(), thisModel.getY());
    }

    public void setP1(int x, int y){
        thisModel.setP1(x, y);

    }

    public void setP2(int x, int y){
        thisModel.setP2(x, y);

    }

    public void resize(Point movingPoint, Point anchorPoint, MouseEvent e){

        boolean moveP1 = false;
        for(Point knob: getKnobs()){
            Rectangle temp = new Rectangle((int)knob.getX(), (int)knob.getY(), 9, 9);
            if(temp.contains(movingPoint) && temp.contains(thisModel.p1)){
                moveP1 = true;
                break;
            } else if(temp.contains(movingPoint) && temp.contains(thisModel.p2)){
                moveP1 = false;
                break;
            }
        }
        int xChange = e.getX() - (int) movingPoint.getX();
        int yChange = e.getY() - (int) movingPoint.getY();
        movingPoint.move(e.getX(), e.getY());
        int newHeight = this.getHeight() - yChange;
        int newWidth = this.getWidth() - xChange;
        int x = this.getX() - xChange;
        int y = this.getY() - yChange;
        if(moveP1){
            setP1((int)thisModel.p1.getX() + xChange, (int)thisModel.p1.getY() + yChange);

        } else {
            setP2((int)thisModel.p2.getX() + xChange, (int)thisModel.p2.getY() + yChange);
        }
        thisModel.setWidth((int)thisModel.getBounds().getWidth());
        thisModel.setHeight((int)thisModel.getBounds().getHeight());
        thisModel.notifyListeners();

//        if (movingPoint.getY() < anchorPoint.getY()) {
//            if (movingPoint.getX() < anchorPoint.getX()) {
//                this.setX(x + 2 * xChange);
//                this.setY(y + 2 * yChange);
//                this.setHeight(newHeight);
//                this.setWidth(newWidth);
//                setP1(this.getX(), this.getY());
//                setP2(this.getX() + this.getWidth(), this.getY() + this.getHeight());
//            } else if (movingPoint.getX() > anchorPoint.getX()) {
//                newWidth = this.getWidth() - xChange;
//                this.setX((int)anchorPoint.getX());
//                this.setY((int)movingPoint.getY());
//                this.setHeight(newHeight);
//                this.setWidth(newWidth);
//                setP1((int)anchorPoint.getX(), (int)anchorPoint.getY());
//                setP2((int)movingPoint.getX(), (int)movingPoint.getY());
//            }
//        } else {
//            if (movingPoint.getX() < anchorPoint.getX()) {
//                this.setX(x + 2 * xChange);
//                newHeight = this.getHeight() + yChange;
//                this.setHeight(newHeight);
//                this.setWidth(newWidth);
//                setP1(this.getX(), this.getY());
//                setP2(this.getX() + this.getWidth(), this.getY() + this.getHeight());
//            } else if (movingPoint.getX() > anchorPoint.getX()){
//                newWidth = this.getWidth() + xChange;
//                newHeight = this.getHeight() + yChange;
//                this.setHeight(newHeight);
//                this.setWidth(newWidth);
//                setP1(this.getX(), this.getY());
//                setP2(this.getX() + this.getWidth(), this.getY() + this.getHeight());
//
//            }
//
//        }
    }

    public void move(int pressPointX, int pressPointY, MouseEvent e, int p1X, int p1Y, int p2X, int p2Y){
        int xDif = e.getX() - pressPointX;
        int yDif = e.getY() - pressPointY;
        setP1(p1X + xDif, p1Y + yDif);
        setP2(p2X + xDif, p2Y + yDif);
        thisModel.notifyListeners();
    }

    public int getP1X(){
        return  thisModel.getP1X();
    }

    public int getP1Y(){
        return thisModel.getP1Y();
    }

    public int getP2X(){
        return thisModel.getP2X();
    }

    public int getP2Y(){
        return thisModel.getP2Y();
    }


    public Color getColor(){
        return model.getColor();
    }

    public Rectangle getBounds(){
        return model.getBounds();
    }
}
