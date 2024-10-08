/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamez;

/**
 *
 * @author gabri
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class JZombie extends JLabel {
    // Imagens dos zumbis mortos

    private final ImageIcon zNormalDeadGif = new ImageIcon(getClass().getResource("/taz/zNormalDead.gif"));
    private final ImageIcon zBigDeadGif = new ImageIcon(getClass().getResource("/taz/zBigDead.gif"));
    private final ImageIcon zFastDeadGif = new ImageIcon(getClass().getResource("/taz/zFastDead.gif"));
    private final ImageIcon zMasterDeadGif = new ImageIcon(getClass().getResource("/taz/zMasterDead.gif"));

    private int x, y, shape, speed, waveNumber;
    private boolean zAlive, play;
    private double damage, health;
    private final JLabel killCounter, wave;

    /**
     * Construtor do JZombie (JLabel kc, JLabel wv) 
     * 
     * @param kc
     * @param wv 
     */
    public JZombie(JLabel kc, JLabel wv) {

        killCounter = kc;
        wave = wv;

        addMouseListener(new MouseAdapter() {
            // Criação da ação ao pressionar o mouse
            @Override
            public void mousePressed(MouseEvent e) {
                if (iszAlive() && play == true) {
                    // -25 é o espaço width do texto de vida do zumbi
                    // -30 é o espaço height do texto de vida do zumbi
                    if (e.getX() <= getSizeW() - 25 && e.getY() <= getSizeH() - 30) {
                        health = Double.parseDouble(getText());
                        setText(Double.toString(health - damage));
                        if (Double.parseDouble(getText()) <= 0) {
                            dieNow();
                        }
                    }
                }
            }
        });
    }

    /**
     * Método que retorna a largura (int)
     *
     * @return
     */
    public int getSizeW() {
        return (int) getSize().getWidth();
    }

    /**
     * Método que retorna a altura (int)
     *
     * @return
     */
    public int getSizeH() {
        return (int) getSize().getHeight();
    }

    /**
     * Método que retorna a posição X (int)
     *
     * @return
     */
    public int getPosX() {
        return x;
    }

    /**
     * Método que define a posição X (int)
     *
     * @param x
     */
    public void setPosX(int x) {
        this.x = x;
        setLocation(this.x, this.y);
    }

    /**
     * Método que retorna a posição Y (int)
     *
     * @return
     */
    public int getPosY() {
        return y;
    }

    /**
     * Método que define a posição Y (int)
     *
     * @param y
     */
    public void setPosY(int y) {
        this.y = y;
        setLocation(this.x, this.y);
    }

    /**
     * Método que retorna o shape (int)
     *
     * @return
     */
    public int getShape() {
        return shape;
    }

    /**
     * Método que define o shape (int)
     *
     * @param shape
     */
    public void setShape(int shape) {
        this.shape = shape;
    }

    /**
     * Método que retorna a velocidade (int)
     *
     * @return
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Método que define a velocidade (int)
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Método que define o dano (Double)
     *
     * @param damage
     */
    public void setDamage(Double damage) {
        this.damage = damage;
    }

    /**
     * Método que retorna se está vivo (boolean)
     *
     * @return
     */
    public boolean iszAlive() {
        return zAlive;
    }

    /**
     * Método que define se está vivo (boolean)
     *
     * @param zAlive
     */
    public void setzAlive(boolean zAlive) {
        this.zAlive = zAlive;
    }

    /**
     * Método que define se pode alterar o zumbi(boolean)
     *
     * @param b
     */
    public void setPlay(boolean b) {
        play = b;
    }

    /**
     * Método que mata o zumbi (sem inserção)
     */
    public void dieNow() {
        setzAlive(false);
        setText("");
        switch (shape) {
            case 4 -> {
                setSize(85, 77);
                setIcon(zMasterDeadGif);
                setPosY(getPosY() + 29);
            }
            case 3 -> {
                setSize(20, 36);
                setIcon(zFastDeadGif);
                setPosY(getPosY() + 12);
            }
            case 2 -> {
                setSize(32, 66);
                setIcon(zBigDeadGif);
                setPosY(getPosY() + 19);
            }
            default -> {
                setSize(26, 48);
                setIcon(zNormalDeadGif);
                setPosY(getPosY() + 6);
            }
        }
        killCounter.setText(Integer.toString(Integer.parseInt(killCounter.getText()) + 1));
        if ((Integer.parseInt(killCounter.getText())) % 10 == 0 && (Integer.parseInt(killCounter.getText())) != 100 ){
            waveNumber = Integer.parseInt(wave.getText()) + 1;
            wave.setText(Integer.toString(waveNumber));
            if (killCounter.getText().equals("40") || killCounter.getText().equals("90")){
                wave.setForeground(Color.RED);
            }else{
                wave.setForeground(Color.WHITE);
            }
        }
    }
}
