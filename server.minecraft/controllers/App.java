package controllers;

import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {

            MainMenu server = new MainMenu();

            server.init();

        });
    }
}