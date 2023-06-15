package Snake;


import java.util.ArrayList;

public class Snake extends GameObject{

    private int length;
    private GameObject headPosition;
    private ArrayList<GameObject> bodyPositions;

    public Snake(GameObject headPosition, ArrayList<GameObject> bodyPositions){
        super(4, 7);
        this.headPosition = headPosition;
        this.bodyPositions = bodyPositions;

        GameObject body0 = new GameObject(headPosition.getX() - 1, headPosition.getY());
        GameObject body1 = new GameObject(body0.getX() - 1, body0.getY());
        GameObject body2 = new GameObject(body1.getX() - 1, body0.getY());

        this.headPosition.setPreviousPosition(this.headPosition.getCurentPosition());
        body0.setPreviousPosition(body0.getCurentPosition());
        body1.setPreviousPosition(body1.getCurentPosition());
        body2.setPreviousPosition(body2.getCurentPosition());

        this.bodyPositions.add(this.headPosition);
        this.bodyPositions.add(body0);
        this.bodyPositions.add(body1);
        this.bodyPositions.add(body2);
    }


    public int getLength(){
        return this.length;
    }
    public void setCurrentHeadDirection(int vxN, int vyN){
        this.headPosition.setVxVy(vxN, vyN);
    }


    public void setLength(int newLength){
        this.length = newLength;
    }
    public void setHeadPosition(int x, int y) {
        this.headPosition.setX(x);
        this.headPosition.setY(y);
    }
    public void addBodyPosition(GameObject newBodyPosition){
        this.bodyPositions.add(newBodyPosition);
    }
    public GameObject getHeadPosition(){
        return this.headPosition;
    }

    public void move() {
        this.setHeadPosition(this.headPosition.getX() + this.headPosition.getVx(), this.headPosition.getY() + this.headPosition.getVy());
        GameObject bodyPosition = this.bodyPositions.get(0);
        for(int i = 0; i < this.bodyPositions.size() - 1; i++){
            if(this.headPosition.getX() > 15 || this.headPosition.getX() < 0 || this.headPosition.getY() > 15 || this.headPosition.getY() < 0){
                this.headPosition.setVxVy(0, 0);
                break;
            }
            bodyPosition = this.bodyPositions.get(i);
            GameObject followerBodyPosition = this.bodyPositions.get(i + 1);
            followerBodyPosition.setPreviousPosition(new Point(followerBodyPosition.getX(), followerBodyPosition.getY()));
            followerBodyPosition.setY(bodyPosition.getPreviousPosition().getY());
            followerBodyPosition.setX(bodyPosition.getPreviousPosition().getX());
            this.bodyPositions.set(i+1, followerBodyPosition);
        }
        bodyPosition.setPreviousPosition(this.headPosition.getCurentPosition());
        this.bodyPositions.set(0, bodyPosition);

    }

    public ArrayList<GameObject> getBodyPositions() {
        return this.bodyPositions;
    }
}
