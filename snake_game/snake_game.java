package com.swing.snake_game;

import javax.swing.*;
import java.awt.*;

public class snake_game {
    public static void main(String[] args) {
        JFrame frame=new JFrame("SNAKE GAME");
        frame.setBounds(10,10,905,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          Game_panel panel=new Game_panel();
          panel.setBackground(Color.DARK_GRAY);
          frame.add(panel);
        frame.setVisible(true);
    }
}
