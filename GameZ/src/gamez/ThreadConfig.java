/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamez;

/**
 *
 * @author gabri
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadConfig implements Runnable {
    // ArrayLists necessárias

    private ArrayList<JZombie> zombies = new ArrayList<>();
    private ArrayList<JLandMine> landMines = new ArrayList<>();

    // Variáveis necessárias
    private boolean play;

    private int xZ, yZ, wZ, hZ, xL, yL, wL, hL;

    /**
     * Construtor da ThreadConfig (ArrayList JZombie zombies, ArrayList
     * JLandMine landMines)
     *
     * @param zombies
     * @param landMines
     */
    public ThreadConfig(ArrayList<JZombie> zombies, ArrayList<JLandMine> landMines) {
        this.zombies = zombies;
        this.landMines = landMines;
    }

    /**
     * Método que define se irá iniciar os testes (boolean))
     *
     * @param play
     */
    public void setPlay(boolean play) {
        this.play = play;
    }

    // Método que inicia em Thread.start()
    @Override
    public void run() {
        try {
            // Enquanto a Thread não for interrompida 
            while (!Thread.currentThread().isInterrupted()) {
                // Intervalo (milissegundos) entre a repetição dos testes
                Thread.sleep(200);
                if (play == true) {
                    for (JLandMine landMine : landMines) {
                        if (landMine.isEnabled()) {
                            xL = landMine.getPosX();
                            yL = landMine.getPosY();
                            wL = landMine.getSizeW();
                            hL = landMine.getSizeH();
                            for (JZombie zombie : zombies) {
                                if (zombie.iszAlive()) {
                                    xZ = zombie.getPosX();
                                    yZ = zombie.getPosY();
                                    // -25 é o espaço width do texto de vida do zumbi
                                    wZ = zombie.getSizeW() - 25;
                                    // -30 é o espaço height do texto de vida do zumbi
                                    hZ = zombie.getSizeH() - 30;
                                    // Se o Zumbi está na mesma localização da Mina
                                    if (((xZ + wZ >= xL && xZ <= xL + wL)
                                            && (yZ + hZ >= yL && yZ + hZ <= yL + hL)) && (landMine.isMovable() == false)) {
                                        landMine.detonateNow();
                                        zombie.setText(Double.toString(Double.parseDouble(zombie.getText()) - landMine.getDamage()));
                                        if (Double.parseDouble(zombie.getText()) <= 0) {
                                            zombie.dieNow();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (InterruptedException ex) {
          Logger.getLogger(ThreadConfig.class.getName()).log(Level.SEVERE, null, ex);       
        }
    }
}
