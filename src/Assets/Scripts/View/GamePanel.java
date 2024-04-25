package View;

import Controller.Character.PlayerController;
import Controller.Event.CollisionManager;
import Controller.Event.KeyHandler;
import Controller.Projectile.BallController;
import Controller.Projectile.BallPool;
import Controller.Projectile.LaserController;
import Controller.Projectile.LaserPool;
import Controller.TileSet.TileManager;
import Controller.Tool.DataStorage;
import Controller.Tool.SoundManager;
import Controller.Tool.TimeManager;
import Controller.Tool.UIController;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable {
    // CONSTANT var
    public static final long ONE_BILL = 1000000000;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameEndState = 3;
    // SCREEN SETTINGS
    private final int originalTileSize = 16; // 16x16px tile
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48px
    public final int screenWidth = maxScreenCol * tileSize; // WIDTH of screen = 48 * 16 = 768px
    public final int screenHeight = maxScreenRow * tileSize; // HEIGHT of screen = 48 * 12 = 576px
    // Instantiate
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Thread gameThread;
    public CollisionManager collisionManager = new CollisionManager(this);
    public PlayerController playerController = new PlayerController(this, keyH);
    public LinkedList<BallController> ballList = new LinkedList<>();
    public BallPool ballPool = new BallPool(this);
    public LinkedList<LaserController> laserList = new LinkedList<>();
    public LaserPool laserPool = new LaserPool(this);
    public TimeManager timeManager = new TimeManager(this);
    public SoundManager music = new SoundManager();
    public SoundManager sound = new SoundManager();
    public DataStorage data = new DataStorage();
    // Game state
    public int gameState;
    // UI
    public UIController ui = new UIController(this);

    // Frame
    private Game frame;

    public GamePanel(Game frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set size for screen
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Improve rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // FOCUS on key event;
    }

    public Game getFrame() {
        return this.frame;
    }

    // Run game
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // "DELTA" method loop game
    @Override
    public void run() {
        // FPS of game
        int FPS = 70;
        double drawInterval = (double) ONE_BILL / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        setUpGame();
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = System.nanoTime();

            if (delta >= 1) {
                // 1. UPDATE: update information
                update(); // Call update method
                // 2. DRAW: draw the screen with the updated information
                repaint(); // Call paintComponent method
                delta--;
                drawCount++;
            }

            // Display FPS
            if (timer >= ONE_BILL) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    // Set up default entity
    public void setUpGame() {
        gameState = playState;
        playMusic(1);
        ballList.add(ballPool.getBall());
        timeManager.setHighestScore(readScore());
    }

    // Update is called 60 per frame
    public void update() {
        // Check if state is playing
        if (gameState == playState) {
            timeManager.update(); // Update time and score
            playerController.update(); // Update player

            // Update Ball Projectile
            if (canSpawnBall()) {
                ballList.add(ballPool.getBall());
            }
            Iterator<BallController> it = ballList.iterator();

            while (it.hasNext()) {
                BallController curr = it.next();
                curr.update();

                // if ball out of screen remove it
                if (curr.isOutRange()) {
                    it.remove();
                    ballPool.returnBall(curr);
                }

                // Check if collide
                if (collisionManager.checkCollide(playerController.player, curr.projectile)) {
//                    saveScore();
//                    gameState = gameEndState;
                    gameOver();
                    System.out.println("Score: " + timeManager.getPlayTime());
                    if (ui.isSoundOn) {
                        playSoundEffect(2);
                    }
//                    System.exit(1);
                }
            }

            // Update Laser Projectiles
            if (canSpawnLaser()) {
                laserList.add(laserPool.getLaser());
            }
            Iterator<LaserController> iter = laserList.iterator();

            while (iter.hasNext()) {
                LaserController curr = iter.next();
                curr.update();

                // If laser is time out remove it
                if (isTimeOutLaser(curr)) {
                    iter.remove();
                    laserPool.returnLaser(curr);
                }

                // Check collision
                if (collisionManager.checkCollide(curr.projectile, playerController.player)) {
                    gameOver();
                    System.out.println("Score: " + timeManager.getPlayTime());
                    if (ui.isSoundOn) {
                        playSoundEffect(2);
                    }
//                    System.exit(1);
                }
            }
        }
    }

    // Draw component
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // Convert graphics to graphics2D

        if (gameState == playState) {
            tileM.draw(g2); // Draw tile set background

            // Draw Laser Projectile
            for (LaserController ls : laserList) {
                ls.draw(g2);
            }

            playerController.draw(g2); // Draw player

            // Draw Ball Projectile
            for (BallController ball : ballList) {
                ball.draw(g2);
            }

            timeManager.draw(g2); // Draw score
        }

        ui.draw(g2);
        g2.dispose(); // Release system resource that is using
    }

    // Check if ball can spawn
    public boolean canSpawnBall() {
        return timeManager.isTimeSpawnBall();
    }

    // Check if laser can spawn
    public boolean canSpawnLaser() {
        return timeManager.isTimeSpawnLaser();
    }

    // Check if laser is time out and disappear
    public boolean isTimeOutLaser(LaserController laser) {
        return laser.projectile.state.equals("death");
    }

    // Play music
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    // Stop music
    public void stopMusic() {
        music.stop();
    }

    // Play sound effect
    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

    // Save highest score
    public void saveScore() {
        double s = timeManager.getPlayTime();
        timeManager.setHighestScore(s);
        data.saveScore(timeManager.getHighestScore());
    }

    // Read Highest score from file
    public double readScore() {
        return data.readScore();
    }

    // Game over
    public void gameOver() {
        gameState = gameEndState;
        saveScore();
        stopMusic();
        frame.switchToGameOver();
    }

    // Play Again
    public void restart() {

    }
}
