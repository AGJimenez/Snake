/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author alexg
 */
public class GameContent extends JPanel implements ActionListener{
    //Window
    static final int SCREEN = 600; //Variable estática para la pantalla, usamos final porque nunca cambiará. Es como una constante
    static final int DEFAULT_PIXEL = 25; //Elementos del juego omaigod
    static final int PARALEL_PIXEL = (int)SCREEN/DEFAULT_PIXEL;//casteamos con un int para que la division sea entera si o si
    //Snake
    static final int TOTAL_SNAKE_BODY = (SCREEN*SCREEN)/DEFAULT_PIXEL; //tamaño que tendrá el cuerpo de la serpiente
    int [] snakeX = new int[TOTAL_SNAKE_BODY]; //ya empezamos con los arrayssss... Creamos ejes X e Y para guardar el tamaño del cuerpo antes creado.
    int [] snakeY = new int[TOTAL_SNAKE_BODY];
    int snakeBody = 3; //Guardamos el cuerpo de la serpiente con una longitud inicial de 3.
    char moveSnake = 'd'; //awsd
    //Time
    static final int DELAY = 100;//delay del movimiento
    Timer timer; //es una librería de Swing que se usa para tener un reloj.
    boolean running = true;
    //Other
    Random random = new Random();//posicion de la comida
    //Food
    int foodX; //Donde se guardará la comida aleatoriamente. Aún no le damos la coordenada, pues se meterá luego.
    int foodY;
    
    //CONSTRUCTOR
    GameContent(){
        this.setPreferredSize(new Dimension(SCREEN,SCREEN));//librería importada de awt.
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new Control());
        startGame();
    }

    public void startGame(){
        addFood();
        timer = new Timer(DELAY, this);
        timer.start();//importante para ver que está ocurriendo en tiempo real dentro del juego
        
    }
    
    public void addFood(){
        foodX = random.nextInt(PARALEL_PIXEL)*DEFAULT_PIXEL;
        foodY = random.nextInt(PARALEL_PIXEL)*DEFAULT_PIXEL;
    }
    public void moveSnake(){
        for(int i = snakeBody; i>0; i--){
            snakeX[i] = snakeX[i-1];//el extremo mas lejano toma el valor del mas cercano
            snakeY[i] = snakeY[i-1];
        }
        switch(moveSnake){
            case 'd':
                snakeX[0] = snakeX[0]+DEFAULT_PIXEL;
                break;
            case 'a':
                snakeX[0] = snakeX[0]-DEFAULT_PIXEL;
                break;
            case 'w':
                snakeY[0] = snakeY[0]-DEFAULT_PIXEL;
                break;
            case 's':
                snakeY[0] = snakeY[0]+DEFAULT_PIXEL;
                break;    
        }
    }
    
    public void checkFood(){
        if(snakeX[0]==foodX && snakeY[0]==foodY){
            snakeBody++;
            addFood();
        }
    }
    
    public void checkColissions(){
        if(snakeX[0]<0){
            running = false;
        }
        if(snakeY[0]<0){
            running = false;
        }
        if(snakeX[0]>SCREEN-DEFAULT_PIXEL){
            running = false;
        }
        if(snakeY[0]>SCREEN-DEFAULT_PIXEL){
            running = false;
        }
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            moveSnake();
            checkFood();
            checkColissions();
        }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<PARALEL_PIXEL;i++){
            g.drawLine(0, DEFAULT_PIXEL*i, SCREEN, DEFAULT_PIXEL*i);
            g.drawLine(DEFAULT_PIXEL*i,0, DEFAULT_PIXEL*i, SCREEN);
        }
        g.setColor(Color.red);
        g.fillOval(foodX, foodY, DEFAULT_PIXEL, DEFAULT_PIXEL);
        g.setColor(Color.green);
        for(int i = 0; i<snakeBody; i++){
            g.fillRect(snakeX[i], snakeY[i], DEFAULT_PIXEL, DEFAULT_PIXEL);
        }
    }
    
    public class Control extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyChar()){
            case 'w':
                moveSnake = 'w';
                break;
            case 's':
                moveSnake = 's';
                break;
            case 'd':
                moveSnake = 'd';
                break;
            case 'a':
                moveSnake = 'a';
                break;
        }
    }
}

    
}
