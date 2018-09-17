package com.huidi.role;

import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;

import java.awt.*;

public class LifePanel extends MyRectangle implements Drawable {
    public static final int WIDTH=20,HEIGHT=20;

    public LifePanel(int x, int y, GameStart gs) {
        super(x, y, WIDTH, HEIGHT,gs,true);
    }

    @Override
    public void drawMe(Graphics g) {
        g.drawImage(gs.lifePlane,x,y,width,height,null);
    }
}
