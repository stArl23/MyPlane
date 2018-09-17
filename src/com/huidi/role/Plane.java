package com.huidi.role;

import com.huidi.GameStart;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;
import com.huidi.utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Plane extends MyRectangle implements Drawable {
    private static final int SPEED=10;
    public static final int DEF_BLOOD=5;
    //TODO
    private static int bloods =DEF_BLOOD;

    //飞机默认宽60
    //飞机默认高70
    public static final int WIDTH=80;
    public static final int HEIGHT =90;

    private boolean isUp=false,isDown=false,isLeft=false,isRight=false;
    private int count=0;
    private List<Bullet> bullets= new ArrayList<Bullet>();
    public List<Bullet> getBullets() {
        return bullets;
    }

    public Plane(int x, int y, GameStart gs, boolean isAlive) {
        super(x, y,WIDTH, HEIGHT, gs, isAlive);
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean isDown() {
        return isDown;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isRight() {
        return isRight;
    }

    public boolean isKilled(){
        System.out.println(bloods);
        return --bloods <=0;
    }

    public void reBlood(){
        bloods=DEF_BLOOD;
    }

    public void addBlood(){
        bloods++;
    }

    public void setCounts(int points){
        count=points;
    }

    public int getCounts(){
        return count;
    }
    //keyProcess方法 设置move方向响应，为了后面的四个斜方向做扩展
    public void keyPressed(KeyEvent event){
        switch (event.getKeyCode()){
            case KeyEvent.VK_W:
                isUp=true;
                break;
            case KeyEvent.VK_A:
                isLeft=true;
                break;
            case KeyEvent.VK_S:
                isDown=true;
                break;
            case KeyEvent.VK_D:
                isRight=true;
                break;
            case KeyEvent.VK_J:
                fire();
                break;
        }
    }
    //keyRealised 方法
    public void keyRealised(KeyEvent event){
        switch (event.getKeyCode()){
            case KeyEvent.VK_W:
                isUp=false;
                break;
            case KeyEvent.VK_A:
                isLeft=false;
                break;
            case KeyEvent.VK_S:
                isDown=false;
                break;
            case KeyEvent.VK_D:
                isRight=false;
                break;
        }
    }
    private void action(){
        //分别对应上下左右四个方向，响应时按照指定速度向指定方向推进
        int upBound = HEIGHT / 2 + SPEED;
        int downBound = GameStart.HEIGHT - 3 * HEIGHT / 2 - SPEED;
        int leftBound = WIDTH / 2 + SPEED;
        int rightBound = GameStart.WIDTH - 3 * WIDTH / 2 - SPEED;

        if (isUp&&!isDown&&!isLeft&&!isRight){
            //坐标向左下增加
            //上边界刚好是半个机身的高度，下边界刚好是面板高度减去半个机身高度，左右边界同理
            if (y>= upBound)
                y-=SPEED;
        }
        else if (!isUp&&isDown&&!isLeft&&!isRight){
            if (y<= downBound)
                y+=SPEED;
        }
        else if (!isUp&&!isDown&&isLeft&&!isRight){
            if (x>= leftBound)
                x-=SPEED;
        }else if (!isUp&&!isDown&&!isLeft&&isRight){
            if (x<= rightBound)
                x+=SPEED;
        }//左上移动
        else if(isUp&&!isDown&&isLeft&&!isRight){
            if (y>= upBound&&x>=leftBound) {
                y -= SPEED;
                x -= SPEED;
            }
        }//右下
        else if(isUp&&!isDown&&!isLeft&&isRight){
            if (y>= upBound&&x<=rightBound) {
                y -= SPEED;
                x += SPEED;
            }
        }//左下
        else if(!isUp&&isDown&&isLeft&&!isRight){
            if (y<=downBound&&x>=leftBound) {
                y += SPEED;
                x -= SPEED;
            }
        }//右下
        else if(!isUp&&isDown&&!isLeft&&isRight){
            if (y<=downBound&&x<=rightBound) {
                y += SPEED;
                x += SPEED;
            }
        }
        else{
            //TODO
        }
        //判断是否被击中
        isHitted();
        //hit();
    }

    //TODO
    //开火方法，开火后将一个子弹类加入，飞机的子弹队列
    public void fire(){
        bullets.add(new Bullet(x+Plane.WIDTH/2,y,gs,true));
    }

    /*private void hit(){
        for(Bullet b:bullets){
            List<Enemy> enemies=gs.getEnemies();
            for (Enemy e:enemies){
                if(Utils.isHitted(b,e)){//被击中则将敌机状态调为死亡
                    e.setIsAlive(false);
                    b.setIsAlive(false);
                    gs.getBoomsList().add(new Booms(e.x,e.y,gs));
                    this.count+=Enemy.POINTS;
                }
            }
        }
    }*/

    //飞机被击中
    private void isHitted() {
       boolean flag=false;
        for(Bullet b:gs.getEnemyBullets()){
            if(Utils.isHitted(b,this)){
                flag=true;
                b.setIsAlive(false);
                break;
            }
        }
        //如果被敌机撞中
        for(Enemy e:gs.getEnemies()){
            if (Utils.isHitted(e,this)){
                flag=true;
                e.setIsAlive(false);
                break;
            }
        }
        List<LifePanel> lifePanels=gs.getLifePanels();
        if (flag&&this.isKilled()){//扣血并判断是否继续游戏
            lifePanels.remove(0);
            //创建退出窗口
            Utils.createNewDialog(gs);
        }
        else if(flag)
            lifePanels.remove(lifePanels.size()-1);
    }


    //drawMe 方法，更新frame里的图像
    public void drawMe(Graphics g){
        action();
        g.drawImage(gs.planeImg,x,y,WIDTH,HEIGHT,null);
    }
}
