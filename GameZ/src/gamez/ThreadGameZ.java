/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamez;

/**
 *
 * @author gabri
 */
public class ThreadGameZ implements Runnable {
    // Variável necessária

    private final int tipoGame;

    /**
     * Construtor da ThreadGameZ (int tipoGame)
     *
     * @param tipoGame
     */
    public ThreadGameZ(int tipoGame) {
        this.tipoGame = tipoGame;
    }

    // Método que inicia em Thread.start()
    @Override
    public void run() {
        if (tipoGame == 1) {
            TheyAre100 tah = new TheyAre100(1);
        }
        else{
            TheyAre100 tah = new TheyAre100(2);
        }
    }
}
