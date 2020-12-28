package semafor;

import graphicsModel.GraphicsModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import static semafor.ColorEnum.GreenTYellowRed;
import static semafor.ColorEnum.GreenYellowTRed;
import static semafor.ColorEnum.TGreenYellowRed;
import static semafor.ColorEnum.GreenYellowRed;

public class StateSemaphor implements Runnable {

    Color green;
    Color red;
    Color yellow;
    Color nul;
    Color state;
    Color oldState;
    GraphicsModel gm;
    int count=100;
    boolean suspendFlag = false;
    int kol =3;
    int time =500;
    public StateSemaphor(GraphicsModel model) {
        green = new Green();
        red = new Red();
        yellow = new Yellow();
        nul =new Null();
        state = green;
        oldState = green;
        gm = model;
        suspendFlag = false;
    }

    public void changeState() {
        gm.setColor(state.getColorEnum());
        try {
                Thread.sleep(count);
            } catch (InterruptedException ex) {
                Logger.getLogger(Green.class.getName()).log(Level.SEVERE, null, ex);
            }
        state.changeColor(kol);
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            changeState();
            oldState.stop();
        }

    }
    public interface Color{
    public void stop();
    public ColorEnum getColorEnum();
    public abstract void changeColor(int w);
    }

    public synchronized void mysuspend() {
        suspendFlag = true;
    }

    public synchronized void myresume() {
        suspendFlag = false;
        notify();

    }



    public class Green implements Color {
        ColorEnum colorEnum;
        public Green() {
           colorEnum = TGreenYellowRed; 
        }

        @Override
        public void changeColor(int w) {
            if(w>0){
            oldState = green;
            state = nul;
            }
            else{
                oldState = green;
                state = yellow;
                kol=3;
            }
        }

        @Override
        public void stop() {
            try {
                Thread.sleep(time);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(StateSemaphor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public ColorEnum getColorEnum() {
            return colorEnum;
        }
    }
    public class Null implements Color {
        ColorEnum colorEnum;
        public Null() {
            colorEnum = GreenYellowRed;
        }

        @Override
        public void changeColor(int w) {
            oldState = nul;
            state = green;
            time = 30;
            kol--;
        }
        @Override
        public void stop() {
            try {
                Thread.sleep(30);
                synchronized (this){
                    while (suspendFlag) {
                        wait();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(StateSemaphor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public ColorEnum getColorEnum() {
            return colorEnum;
        }
    }

    public class Red implements Color {
        ColorEnum colorEnum;
        public Red() {
            colorEnum = GreenYellowTRed; 
        }

        @Override
        public void changeColor(int w) {
            oldState = red;
            state = yellow;
            time =500;
        }
        @Override
        public void stop() {
            try {
                Thread.sleep(500);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(StateSemaphor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public ColorEnum getColorEnum() {
            return colorEnum;
        }
    }

    public class Yellow implements Color{
        ColorEnum colorEnum;
        public Yellow() {
            count = 100;
            colorEnum = GreenTYellowRed; 
        }

        @Override
        public void changeColor(int w) {
            if (oldState == red) {
                state = green;
                oldState = yellow;
            } 
            else {
                state = red;
                oldState = yellow;
            }
            time =500;
        }
        @Override
        public void stop() {
            try {
                Thread.sleep(500);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(StateSemaphor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public ColorEnum getColorEnum() {
            return colorEnum;
        }
    }
}
