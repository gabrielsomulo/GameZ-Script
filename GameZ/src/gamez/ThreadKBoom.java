/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamez;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author gabri
 */
public class ThreadKBoom implements Runnable  {
     // ArrayList necessária
    private final ImageIcon zLandMineKBoom = new ImageIcon(getClass().getResource("/taz/zLandMineKBoom.png"));
    private ArrayList<JLandMine> landMines = new ArrayList<>();
    
    /**
     * Construtor da ThreadKBoom (ArrayList JLandMine landMines)
     * @param landMines
     */
    public ThreadKBoom (ArrayList<JLandMine> landMines){
        this.landMines = landMines;
    }

    
    // Método que inicia em Thread.start()
    @Override
    public void run() {
        try {
            // Enquanto a Thread não for interrompida 
            while (!Thread.currentThread().isInterrupted()) {
                for (JLandMine landMine : landMines) {
                    if (landMine.getDetonator() && landMine.isEnabled()) {
                        landMine.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.RED));
                        landMine.setPosY(landMine.getPosY() - 70 + 29);
                        landMine.setSize(59, 70);
                        landMine.setIcon(zLandMineKBoom);
                        // Intervalo (milissegundos) entre os comandos do teste
                        Thread.sleep(160);
                        landMine.setEnabled(false);
                        landMine.setVisible(false);
                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadKBoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
