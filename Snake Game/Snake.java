import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Tile> body;
    private Tile head;
    private int tileSize;
    private Color color;
    private int velocityX;
    private int velocityY;
    //private Food food;
    private int newHeadX;
    private int newHeadY;

    public Snake(int x, int y, int tileSize, Color color) {
        this.body = new ArrayList<>();
        this.tileSize = tileSize;
        this.color = color;
        Tile snake = new Tile(x, y, tileSize, color);
        this.head = snake;
        this.body.add(snake);
        this.velocityX = 1;
        this.velocityY = 0;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public ArrayList<Tile> getBody() {
        return body;
    }

    public void setBody(ArrayList<Tile> body) {
        this.body = body;
    }

    public Tile getHead() {
        return head; 
    }

    public void setHead(Tile head) {
        this.head = head;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        for (Tile tile : body) {
            tile.draw(g);
        }
    }

    public void move(){
        Tile head = body.getFirst();

        newHeadX = head.getX() + getVelocityX() * tileSize;
        newHeadY = head.getY() + getVelocityY() * tileSize;

        Tile newHead = new Tile(newHeadX, newHeadY, tileSize, color);
        this.head = newHead;
        body.addFirst(newHead);
        body.removeLast();    
    }

    public void grow() {
        Tile lastTile = body.get(body.size() - 1);
        Tile newTile = new Tile(lastTile.getX(), lastTile.getY(), 25, Color.green);
        body.add(newTile);
    }

    public void checkFoodColision(Food food) {
        if (body.get(0).checkCollision(food)){
            this.grow();
            food.placeFood();
        }
    }

    public int getSize(){
        return body.size();
    }
}