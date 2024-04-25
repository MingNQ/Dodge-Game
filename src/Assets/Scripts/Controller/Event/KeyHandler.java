package Controller.Event;

import View.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener, MouseListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed; // Control key event
    public boolean fistSkillPressed, secondSkillPressed;
    public boolean musicPressed, soundPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keyMovement(code);
        keyOption(code);
    }

    // Handle player movement and use item
    public void keyMovement(int code) {
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_J || code == KeyEvent.VK_NUMPAD1) {
                fistSkillPressed = true;
            }
            if (code == KeyEvent.VK_K || code == KeyEvent.VK_NUMPAD2) {
                secondSkillPressed = true;
            }
        }
    }

    public void keyOption(int code) {
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                // Handle Options
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum > 0) {
                        gp.ui.commandNum--;
                    }
                }
                if (gp.ui.subState == 1) {
                    if (gp.ui.commandNum > 0) {
                        gp.ui.commandNum--;
                    }
                }
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                // Handle Options
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
                        gp.music.volumeScale--;
                        gp.music.checkVolume();
                    }
                    if (gp.ui.commandNum == 1 && gp.sound.volumeScale > 0) {
                        gp.sound.volumeScale--;
                        gp.sound.checkVolume();
                    }
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                // Handle Options
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum < 2) {
                        gp.ui.commandNum++;
                    }
                }
                if (gp.ui.subState == 1) {
                    if (gp.ui.commandNum < 1) {
                        gp.ui.commandNum++;
                    }
                }
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
                        gp.music.volumeScale++;
                        gp.music.checkVolume();
                    }
                    if (gp.ui.commandNum == 1 && gp.sound.volumeScale < 5) {
                        gp.sound.volumeScale++;
                        gp.sound.checkVolume();
                    }
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                // Handle Options
                if (gp.ui.subState == 0) {
                    if (gp.ui.commandNum == 0) {
                        musicPressed = !musicPressed;
                    } else if (gp.ui.commandNum == 1) {
                        soundPressed = !soundPressed;
                    } else if (gp.ui.commandNum == 2) {
                        gp.ui.subState = 1;
                    }
                }
                if (gp.ui.subState == 1) {
                    if (gp.ui.commandNum == 0) {
                        gp.gameOver();
                        gp.getFrame().switchToStart();
                    } else if (gp.ui.commandNum == 1) {
                        gp.ui.subState = 0;
                        gp.ui.commandNum = 0;
                    }
                }
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            // Handle Options
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else {
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_J || code == KeyEvent.VK_NUMPAD1) {
            fistSkillPressed = false;
        }
        if (code == KeyEvent.VK_K || code == KeyEvent.VK_NUMPAD2) {
            secondSkillPressed = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
