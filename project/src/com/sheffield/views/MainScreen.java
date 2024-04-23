package com.sheffield.views;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen(String title) throws HeadlessException {
        super(title);

        setSize(400, 300);
        //setLocationRelativeTo(null);

        JLabel welcomeMsg = new JLabel("Welcome Customer");
        add(welcomeMsg);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}