package com.huidi;

import com.huidi.role.*;
import com.huidi.utils.PaintThread;
import com.huidi.utils.Utils;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;


public class GameStart extends Frame {
    public Image img, planeImg,bgImg,lifePlane;
    public Image[] enemyImgs,bulletImgs,boomImgs,foodImgs;
    private Object lock;


    private final static int MAX_LEVEL=5;
    //定义敌机队列
    //private int enemySize=5;
    private Toolkit toolkit=this.getToolkit();
    public static final int HEIGHT = 700;
    public static final int WIDTH = 600;
    private static Random random=new Random();


    private Plane plane = new Plane(250, 500, this, true);
    private List<Enemy> enemies=new ArrayList<Enemy>();
    private List<Booms>boomsList=new ArrayList<Booms>();
    private List<Food> foods=new ArrayList<Food>(3);//场上最多同时出现3个食物

    private List<LifePanel> lifePanels=new ArrayList<LifePanel>(Plane.DEF_BLOOD);


    private BackGround bg=new BackGround(this);

    private int level=1;

    private int passPoint=500;
    private List<Bullet> enemyBullets=new ArrayList<>();
    private PaintThread paintThread;


    //getter setter

    public List<Food> getFoods() {
        return foods;
    }
    public int getLevel() {
        return level;
    }

    public PaintThread getPaintThread() {
        return paintThread;
    }

    public List<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public List<LifePanel> getLifePanels() {
        return lifePanels;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Booms> getBoomsList() {
        return boomsList;
    }

    public void setBoomsList(List<Booms> boomsList) {
        this.boomsList = boomsList;
    }

    public Plane getPlane() {
        return plane;
    }

    public GameStart() {
        this.setLayout(null);
        this.setTitle("飞机大战");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setFont(new Font("楷体", Font.PLAIN, 16));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            //按下某个键调用该方法
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_SPACE:
                        paintThread.setFlag(!paintThread.getFlag());
                }
                //super.keyPressed(e);
            }
            //按键释放时触发
            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyRealised(e);
            }
        });
        //加满生命值
        resurrection();
        paintThread =new PaintThread(this);
        paintThread.start();
    }

    private void resurrection() {
        for (int i = 0; i < Plane.DEF_BLOOD ; i++) {
            //初始化LifePanel
            lifePanels.add(new LifePanel((i+1)*3*LifePanel.WIDTH/2,3*LifePanel.HEIGHT/2+10,this));
        }
    }

    //从第一关开始 每关的通关分数为斐波纳数列对应的数值*500
    private void passLevel(){
        if(plane.getCounts()>=passPoint){
            System.out.println("next level");
            level++;
            passPoint+=level*500;
        }
    }


    public static void main(String[] args) {
        // write your code here
        new GameStart();
    }

    //设置图形页面默认属性
    public void initView() {
        bgImg = toolkit.getImage(GameStart.class.getResource("./imgs/bg01.jpg"));
        if (plane.isLeft()) {
            planeImg = toolkit.getImage(GameStart.class
                    .getResource("./imgs/51.png"));
        } else if (plane.isRight()) {
            planeImg = toolkit.getImage(GameStart.class
                    .getResource("./imgs/61.png"));
        } else {
            planeImg = toolkit.getImage(GameStart.class
                    .getResource("./imgs/7.png"));
        }
        boomImgs = new Image[] {
                toolkit.getImage(GameStart.class.getResource("./imgs/b1.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b2.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b3.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b4.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b5.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b6.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b7.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b8.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b9.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b10.gif")),
                toolkit.getImage(GameStart.class.getResource("./imgs/b11.gif")) };
        enemyImgs = new Image[] {
                toolkit.getImage(GameStart.class.getResource("./imgs/5.png")),
                toolkit.getImage(GameStart.class.getResource("./imgs/21.png")),
                toolkit.getImage(GameStart.class.getResource("./imgs/15.png")),
                toolkit.getImage(GameStart.class.getResource("./imgs/敌机2.png")) };
        bulletImgs = new Image[] {
                toolkit.getImage(GameStart.class.getResource("./imgs/子弹1.png")),
                toolkit.getImage(GameStart.class.getResource("./imgs/坦克.png"))
        };
      /*  bulletEmImg = toolkit.getImage(GameStart.class
                .getResource("./imgs/敌军子弹.png"));
        bulletEm1Img = toolkit.getImage(GameStart.class
                .getResource("./imgs/敌军子弹1.png"));
        boosImg = toolkit.getImage(GameStart.class
                .getResource("./imgs/BossA.png"));
        bulletBossImgs = new Image[] {
                toolkit.getImage(GameStart.class
                        .getResource("./imgs/BOSS子弹.png")),
                toolkit.getImage(GameStart.class.getResource("./imgs/子弹2.png")) };*/
      /*  ult = toolkit.getImage(GameStart.class.getResource("./imgs/BKILL.png"));
        continueImg = toolkit.getImage(GameStart.class
                .getResource("./imgs/continue.png"));

        startImg = toolkit.getImage(GameStart.class
                .getResource("./imgs/gamebegin1.gif"));*/
        foodImgs = new Image[] {
                toolkit.getImage(GameStart.class.getResource("./imgs/食物1.jpg")),
                toolkit.getImage(GameStart.class.getResource("./imgs/22.png")) };
        lifePlane = toolkit.getImage(GameStart.class
                .getResource("./imgs/飞猪boss子弹.png"));
    }

    @Override
    public void paint(Graphics g) {
        bg.drawMe(g);
        paintLifePanels(g);
        passLevel();
        g.drawString("Level: "+level,500,50);
        g.drawString("Count: "+plane.getCounts(),500,70);
        //处理子弹
        Utils.drawAndCleanBullet(plane.getBullets(),g,Bullet.PLAYER);
        Utils.drawAndCleanBullet(enemyBullets,g,Bullet.ENEMY);
        //处理敌人
        Enemy.randomCreate(500,this);
        Food.randomCreate(600,this);
        Utils.drawAndClean(enemies,g);
        Utils.drawAndClean(foods,g);
        //处理boom
        Utils.drawAndClean(boomsList,g);



       // drawBooms(g);
        //if (!boomsList.isEmpty())boomsList.get(0).drawMe(g);
        plane.drawMe(g);
    }

    /*private void drawBooms(Graphics g) {
        //System.out.println("Boomslist size: "+boomsList.size());
        for(int i=0;i<boomsList.size();i++){
            Booms booms=boomsList.get(i);
            if(booms.getIsAlive()){
                System.out.println("draw booms");
                booms.drawMe(g);
                //Utils.drawEachMilSec(booms,g,100,1100);
            }else{
                boomsList.remove(booms);
            }
        }

    }*/

    //重新开始
        public void restart() {
            resurrection();
            plane.reBlood();
        }

    /*private void boomsHappen(Enemy e,Graphics g) {
        //一次添加一组爆炸画面
        Booms booms=new Booms(e.x,e.y,this);
        for (int i = 0; i <boomImgs.length ; i++)
            booms.drawMe(g);
    }*/

    private void paintLifePanels(Graphics g) {
        for(LifePanel lp:lifePanels){
            g.drawImage(lifePlane,lp.x,lp.y,lp.width,lp.height,null);
        }
    }

    @Override
    public void update(Graphics g) {
        // TODO Auto-generated method stub
        //System.out.println("is updated");
        //应对图片闪烁做双缓冲处理
        if (img == null) {
            img = this.createImage(WIDTH, HEIGHT);
        }
        Graphics graphics = img.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        //?
        print(graphics);
        g.drawImage(img, 0, 0, null);
    }

}
