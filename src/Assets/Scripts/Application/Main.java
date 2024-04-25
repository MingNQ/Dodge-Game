package Application;

import View.Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("sun.java2d.opengl", "True");

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            new Game();
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}