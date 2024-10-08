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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

public final class TheyAre100 extends JFrame {  
    // ArrayLists necessárias

    private final ArrayList<JZombie> zombies = new ArrayList<>();
    private final ArrayList<JLandMine> landMines = new ArrayList<>();
    private final ArrayList<JRadioButton> zRbD = new ArrayList<>();

    // Variáveis e Elementos 
    private final JPanel panelNorth, panelCenter;
    
    private final ThreadMotion zMove;
    private final ThreadConfig zConfig;
    private final ThreadKBoom zKBoom;
    private final Thread threadM, threadC, threadK;
    
    private final JLabel zLblCamping, zLblCounter, zLblKC, zLblWave;
    private final JCheckBox zPlayPause;
    private final ButtonGroup zGpDifficulty;
    private final JButton zBtnStart, zBtnRestart, zBtnOut, zBtnWinLose;
    
    private int difficulty;
    private int level;

    //private Boolean endGame;
    // Imagens e Gifs do jogo
    private final ImageIcon zNormalMov = new ImageIcon(getClass().getResource("/taz/zNormalMov.gif"));
    private final ImageIcon zNormal = new ImageIcon(getClass().getResource("/taz/zNormal.png"));
    private final ImageIcon zNormalDeadGif = new ImageIcon(getClass().getResource("/taz/zNormalDead.gif"));
    private final ImageIcon zNormalDeadPng = new ImageIcon(getClass().getResource("/taz/zNormalDead.png"));
    private final ImageIcon zBigMov = new ImageIcon(getClass().getResource("/taz/zBigMov.gif"));
    private final ImageIcon zBig = new ImageIcon(getClass().getResource("/taz/zBig.png"));
    private final ImageIcon zBigDeadGif = new ImageIcon(getClass().getResource("/taz/zBigDead.gif"));
    private final ImageIcon zBigDeadPng = new ImageIcon(getClass().getResource("/taz/zBigDead.png"));
    private final ImageIcon zFastMov = new ImageIcon(getClass().getResource("/taz/zFastMov.gif"));
    private final ImageIcon zFast = new ImageIcon(getClass().getResource("/taz/zFast.png"));
    private final ImageIcon zFastDeadGif = new ImageIcon(getClass().getResource("/taz/zFastDead.gif"));
    private final ImageIcon zFastDeadPng = new ImageIcon(getClass().getResource("/taz/zFastDead.png"));
    private final ImageIcon zMasterMov = new ImageIcon(getClass().getResource("/taz/zMasterMov.gif"));
    private final ImageIcon zMaster = new ImageIcon(getClass().getResource("/taz/zMaster.png"));
    private final ImageIcon zMasterDeadGif = new ImageIcon(getClass().getResource("/taz/zMasterDead.gif"));
    private final ImageIcon zMasterDeadPng = new ImageIcon(getClass().getResource("/taz/zMasterDead.png"));
    private final ImageIcon zCampingGif = new ImageIcon(getClass().getResource("/taz/zCamping.gif"));
    private final ImageIcon zCampingPng = new ImageIcon(getClass().getResource("/taz/zCamping.png"));
    
    private final Image zGround = new ImageIcon(getClass().getResource("/taz/zGround.png")).getImage();
    private final Image zGround2 = new ImageIcon(getClass().getResource("/taz/zGround2.png")).getImage();
    private final ImageIcon zHard = new ImageIcon(getClass().getResource("/taz/zHard.png"));
    private final ImageIcon zMiddle = new ImageIcon(getClass().getResource("/taz/zMiddle.png"));
    private final ImageIcon zEasy = new ImageIcon(getClass().getResource("/taz/zEasy.png"));
    private final ImageIcon zStart = new ImageIcon(getClass().getResource("/taz/zStart.png"));
    private final ImageIcon zRestart = new ImageIcon(getClass().getResource("/taz/zRestart.png"));
    private final ImageIcon zPlay = new ImageIcon(getClass().getResource("/taz/zPlay.png"));
    private final ImageIcon zPause = new ImageIcon(getClass().getResource("/taz/zPause.png"));
    private final ImageIcon zOut = new ImageIcon(getClass().getResource("/taz/zOut.png"));
    private final ImageIcon zSkull = new ImageIcon(getClass().getResource("/taz/zSkull.png"));
    private final ImageIcon zWave = new ImageIcon(getClass().getResource("/taz/zWave.png"));
    
    private final ImageIcon zTAH = new ImageIcon("TAZ/zTAH.png");

    /**
     * Construtor do Game TheyAre100 (vazio)
     * @param level
     */
    public TheyAre100(int level) {
        super("They Are 100");
        setIconImage(zTAH.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setSize(1120, 740);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(0, 0));
        this.level = level;

        // Criação e definição do painel onde os botões ficarão 
        panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelNorth.setBackground(Color.BLACK);
        add(panelNorth, BorderLayout.NORTH);

        // Criação e definição do painel onde todo o jogo acontecerá  
        
        
        panelCenter = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundZ;
                if(level == 1)
                    {
                        backgroundZ = zGround;
                    }
                    else
                    {
                        backgroundZ = zGround2;
                    }
                // Definição do Campo dos zumbis
                g.drawImage(backgroundZ, 0, 0, this);
            }
        };
        panelCenter.setLayout(null);
        panelCenter.setBackground(Color.DARK_GRAY);
        panelCenter.setSize(1120, 655);
        add(panelCenter, BorderLayout.CENTER);

// Criação do botão de Vitória ou Derrota
        zBtnWinLose = new JButton("");
        zBtnWinLose.setSize(500, 250);
        zBtnWinLose.setLocation((1120 / 2) - 250, (655 / 2) - 125);
        panelCenter.add(zBtnWinLose);
        zBtnWinLose.setOpaque(true);
        zBtnWinLose.setFocusable(false);
        zBtnWinLose.setFont(new Font("SansSerif", Font.BOLD, 75));
        zBtnWinLose.setBackground(Color.black);
        zBtnWinLose.addActionListener(this::winloseButton);

// Criação e definição do Wave counter
        zLblWave = new JLabel();
        zLblWave.setIcon(zWave);
        zLblWave.setSize(35, 42);
        panelNorth.add(zLblWave);
        zLblWave.setFont(new Font("SansSerif", Font.BOLD, 15));
        zLblWave.setForeground(Color.WHITE);
        zLblWave.setHorizontalTextPosition(JLabel.CENTER);
        zLblWave.setVerticalTextPosition(JLabel.BOTTOM);

// Criação e definição do KillCounter
        zLblKC = new JLabel();
        zLblKC.setIcon(zSkull);
        zLblKC.setSize(35, 42);
        panelNorth.add(zLblKC);
        zLblKC.setFont(new Font("SansSerif", Font.BOLD, 15));
        zLblKC.setForeground(Color.RED);
        zLblKC.setHorizontalTextPosition(JLabel.CENTER);
        zLblKC.setVerticalTextPosition(JLabel.BOTTOM);

// Criação e definição do Counter
        zLblCounter = new JLabel();
        zLblCounter.setOpaque(true);
        zLblCounter.setSize(50, 20);
        panelCenter.add(zLblCounter);
        zLblCounter.setBackground(Color.black);
        zLblCounter.setFont(new Font("SansSerif", Font.BOLD, 18));
        zLblCounter.setForeground(Color.RED);
        zLblCounter.setHorizontalAlignment(SwingConstants.CENTER);
        zLblCounter.setVerticalAlignment(SwingConstants.CENTER);

        // Criação dos elementos JZombie e JLandMine
        createZombies();
        createLandMines();

        //Por a posição do contador após relacionar ele com as Minas Terrestres
        zLblCounter.setLocation(landMines.get(30).getPosX(), landMines.get(30).getPosY() + landMines.get(30).getSizeH());

        // Criação e definição do Acampamento 
        zLblCamping = new JLabel();
        zLblCamping.setSize(131, 655);
        panelCenter.add(zLblCamping);
        zLblCamping.setLocation(917, 0);

        // Criação e definição do Botão Start
        zBtnStart = new JButton();
        zBtnStart.setIcon(zStart);
        zBtnStart.setBorder(null);
        zBtnStart.setFocusable(false);
        zBtnStart.setContentAreaFilled(false);
        zBtnStart.setSize(115, 52);
        panelNorth.add(zBtnStart);
        zBtnStart.addActionListener(this::startButton);

        // Criação e definição da CheckBox Play/Pause
        zPlayPause = new JCheckBox();
        zPlayPause.setIcon(zPause);
        zPlayPause.setSelectedIcon(zPlay);
        zPlayPause.setFocusable(false);
        zPlayPause.setContentAreaFilled(false);
        zPlayPause.setSize(52, 52);
        panelNorth.add(zPlayPause);
        zPlayPause.addActionListener(this::playPauseCheckBox);

        // Grupo dos RadioButtons de dificuldade 
        zGpDifficulty = new ButtonGroup();

        //  Criação e definição dos RadioButtons de dificuldade 
        for (int i = 0; i < 3; i++) {
            JRadioButton zRb = new JRadioButton();
            zRbD.add(zRb);
            switch (i) {
                case 0 -> {
                    zRbD.get(i).setText("EASY");
                    zRbD.get(i).setForeground(Color.BLUE);
                    zRbD.get(i).setIcon(zEasy);
                }
                case 1 -> {
                    zRbD.get(i).setText("NORMAL");
                    zRbD.get(i).setForeground(Color.WHITE);
                    zRbD.get(i).setIcon(zMiddle);
                }
                default -> {
                    zRbD.get(i).setText("HARD");
                    zRbD.get(i).setForeground(Color.RED);
                    zRbD.get(i).setIcon(zHard);
                }
            }
            zRbD.get(i).setFont(new Font("SansSerif", Font.BOLD, 20));
            zRbD.get(i).setFocusable(false);
            zRbD.get(i).setContentAreaFilled(false);
            zRbD.get(i).setSize(52, 52);
            zGpDifficulty.add(zRbD.get(i));
            panelNorth.add(zRbD.get(i));
            zRbD.get(i).addActionListener(this::difficultyRb);
        }

        // Criação e definição do Botão de Restart
        zBtnRestart = new JButton();
        zBtnRestart.setIcon(zRestart);
        zBtnRestart.setBorder(null);
        zBtnRestart.setFocusable(false);
        zBtnRestart.setContentAreaFilled(false);
        zBtnRestart.setSize(75, 52);
        panelNorth.add(zBtnRestart);
        zBtnRestart.addActionListener(this::restartButton);

        // Criação e definição do Botão de Saída 
        zBtnOut = new JButton();
        zBtnOut.setIcon(zOut);
        zBtnOut.setBorder(null);
        zBtnOut.setContentAreaFilled(false);
        zBtnOut.setFocusable(false);
        zBtnOut.setSize(75, 52);
        panelNorth.add(zBtnOut);
        zBtnOut.addActionListener(this::outButton);

        // Definição dos elementos a utilizar para cada Thread
        zMove = new ThreadMotion(zombies, zRbD, zLblKC, zBtnWinLose, zBtnOut, zBtnRestart, zPlayPause);
        zConfig = new ThreadConfig(zombies, landMines);
        zKBoom = new ThreadKBoom(landMines);

        // Organizar o jogo inicialmente 
        zBtnStart.setEnabled(false);
        setAll();

        // Ativação das Threads e suas iniciações 
        threadM = new Thread(zMove);
        threadC = new Thread(zConfig);
        threadK = new Thread(zKBoom);
        setVisible(true);
        threadM.start();
        threadC.start();
        threadK.start();
        
    }
    
    /**
     * Método que é acionado no Botão winlose
     *
     * @param e
     */
    protected void winloseButton(ActionEvent e) {
        zBtnRestart.setEnabled(true);
        zBtnOut.setEnabled(true);
        zBtnRestart.doClick();
    }
    
    /**
     * Método que é acionado no Botão Start
     *
     * @param e
     */
    protected void startButton(ActionEvent e) {
        zMove.setPlay(true);
        zConfig.setPlay(true);
        zBtnStart.setEnabled(false);
        zPlayPause.setEnabled(true);
        zLblWave.setText("1");
    }

    /**
     * Método que é acionado na CheckBox Play/Pause
     *
     * @param e
     */
    protected void playPauseCheckBox(ActionEvent e) {
        // Pause
        if (zPlayPause.isSelected()) {
            
            zLblCamping.setIcon(zCampingPng);
            zMove.setPlay(false);
            zConfig.setPlay(false);
            
            for (JLandMine landMine : landMines) {
                landMine.setPlay(false);
            }
            
            for (JZombie zombie : zombies) {
                zombie.setPlay(false);
                if (zombie.iszAlive()) {
                    switch (zombie.getShape()) {
                        case 4 ->
                            zombie.setIcon(zMaster);
                        case 3 ->
                            zombie.setIcon(zFast);
                        case 2 ->
                            zombie.setIcon(zBig);
                        default ->
                            zombie.setIcon(zNormal);
                    }
                } else {
                    switch (zombie.getShape()) {
                        case 4 ->
                            zombie.setIcon(zMasterDeadPng);
                        case 3 ->
                            zombie.setIcon(zFastDeadPng);
                        case 2 ->
                            zombie.setIcon(zBigDeadPng);
                        default ->
                            zombie.setIcon(zNormalDeadPng);
                    }
                }
            }

            // Play
        } else {
            
            zLblCamping.setIcon(zCampingGif);
            zMove.setPlay(true);
            zConfig.setPlay(true);
            
            for (JLandMine landMine : landMines) {
                landMine.setPlay(true);
            }
            
            for (JZombie zombie : zombies) {
                zombie.setPlay(true);
                if (zombie.iszAlive()) {
                    switch (zombie.getShape()) {
                        case 4 ->
                            zombie.setIcon(zMasterMov);
                        case 3 ->
                            zombie.setIcon(zFastMov);
                        case 2 ->
                            zombie.setIcon(zBigMov);
                        default ->
                            zombie.setIcon(zNormalMov);
                    }
                } else {
                    switch (zombie.getShape()) {
                        case 4 ->
                            zombie.setIcon(zMasterDeadGif);
                        case 3 ->
                            zombie.setIcon(zFastDeadGif);
                        case 2 ->
                            zombie.setIcon(zBigDeadGif);
                        default ->
                            zombie.setIcon(zNormalDeadGif);
                    }
                }
            }
        }
    }

    /**
     * Método que é acionado nos RadioButtons de dificuldade
     *
     * @param e
     */
    protected void difficultyRb(ActionEvent e) {
        if (zRbD.get(0).isSelected()) {
            difficulty = 0;
            zRbD.get(1).setEnabled(false);
            zRbD.get(2).setEnabled(false);
        } else if (zRbD.get(1).isSelected()) {
            difficulty = 1;
            zRbD.get(0).setEnabled(false);
            zRbD.get(2).setEnabled(false);
        } else {
            difficulty = 2;
            zRbD.get(0).setEnabled(false);
            zRbD.get(1).setEnabled(false);
        }
        zBtnStart.setEnabled(true);
        setAll();
        zLblCounter.setEnabled(true);
        for (int i = 0; i < 30; i++) {
            landMines.get(i).setEnabled(true);
            landMines.get(i).setMovable(true);
        }
    }

    /**
     * Método que é acionado no Botão Restart
     *
     * @param e
     */
    protected void restartButton(ActionEvent e) {
        zRbD.get(0).setEnabled(true);
        zRbD.get(1).setEnabled(true);
        zRbD.get(2).setEnabled(true);
        zBtnStart.setEnabled(false);
        setAll();
    }

    /**
     * Método que é acionado no Botão Out
     *
     * @param e
     */
    protected void outButton(ActionEvent e) {
        threadM.interrupt();
        threadC.interrupt();
        threadK.interrupt();
        setVisible(false);
    }

    /**
     * Método que cria os JZombies (sem inserção)
     */
    protected void createZombies() {
        for (int i = 0; i < 100; i++) {
            JZombie zombie = new JZombie(zLblKC, zLblWave);
            zombies.add(zombie);
            panelCenter.add(zombies.get(i));
            zombie.setFont(new Font("SansSerif", Font.BOLD, 18));
            zombie.setForeground(Color.RED);
            zombie.setHorizontalTextPosition(JLabel.CENTER);
            zombie.setVerticalTextPosition(JLabel.BOTTOM);
        }
    }

    /**
     * Método que cria as JLandMines (sem inserção)
     */
    protected void createLandMines() {
        for (int i = 0; i < 31; i++) {
            JLandMine landMine = new JLandMine(zLblCounter);
            landMines.add(landMine);
            panelCenter.add(landMines.get(i));
            landMines.get(i).setHorizontalAlignment(SwingConstants.CENTER);
        }
        landMines.get(30).setBigSize();
        landMines.get(30).setPosX(1048 + 11);
        landMines.get(30).setPosY(10);
    }

    /**
     * Método que define os elementos e seus parâmetros
     */
    protected void setAll() {
        zPlayPause.setSelected(false);
        zPlayPause.setEnabled(false);
        zMove.setPlay(false);
        zConfig.setPlay(false);
        setLandMines(difficulty);
        setZombies(difficulty);
        zLblCamping.setIcon(zCampingGif);
        zLblKC.setText("0");
        zLblWave.setText("0");
        zLblWave.setForeground(Color.WHITE);
        zLblCounter.setText("30");
        zLblCounter.setEnabled(false);
        zBtnWinLose.setVisible(false);
        zBtnWinLose.setEnabled(false);
    }

    /**
     * Método que define os parâmetros de cada JLandMine
     *
     * @param d
     */
    protected void setLandMines(int d) {
        for (int i = 0; i < 30; i++) {
            landMines.get(i).setBigSize();
            landMines.get(i).setPosX(1048 + 11);
            landMines.get(i).setPosY(10);
            switch (d) {
                //Dificuldade 1
                //Dificuldade 2
                //Dificuldade 3
                case 0 ->
                    landMines.get(i).setDamage(230.0);
                case 1 ->
                    landMines.get(i).setDamage(210.0);
                case 2 ->
                    landMines.get(i).setDamage(200.0);
                default ->
                    throw new AssertionError();
            }
        }
    }

    /**
     * Método que define os parâmetros de cada JZombie
     *
     * @param d
     */
    protected void setZombies(int d) {
        // O Tamanho (size)dos zumbis foi setado em: (width da imagem + 25) x (height da imagem + 30). Para que caiba o texto da vida.
        for (int i = 0; i < 100; i++) {
            zombies.get(i).setzAlive(true);
            zombies.get(i).setPlay(true);
            if (i == 99 || i == 98 || i == 49) {
                zombies.get(i).setShape(4);
                zombies.get(i).setSize(110, 136);
                zombies.get(i).setIcon(zMasterMov);
                zombies.get(i).setSpeed(3*level);
                
            } else if (i == 95 || (i >= 82 && i <= 86) || i == 76 || i == 75 || i == 69 || i == 46 || i == 45 || i == 37 || i == 29 || i == 28 || i == 19) {
                zombies.get(i).setShape(3);
                zombies.get(i).setSize(45, 78);
                zombies.get(i).setIcon(zFastMov);
                zombies.get(i).setSpeed(8*level);
                
            } else if (i == 94 || i == 90 || i == 73 || i == 72 || i == 64 || i == 44 || i == 40 || i == 39 || i == 38 || i == 33 || i == 32 || i == 24 || i == 20 || i == 14) {
                zombies.get(i).setShape(2);
                zombies.get(i).setSize(57, 115);
                zombies.get(i).setIcon(zBigMov);
                zombies.get(i).setSpeed(6*level);
                
            } else {
                zombies.get(i).setShape(1);
                zombies.get(i).setSize(51, 84);
                zombies.get(i).setIcon(zNormalMov);
                zombies.get(i).setSpeed(4*level);               
                
            }
            switch (d) {
                //Dificuldade 1
                //Dificuldade 2
                //Dificuldade 3
                case 0 -> {
                    zombies.get(i).setDamage(15.0*level);
                    switch (zombies.get(i).getShape()) {
                        case 4 -> {
                            zombies.get(i).setText("975");
                        }
                        case 3 -> {
                            zombies.get(i).setText("55");
                        }
                        case 2 -> {
                            zombies.get(i).setText("225");
                        }
                        case 1 -> {
                            zombies.get(i).setText("75");
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
                case 1 -> {
                    zombies.get(i).setDamage(8.0*level);
                    switch (zombies.get(i).getShape()) {
                        case 4 -> {
                            zombies.get(i).setText("1105");
                        }
                        case 3 -> {
                            zombies.get(i).setText("60");
                        }
                        case 2 -> {
                            zombies.get(i).setText("255");
                        }
                        case 1 -> {
                            zombies.get(i).setText("85");
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
                case 2 -> {
                    zombies.get(i).setDamage(7.0*level);
                    switch (zombies.get(i).getShape()) {
                        case 4 -> {
                            zombies.get(i).setText("1300");
                        }
                        case 3 -> {
                            zombies.get(i).setText("70");
                        }
                        case 2 -> {
                            zombies.get(i).setText("300");
                        }
                        case 1 -> {
                            zombies.get(i).setText("100");
                        }
                        default ->
                            throw new AssertionError();
                    }
                }
                
                default ->
                    throw new AssertionError();
            }
            zombies.get(i).setVisible(true);
            if (i % 10 == 0 || i == 0) {
                zombies.get(i).setPosX(-(zombies.get(i).getSizeW()) - 5);
            } else {
                zombies.get(i).setPosX((zombies.get(i - 1).getPosX()) - (zombies.get(i).getSizeW()) - 5);
            }
            zombies.get(i).setPosY((int) (Math.random() * (655 - zombies.get(i).getSizeH())));
        }
    }
}
