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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadMotion implements Runnable {

    // ArrayList necessária
    private ArrayList<JZombie> zombies = new ArrayList<>();
    private final ArrayList<JRadioButton> zRbD;
    // Variáveis necessárias
    private boolean play;
    private final JLabel killCounter;
    private final JCheckBox playpause;
    private final JButton winlose, out, restart;

    /**
     * Construtor da ThreadMotion (ArrayList JZombie zombies, JFrame frame)
     *
     * @param zombies
     * @param zRbD
     * @param killCounter
     * @param winlose
     * @param out
     * @param restart
     * @param playpause
     */
    public ThreadMotion(ArrayList<JZombie> zombies, ArrayList<JRadioButton> zRbD, JLabel killCounter, JButton winlose, JButton out, JButton restart, JCheckBox playpause) {
        this.zombies = zombies;
        this.zRbD = zRbD;
        this.killCounter = killCounter;
        this.winlose = winlose;
        this.out = out;
        this.restart = restart;
        this.playpause = playpause;
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
                Thread.sleep(220);
                if (play == true) {
                    if (Integer.parseInt(killCounter.getText()) == 100) {
                        playpause.doClick();
                        restart.setEnabled(false);
                        out.setEnabled(false);
                        for (JRadioButton difficulty : zRbD) {
                            difficulty.setEnabled(false);
                        }
                        playpause.setEnabled(false);
                        winlose.setVisible(true);
                        winlose.setEnabled(true);
                        winlose.setText("<html> <p style='text-align:center; color:green''> WINNER </p> <p style='text-align:center; color:#898a74 '> CLICK HERE </p> </html>");
                        winlose.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));
                    }
                    for (int i = 0; i < 100; i++) {
                        if (Integer.parseInt(killCounter.getText()) >= 90) {
                            if (i >= 90 && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 80) {
                            if ((i >= 80 && i <= 89) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 70) {
                            if ((i >= 70 && i <= 79) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 60) {
                            if ((i >= 60 && i <= 69) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 50) {
                            if ((i >= 50 && i <= 59) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 40) {
                            if ((i >= 40 && i <= 49) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 30) {
                            if ((i >= 30 && i <= 39) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 20) {
                            if ((i >= 20 && i <= 29) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 10) {
                            if ((i >= 10 && i <= 19) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        } else if (Integer.parseInt(killCounter.getText()) >= 0) {
                            if ((i >= 0 && i <= 9) && zombies.get(i).iszAlive()) {
                                zombies.get(i).setPosX(zombies.get(i).getPosX() + zombies.get(i).getSpeed());
                            }
                        }

                        if (zombies.get(i).getPosX() + zombies.get(i).getSizeW() - 25 > 917) {
                            playpause.doClick();
                            restart.setEnabled(false);
                            out.setEnabled(false);
                            for (JRadioButton difficulty : zRbD) {
                                difficulty.setEnabled(false);
                            }
                            playpause.setEnabled(false);
                            winlose.setVisible(true);
                            winlose.setEnabled(true);
                            winlose.setText("<html> <p style='text-align:center; color:red''> GAME OVER </p> <p style='text-align:center; color:#898a74 '> CLICK HERE </p> </html>");
                            winlose.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
                        }
                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadMotion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
