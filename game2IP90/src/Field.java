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
    JPanel panelForTiles;
    Tile tiles[][];
    ActionListener AL_F;
    MainFrame mf;
    private JButton back;

    Field(MainFrame mf) {
        this.mf = mf;
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

        tiles = new Tile[FIELD_WIDTH][FIELD_LENGTH];

        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {

                Tile temp = new Tile();
                temp.setBackground(new Color(73, 132, 103));
                temp.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                temp.setMaximumSize(temp.getPreferredSize());
                temp.setBorder(BorderFactory.createLineBorder(Color.black));

                panelForTiles.add(temp);
                tiles[i][k] = temp;
            }
        }
        
        panelForTiles.repaint();

        add(panelForTiles);
        
        revalidate();
        repaint();

        // JPanel buttonPanel = new JPanel();
        // buttonPanel.setLayout(new GridLayout());
        // back = new JButton("X");
        // back.setPreferredSize(new Dimension(30, 30));
        // back.setFocusPainted(false);
        // back.setFont(new Font("Sans", Font.BOLD, 10));
        // back.setBackground(Color.white);

        // back.setActionCommand("BACK");

        // buttonPanel.add(back);
        // add(buttonPanel);
    }

    // public void addListener(ActionHandler al) {
    //     back.addActionListener(al);
    // }

}
