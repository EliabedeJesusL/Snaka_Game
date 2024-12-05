import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Timer;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener, KeyListener {
    private int boardWidth;
    private int boardHeight;
    private int tileSize = 25;
    private Food food;
    private Snake snake;
    private Timer timer;
    //private Tile tile;
    private boolean gameOver;

    public Board(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.gameOver = false;
        this.food = new Food(boardWidth / tileSize, boardHeight / tileSize , tileSize, Color.RED, boardWidth, boardHeight);
        this.snake = new Snake(0, 0, tileSize, Color.GREEN);
         
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);
        
        this.timer = new Timer(100, this);
        timer.start();

        food.placeFood();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        snake.move();

        if (gameOver) {
            timer.stop();
        }

        checkBoardCollision();
        checkSelfCollision();
        snake.checkFoodColision(food);

        repaint();
    }

    public void checkBoardCollision(){
        Tile head = snake.getHead();
        System.out.println("Head Position: X = " + head.getX() + ", Y = " + head.getY());
        if (!isTileInsideBoard(head)) { 
            gameOver = true;
        }
    }

    public void checkSelfCollision(){
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (snake.getHead().checkCollision(snake.getBody().get(i))) {
                gameOver = true;
            }
        }
    }

    public boolean isTileInsideBoard(Tile tile) {
        return (tile.getX() >= 0 && tile.getX() < boardWidth) && (tile.getY() >= 0 && tile.getY() < boardHeight);
    }

    public void drawScore(Graphics g){
        int score = snake.getSize();

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));

        if (!gameOver) {
            g.drawString("Score: " + score, 10, 20);
        } else {
            g.drawString("Game Over! Final Score: " + score, 10, 20);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpa o painel antes de desenhar
        food.draw(g);
        snake.draw(g);
        drawScore(g);
        
        g.setColor(Color.DARK_GRAY); // Define a cor das linhas

        for(int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
        }
        for(int i = 0; i < boardHeight / tileSize; i++) {
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        int key = e.getKeyCode();
    
        // Verifica qual tecla foi pressionada e ajusta a direção da cobra
        if (key == KeyEvent.VK_UP && snake.getVelocityY() == 0) {
            snake.setVelocityX(0);
            snake.setVelocityY(-1);
        } else if (key == KeyEvent.VK_DOWN && snake.getVelocityY() == 0) {
            snake.setVelocityX(0);
            snake.setVelocityY(1);
        } else if (key == KeyEvent.VK_LEFT && snake.getVelocityX() == 0) {
            snake.setVelocityX(-1);
            snake.setVelocityY(0);
        } else if (key == KeyEvent.VK_RIGHT && snake.getVelocityX() == 0) {
            snake.setVelocityX(1);
            snake.setVelocityY(0);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    // Não implementado
    }

    @Override
    public void keyReleased(KeyEvent e) {
    // Não implementado
    }
}