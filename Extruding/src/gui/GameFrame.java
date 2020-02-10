/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import engine.GameEngine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
 *
 * @author Geri
 */
public class GameFrame extends JFrame {

    private int SIZE;
    private GameEngine engine;
    private final JPanel border = new JPanel();
    private final JPanel gameArea = new JPanel();
    private final JLabel statusbar = new JLabel("");
    private final MyButton[][] fields;
    private int selectedI;
    private int selectedJ;

    public GameFrame(int size) {
        this.SIZE = size;
        engine = new GameEngine(SIZE);
        fields = new MyButton[SIZE][SIZE];
        setTitle("Extruding");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

       
        KeyStroke btnA = KeyStroke.getKeyStroke(VK_A, 0);
        Action aPressed = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine.moveLeft(selectedI, selectedJ);
                refresh();
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(btnA, "btnA");
        getRootPane().getActionMap().put("btnA", aPressed);

        KeyStroke btnD = KeyStroke.getKeyStroke(VK_D, 0);
        Action dPressed = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine.moveRight(selectedI, selectedJ);
                refresh();
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(btnD, "btnD");
        getRootPane().getActionMap().put("btnD", dPressed);

        KeyStroke w = KeyStroke.getKeyStroke(VK_W, 0);
        Action leftPressed = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine.moveUp(selectedI, selectedJ);
                refresh();
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(w, "w");
        getRootPane().getActionMap().put("w", leftPressed);

        KeyStroke s = KeyStroke.getKeyStroke(VK_S, 0);
        Action rightPressed = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine.moveDown(selectedI, selectedJ);
                refresh();
            }
        };

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(s, "s");
        getRootPane().getActionMap().put("s", rightPressed);
        
        

        border.setLayout(new BorderLayout());
        gameArea.setLayout(new GridLayout(SIZE, SIZE));

        JMenuBar menubar = new JMenuBar();
        border.add(menubar, BorderLayout.NORTH);
        JMenu file = new JMenu("Menü");
        menubar.add(file);

        JMenuItem setsize = new JMenuItem("Set size");
        setsize.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SetSizeFrame().setVisible(true);
                dispose();
            }

        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        JMenuItem newgame = new JMenuItem("New Game");
        newgame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                engine = new GameEngine(SIZE);
                engine.placeFields();
                refresh();
            }
        });
        file.add(newgame);
        file.add(setsize);
        file.add(exit);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final MyButton field = new MyButton(i, j);
                field.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedI = field.getI();
                        selectedJ = field.getJ();
                    }
                });
                fields[i][j] = field;
                gameArea.add(field);
            }
        }

        border.add(gameArea, BorderLayout.CENTER);
        statusbar.setBorder(BorderFactory.createBevelBorder(1));
        border.add(statusbar, BorderLayout.SOUTH);
        add(border);
        setResizable(false);
    }

    public void refresh() {
        statusbar.setText(engine.getRounds());
        selectedI = 200;
        selectedJ = 200;
        if (engine.gameOver() == 0) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (engine.getFieldStatus(i, j) == 1) {
                        fields[i][j].setBackground(Color.decode("#000000"));
                    }
                    if (engine.getFieldStatus(i, j) == 2) {
                        fields[i][j].setBackground(Color.decode("#FFFFFF"));
                    }
                    if (engine.getFieldStatus(i, j) == 0) {
                        fields[i][j].setBackground(Color.decode("#D3D3D3"));
                    }
                }
            }

        } else {
            if (engine.gameOver() == 1) {
                congratulate(1);
                engine = new GameEngine(SIZE);
                engine.placeFields();
                refresh();
            }
            if (engine.gameOver() == 2) {
                congratulate(2);
                engine = new GameEngine(SIZE);
                engine.placeFields();
                refresh();
            }
            if (engine.gameOver() == 3) {
                congratulate(3);
                engine = new GameEngine(SIZE);
                engine.placeFields();
                refresh();
            }
        }
    }
    
    private void congratulate(int playerNumber){
        UIManager UI = new UIManager();
        if(playerNumber == 1 || playerNumber == 2 || playerNumber == 3){
            UI.put("OptionPane.messageForeground", Color.decode("#000000"));
        }
        JOptionPane.showMessageDialog(this, getWinMessage(playerNumber),"Game Over!", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getWinMessage(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return "WHITE won!";
            case 2:
                return "BLACK won!";
            case 3:
                return "DRAW!";
            default:
                return "0";
        }
    }
}
