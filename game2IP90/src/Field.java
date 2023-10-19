import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Generates a frame with the field, obstacles and troops
 */
public class Field extends JPanel {
    private static final int TILE_SIZE = 50;
    private static final int FIELD_WIDTH = 9;
    private static final int FIELD_LENGTH = 17;
    //1 cap,2 lob,2 inf, 1 sprinter, 1 tank
    private static int[][] INFANTRY_START = {{1,0}, {0,2}, {1,3}, {1,4},{1,5},{0,6},{1,8}};
    private static int[][] LOBBER_START = {{15,0}, {16,2}, {15,3}, {15,4},{15,5},{16,6},{15,8}};
    private static int[][] TANK_START = {{15,0}, {16,2}, {15,3}, {15,4},{15,5},{16,6},{15,8}};
    private static int[][] SPRINTER_START = {{15,0}, {16,2}, {15,3}, {15,4},{15,5},{16,6},{15,8}};
    private static int[][] CAPTAIN = {{15,0}, {16,2}, {15,3}, {15,4},{15,5},{16,6},{15,8}};
    JPanel panelForTiles;
    Tile tiles[][];
    ActionListener AL_F;
    MainFrame mf;

    JButton back;

    Popup pop;
    JButton walk;
    JButton shoot;

    Field(MainFrame mf) {
        this.mf = mf;
        new JPanel();
        setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // setPreferredSize(new Dimension(900, 650));// hardCoded sizing
        panelForTiles = new JPanel();
        panelForTiles.setLayout(new GridLayout(FIELD_WIDTH, FIELD_LENGTH));

        panelForTiles.setBackground(getBackground());
        panelForTiles.setPreferredSize(new Dimension(17 * TILE_SIZE, 9 * TILE_SIZE));// hardCoded sizing
        panelForTiles.setMaximumSize(new Dimension(17 * TILE_SIZE, 9 * TILE_SIZE));
        // hardCoded sizing
        panelForTiles.setMinimumSize(new Dimension(17 * TILE_SIZE, 9 * TILE_SIZE));

        tiles = new Tile[FIELD_WIDTH][FIELD_LENGTH];

        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {

                Tile temp = new Tile(k,i);
                temp.setBackground(new Color(73, 132, 103));
                temp.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                temp.setMaximumSize(temp.getPreferredSize());
                temp.setBorder(BorderFactory.createLineBorder(Color.black));
                temp.addMouseListener(new TileListener());

                panelForTiles.add(temp);
                tiles[i][k] = temp;
            }
        }
        
        panelForTiles.repaint();

        constraints.anchor =  GridBagConstraints.CENTER;
        constraints.gridy = 1;
        add(panelForTiles, constraints);
        
        revalidate();
        repaint();

        // The Back Panel //
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());

        back = new JButton("←");
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

    // public void generateTroops() {
    //     for(int i = 0; i<tiles.length; i++){
    //         for(int k = 0; k < tiles[0].length; k++){
    //             if( == STARTING_POINTS_P1[]){

    //             }
    //         }

    //     }
    // }

    public void addListener(ActionHandler al){
        back.addActionListener(al);
        back.setActionCommand("BACK");

        // walk.addActionListener(al);
        // shoot.addActionListener(al);
    }

}
