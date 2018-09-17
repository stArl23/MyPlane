package com.huidi.role;

import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;
import com.huidi.utils.Utils;

import java.awt.*;
import java.util.Random;

public class Enemy extends MyRectangle{

    private static final int SPEED=5;
    public static final int POINTS=100;
    private Image enemyImg;
    private static Random random=new Random();
    //飞机默认宽80
    //飞机默认高90
    public static final int WIDTH=80;
    public static final int HEIGHT =90;

    public Enemy(int x, int y, int width, int height, GameStart gs, boolean isAlive) {
        super(x, y, width, height, gs, isAlive);
        enemyImg=gs.enemyImgs[random.nextInt(4)];
    }

    //TODO
    //敌人的移动和开火
    private void action(){

        this.y+=SPEED;
        if(this.y>GameStart.HEIGHT)
            setIsAlive(false);
        if (random.nextInt(1000)>980)
            fire();
    }

    private void fire(){
        gs.getEnemyBullets().add(new Bullet(x+Enemy.WIDTH/2,y+HEIGHT,gs,true));
    }

    //drawMe方法来更新GS里的图像
    public void drawMe(Graphics g){
        isHitted();
        action();
        g.drawImage(enemyImg,x,y,WIDTH,HEIGHT,null);
    }

    private void isHitted() {
        for (Bullet b : gs.getPlane().getBullets()) {
            if (Utils.isHitted(b, this)) {
                //被击中则将敌机状态调为死亡
                this.setIsAlive(false);
                b.setIsAlive(false);
                gs.getBoomsList().add(new Booms(this.x, this.y, gs));
                //System.out.println("count: " + gs.getPlane().getCounts());
                gs.getPlane().setCounts(gs.getPlane().getCounts() + Enemy.POINTS);
            }
        }
    }

    public static void randomCreate(int scope,GameStart gs) {
        if(random.nextInt(100)>97)
            gs.getEnemies().add(new Enemy(random.nextInt(scope),0,WIDTH,HEIGHT,gs,true));
    }
}
