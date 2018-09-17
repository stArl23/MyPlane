package com.huidi.role.model;

import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;

import java.awt.*;

public class MyRectangle extends Rectangle implements Drawable{
    //是否存活
    private boolean isAlive;
    public GameStart gs;

    public MyRectangle(int x, int y, int width, int height, GameStart gs, boolean isAlive) {
        super(x, y, width, height);
        this.gs=gs;
        this.isAlive=isAlive;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public void drawMe(Graphics g) {

    }
}
