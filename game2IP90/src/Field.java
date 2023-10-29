import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import javax.print.StreamPrintServiceFactory;
import javax.swing.*;

/**
 * Generates a frame with the field, obstacles and troops
 */
public class Field extends JPanel {
    private static final int TILE_SIZE = 50;
    private static final int FIELD_WIDTH = 9;
    private static final int FIELD_LENGTH = 17;

    // { y , x} //
    // 1 cap,2 lob,2 inf, 1 sprinter, 1 tank
    private static int[][] INFANTRY = { { 0, 1 }, { 2, 0 }, { 8, 1 }, { 0, 15 }, { 6, 16 }, { 8, 15 } };
    private static int[][] SPRINTER = { { 6, 0 }, { 2, 16 } };
    private static int[][] LOBBER = { { 3, 1 }, { 5, 1 }, { 3, 15 }, { 5, 15 } };
    private static int[][] CAPITAN = { { 4, 1 }, { 4, 15 } };

    private static int[][] ROCKS = { { 1, 5 }, { 1, 11 }, { 8, 11 } };
    // wall piece that goes from left to right
    private static int[][] WALL_HOR = { { 7, 7 }, { 7, 8 } };
    // wall piece that goes from top to bottom
    private static int[][] WALL_VER = { { 4, 4 }, { 0, 7 }, { 1, 7 }, { 2, 7 }, { 6, 9 }, { 4, 12 } };
    // end of wall that comes from top
    private static int[][] WALL_N_END = { { 5, 4 }, { 3, 7 } };
    // end of wall that comes from bottom
    private static int[][] WALL_S_END = { { 5, 9 }, { 3, 12 } };
    // end of wall that comes from left
    private static int[][] WALL_W_END = { { 5, 13 } };
    // end of wall that comes from right
    private static int[][] WALL_E_END = { { 3, 3 }, { 7, 6 } };
    // corner wall from left to top
    private static int[][] WALL_COR1 = { { 7, 9 } };
    // corner wall from right to top
    private static int[][] WALL_COR2 = { { 5, 12 } };
    // corner wall from left to bottom
    private static int[][] WALL_COR3 = { { 3, 4 } };

    JPanel panelForTiles;
    Tile tiles[][];
    ArrayList<Troop> troops = new ArrayList<Troop>();
    ArrayList<Object> objects = new ArrayList<Object>();
    ActionListener AL_F;
    MainFrame mf;
    TileListener tl = new TileListener(this);
    JButton back;

    JLabel infoLabel = new JLabel("");
    Popup pop;
    JButton walk;
    JButton shoot;

    JLabel infImage1;
    JLabel infImage2;
    JLabel infImage3;
    JLabel infImage4;
    JLabel infImage5;
    JLabel infImage6;

    JLabel lobberImage1;
    JLabel lobberImage2;
    JLabel lobberImage3;
    JLabel lobberImage4;

    JLabel capitanImage1;
    JLabel capitanImage2;

    JLabel sprinterImage1;
    JLabel sprinterImage2;

    JLabel rockImage1;
    JLabel rockImage2;
    JLabel rockImage3;

    JLabel wall1Image1;
    JLabel wall1Image2;

    JLabel wall2Image1;
    JLabel wall2Image2;
    JLabel wall2Image3;
    JLabel wall2Image4;
    JLabel wall2Image5;
    JLabel wall2Image6;

    JLabel wall3Image1;
    JLabel wall3Image2;

    JLabel wall4Image1;
    JLabel wall4Image2;

    JLabel wall5Image;

    JLabel wall6Image1;
    JLabel wall6Image2;

    JLabel wall7Image;

    JLabel wall8Image;

    JLabel wall9Image;

    Field(MainFrame mf) {
        this.mf = mf;
        back = new JButton("←");

    }

    public void generateField() {
        new JPanel();
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        // setPreferredSize(new Dimension(900, 650));// hardCoded sizing
        panelForTiles = new JPanel();
        panelForTiles.setLayout(new GridLayout(FIELD_WIDTH, FIELD_LENGTH));

        panelForTiles.setBackground(getBackground());
        panelForTiles.setPreferredSize(new Dimension(17 * TILE_SIZE, 9 * TILE_SIZE));// hardCoded sizing
        panelForTiles.setMaximumSize(new Dimension(17 * TILE_SIZE, 9 * TILE_SIZE));
        // hardCoded sizing
        panelForTiles.setMinimumSize(new Dimension(17 * TILE_SIZE, 9 * TILE_SIZE));

        GridBagConstraints constraints = new GridBagConstraints();

        tiles = new Tile[FIELD_WIDTH][FIELD_LENGTH];
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {

                Tile temp = new Tile(k, i);
                if (k == 0) {
                    temp.setBackground(new Color(100, 149, 237));
                } else if (k == 16) {
                    temp.setBackground(new Color(220, 20, 60));
                } else {
                    temp.setBackground(new Color(73, 132, 103));

                }
                temp.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                temp.setMaximumSize(temp.getPreferredSize());
                temp.setBorder(BorderFactory.createLineBorder(Color.black));
                temp.addMouseListener(tl);
                panelForTiles.add(temp);
                tiles[i][k] = temp;
            }
        }

        panelForTiles.repaint();

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridy = 1;
        add(panelForTiles, constraints);
        infoLabel.setForeground(Color.white);
        infoLabel.setText("P1 turn");
        infoLabel.setFont(new Font("Sans", Font.PLAIN, 40));
        add(infoLabel);

        revalidate();
        repaint();

        // The Back Panel //
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());

        back.setLayout(new GridLayout());
        back.setPreferredSize(new Dimension(70, 40));
        back.setOpaque(false);
        back.setFocusPainted(false);
        back.setFont(new Font("Sans", Font.BOLD, 35));
        back.setBorderPainted(false);
        back.setBackground(Color.WHITE);
        back.setForeground(Color.WHITE);

        back.setActionCommand("BACK");

        constraints.gridx = 0;
        constraints.gridy = 0;
        buttonPanel.add(back, constraints);
        buttonPanel.add(Box.createVerticalStrut(50));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(back, constraints);

    }

    public void generateTroops() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        java.net.URL imgURLI1 = classLoader.getResource("source/images/infantry1.png");
        java.net.URL imgURLC1 = classLoader.getResource("source/images/cap1.png");
        java.net.URL imgURLS1 = classLoader.getResource("source/images/sprint1.png");
        java.net.URL imgURLL1 = classLoader.getResource("source/images/lobber1.png");

        java.net.URL imgURLI2 = classLoader.getResource("source/images/infantry2.png");
        java.net.URL imgURLC2 = classLoader.getResource("source/images/cap2.png");
        java.net.URL imgURLS2 = classLoader.getResource("source/images/sprint2.png");
        java.net.URL imgURLL2 = classLoader.getResource("source/images/lobber2.png");

        infImage1 = new JLabel("", new ImageIcon(imgURLI1), JLabel.CENTER);
        infImage2 = new JLabel("", new ImageIcon(imgURLI1), JLabel.CENTER);
        infImage3 = new JLabel("", new ImageIcon(imgURLI1), JLabel.CENTER);
        infImage4 = new JLabel("", new ImageIcon(imgURLI2), JLabel.CENTER);
        infImage5 = new JLabel("", new ImageIcon(imgURLI2), JLabel.CENTER);
        infImage6 = new JLabel("", new ImageIcon(imgURLI2), JLabel.CENTER);

        lobberImage1 = new JLabel("", new ImageIcon(imgURLL1), JLabel.CENTER);
        lobberImage2 = new JLabel("", new ImageIcon(imgURLL1), JLabel.CENTER);
        lobberImage3 = new JLabel("", new ImageIcon(imgURLL2), JLabel.CENTER);
        lobberImage4 = new JLabel("", new ImageIcon(imgURLL2), JLabel.CENTER);

        capitanImage1 = new JLabel("", new ImageIcon(imgURLC1), JLabel.CENTER);
        capitanImage2 = new JLabel("", new ImageIcon(imgURLC2), JLabel.CENTER);

        sprinterImage1 = new JLabel("", new ImageIcon(imgURLS1), JLabel.CENTER);
        sprinterImage2 = new JLabel("", new ImageIcon(imgURLS2), JLabel.CENTER);

        // put infantry
        Infantry inf1 = new Infantry((int) tiles[INFANTRY[0][0]][INFANTRY[0][1]].d.getX(),
                (int) tiles[INFANTRY[0][0]][INFANTRY[0][1]].d.getY(), 1, infImage1);
        Infantry inf2 = new Infantry((int) tiles[INFANTRY[1][0]][INFANTRY[1][1]].d.getX(),
                (int) tiles[INFANTRY[1][0]][INFANTRY[1][1]].d.getY(), 1, infImage2);
        Infantry inf3 = new Infantry((int) tiles[INFANTRY[2][0]][INFANTRY[2][1]].d.getX(),
                (int) tiles[INFANTRY[2][0]][INFANTRY[2][1]].d.getY(), 1, infImage3);
        Infantry inf4 = new Infantry((int) tiles[INFANTRY[3][0]][INFANTRY[3][1]].d.getX(),
                (int) tiles[INFANTRY[3][0]][INFANTRY[3][1]].d.getY(), 2, infImage4);
        Infantry inf5 = new Infantry((int) tiles[INFANTRY[4][0]][INFANTRY[4][1]].d.getX(),
                (int) tiles[INFANTRY[4][0]][INFANTRY[4][1]].d.getY(), 2, infImage5);
        Infantry inf6 = new Infantry((int) tiles[INFANTRY[5][0]][INFANTRY[5][1]].d.getX(),
                (int) tiles[INFANTRY[5][0]][INFANTRY[5][1]].d.getY(), 2, infImage6);
        tiles[INFANTRY[0][0]][INFANTRY[0][1]].addTroop(inf1, inf1.image);
        tiles[INFANTRY[1][0]][INFANTRY[1][1]].addTroop(inf2, inf2.image);
        tiles[INFANTRY[2][0]][INFANTRY[2][1]].addTroop(inf3, inf3.image);
        tiles[INFANTRY[3][0]][INFANTRY[3][1]].addTroop(inf4, inf4.image);
        tiles[INFANTRY[4][0]][INFANTRY[4][1]].addTroop(inf5, inf5.image);
        tiles[INFANTRY[5][0]][INFANTRY[5][1]].addTroop(inf6, inf6.image);
        troops.add(inf1);
        troops.add(inf2);
        troops.add(inf3);
        troops.add(inf4);
        troops.add(inf5);
        troops.add(inf6);

        // put sprinters
        Sprinter sp1 = new Sprinter((int) tiles[SPRINTER[0][0]][SPRINTER[0][1]].d.getX(),
                (int) tiles[SPRINTER[0][0]][SPRINTER[0][1]].d.getY(), 1, sprinterImage1);
        Sprinter sp2 = new Sprinter((int) tiles[SPRINTER[1][0]][SPRINTER[1][1]].d.getX(),
                (int) tiles[SPRINTER[1][0]][SPRINTER[1][1]].d.getY(), 2, sprinterImage2);
        tiles[SPRINTER[0][0]][SPRINTER[0][1]].addTroop(sp1, sp1.image);
        tiles[SPRINTER[1][0]][SPRINTER[1][1]].addTroop(sp2, sp2.image);
        troops.add(sp1);
        troops.add(sp2);

        // put lobbers
        Lobber l1 = new Lobber((int) tiles[LOBBER[0][0]][LOBBER[0][1]].d.getX(),
                (int) tiles[LOBBER[0][0]][LOBBER[0][1]].d.getY(), 1, lobberImage1);
        Lobber l2 = new Lobber((int) tiles[LOBBER[1][0]][LOBBER[1][1]].d.getX(),
                (int) tiles[LOBBER[1][0]][LOBBER[1][1]].d.getY(), 1, lobberImage2);
        Lobber l3 = new Lobber((int) tiles[LOBBER[2][0]][LOBBER[2][1]].d.getX(),
                (int) tiles[LOBBER[2][0]][LOBBER[2][1]].d.getY(), 2, lobberImage3);
        Lobber l4 = new Lobber((int) tiles[LOBBER[3][0]][LOBBER[3][1]].d.getX(),
                (int) tiles[LOBBER[3][0]][LOBBER[3][1]].d.getY(), 2, lobberImage4);
        tiles[LOBBER[0][0]][LOBBER[0][1]].addTroop(l1, l1.image);
        tiles[LOBBER[1][0]][LOBBER[1][1]].addTroop(l2, l2.image);
        tiles[LOBBER[2][0]][LOBBER[2][1]].addTroop(l3, l3.image);
        tiles[LOBBER[3][0]][LOBBER[3][1]].addTroop(l4, l4.image);
        troops.add(l1);
        troops.add(l2);
        troops.add(l3);
        troops.add(l4);

        // put capitans
        Capitan cap1 = new Capitan((int) tiles[CAPITAN[0][0]][CAPITAN[0][1]].d.getX(),
                (int) tiles[CAPITAN[0][0]][CAPITAN[0][1]].d.getY(), 1, capitanImage1);
        Capitan cap2 = new Capitan((int) tiles[CAPITAN[1][0]][CAPITAN[1][1]].d.getX(),
                (int) tiles[CAPITAN[1][0]][CAPITAN[1][1]].d.getY(), 2, capitanImage2);
        tiles[CAPITAN[0][0]][CAPITAN[0][1]].addTroop(cap1, cap1.image);
        tiles[CAPITAN[1][0]][CAPITAN[1][1]].addTroop(cap2, cap2.image);
        troops.add(cap1);
        troops.add(cap2);

        revalidate();
        repaint();
    }

    public void generateObjects() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        java.net.URL imgURLR = classLoader.getResource("source/images/rock.png");

        java.net.URL imgURLW1 = classLoader.getResource("source/images/wall_H.png");
        java.net.URL imgURLW2 = classLoader.getResource("source/images/wall_V.png");
        java.net.URL imgURLW3 = classLoader.getResource("source/images/wall_N.png");
        java.net.URL imgURLW4 = classLoader.getResource("source/images/wall_S.png");
        java.net.URL imgURLW5 = classLoader.getResource("source/images/wall_W.png");
        java.net.URL imgURLW6 = classLoader.getResource("source/images/wall_E.png");
        java.net.URL imgURLW7 = classLoader.getResource("source/images/wall_W_N.png");
        java.net.URL imgURLW8 = classLoader.getResource("source/images/wall_N_E.png");
        java.net.URL imgURLW9 = classLoader.getResource("source/images/wall_W_S.png");

        rockImage1 = new JLabel("", new ImageIcon(imgURLR), JLabel.CENTER);
        rockImage2 = new JLabel("", new ImageIcon(imgURLR), JLabel.CENTER);
        rockImage3 = new JLabel("", new ImageIcon(imgURLR), JLabel.CENTER);

        wall1Image1 = new JLabel("", new ImageIcon(imgURLW1), JLabel.CENTER);
        wall1Image2 = new JLabel("", new ImageIcon(imgURLW1), JLabel.CENTER);
        
        wall2Image1 = new JLabel("", new ImageIcon(imgURLW2), JLabel.CENTER);
        wall2Image2 = new JLabel("", new ImageIcon(imgURLW2), JLabel.CENTER);
        wall2Image3 = new JLabel("", new ImageIcon(imgURLW2), JLabel.CENTER);
        wall2Image4 = new JLabel("", new ImageIcon(imgURLW2), JLabel.CENTER);
        wall2Image5 = new JLabel("", new ImageIcon(imgURLW2), JLabel.CENTER);
        wall2Image6 = new JLabel("", new ImageIcon(imgURLW2), JLabel.CENTER);

        wall3Image1 = new JLabel("", new ImageIcon(imgURLW3), JLabel.CENTER);
        wall3Image2 = new JLabel("", new ImageIcon(imgURLW3), JLabel.CENTER);

        wall4Image1 = new JLabel("", new ImageIcon(imgURLW4), JLabel.CENTER);
        wall4Image2 = new JLabel("", new ImageIcon(imgURLW4), JLabel.CENTER);

        wall5Image = new JLabel("", new ImageIcon(imgURLW5), JLabel.CENTER);

        wall6Image1 = new JLabel("", new ImageIcon(imgURLW6), JLabel.CENTER);
        wall6Image2 = new JLabel("", new ImageIcon(imgURLW6), JLabel.CENTER);

        wall7Image = new JLabel("", new ImageIcon(imgURLW7), JLabel.CENTER);

        wall8Image = new JLabel("", new ImageIcon(imgURLW8), JLabel.CENTER);

        wall9Image = new JLabel("", new ImageIcon(imgURLW9), JLabel.CENTER);


        // put rocks
        Rock rock1 = new Rock((int) tiles[ROCKS[0][0]][ROCKS[0][1]].d.getX(),
                (int) tiles[ROCKS[0][0]][ROCKS[0][1]].d.getY(), rockImage1);
        Rock rock2 = new Rock((int) tiles[ROCKS[1][0]][ROCKS[1][1]].d.getX(),
                (int) tiles[ROCKS[1][0]][ROCKS[1][1]].d.getY(), rockImage2);
        Rock rock3 = new Rock((int) tiles[ROCKS[2][0]][ROCKS[2][1]].d.getX(),
                (int) tiles[ROCKS[2][0]][ROCKS[2][1]].d.getY(), rockImage3);
        tiles[ROCKS[0][0]][ROCKS[0][1]].addObject(rock1, rock1.image);
        tiles[ROCKS[1][0]][ROCKS[1][1]].addObject(rock2, rock2.image);
        tiles[ROCKS[2][0]][ROCKS[2][1]].addObject(rock3, rock3.image);
        objects.add(rock1);
        objects.add(rock2);
        objects.add(rock3);

        // add HOR walls
        Wall wallH_1 = new Wall((int) tiles[WALL_HOR[0][0]][WALL_HOR[0][1]].d.getX(),
                (int) tiles[WALL_HOR[0][0]][WALL_HOR[0][1]].d.getY(), wall1Image1);
        Wall wallH_2 = new Wall((int) tiles[WALL_HOR[1][0]][WALL_HOR[1][1]].d.getX(),
                (int) tiles[WALL_HOR[1][0]][WALL_HOR[1][1]].d.getY(), wall1Image2);
        tiles[WALL_HOR[0][0]][WALL_HOR[0][1]].addObject(wallH_1, wallH_1.image);
        tiles[WALL_HOR[1][0]][WALL_HOR[1][1]].addObject(wallH_2, wallH_2.image);
        objects.add(wallH_1);
        objects.add(wallH_2);
        
        // add VER walls
        Wall wallV_1 = new Wall((int) tiles[WALL_VER[0][0]][WALL_VER[0][1]].d.getX(),
                (int) tiles[WALL_VER[0][0]][WALL_VER[0][1]].d.getY(), wall2Image1);
        Wall wallV_2 = new Wall((int) tiles[WALL_VER[1][0]][WALL_VER[1][1]].d.getX(),
                (int) tiles[WALL_VER[1][0]][WALL_VER[1][1]].d.getY(), wall2Image2);
        Wall wallV_3 = new Wall((int) tiles[WALL_VER[2][0]][WALL_VER[2][1]].d.getX(),
                (int) tiles[WALL_VER[2][0]][WALL_VER[2][1]].d.getY(), wall2Image3);
        Wall wallV_4 = new Wall((int) tiles[WALL_VER[3][0]][WALL_VER[3][1]].d.getX(),
                (int) tiles[WALL_VER[3][0]][WALL_VER[3][1]].d.getY(), wall2Image4);
        Wall wallV_5 = new Wall((int) tiles[WALL_VER[4][0]][WALL_VER[4][1]].d.getX(),
                (int) tiles[WALL_VER[4][0]][WALL_VER[4][1]].d.getY(), wall2Image5);
        Wall wallV_6 = new Wall((int) tiles[WALL_VER[5][0]][WALL_VER[5][1]].d.getX(),
                (int) tiles[WALL_VER[5][0]][WALL_VER[5][1]].d.getY(), wall2Image6);
        tiles[WALL_VER[0][0]][WALL_VER[0][1]].addObject(wallV_1, wallV_1.image);
        tiles[WALL_VER[1][0]][WALL_VER[1][1]].addObject(wallV_2, wallV_2.image);
        tiles[WALL_VER[2][0]][WALL_VER[2][1]].addObject(wallV_3, wallV_3.image);
        tiles[WALL_VER[3][0]][WALL_VER[3][1]].addObject(wallV_4, wallV_4.image);
        tiles[WALL_VER[4][0]][WALL_VER[4][1]].addObject(wallV_5, wallV_5.image);
        tiles[WALL_VER[5][0]][WALL_VER[5][1]].addObject(wallV_6, wallV_6.image);
        objects.add(wallV_1);
        objects.add(wallV_2);
        objects.add(wallV_3);
        objects.add(wallV_4);
        objects.add(wallV_5);
        objects.add(wallV_6);

        // add NORTH walls
        Wall wallN_1 = new Wall((int) tiles[WALL_N_END[0][0]][WALL_N_END[0][1]].d.getX(),
                (int) tiles[WALL_N_END[0][0]][WALL_N_END[0][1]].d.getY(), wall3Image1);
        Wall wallN_2 = new Wall((int) tiles[WALL_N_END[1][0]][WALL_N_END[1][1]].d.getX(),
                (int) tiles[WALL_N_END[1][0]][WALL_N_END[1][1]].d.getY(), wall3Image2);
        tiles[WALL_N_END[0][0]][WALL_N_END[0][1]].addObject(wallN_1, wallN_1.image);
        tiles[WALL_N_END[1][0]][WALL_N_END[1][1]].addObject(wallN_2, wallN_2.image);
        objects.add(wallN_1);
        objects.add(wallN_2);

        // add SOUTH walls
        Wall wallS_1 = new Wall((int) tiles[WALL_S_END[0][0]][WALL_S_END[0][1]].d.getX(),
                (int) tiles[WALL_S_END[0][0]][WALL_S_END[0][1]].d.getY(), wall4Image1);
        Wall wallS_2 = new Wall((int) tiles[WALL_S_END[1][0]][WALL_S_END[1][1]].d.getX(),
                (int) tiles[WALL_S_END[1][0]][WALL_S_END[1][1]].d.getY(), wall4Image2);
        tiles[WALL_S_END[0][0]][WALL_S_END[0][1]].addObject(wallS_1, wallS_1.image);
        tiles[WALL_S_END[1][0]][WALL_S_END[1][1]].addObject(wallS_2, wallS_2.image);
        objects.add(wallS_1);
        objects.add(wallS_2);

        // add WEST walls
        Wall wallW = new Wall((int) tiles[WALL_W_END[0][0]][WALL_W_END[0][1]].d.getX(),
                (int) tiles[WALL_W_END[0][0]][WALL_W_END[0][1]].d.getY(), wall5Image);
        tiles[WALL_W_END[0][0]][WALL_W_END[0][1]].addObject(wallW, wallW.image);
        objects.add(wallW);

        // add EAST walls
        Wall wallE_1 = new Wall((int) tiles[WALL_E_END[0][0]][WALL_E_END[0][1]].d.getX(),
                (int) tiles[WALL_E_END[0][0]][WALL_E_END[0][1]].d.getY(), wall6Image1);
        Wall wallE_2 = new Wall((int) tiles[WALL_E_END[1][0]][WALL_E_END[1][1]].d.getX(),
                (int) tiles[WALL_E_END[1][0]][WALL_E_END[1][1]].d.getY(), wall6Image2);
        tiles[WALL_E_END[0][0]][WALL_E_END[0][1]].addObject(wallE_1, wallE_1.image);
        tiles[WALL_E_END[1][0]][WALL_E_END[1][1]].addObject(wallE_2, wallE_2.image);
        objects.add(wallE_1);
        objects.add(wallE_2);

        // add WEST-NORTH walls
        Wall wallWN = new Wall((int) tiles[WALL_COR1[0][0]][WALL_COR1[0][1]].d.getX(),
                (int) tiles[WALL_COR1[0][0]][WALL_COR1[0][1]].d.getY(), wall7Image);
        tiles[WALL_COR1[0][0]][WALL_COR1[0][1]].addObject(wallWN, wallWN.image);
        objects.add(wallWN);
        
        // add NORTH-EAST walls
        Wall wallNE = new Wall((int) tiles[WALL_COR2[0][0]][WALL_COR2[0][1]].d.getX(),
                (int) tiles[WALL_COR2[0][0]][WALL_COR2[0][1]].d.getY(), wall8Image);
        tiles[WALL_COR2[0][0]][WALL_COR2[0][1]].addObject(wallNE, wallNE.image);
        objects.add(wallNE);

        // add WEST-SOUTH walls
        Wall wallWS = new Wall((int) tiles[WALL_COR3[0][0]][WALL_COR3[0][1]].d.getX(),
                (int) tiles[WALL_COR3[0][0]][WALL_COR3[0][1]].d.getY(), wall9Image);
        tiles[WALL_COR3[0][0]][WALL_COR3[0][1]].addObject(wallWS, wallWS.image);
        objects.add(wallWS);

        revalidate();
        repaint();
    }

    public void addListener(ActionHandler al) {
        back.addActionListener(al);
        back.setActionCommand("BACK");
    }

    public void moveTroop(Point startPoint, Point destinationPoint) {
        Troop troop = tiles[(int) startPoint.getY()][(int) startPoint.getX()].troop;
        tiles[(int) destinationPoint.getY()][(int) destinationPoint.getX()].addTroop(troop, troop.image);
        tiles[(int) startPoint.getY()][(int) startPoint.getX()].removeTroop();
        revalidate();
        repaint();

    }

    public boolean eliminateTroop(Point coords) {
        Troop troopToElim = tiles[(int) coords.getY()][(int) coords.getX()].troop;
        System.out.println(troopToElim.toString());
        tiles[(int) coords.getY()][(int) coords.getX()].removeTroop();
        troops.remove(troopToElim);
        revalidate();
        repaint();
        // check enemy troops left
        if (troopToElim.player == 1) {
            for (Troop t : troops) {
                if (t.player == 2) {
                    return false;
                }
            }
        } else {
            for (Troop t : troops) {
                if (t.player == 1) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean checkObstacle(Point troopLoc, Point destinationLoc, int diffX, int diffY) {
        int trace[][];
        if (diffX != 0 && diffY == 0) {
            trace = new int[Math.abs(diffX) - 1][Math.abs(diffX) - 1];
            if (diffX < 0) {
                for (int i = -1, k = 0; i > diffX; i--, k++) {
                    trace[k][0] = i + (int) troopLoc.getX();
                    trace[k][1] = (int) troopLoc.getY();
                }

            } else {
                for (int i = 1, k = 0; i < diffX; i++, k++) {
                    trace[k][0] = i + (int) troopLoc.getX();
                    trace[k][1] = (int) troopLoc.getY();
                }
            }
        } else if (diffX == 0 && diffY != 0) {
            trace = new int[Math.abs(diffY) - 1][Math.abs(diffY) - 1];
            if (diffY < 0) {
                for (int i = -1, k = 0; i > diffY; i--, k++) {
                    trace[k][0] = (int) troopLoc.getX();
                    trace[k][1] = i + (int) troopLoc.getY();
                }

            } else {
                for (int i = 1, k = 0; i < diffY; i++, k++) {
                    trace[k][0] = (int) troopLoc.getX();
                    trace[k][1] = i + (int) troopLoc.getY();
                }
            }
        } else {
            trace = new int[Math.abs(diffY) - 1][Math.abs(diffY) - 1];
            if (diffX < 0 && diffY < 0) {
                for (int i = 1, k = 0; i < Math.abs(diffY); i++, k++) {
                    trace[k][0] = (int) troopLoc.getX() - i;
                    trace[k][1] = (int) troopLoc.getY() - i;
                }
            } else if (diffX < 0 && diffY > 0) {
                for (int i = 1, k = 0; i < diffY; i++, k++) {
                    trace[k][0] = (int) troopLoc.getX() - i;
                    trace[k][1] = i + (int) troopLoc.getY();
                }
            } else if (diffX > 0 && diffY < 0) {
                for (int i = 1, k = 0; i < diffY; i++, k++) {
                    trace[k][0] = (int) troopLoc.getX() + i;
                    trace[k][1] = (int) troopLoc.getY() - i;
                }
            } else if (diffX > 0 && diffY > 0) {
                for (int i = 1, k = 0; i < diffY; i++, k++) {
                    trace[k][0] = (int) troopLoc.getX() + i;
                    trace[k][1] = (int) troopLoc.getY() + i;
                }
            }

        }

        return trace(trace);
    }

    public boolean trace(int[][] trace) {
        for(int i=0; i<trace.length;i++){
            // ||tiles[trace[i][0]][trace[i][1]].troop!=null
            if(tiles[trace[i][0]][trace[i][1]].object!=null){
                return false;
            }
        }
        return true;
    }

    public void changeInfoLabel(String text) {
        infoLabel.setText(text);
        repaint();

    }
}
