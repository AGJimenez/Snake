/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Snake;

import javax.swing.JFrame;

/**
 *
 * @author alexg
 */
public class GameWindow extends JFrame{
    
    
    GameWindow(){
        this.setTitle("Snake");
        this.add(new GameContent());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
