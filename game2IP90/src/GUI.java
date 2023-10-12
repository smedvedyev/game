import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
    JFrame mainFrame;
    JPanel panelForTiles;
    JPanel tiles[][];

    public void generateField(int[][] grid) {
        // setting the main mainFrame
        mainFrame = new JFrame("Game");

        mainFrame.setSize(900, 650);
        mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);


        mainFrame.pack();

        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelForTiles = new JPanel();
        panelForTiles.setBackground(Color.DARK_GRAY);
        panelForTiles.setLayout(new GridLayout(grid[0].length, grid.length));
        panelForTiles.setPreferredSize(new Dimension(17 * 75, 9 * 75));// hardCoded sizing
        panelForTiles.setMaximumSize(new Dimension(17 * 75, 9 * 75));  // hardCoded sizing
        panelForTiles.setMinimumSize(new Dimension(17 * 75150, 9 * 75));  // har
        tiles = new JPanel[grid[0].length][grid.length];

        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {
                Tile temp = new Tile();
                temp.setBackground(Color.GREEN);
                temp.setPreferredSize(new Dimension(75, 75));
                temp.setMaximumSize(temp.getPreferredSize());
                temp.setBorder(BorderFactory.createLineBorder(Color.black));

                panelForTiles.add(temp);
                tiles[i][k] = temp;
            }
        }
        panelForTiles.revalidate();
        panelForTiles.repaint();
        mainFrame.add(panelForTiles);

        // BoxLayout can't be shared
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    GUI(int[][] grid) {
        generateField(grid);
    }

}
