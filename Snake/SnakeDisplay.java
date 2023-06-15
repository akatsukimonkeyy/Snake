package Snake;
import processing.core.PApplet;
import java.util.ArrayList;

public class SnakeDisplay extends PApplet{

    public static void main(String[] args) {
        //WHERE THIS CLASS IS LOCATED
        PApplet.main("Snake.SnakeDisplay");
    }
    final int BLOCKX = 50;
    final int BLOCKY = 50;

    private GameObject headPosition = new GameObject(4, 4);

    private ArrayList<GameObject> bodyPositions = new ArrayList<>();

    private Snake snake = new Snake(headPosition, bodyPositions);
    private Food apple = new Food();
    private int score, storeScore;


    public void settings() {
        size(800, 800);
    }

    public void setup() {
        frameRate(60.0f);
        drawBoard();
        headPosition.setVxVy(1, 0);
        storeScore = 0;
    }

    public void draw() {
        if(frameCount % 10 == 0) {
            clear();
            drawBoard();
            if (!(headPosition.getX() > 15 || headPosition.getX() < 0 || headPosition.getY() > 15 || headPosition.getY() < 0)) {
                int count = 0;
                for (int i = 0; i < bodyPositions.size(); i++) {
                    if (headPosition.getX() == bodyPositions.get(i).getX() && bodyPositions.get(i).getY() == headPosition.getY())
                        count++;
                }
                System.out.println(count);
                if (count == 0 || bodyPositions.size() <= 4) {
                    snake.move();
                    drawFood();
                    drawSnake();

                }
                else {
                    resetSnake();
                    drawGameOver();
                    noLoop();
                }

            }
            else {
                resetSnake();
                drawGameOver();
                noLoop();
            }
            drawScore();
        }

    }
    public void drawScore(){
        fill(0);
        textSize(50);
        text(score, 730, 50);
        fill(255);
    }
    public void drawBoard(){
        for (int row = 0; row < 16; row ++) {
            for (int col = 0; col < 16; col ++) {
                rect(row * BLOCKX, col * BLOCKY, (row + 1) * BLOCKX, (col + 1) * BLOCKY);
            }
        }
    }

    public void drawGameOver(){
        fill(0);
        textSize(50);
        text("        GAME OVER! \n Click anywhere to restart \n         SCORE: " + storeScore,100, 350);
    }
    public void drawSnake(){
        fill(0, 255, 0);
            rect(headPosition.getX() * BLOCKX, headPosition.getY() * BLOCKY,
                    (BLOCKX), (BLOCKY));
            for (int i = 0; i < bodyPositions.size(); i++) {
                rect(bodyPositions.get(i).getX() * BLOCKX, bodyPositions.get(i).getY() * BLOCKY, BLOCKX, BLOCKY);
            }
            fill(255);
    }
    public void keyPressed(){

        if(key == CODED){
            if(keyCode == UP && headPosition.getVy() != 1)
                snake.setCurrentHeadDirection(0, -1);
            else if(keyCode == DOWN && headPosition.getVy() != -1)
                snake.setCurrentHeadDirection(0, 1);
            else if(keyCode == RIGHT && headPosition.getVx() != -1)
                snake.setCurrentHeadDirection(1, 0);
            else if(keyCode == LEFT && headPosition.getVx() != 1)
                snake.setCurrentHeadDirection(-1, 0);

        }
    }
    public void mousePressed(){
        resetSnake();
        loop();
    }
    public void drawFood(){
        boolean A = true;
        if(headPosition.getX() == apple.getX() && headPosition.getY() == apple.getY()){
            newXandY();
            score++;
            int newSquareX = (bodyPositions.get(bodyPositions.size() - 1).getPreviousPosition().getX());
            int newSquareY = bodyPositions.get(bodyPositions.size() - 1).getPreviousPosition().getY();
            GameObject newSquare = new GameObject(newSquareX, newSquareY);
            snake.addBodyPosition(newSquare);
        }

        for(GameObject a : bodyPositions){
            if(a.getX() == apple.getX() && a.getY() == apple.getY())
                A = false;
        }
        if(A == false){
            newXandY();
        }
        else {
            if(headPosition.getX() != apple.getX() || headPosition.getY() != apple.getY()) {
            fill(255, 0, 0);
            rect(apple.getX() * BLOCKX, apple.getY() * BLOCKY, BLOCKX, BLOCKY);
            fill(255);

            }
        }
    }

    public void newXandY(){
        int min = 0;
        int max = 15;
        apple.setX((int) (Math.random() * (max - min + 1)) + min);
        apple.setY((int) (Math.random() * (max - min + 1)) + min);
    }
    public void resetSnake(){
        for(int i = 4; i < bodyPositions.size(); i++){
            bodyPositions.remove(i);
        }
        snake.setHeadPosition(4, 4);
        for(int i = 0; i < bodyPositions.size() - 1; i++){
            bodyPositions.get(i + 1).setX(bodyPositions.get(i).getX() - 1);
        }
        headPosition.setVxVy(0, 0);
        storeScore = score;
        score = 0;
    }

}