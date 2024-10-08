/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamez;

/**
 *
 * @author gabri
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;

public class JLandMine extends JLabel {
    // Imagens da Mina
    private final ImageIcon zLandMine1 = new ImageIcon(getClass().getResource("/taz/zLandMine.png"));
    private final ImageIcon zLandMine2 = new ImageIcon(getClass().getResource("/taz/zLandMine2.png"));
    private int x, y, draggedAtX, draggedAtY;
            
    private boolean movable, play;
    private boolean isKBoom;
    private Double damage;
    private JLabel count;
    private boolean detonator;

    /**
     * Construtor do JLandMineb (JLabel cntr)
     * 
     * @param cntr 
     */
    public JLandMine(JLabel cntr) {

        count = cntr;
        addMouseListener(new MouseAdapter() {
            // Criação da ação ao pressionar o mouse
            @Override
            public void mousePressed(MouseEvent e) {
                if (movable == true && play == true) {
                    draggedAtX = e.getX();
                    draggedAtY = e.getY();
                    setIcon(zLandMine1);
                    setSize(59, 44);
                    setBorder(BorderFactory.createLineBorder(Color.red, 3));
                    count.setText(Integer.toString(Integer.parseInt(count.getText()) - 1));
                    if (Integer.parseInt(count.getText()) < 1) {
                        count.setEnabled(false);
                    }
                }
            }

            // Criação da ação ao soltar o mouse
            @Override
            public void mouseReleased(MouseEvent e) {
                if (movable == true && play == true) {
                    draggedAtX = 0;
                    draggedAtY = 0;
                    movable = false;
                    if (getPosX() < 0 || getPosX() > 920 - getSizeW() || getPosY() < 0 || getPosY() > 655 - getSizeH()) {
                        setPosX((int) (Math.random() * (917 - getSizeW())));
                        setPosY((int) (Math.random() * (655 - getSizeH())));
                    }
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            // Criação da ação ao pressionar e arrastar o mouse
            @Override
            public void mouseDragged(MouseEvent e) {
                if (movable == true && play == true) {
                    setPosX(e.getX() - draggedAtX + getPosX());
                    setPosY(e.getY() - draggedAtY + getPosY());
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
     * Método que retorna se pode movimentar (boolean)
     *
     * @return
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * Método que define se é movimentável (boolean)
     *
     * @param b
     */
    public void setMovable(boolean b) {
        movable = b;
    }

    /**
     * Método que define se pode movimentar (boolean)
     *
     * @param b
     */
    public void setPlay(boolean b) {
        play = b;
    }

    /**
     * Método que retorna o dano (double)
     *
     * @return
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Método que define o dano (double)
     *
     * @param damage
     */
    public void setDamage(Double damage) {
        this.damage = damage;
    }
    
    public Boolean getDetonator(){
        return this.detonator;
    }
 
    /**
     * Método que define o tamanho normal (sem inserção)
     */
    public void setBigSize() {
        detonator = false;
        setSize(50, 24);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
        setIcon(zLandMine2);
        movable = false;
        play = true;
        isKBoom = true;
        setVisible(true);
        setEnabled(false);
    }

    /**
     * Método que define a detonação (sem inserção)
     */
    public void detonateNow() {
        if (isKBoom) {
            isKBoom = false;
            detonator = true;
        }
    }
}
