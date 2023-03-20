package nl.tudelft.jpacman.ui.theme;

import javax.swing.*;

public class ThemeDefault {
    private ImageIcon logo;
    private ImageIcon map1;
    private ImageIcon map2;
    private ImageIcon map3;
    private ImageIcon map4;
    private ImageIcon map5;

    public ThemeDefault() {
        // Load the images for Theme A
        logo = new ImageIcon("./src/main/resources/img/default_theme/default_logo.png");
        map1 = new ImageIcon("./src/main/resources/img/default_theme/background/default_1.png");
        map2 = new ImageIcon("./src/main/resources/img/default_theme/background/default_2.png");
        map3 = new ImageIcon("./src/main/resources/img/default_theme/background/default_3.png");
        map4 = new ImageIcon("./src/main/resources/img/default_theme/background/default_4.png");
        map5 = new ImageIcon("./src/main/resources/img/default_theme/background/default_5.png");
    }

    public ImageIcon getLogo() {
        return logo;
    }

    public ImageIcon getMap1() {
        return map1;
    }

    public ImageIcon getMap2() {
        return map2;
    }

    public ImageIcon getMap3() {
        return map3;
    }

    public ImageIcon getMap4() {
        return map4;
    }

    public ImageIcon getMap5() {
        return map5;
    }

}
