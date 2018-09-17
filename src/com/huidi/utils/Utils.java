package com.huidi.utils;

import com.huidi.GameStart;
import com.huidi.components.MyDialog;
import com.huidi.role.Bullet;
import com.huidi.role.interfaces.Drawable;
import com.huidi.role.model.MyRectangle;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Utils {

    private static Timer timer=new Timer(true);
    private static Random random=new Random();
    //判断是否击中
    public static boolean isHitted(MyRectangle attacker,MyRectangle defender){
        return attacker.getIsAlive()&&defender.getIsAlive()&&attacker.intersects(defender);
    }

    public static void drawEachMilSec(Drawable dw, Graphics g, long millionSecond,long duringTime){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(this.scheduledExecutionTime()>duringTime)
                    this.cancel();
                dw.drawMe(g);

            }
        }, millionSecond);

    }

    //绘制和清理MyRectangle 下属的一般类
    //使用for-each 循环 抛出java.util.ConcurrentModificationException，
    // 改用迭代器的remove方法
    public static void drawAndClean(List myRectangles, Graphics g){
        for(Iterator iter=myRectangles.iterator();iter.hasNext();){
            MyRectangle temp= (MyRectangle) iter.next();
            if (temp.getIsAlive())
                temp.drawMe(g);
            else
                iter.remove();
        }
    }

    //绘制和清理子弹
    public static void drawAndCleanBullet(List<Bullet> bullets, Graphics g, int type){
        for(Iterator<Bullet> iter=bullets.iterator();iter.hasNext();){
            Bullet temp=iter.next();
            if (temp.getIsAlive())
                temp.drawMe(g,type);
            else
                iter.remove();
        }
    }

    public static void createNewDialog(GameStart gs){
        MyDialog confirmDialog=new MyDialog(gs,"是否退出游戏");
        confirmDialog.setBounds(550,200,250,120);
        //gs.add(confirmDialog);
        gs.getPaintThread().setFlag(false);
    }






    //边界检查 由于y坐标是从上到下增加的，所以y应该大于上边界坐标小于下边界坐标
    /*public static boolean boundCheck(int x,int y,int upBound,int downBound,int leftBound,int rightBound){
        return leftBound<=x&&x<=rightBound&&y>=upBound&&y<=downBound;
    }*/
}
