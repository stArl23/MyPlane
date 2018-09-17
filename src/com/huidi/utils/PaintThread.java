package com.huidi.utils;

import com.huidi.GameStart;

public class PaintThread extends Thread{
    private GameStart gs;
    private boolean flag=true;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public PaintThread(GameStart gs) {
        this.gs = gs;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        do {
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gs.initView();
            if (flag) gs.repaint();
        } while (true);
    }

    /*public void currWait(){
        synchronized (lock){
            try {
                System.out.println("await");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
