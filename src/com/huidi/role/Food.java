package com.huidi.role;

import com.huidi.GameStart;
import com.huidi.role.model.MyRectangle;
import com.huidi.utils.Utils;

import java.awt.*;
import java.util.Random;

public class Food extends MyRectangle{
    public static final int SPEED=5;
    public static final int HEIGHT=30;
    public static final int WIDTH=30;
    private boolean isReverse=false;
    private static Random random=new Random();

    public Food(int x, int y, GameStart gs) {
        super(x, y, WIDTH, HEIGHT, gs, true);
    }


    public static void randomCreate(int scope,GameStart gs) {
        //出现的food不超过3个
        if(random.nextInt(100)>97&&gs.getFoods().size()<3)
            gs.getFoods().add(new Food(random.nextInt(scope),0,gs));
    }

    private void move(){
        //设向左为正向，这里判断是否逆向
        if(y<GameStart.HEIGHT)
            y+=SPEED;
        else
            setIsAlive(false);
        //螃蟹式移动
        if (!isReverse){
            if (x<gs.WIDTH-WIDTH){
                x+=SPEED*2;
            }else {
                isReverse=!isReverse;
            }
        }
       else{
            if (x>WIDTH){
                x-=SPEED*2;
            }else {
                isReverse=!isReverse;
            }
        }
    }

    private void isHitted(){
        if(Utils.isHitted(gs.getPlane(),this)){
            this.setIsAlive(false);
            int length=gs.getLifePanels().size();
            //最多5血
            //TODO
            //做些什么事
            System.out.println("lifes "+length);
            if (length<5){
                gs.getLifePanels().add(new LifePanel((length+1)*3*LifePanel.WIDTH/2,3*LifePanel.HEIGHT/2+10,gs));
                gs.getPlane().addBlood();
            }


        }
    }

    @Override
    public void drawMe(Graphics g) {
        isHitted();
        move();
        g.drawImage(gs.foodImgs[0],x,y,width,height,null);
    }
}
