package com.huidi.role;
import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;

import java.awt.*;

public class Bullet extends MyRectangle {
    public static final int PLAYER=1;
    public static final int ENEMY=2;

    public static int  SPEED=10;
    private static final int HEIGHT=10;
    private static final int WIDTH=10;

    public Bullet(int x, int y, GameStart gs, boolean isAlive) {
        super(x, y, WIDTH, HEIGHT, gs, isAlive);
    }

    //keyPressed 方法记录子弹的移动 记录子弹的移动，直线移动，要做边界检查
    //typeid 1 为玩家的子弹，typeid 2 为敌人的子弹
    public void move(int type){
        if (type==1){
            this.y=this.y-SPEED;
            if(y<=0)
                this.setIsAlive(false);
        }else{
            this.y=this.y+SPEED;
            if(y>=GameStart.HEIGHT)
                this.setIsAlive(false);
        }
    }

    public void drawMe(Graphics g,int type){
        move(type);
        g.drawImage(gs.bulletImgs[0],x,y,WIDTH,HEIGHT,null);
    }

    @Override
    public void drawMe(Graphics g) {

    }
}
