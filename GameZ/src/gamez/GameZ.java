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
import java.awt.event.ActionEvent;

public class GameZ extends JFrame {

    private final ImageIcon zGame = new ImageIcon(getClass().getResource("/taz/zGame.png"));
    // Imagens para os botões e logo 
    private final ImageIcon zTAH = new ImageIcon(getClass().getResource("/taz/zTAH.png"));
     private final ImageIcon zTAH2 = new ImageIcon(getClass().getResource("/taz/zTAH2.png"));
    private final ImageIcon zOut = new ImageIcon(getClass().getResource("/taz/zGameOut.png"));
    private final ImageIcon zMenu = new ImageIcon(getClass().getResource("/taz/zGameMenu.png"));

    // Variáveis básicas para a Interface
    private final JButton zBtnTAH, zBtnTAH2, zBtnOut;
    private final JLabel zTitle;

    private final JPanel panelFlow, panelButtons1, panelButtons2;

    // Thread que irá iniciar n jogos
    private Thread tah;

    /**
     * Construtor do Menu GameZ (vazio)
     */
    public GameZ() {
        super("GameZ");
        setIconImage(zGame.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setSize(1120, 740);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(4, 1, 0, 0));
        getContentPane().setBackground(Color.BLACK);

        // Criação e definição do panel para a logo
        panelFlow = new JPanel();
        panelFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelFlow.setOpaque(false);
        add(panelFlow);

        // Criação e definição do panel para o Botão do They Are 100
        panelButtons1 = new JPanel();
        panelButtons1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelButtons1.setOpaque(false);
        add(panelButtons1);

        // Criação e definição do panel para o Botão de Saída 
        panelButtons2 = new JPanel();
        panelButtons2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelButtons2.setOpaque(false);
        add(panelButtons2);

        // Criação e definição da label da logo
        zTitle = new JLabel();
        zTitle.setIcon(zMenu);
        zTitle.setSize(396, 158);
        zTitle.setOpaque(false);
        panelFlow.add(zTitle);

        // Botão que inicia a Thread do jogo TheyAre100 
        zBtnTAH = new JButton();
        zBtnTAH.setIcon(zTAH);
        zBtnTAH.setBorder(null);
        zBtnTAH.setContentAreaFilled(false);
        zBtnTAH.setSize(396, 158);
        zBtnTAH.setFocusable(false);
        panelButtons1.add(zBtnTAH);
        zBtnTAH.addActionListener(this::tahButton1);
        
        // Botão que inicia a Thread do jogo TheyAre100 
        zBtnTAH2 = new JButton();
        zBtnTAH2.setIcon(zTAH2);
        zBtnTAH2.setBorder(null);
        zBtnTAH2.setContentAreaFilled(false);
        zBtnTAH2.setSize(396, 158);
        zBtnTAH2.setFocusable(false);
        panelButtons1.add(zBtnTAH2);
        zBtnTAH2.addActionListener(this::tahButton2);

        // Criação e definição do Botão de Saída 
        zBtnOut = new JButton();
        zBtnOut.setIcon(zOut);
        zBtnOut.setBorder(null);
        zBtnOut.setContentAreaFilled(false);
        zBtnOut.setFocusable(false);
        zBtnOut.setSize(396, 158);
        panelButtons2.add(zBtnOut);
        zBtnOut.addActionListener(this::outButton);

        setVisible(true);
    }

    /**
     * Método que inicia a Thread ThreadGameZ(1) que inicia o jogo TheyAre100
     *
     * @param e
     */
    public void tahButton1(ActionEvent e) {
        tah = new Thread(new ThreadGameZ(1));
        tah.start();
    }
    public void tahButton2(ActionEvent e) {
        tah = new Thread(new ThreadGameZ(2));
        tah.start();
    }

    /**
     * Método que é acionado no Botão Out
     *
     * @param e
     */
    protected void outButton(ActionEvent e) {
        System.exit(0);
    }
}
