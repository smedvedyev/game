import java.awt.*;
import java.awt.event.ActionListener;
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

    // 1 cap,2 lob,2 inf, 1 sprinter, 1 tank
    private static int[][] INFANTRY = { { 0, 1 }, { 8, 1 }, { 0, 15 }, { 8, 15 } };
    private static int[][] SPRINTER = { { 6, 0 }, { 2, 16 } };
    private static int[][] LOBBER = { { 3, 1 }, { 5, 1 }, { 3, 15 }, { 5, 15 } };
    private static int[][] CAPITAN = { { 4, 1 }, { 4, 15 } };
    private static int[][] TANK = { { 2, 0 }, { 6, 16 } };

    JPanel panelForTiles;
    Tile tiles[][];
    ArrayList<Troop> troops = new ArrayList<Troop>();
    ActionListener AL_F;
    MainFrame mf;
    TileListener tl = new TileListener(this);
    JButton back;

    JLabel infoLabel = new JLabel("kur");
    Popup pop;
    JButton walk;
    JButton shoot;

    JLabel infImage1;
    JLabel infImage2;
    JLabel infImage3;
    JLabel infImage4;

    JLabel tankImage1;
    JLabel tankImage2;

    JLabel lobberImage1;
    JLabel lobberImage2;
    JLabel lobberImage3;
    JLabel lobberImage4;

    JLabel capitanImage1;
    JLabel capitanImage2;

    JLabel sprinterImage1;
    JLabel sprinterImage2;

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
        java.net.URL imgURLI = getClass().getResource("source/images/infantry.png");
        java.net.URL imgURLT = getClass().getResource("source/images/tank.jpg");
        java.net.URL imgURLC = getClass().getResource("source/images/cap.png");
        java.net.URL imgURLS = getClass().getResource("source/images/sprint.png");
        java.net.URL imgURLL = getClass().getResource("source/images/lobber.png");

        infImage1 = new JLabel("", new ImageIcon(imgURLI), JLabel.CENTER);
        infImage2 = new JLabel("", new ImageIcon(imgURLI), JLabel.CENTER);
        infImage3 = new JLabel("", new ImageIcon(imgURLI), JLabel.CENTER);
        infImage4 = new JLabel("", new ImageIcon(imgURLI), JLabel.CENTER);

        tankImage1 = new JLabel("", new ImageIcon(imgURLT), JLabel.CENTER);
        tankImage2 = new JLabel("", new ImageIcon(imgURLT), JLabel.CENTER);

        lobberImage1 = new JLabel("", new ImageIcon(imgURLL), JLabel.CENTER);
        lobberImage2 = new JLabel("", new ImageIcon(imgURLL), JLabel.CENTER);
        lobberImage3 = new JLabel("", new ImageIcon(imgURLL), JLabel.CENTER);
        lobberImage4 = new JLabel("", new ImageIcon(imgURLL), JLabel.CENTER);

        capitanImage1 = new JLabel("", new ImageIcon(imgURLC), JLabel.CENTER);
        capitanImage2 = new JLabel("", new ImageIcon(imgURLC), JLabel.CENTER);

        sprinterImage1 = new JLabel("", new ImageIcon(imgURLS), JLabel.CENTER);
        sprinterImage2 = new JLabel("", new ImageIcon(imgURLS), JLabel.CENTER);

        // put infantry
        Infantry inf1 = new Infantry((int) tiles[INFANTRY[0][0]][INFANTRY[0][1]].d.getX(),
                (int) tiles[INFANTRY[0][0]][INFANTRY[0][1]].d.getY(), 1, infImage1);
        Infantry inf2 = new Infantry((int) tiles[INFANTRY[1][0]][INFANTRY[1][1]].d.getX(),
                (int) tiles[INFANTRY[1][0]][INFANTRY[1][1]].d.getY(), 1, infImage2);
        Infantry inf3 = new Infantry((int) tiles[INFANTRY[2][0]][INFANTRY[2][1]].d.getX(),
                (int) tiles[INFANTRY[2][0]][INFANTRY[2][1]].d.getY(), 2, infImage3);
        Infantry inf4 = new Infantry((int) tiles[INFANTRY[3][0]][INFANTRY[3][1]].d.getX(),
                (int) tiles[INFANTRY[3][0]][INFANTRY[3][1]].d.getY(), 2, infImage4);
        tiles[INFANTRY[0][0]][INFANTRY[0][1]].addTroop(inf1, inf1.image);
        tiles[INFANTRY[1][0]][INFANTRY[1][1]].addTroop(inf2, inf2.image);
        tiles[INFANTRY[2][0]][INFANTRY[2][1]].addTroop(inf3, inf3.image);
        tiles[INFANTRY[3][0]][INFANTRY[3][1]].addTroop(inf4, inf4.image);
        troops.add(inf1);
        troops.add(inf2);
        troops.add(inf3);
        troops.add(inf4);
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

        // put tanks
        Tank t1 = new Tank((int) tiles[TANK[0][0]][TANK[0][1]].d.getX(), (int) tiles[TANK[0][0]][TANK[0][1]].d.getY(),
                1, tankImage1);
        Tank t2 = new Tank((int) tiles[TANK[1][0]][TANK[1][1]].d.getX(), (int) tiles[TANK[1][0]][TANK[1][1]].d.getY(),
                2, tankImage2);
        tiles[TANK[0][0]][TANK[0][1]].addTroop(t1, t1.image);
        tiles[TANK[1][0]][TANK[1][1]].addTroop(t2, t2.image);
        troops.add(t1);
        troops.add(t2);

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

    public void changeInfoLabel(String text) {
        infoLabel.setText(text);
        repaint();

    }
}
