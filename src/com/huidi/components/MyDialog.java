package com.huidi.components;

import com.huidi.GameStart;
import javax.swing.*;

public class MyDialog extends JDialog {
    public static final int WIDTH=250;
    public static final int HEIGHT=120;
    public static final int BUTTON_WIDTH =60;
    public static final int BUTTON_HEIGHT=40;

    private JButton confirm, cancel;

    private GameStart gs;
    public MyDialog(GameStart gs, String title){
        super(gs,title);
        this.gs=gs;
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
        this.setLayout(null);
        confirm=new JButton("确认");
        cancel=new JButton("取消");
        confirm.setVisible(true);
        cancel.setVisible(true);
        confirm.setBounds(35,HEIGHT/2-BUTTON_HEIGHT,BUTTON_WIDTH,BUTTON_HEIGHT);
        cancel.setBounds(WIDTH-115,HEIGHT/2-BUTTON_HEIGHT,BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(confirm);
        this.add(cancel);

        confirm.addActionListener(e -> {
            System.exit(1);
            System.out.println("游戏结束");
        });

        cancel.addActionListener(e -> {
            gs.restart();
            gs.getPaintThread().setFlag(true);
            //关闭窗口
            this.dispose();
        });


    }
}
