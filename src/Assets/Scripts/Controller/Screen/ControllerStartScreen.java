package Controller.Screen;

import View.StartScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerStartScreen implements ActionListener {
    private StartScreen screen;

    /**
     * @param screen
     */
    public ControllerStartScreen(StartScreen screen) {
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String check = e.getActionCommand();
        if (check.equals("START")) {
            screen.getFrame().switchToSelect();
        } else if (check.equals("EXIT")) {
            System.exit(0);
        }
    }

}
