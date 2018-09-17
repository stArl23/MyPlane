package com.huidi.role;

import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;

import java.awt.*;

public class Booms extends MyRectangle{
    //static 会被全部对象共享
    private int i;
    public static final int WIDTH=100;
    public static final int HEIGHT=100;

    public Booms(int x, int y, GameStart gs) {
        super(x, y, WIDTH, HEIGHT, gs, true);
        i=0;
    }

    //TODO
    public void drawMe(Graphics g){
        if(i<11){
            g.drawImage(gs.boomImgs[i],x,y,width,height,null);
        }else{
            setIsAlive(false);
        }
        i++;
    }
}
