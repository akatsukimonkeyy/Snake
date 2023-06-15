package Snake;

public abstract class Item implements GameObjectInterface{
    private int x, y;
    private Point currentPosition;
    public Item(int x, int y){
        this.x = x;
        this.y = y;
        this.currentPosition = new Point(this.x, this.y);
    }

    public int getX(){
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
        this.currentPosition = new Point(this.x, this.y);
    }

    public int getY() {
        return this.y;

    }

    public void setY(int y) {
        this.y = y;
        this.currentPosition = new Point(this.x, this.y);
    }
    public void setCurrentPosition(Point p){
        this.currentPosition = p;
    }
    public Point getCurrentPosition(){
        return this.currentPosition;
    }

}

