/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Geri
 */
public class SetSizeFrame extends JFrame {

    public SetSizeFrame() {
        setTitle("Select size!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JButton size3 = new JButton("3x3");
        final JButton size4 = new JButton("4x4");
        final JButton size6 = new JButton("6x6");
        ButtonGroup group = new ButtonGroup();
        group.add(size3);
        group.add(size4);
        group.add(size6);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.add(size3);
        panel.add(size4);
        panel.add(size6);

        size3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameFrame(3).setVisible(true);
            }

        });

        size4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameFrame(4).setVisible(true);
            }
        });

        size6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new GameFrame(6).setVisible(true);
            }
        });

        add(panel);
        pack();
        setResizable(false);

    }

}
