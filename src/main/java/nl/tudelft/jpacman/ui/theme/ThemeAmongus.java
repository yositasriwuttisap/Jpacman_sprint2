package nl.tudelft.jpacman.ui.theme;

import javax.swing.*;

public class ThemeAmongus {
    private ImageIcon logo;
    private ImageIcon map1;
    private ImageIcon map2;
    private ImageIcon map3;
    private ImageIcon map4;
    private ImageIcon map5;

    public ThemeAmongus() {
        // Load the images for Theme A
        logo = new ImageIcon("./src/main/resources/img/amongus_theme/amongus_logo.png");
        map1 = new ImageIcon("./src/main/resources/img/amongus_theme/background/amongus_1.png");
        map2 = new ImageIcon("./src/main/resources/img/amongus_theme/background/amongus_2.png");
        map3 = new ImageIcon("./src/main/resources/img/amongus_theme/background/amongus_3.png");
        map4 = new ImageIcon("./src/main/resources/img/amongus_theme/background/amongus_4.png");
        map5 = new ImageIcon("./src/main/resources/img/amongus_theme/background/amongus_5.png");
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

    public static void main(String[] args) {
        ThemeAmongus themeA = new ThemeAmongus();

        System.out.println(themeA.getMap1().toString());
    }
}
