import java.awt.Color;
import java.util.Random;

public class Food extends Tile{

    private int maxX;
    private int maxY;
    private int tileSize;
    private Color color;
    private Random random;
    
    public Food(int maxX, int maxy, int tileSize, Color color, int x, int y){
        super(x, y, tileSize, color);
        this.maxX = maxX;
        this.maxY = maxy;
        this.tileSize = tileSize;
        this.color = color;
        this.random = new Random();
    }


    public void placeFood(){
        setX(random.nextInt(maxX) * tileSize);
        setY(random.nextInt(maxY) * tileSize);
    }
}
