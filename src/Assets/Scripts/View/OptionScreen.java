package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OptionScreen extends JPanel {
    Font font_title = null;
    Font font_button = null;
    Font font_letter = null;
    private Game frame;
    private mainPanel mainpanel;
    private JLabel w_label;
    private JLabel up_label;
    private JLabel s_label;
    private JLabel a_label;
    private JLabel d_label;
    private JLabel down_label;
    private JLabel right_label;
    private JLabel left_label;
    private JLabel k_label;
    private JLabel j_label;
    private JLabel flash_label;
    private JLabel ghost_label;
    private JLabel label_1;
    private JLabel label_2;

    public OptionScreen(Game frame) {
        this.frame = frame;
        this.setDoubleBuffered(true);
        init();
    }

    public void init() {
        this.setPreferredSize(new Dimension(frame.screenWidth, frame.screenHeight));

        this.setLayout(new BorderLayout());
        mainpanel = new mainPanel();
        mainpanel.setLayout(null);

        try {
            font_title = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(72f);
            font_button = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(72f);
            font_letter = Font.createFont(Font.TRUETYPE_FONT, new File("src/Assets/Sprites/Font/BrickSans-Bold.otf")).deriveFont(48f);
        } catch (Exception e) {
            font_title = new Font("Arial", Font.PLAIN, 16);
        }

        JLabel titleLabel = new JLabel("DODGE GAME");
        titleLabel.setFont(font_title);
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(220, -100, 300, 300);

        JLabel moveLabel = new JLabel("MOVEMENT");
        moveLabel.setFont(font_letter);
        moveLabel.setForeground(Color.white);
        moveLabel.setBounds(100, 30, 200, 200);

        JLabel spellLabel = new JLabel("SPELL");
        spellLabel.setFont(font_letter);
        spellLabel.setForeground(Color.white);
        spellLabel.setBounds(500, 30, 200, 200);

        ImageIcon wIcon = new ImageIcon("./src/Assets/Sprites/Items/W.png");
        wIcon = new ImageIcon(resizeImage(wIcon, 48));
        w_label = new JLabel(wIcon);
        w_label.setBounds(100, 140, 150, 150);

        ImageIcon sIcon = new ImageIcon("./src/Assets/Sprites/Items/S.png");
        sIcon = new ImageIcon(resizeImage(sIcon, 48));
        s_label = new JLabel(sIcon);
        s_label.setBounds(100, 190, 150, 150);

        ImageIcon aIcon = new ImageIcon("./src/Assets/Sprites/Items/A.png");
        aIcon = new ImageIcon(resizeImage(aIcon, 48));
        a_label = new JLabel(aIcon);
        a_label.setBounds(50, 190, 150, 150);

        ImageIcon dIcon = new ImageIcon("./src/Assets/Sprites/Items/D.png");
        dIcon = new ImageIcon(resizeImage(dIcon, 48));
        d_label = new JLabel(dIcon);
        d_label.setBounds(150, 190, 150, 150);

        ImageIcon upIcon = new ImageIcon("./src/Assets/Sprites/Items/Up.png");
        upIcon = new ImageIcon(resizeImage(upIcon, 48));
        up_label = new JLabel(upIcon);
        up_label.setBounds(100, 250, 150, 150);

        ImageIcon downIcon = new ImageIcon("./src/Assets/Sprites/Items/Down.png");
        downIcon = new ImageIcon(resizeImage(downIcon, 48));
        down_label = new JLabel(downIcon);
        down_label.setBounds(100, 300, 150, 150);

        ImageIcon leftIcon = new ImageIcon("./src/Assets/Sprites/Items/Left.png");
        leftIcon = new ImageIcon(resizeImage(leftIcon, 48));
        left_label = new JLabel(leftIcon);
        left_label.setBounds(50, 300, 150, 150);

        ImageIcon rightIcon = new ImageIcon("./src/Assets/Sprites/Items/Right.png");
        rightIcon = new ImageIcon(resizeImage(rightIcon, 48));
        right_label = new JLabel(rightIcon);
        right_label.setBounds(150, 300, 150, 150);

        ImageIcon flashIcon = new ImageIcon("./src/Assets/Sprites/Items/item_00.png");
        flashIcon = new ImageIcon(resizeImage(flashIcon, 96));
        flash_label = new JLabel(flashIcon);
        flash_label.setBounds(400, 220, 150, 150);

        ImageIcon ghostIcon = new ImageIcon("./src/Assets/Sprites/Items/item_01.png");
        ghostIcon = new ImageIcon(resizeImage(ghostIcon, 96));
        ghost_label = new JLabel(ghostIcon);
        ghost_label.setBounds(550, 220, 150, 150);

        ImageIcon jIcon = new ImageIcon("./src/Assets/Sprites/Items/J.png");
        jIcon = new ImageIcon(resizeImage(jIcon, 48));
        j_label = new JLabel(jIcon);
        j_label.setBounds(400, 120, 150, 150);

        ImageIcon kIcon = new ImageIcon("./src/Assets/Sprites/Items/K.png");
        kIcon = new ImageIcon(resizeImage(kIcon, 48));
        k_label = new JLabel(kIcon);
        k_label.setBounds(550, 120, 150, 150);

        ImageIcon Icon1 = new ImageIcon("./src/Assets/Sprites/Items/Num_1.png");
        Icon1 = new ImageIcon(resizeImage(Icon1, 48));
        label_1 = new JLabel(Icon1);
        label_1.setBounds(400, 320, 150, 150);

        ImageIcon Icon2 = new ImageIcon("./src/Assets/Sprites/Items/Num_2.png");
        Icon2 = new ImageIcon(resizeImage(Icon2, 48));
        label_2 = new JLabel(Icon2);
        label_2.setBounds(550, 320, 150, 150);

        JButton back_button = new JButton("BACK");
        back_button.setContentAreaFilled(false);
        back_button.setOpaque(false);
        back_button.setFont(font_button);
        back_button.setBorderPainted(false);
        back_button.setForeground(Color.white);
        back_button.setBounds(20, 400, 150, 150);
        back_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.switchToStart();
            }
        });

        mainpanel.add(back_button);
        mainpanel.add(label_1);
        mainpanel.add(label_2);
        mainpanel.add(j_label);
        mainpanel.add(k_label);
        mainpanel.add(ghost_label);
        mainpanel.add(flash_label);
        mainpanel.add(right_label);
        mainpanel.add(down_label);
        mainpanel.add(left_label);
        mainpanel.add(up_label);
        mainpanel.add(d_label);
        mainpanel.add(a_label);
        mainpanel.add(s_label);
        mainpanel.add(w_label);
        mainpanel.add(spellLabel);
        mainpanel.add(moveLabel);
        mainpanel.add(titleLabel);

        this.add(mainpanel, BorderLayout.CENTER);
    }

    public Image resizeImage(ImageIcon icon, int size) {
        Image image = icon.getImage();

        image = image.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);

        return image;
    }

    public class mainPanel extends JPanel {
        public ImageIcon background;

        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            background = new ImageIcon("./src/Assets/Sprites/Background/Screen/background_img.png");
            g.drawImage(background.getImage(), 0, 0, null);
        }
    }
}
