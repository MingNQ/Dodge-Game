package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

public class GameOverScreen extends JPanel {
    private Font font_letter, font_title, font_button;
    private Game frame;

    public GameOverScreen(Game frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        this.setPreferredSize(new Dimension(frame.screenWidth, frame.screenHeight));// Set the size based on the image resolution
        try {
            font_title = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(72f);
            font_button = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(50f);
            font_letter = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(40f);
        } catch (Exception e) {
            e.printStackTrace();
//            font_title = new Font("Arial", Font.PLAIN, 16);
//            font_button = new Font("Arial", Font.BOLD, 16);
//            font_letter = new Font("Arial", Font.BOLD, 16);
        }

        this.setLayout(new BorderLayout());

        // Set the background panel
        BackgroundPanel bgPanel = new BackgroundPanel();

        // Set the layout to null for absolute positioning
        bgPanel.setLayout(null);

//        // Title labels
        JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        styleLabel(gameOverLabel, font_title, Color.WHITE);
        gameOverLabel.setBounds(0, 60, 768, 72);
        bgPanel.add(gameOverLabel);

        // Score section
        JLabel totalPointLabel = new JLabel("Total Point", SwingConstants.LEFT);
        styleLabel(totalPointLabel, font_letter, Color.WHITE);
        totalPointLabel.setBounds(50, 180, 300, 30);
        bgPanel.add(totalPointLabel);

        String score = new DecimalFormat("#0").format(frame.getGamePanel().timeManager.getPlayTime()) + "";
        JLabel scoreLabel = new JLabel(score, SwingConstants.RIGHT);
        styleLabel(scoreLabel, font_letter, Color.WHITE);
        scoreLabel.setBounds(360, 180, 300, 30);
        bgPanel.add(scoreLabel);

        JLabel highestScoreTitleLabel = new JLabel("Highest Score", SwingConstants.LEFT);
        styleLabel(highestScoreTitleLabel, font_letter, Color.WHITE);
        highestScoreTitleLabel.setBounds(50, 240, 300, 30);
        bgPanel.add(highestScoreTitleLabel);

        String highestScore = new DecimalFormat("#0").format(frame.getGamePanel().timeManager.getHighestScore()) + "";
        JLabel highestScoreLabel = new JLabel(highestScore, SwingConstants.RIGHT);
        styleLabel(highestScoreLabel, font_letter, Color.WHITE);
        highestScoreLabel.setBounds(360, 240, 300, 30);
        bgPanel.add(highestScoreLabel);

        // Rank section
//        JLabel rankLabel = new JLabel("<html>1. ABCXYZ: 9999999<br/>2. ABCXYZ: 8888888<br/>3. ABCXYZ: 7777777<br/>...</html>", SwingConstants.LEFT);
//        styleLabel(rankLabel, font_letter, Color.WHITE);
//        rankLabel.setFont(font_letter);
//        rankLabel.setBounds(350, 180, 500, 200);
//        bgPanel.add(rankLabel);

        // Buttons
        JButton playAgainButton = new JButton("Play Again");
        styleButton(playAgainButton, font_button, new Color(0x666699));
        playAgainButton.setBounds(100, 420, 300, 50);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToSelect();
            }
        });

        bgPanel.add(playAgainButton);

        JButton returnBtn = new JButton("Back Menu");
        styleButton(returnBtn, font_button, new Color(0x666699));
        returnBtn.setBounds(400, 420, 300, 50);
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToStart();
            }
        });
        bgPanel.add(returnBtn);


        JButton exitButton = new JButton("EXIT");
        styleButton(exitButton, font_button, new Color(0x666699));
        exitButton.setBounds(315, 480, 180, 50);
        exitButton.addActionListener(e -> System.exit(0));
        bgPanel.add(exitButton);


        this.add(bgPanel, BorderLayout.CENTER);
    }

    private void styleLabel(JLabel label, Font font, Color color) {
        label.setFont(font);
        label.setForeground(color);
        label.setOpaque(false);
    }

    private void styleButton(JButton button, Font font, Color bgColor) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
    }

    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background color
            g.setColor(new Color(0x666699));
            g.fillRect(0, 0, getWidth(), getHeight());

            // Draw custom graphics or load an image as the background if needed
            // For example, to load an image:
            ImageIcon background = new ImageIcon("./src/Assets/Sprites/Background/Screen/background_img.png");
            g.drawImage(background.getImage(), 0, 0, this);

            // Custom lines
            g.setColor(Color.WHITE);
            g.drawLine(50, 150, 718, 150);
            g.drawLine(50, 390, 718, 390);
        }
    }
}
