package com.huidi.role;

import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;

import java.awt.*;

public class BackGround extends MyRectangle{
    private static int SPEED=3;
    private int y1 =0, y2 =700;
    private static int WIDTH=600,HEIGHT=700;

    public BackGround(GameStart gs) {
        super(0,0,WIDTH,HEIGHT,gs,true);
        this.gs = gs;
    }

    private void move(){
        y1 +=SPEED;
        y2 +=SPEED;
        if(y1 >700) y1 = y2 -700;
        if(y2 >700) y2 = y1 -700;
    }
    public void drawMe(Graphics g){
        //System.out.println(" y1 = "+y1+" y2 = "+y2);
        g.drawImage(gs.bgImg,x, y1,WIDTH,HEIGHT,null);
        g.drawImage(gs.bgImg,x, y2,WIDTH,HEIGHT,null);
        move();
    }

}
