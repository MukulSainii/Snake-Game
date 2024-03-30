package com.swing.snake_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game_panel extends JPanel implements ActionListener, KeyListener {
    private int[]snakexlength=new int[750];
    private int[] snakeylength=new int[750];
    //enemy position
    private int[] xpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] ypos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private Random random=new Random();
    private int enemyX,enemyY;
    private boolean gameOver=false;
    private int lengthofsnake=3;
    private int moves=0;
    private Timer timer;
    private int delay=100;
    private int score=0;
    private boolean left=false;
    private boolean right=true;
    private boolean up=false;
    private boolean down=false;
        Game_panel(){
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(true);
            //timer class do call actionListner every 100 milisecond, to change the position of snake
            timer=new Timer(delay,this);
            timer.start();
            newEnemy();
        }
//set enemy position randomly
    private void newEnemy() {
            enemyX=xpos[random.nextInt(34)];
            enemyY=ypos[random.nextInt(23)];
            for(int i=lengthofsnake-1;i>0;i--){
                if(snakexlength[i]==enemyX && snakeylength[i]==enemyY){
                    newEnemy();
                }
            }
    }

    private ImageIcon snakeTittle=new ImageIcon(getClass().getResource("image/snaketitle.jpg"));
    private ImageIcon snakeimage=new ImageIcon(getClass().getResource("image/snakeimage.png"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("image/leftmouth.png"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("image/rightmouth.png"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("image/downmouth.png"));
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("image/upmouth.png"));
    private ImageIcon enemy=new ImageIcon(getClass().getResource("image/enemy.png"));
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);
        snakeTittle.paintIcon(this,g,25,11);
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        if(moves==0){
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;

            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;

         }
        //draw head direction
          if(left){
              leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          } if(right){
              rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          } if(up){
              upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          } if(down){
              downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
          }
          //draw the body of snake
          for(int i=1;i<lengthofsnake;i++){
              snakeimage.paintIcon(this,g,snakexlength[i],snakeylength[i]);
          }
          //draw enemy
        enemy.paintIcon(this,g,enemyX,enemyY);

          //draw game over
        if(gameOver){
            g.setColor(Color.white);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);

            g.setFont(new Font("Arial",Font.PLAIN,50));
            g.drawString("Press SPACE to restart",320,350);

        }
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Score :"+score,750,30);
        g.drawString("Length :"+lengthofsnake,750,50);
          g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //for moving body of snake with head
        for(int i=lengthofsnake-1;i>0;i--){
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }
        //move the direction of snake left ,right, up,down
        if(left) {
            snakexlength[0] = snakexlength[0] - 25;
        }
        if(right){
            snakexlength[0]=snakexlength[0]+25;
        }
        if(up){
            snakeylength[0]=snakeylength[0]-25;
        }
        if(down) {
            snakeylength[0] = snakeylength[0]+25;
        }
        //if snake touch th boundary the again start from initial
        if(snakexlength[0]>850){
            snakexlength[0]=25;
        } if(snakexlength[0]<25){
            snakexlength[0]=850;
        }  if(snakeylength[0]>625){
            snakeylength[0]=75;
        } if(snakeylength[0]<75){
            snakeylength[0]=625;
        }
        //collide with enemy
        colliodWithEnemy();
        colliodWithBody();
      repaint();
    }

    private void colliodWithBody() {
        for(int i=lengthofsnake-1;i>0;i--){
            if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]){
                timer.stop();
                gameOver=true;
            }
        }
    }

    private void colliodWithEnemy() {
        if(snakexlength[0]==enemyX && snakeylength[0]==enemyY){
            newEnemy();
            lengthofsnake++;
            score++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
//attach keyboard key to move direction
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && !right ){
            left=true;
            right=false;
            up=false;
            down=false;
             moves++;
        }if(e.getKeyCode()==KeyEvent.VK_RIGHT && !left){
            left=false;
            right=true;
            up=false;
            down=false;
             moves++;
        }if(e.getKeyCode()==KeyEvent.VK_UP && !down){
            left=false;
            right=false;
            up=true;
            down=false;
             moves++;
        }if(e.getKeyCode()==KeyEvent.VK_DOWN && !up){
            left=false;
            right=false;
            up=false;
            down=true;
             moves++;
        }
    }

    private void restart() {
        gameOver=false;
        moves=0;
        score=0;
        lengthofsnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
