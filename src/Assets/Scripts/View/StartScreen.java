package View;

import Controller.Screen.ControllerStartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartScreen extends JPanel {
    Font font_title = null;
    Font font_button = null;
    private JButton button_start;
    private JButton button_option;
    private JButton button_exit;
    private Graphics g;
    private JDialog volumeControlDialog;
    private JSlider volumeSlider;
    private Game frame;

    public StartScreen(Game frame) {
        this.frame = frame;
        this.setDoubleBuffered(true);
        init();
    }

    public Game getFrame() {
        return this.frame;
    }

    public void init() {
        this.setPreferredSize(new Dimension(frame.screenWidth, frame.screenHeight));
        this.setLayout(new BorderLayout());

        ControllerStartScreen ctr = new ControllerStartScreen(this);
        //Thiết lập font chữ
        Font font = new Font(Font.DIALOG, Font.BOLD, 40);
        try {
            font_title = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(100f);
            font_button = Font.createFont(Font.TRUETYPE_FONT, new File("./src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(72f);
        } catch (Exception e) {
            font_title = new Font("Arial", Font.PLAIN, 16);
        }
        //thiết lập lớp
        JLayeredPane pane = new JLayeredPane();

        //Thiết lập background
        JLabel label_background = new JLabel();
        ImageIcon bg = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/Background/Screen/background_img.png")));
        label_background.setIcon(bg);
        label_background.setBounds(0, 0, 768, 576);
        pane.add(label_background, JLayeredPane.DEFAULT_LAYER);

        Color c = new Color(246, 70, 104);

        JLabel label_dodge = new JLabel("DODGE");
        label_dodge.setBounds(270, 5, 200, 200);
        pane.add(label_dodge, JLayeredPane.PALETTE_LAYER);
        label_dodge.setFont(font_title);
        label_dodge.setHorizontalAlignment(SwingConstants.CENTER);
        label_dodge.setVerticalTextPosition(SwingConstants.CENTER);
        label_dodge.setForeground(c);

        JLabel label_game = new JLabel("GAME");
        label_game.setBounds(270, 80, 200, 200);
        pane.add(label_game, JLayeredPane.PALETTE_LAYER);
        label_game.setFont(font_title);
        label_game.setHorizontalAlignment(SwingConstants.CENTER);
        label_game.setVerticalTextPosition(SwingConstants.CENTER);
        label_game.setForeground(c);

        JLabel label_left = new JLabel();
        ImageIcon left = new ImageIcon(Toolkit.getDefaultToolkit().createImage(StartScreen.class.getResource("/Characters/Character_01/down_1.png")));
        label_left.setIcon(left);
        label_left.setBounds(150, 30, 300, 300);
        pane.add(label_left, JLayeredPane.PALETTE_LAYER);

        JLabel label_right = new JLabel();
        ImageIcon right = new ImageIcon(Toolkit.getDefaultToolkit().createImage(StartScreen.class.getResource("/Characters/Character_04/down_1.png")));
        label_right.setIcon(right);
        label_right.setBounds(510, 30, 300, 300);
        pane.add(label_right, JLayeredPane.PALETTE_LAYER);

        button_start = new JButton("START");
        button_start.setForeground(Color.white);
        button_start.setBounds(270, 250, 200, 100);
        button_start.setOpaque(true);
        button_start.setBorderPainted(false);
        button_start.setContentAreaFilled(false);
        button_start.setFont(font_button);
        button_start.addActionListener(ctr);

        pane.add(button_start, JLayeredPane.PALETTE_LAYER);

        button_option = new JButton("OPTION");
        button_option.setFont(font);
        button_option.setBounds(240, 350, 250, 100);
        button_option.setOpaque(true);
        button_option.setBorderPainted(false);
        button_option.setContentAreaFilled(false);
        button_option.setFont(font_button);
        button_option.setHorizontalAlignment(SwingConstants.CENTER);
        button_option.setVerticalAlignment(SwingConstants.CENTER);
        button_option.setForeground(Color.white);
        button_option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToOption();
            }
        });

        pane.add(button_option, JLayeredPane.PALETTE_LAYER);

        button_exit = new JButton("EXIT");
        button_exit.setFont(font);
        button_exit.setBounds(270, 450, 200, 100);
        button_exit.setOpaque(false);
        button_exit.setBorderPainted(false);
        button_exit.setContentAreaFilled(false);
        button_exit.setFont(font_button);
        button_exit.setHorizontalAlignment(SwingConstants.CENTER);
        button_exit.setVerticalAlignment(SwingConstants.CENTER);
        button_exit.setForeground(Color.white);
        button_exit.addActionListener(ctr);

        pane.add(button_exit, JLayeredPane.PALETTE_LAYER);

        this.add(pane);
    }

    public void initializeVolumnControlDialog() {
        volumeControlDialog = new JDialog();
        volumeControlDialog.setSize(400, 200);
        volumeControlDialog.setResizable(false);
        volumeControlDialog.setLocationRelativeTo(this);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);

        volumeControlDialog.add(volumeSlider);
    }

    public void showVolumeControlDialog() {
        if (volumeControlDialog == null) {
            initializeVolumnControlDialog();
        }
        volumeControlDialog.setVisible(true);
    }

}
