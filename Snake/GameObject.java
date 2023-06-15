package Snake;

public class GameObject implements GameObjectInterface {
    private Point currentPosition, previousPosition;
    public int x;
    public int y;
    public int vx, vy;
    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        currentPosition = new Point(this.x, this.y);

    }


    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int xNew){
        this.x = xNew;
        this.setCurrentPosition(new Point(this.x,this.y));
    }
    public void setY(int yNew){
        this.y = yNew;
        this.setCurrentPosition(new Point(this.x,this.y));
    }
    public void setCurrentPosition(Point p) {
       this.currentPosition = p;
    }

    public Point getCurentPosition(){
        return this.currentPosition;
    }
    public void setVxVy(int vxN, int vyN){
        this.vx = vxN;
        this.vy = vyN;
    }
    public int getVx(){
        return this.vx;
    }
    public int getVy(){
        return this.vy;
    }

    public Point getPreviousPosition(){
        return this.previousPosition;
    }
    public void setPreviousPosition(Point p){
        previousPosition = p;
    }
}
