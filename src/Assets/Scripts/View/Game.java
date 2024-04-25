package View;

import Controller.Tool.SoundManager;

import javax.swing.*;

public class Game extends JFrame {
    public int screenWidth = 768;
    public int screenHeight = 576;

    private GamePanel gamePanel;
    private StartScreen startScreen;
    private SelectScreen selectScreen;
    private OptionScreen optionScreen;
    private GameOverScreen gameOverScreen;
    private SoundManager music;
    private boolean isMusicOn;

    public Game() {
        // Instantiate
        gamePanel = new GamePanel(this);
        startScreen = new StartScreen(this);
        selectScreen = new SelectScreen(this);
        optionScreen = new OptionScreen(this);

        // Music background
        music = new SoundManager();
        playMusic(0);
        isMusicOn = true;

        // Set Frame
        this.setTitle("Dodge Game"); // Set title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// CLOSE window and END run time
        this.setResizable(false); // Against resize window

        this.add(startScreen); // Add panel to main window
        this.pack(); // Causes window to be sized to fit the preferred size
        this.setLocationRelativeTo(null); // Display on center
        this.setVisible(true);
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    // Switch panel to Game Play
    public void switchToGamePanel() {
        if (gamePanel.gameThread == null) {
            gamePanel = new GamePanel(this);
        }
        gamePanel.playerController.player.getPlayerImage("Character_0" + selectScreen.getCharacterNum());
        stopMusic();
        isMusicOn = false;
        this.setContentPane(gamePanel);
        gamePanel.startGameThread(); // Start game loop
        gamePanel.requestFocus();
        this.revalidate();
    }

    // Switch panel to Select Screen
    public void switchToSelect() {
        if (!isMusicOn)
            playMusic(0);
        this.setContentPane(selectScreen);
        this.revalidate();
    }

    // Switch panel to Option Screen
    public void switchToOption() {
        this.setContentPane(optionScreen);
        this.revalidate();
    }

    // Switch panel to Start Screen
    public void switchToStart() {
        if (!isMusicOn)
            playMusic(0);
        this.setContentPane(startScreen);
        this.revalidate();
    }

    // Switch panel to GameOver Screen
    public void switchToGameOver() {
        gameOverScreen = new GameOverScreen(this);
        gamePanel.gameThread = null;
        this.setContentPane(gameOverScreen);
        this.revalidate();
    }

    // Handle Music
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }
}