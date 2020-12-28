package programma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    Toolkit toolkit= Toolkit.getDefaultToolkit();
    Dimension dimension= toolkit.getScreenSize();
    public MyFrame(){
        GarageManager gm = new GarageManager();
        GarageLight light = new GarageLight();
        GarageDoor door = new GarageDoor();
        GarageLightOn lightOn = new GarageLightOn(light);
        GarageLightOff lightOff = new GarageLightOff(light);
        GarageDoorUp doorUp = new GarageDoorUp(door);
        GarageDoorDown doorDown = new GarageDoorDown(door);
        /////////////////////

        /////////////////////
        JPanel panel=new JPanel();
        JButton door1=new JButton("Open door");
        JButton door2=new JButton("Close door");
        JButton light1=new JButton("ON light");
        JButton light2=new JButton("OFF light");
        JButton apply=new JButton("Apply");
        door1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gm.setCommand(doorUp);
            }
        }
        );
        door2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gm.setCommand(doorDown);
            }
        }
        );
        light1.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e){
                gm.setCommand(lightOn);
            }
        }
        );
        light2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gm.setCommand(lightOff);
            }
        }
        );
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                gm.buttonApply();
            }
        }
        );

        panel.add(door1);
        panel.add(door2);
        panel.add(light1);
        panel.add(light2);
        panel.add(apply);
        panel.add(door);
        panel.add(light);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(dimension.width/2-500,dimension.height/2-300,1000, 600);
        setTitle("Garage");
        setVisible(true);
        add(panel);
    }
}
