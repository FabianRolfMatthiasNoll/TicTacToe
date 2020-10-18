package com.Fabian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    //int count = 0;
    int alternate = 0;
    //private JLabel label;
    private JFrame frame;
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JPanel panel;

    public GUI() {

        frame = new JFrame();

        button = new JButton("1");
        button.addActionListener(this);

        button2 = new JButton("2");
        button2.addActionListener(this);

        button3 = new JButton("3");
        button3.addActionListener(this);

        button4 = new JButton("4");
        button4.addActionListener(this);

        button5 = new JButton("5");
        button5.addActionListener(this);

        button6 = new JButton("6");
        button6.addActionListener(this);

        button7 = new JButton("7");
        button7.addActionListener(this);

        button8 = new JButton("8");
        button8.addActionListener(this);

        button9 = new JButton("9");
        button9.addActionListener(this);

        //label = new JLabel("Number of Clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(3, 3));
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        //panel.add(label);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TicTacToe Motherfucker");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
        if(alternate%2 == 0)
            buttonClicked.setText("X");
        else
            buttonClicked.setText("O");

        //count++;
        //label.setText("Number of Clicks: " + count);
    }
}
