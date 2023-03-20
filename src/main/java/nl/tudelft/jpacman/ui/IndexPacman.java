package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.ui.theme.ThemeAmongus;
import nl.tudelft.jpacman.ui.theme.ThemeDefault;
import nl.tudelft.jpacman.ui.theme.ThemeSquid;
import nl.tudelft.jpacman.ui.theme.ThemeZoo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class IndexPacman extends JFrame implements ActionListener {
    private ThemeDefault themeA;
    private ThemeAmongus themeB;
    private ThemeSquid themeC;
    private ThemeZoo themeD;
    private String currentTheme;
    private String currentMap;
    private JLabel logoLabel;
    // private JLabel mapLabel;
    private String levelName = "/map0.txt";
    public  BackgroundPanel backgroundPanel;
    JButton changeThemeBtn;
    JButton changeMapBtn;

    public IndexPacman() {
        // Initialize themes
        themeA = new ThemeDefault();
        themeB = new ThemeAmongus();
        themeC = new ThemeSquid();
        themeD = new ThemeZoo();

        // Set initial theme to A and map to Map1
        currentTheme = "A";
        currentMap = "Map1";

        // Set up JFrame
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        //setUndecorated(true);

        // Load background image and create background panel

        backgroundPanel = new BackgroundPanel(getCurrentMapBg());

        // Create logo and map labels
        logoLabel = new JLabel(themeA.getLogo());
        // mapLabel = new JLabel(themeA.getMap1());

        // Create buttons for changing theme and map
        changeThemeBtn = new JButton(new ImageIcon("src/main/resources/img/default_theme/themebtn_default70.png"));
        changeThemeBtn.setOpaque(false);
        changeThemeBtn.setContentAreaFilled(false);
        changeThemeBtn.setBorderPainted(false);
        changeThemeBtn.setMargin(new Insets(100, 0, 0, 350));
        changeThemeBtn.setActionCommand("Change Theme"); // Set Action Command here
        changeThemeBtn.addActionListener(this);



        changeMapBtn = new JButton(new ImageIcon("src/main/resources/img/default_theme/defalut_map1.png"));
        changeMapBtn.addActionListener(this);
        changeMapBtn.setOpaque(false);
        changeMapBtn.setContentAreaFilled(false);
        changeMapBtn.setBorderPainted(false);
        changeMapBtn.setMargin(new Insets(100, 0, 0, 0));
        changeMapBtn.setActionCommand("Change Map"); // Set Action Command here


        JButton playBtn = new JButton(new ImageIcon("src/main/resources/img/button/play.png"));
        playBtn.setOpaque(false);
        playBtn.setContentAreaFilled(false);
        playBtn.setBorderPainted(false);
        playBtn.setMargin(new Insets(10, 75, 0, 0));


//        playBtn.setBorder(new EmptyBorder(100, 100, 200, 0));
        playBtn.setActionCommand("Play"); // Set Action Command here
        playBtn.addActionListener(e ->  {
            Launcher Mylauncher = new Launcher();
            Launcher.board(levelName);
            Mylauncher.launch(currentTheme);
            Window win = SwingUtilities.getWindowAncestor(playBtn);
            win.dispose();
        });


        backgroundPanel.setLayout(new GridBagLayout());
        setContentPane(backgroundPanel);
        // Add components to JFrame using GridBagLayout


        // Add logo to first row
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        backgroundPanel.add(logoLabel, c);

        // Add play button to second row
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        backgroundPanel.add(playBtn, c);

        // Add change theme button to third row
        c.gridx = 0;
        c.gridy = 3;
        backgroundPanel.add(changeThemeBtn, c);

        // Add change map button to third row
        c.gridx = 1;
        c.gridy = 3;
        backgroundPanel.add(changeMapBtn, c);


        // Set JFrame properties
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) throws IOException {

        new IndexPacman();


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Change Theme")) {
            // Switch to the next theme
            switch (currentTheme) {
                case "A":
                    currentTheme = "B";
                    changeThemeBtn.setIcon(new ImageIcon("src/main/resources/img/amongus_theme/themebtn_amongus70.png"));
                    logoLabel.setIcon(themeB.getLogo());
                    System.out.println(currentTheme);
                    break;
                case "B":
                    currentTheme = "C";
                    changeThemeBtn.setIcon(new ImageIcon("src/main/resources/img/squid_theme/themebtn_squid70.png"));
                    System.out.println(currentTheme);
                    logoLabel.setIcon(themeC.getLogo());
                    break;
                case "C":
                    currentTheme = "D";
                    changeThemeBtn.setIcon(new ImageIcon("src/main/resources/img/zoo_theme/themebtn_zoo70.png"));
                    System.out.println(currentTheme);
                    logoLabel.setIcon(themeD.getLogo());
                    break;
                case "D":
                    currentTheme = "A";
                    changeThemeBtn.setIcon(new ImageIcon("src/main/resources/img/default_theme/themebtn_default70.png"));
                    System.out.println(currentTheme);
                    logoLabel.setIcon(themeA.getLogo());
                    break;
            }
            // Reset to Map1 for the new theme
            //currentMap = "Map1";
            // mapLabel.setIcon(getCurrentMap());
            backgroundPanel.setBackgroundImage(getCurrentMapBg());
            System.out.println(getCurrentMapBg());
            backgroundPanel.repaint();
        }
        else if (e.getActionCommand().equals("Change Map")) {
            // Switch to the next map for the current theme
            switch (currentMap) {
                case "Map1":
                    currentMap = "Map2";
                    changeMapBtn.setIcon(new ImageIcon(getMap()));
                    levelName = "/map1.txt";
                    backgroundPanel.setBackgroundImage(getCurrentMapBg());
                    System.out.println(getCurrentMapBg());
                    backgroundPanel.repaint();
                    break;
                case "Map2":
                    currentMap = "Map3";
                    changeMapBtn.setIcon(new ImageIcon(getMap()));
                    levelName = "/map2.txt";
                    backgroundPanel.setBackgroundImage(getCurrentMapBg());
                    System.out.println(getCurrentMapBg());
                    backgroundPanel.repaint();
                    break;
                case "Map3":
                    currentMap = "Map4";
                    changeMapBtn.setIcon(new ImageIcon(getMap()));
                    levelName = "/map3.txt";
                    backgroundPanel.setBackgroundImage(getCurrentMapBg());
                    System.out.println(getCurrentMapBg());
                    backgroundPanel.repaint();
                    break;
                case "Map4":
                    currentMap = "Map5";
                    changeMapBtn.setIcon(new ImageIcon(getMap()));
                    levelName = "/map4.txt";
                    backgroundPanel.setBackgroundImage(getCurrentMapBg());
                    System.out.println(getCurrentMapBg());
                    backgroundPanel.repaint();
                    break;
                case "Map5":
                    currentMap = "Map1";
                    changeMapBtn.setIcon(new ImageIcon(getMap()));
                    levelName = "/map0.txt";
                    backgroundPanel.setBackgroundImage(getCurrentMapBg());
                    System.out.println(getCurrentMapBg());
                    backgroundPanel.repaint();
                    break;
            }

        }


    }

    private String getCurrentMapBg() {
        switch (currentTheme) {
            case "A":
                switch (currentMap) {
                    case "Map1":
                        return themeA.getMap1().toString();
                    case "Map2":
                        return themeA.getMap2().toString();
                    case "Map3":
                        return themeA.getMap3().toString();
                    case "Map4":
                        return themeA.getMap4().toString();
                    case "Map5":
                        return themeA.getMap5().toString();
                }
                break;
            case "B":
                switch (currentMap) {
                    case "Map1":
                        return themeB.getMap1().toString();
                    case "Map2":
                        return themeB.getMap2().toString();
                    case "Map3":
                        return themeB.getMap3().toString();
                    case "Map4":
                        return themeB.getMap4().toString();
                    case "Map5":
                        return themeB.getMap5().toString();
                }
                break;
            case "C":
                switch (currentMap) {
                    case "Map1":
                        return themeC.getMap1().toString();
                    case "Map2":
                        return themeC.getMap2().toString();
                    case "Map3":
                        return themeC.getMap3().toString();
                    case "Map4":
                        return themeC.getMap4().toString();
                    case "Map5":
                        return themeC.getMap5().toString();
                }
                break;
            case "D":
                switch (currentMap) {
                    case "Map1":
                        return themeD.getMap1().toString();
                    case "Map2":
                        return themeD.getMap2().toString();
                    case "Map3":
                        return themeD.getMap3().toString();
                    case "Map4":
                        return themeD.getMap4().toString();
                    case "Map5":
                        return themeD.getMap5().toString();
                }
                break;
        }
        return null;
    }

    private String getMap() {
        switch (currentTheme) {
            case "A":
                switch (currentMap) {
                    case "Map1":
                        return "src/main/resources/img/default_theme/defalut_map1.png";
                    case "Map2":
                        return "src/main/resources/img/default_theme/defalut_map2.png";
                    case "Map3":
                        return "src/main/resources/img/default_theme/defalut_map3.png";
                    case "Map4":
                        return "src/main/resources/img/default_theme/defalut_map4.png";
                    case "Map5":
                        return "src/main/resources/img/default_theme/defalut_map5.png";
                }
                break;
            case "B":
                switch (currentMap) {
                    case "Map1":
                        return "src/main/resources/img/amongus_theme/amongus_map1.png";
                    case "Map2":
                        return "src/main/resources/img/amongus_theme/amongus_map2.png";
                    case "Map3":
                        return "src/main/resources/img/amongus_theme/amongus_map3.png";
                    case "Map4":
                        return "src/main/resources/img/amongus_theme/amongus_map4.png";
                    case "Map5":
                        return "src/main/resources/img/amongus_theme/amongus_map5.png";
                }
                break;
            case "C":
                switch (currentMap) {
                    case "Map1":
                        return "src/main/resources/img/squid_theme/squid_map1.png";
                    case "Map2":
                        return "src/main/resources/img/squid_theme/squid_map2.png";
                    case "Map3":
                        return "src/main/resources/img/squid_theme/squid_map3.png";
                    case "Map4":
                        return "src/main/resources/img/squid_theme/squid_map4.png";
                    case "Map5":
                        return "src/main/resources/img/squid_theme/squid_map5.png";
                }
                break;
            case "D":
                switch (currentMap) {
                    case "Map1":
                        return "src/main/resources/img/zoo_theme/zoo_map1.png";
                    case "Map2":
                        return "src/main/resources/img/zoo_theme/zoo_map2.png";
                    case "Map3":
                        return "src/main/resources/img/zoo_theme/zoo_map3.png";
                    case "Map4":
                        return "src/main/resources/img/zoo_theme/zoo_map4.png";
                    case "Map5":
                        return "src/main/resources/img/zoo_theme/zoo_map5.png";
                }
                break;

        }
        return null;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }
}

