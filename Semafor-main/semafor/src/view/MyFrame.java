/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import semafor.StateSemaphor;

public class MyFrame extends JFrame {

    MyPanel myPanel;
    StateSemaphor ss;
//    Controller controller;

    public MyFrame(MyPanel myPanel, StateSemaphor s) {
        this.myPanel = myPanel;
        ss = s;
        JToolBar bar = new JToolBar();
        add(bar, BorderLayout.NORTH);
        JMenuItem start = new JMenuItem(new ImageIcon("start.png"));
        JMenuItem stop = new JMenuItem(new ImageIcon("stop.png"));
        JMenuItem continue1 = new JMenuItem(new ImageIcon("continue.png"));
        stop.setEnabled(false);
        continue1.setEnabled(false);
        start.setEnabled(true);
        bar.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(ss).start();
                stop.setEnabled(true);
                continue1.setEnabled(false);
                start.setEnabled(false);
            }
        });

        bar.add(continue1);
        continue1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ss.myresume();
                start.setEnabled(false);
                stop.setEnabled(true);
                continue1.setEnabled(false);
            }
        });

        bar.add(stop);
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ss.mysuspend();
                start.setEnabled(false);
                stop.setEnabled(false);
                continue1.setEnabled(true);
            }
        });
        add(this.myPanel);
        this.setSize(350, 780);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
