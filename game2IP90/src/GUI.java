import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
    JFrame mainFrame;
    JPanel panelForTiles;
    Tile tiles[][];

    /**
     * @param width
     * @param length
     */
    public void generateField(int width, int length) {
        //TODO set coords for spawn points for two players
        int[][] startPoints = {{2,0},{3,0},{4,0},{5,0},{6,0},{2,12},{3,12},{4,12},{5, 12},{6,12}};
        // setting the main mainframe
        mainFrame = new JFrame("Game");

        mainFrame.setSize(900, 650);
        mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);


        mainFrame.pack();

        mainFrame.setVisible(true);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color g = new Color(0, 210, 110);

        panelForTiles = new JPanel();
        panelForTiles.setBackground(Color.DARK_GRAY);
        panelForTiles.setLayout(new GridLayout(width, length));
        panelForTiles.setPreferredSize(new Dimension(17 * 75, 9 * 75));// hardCoded sizing
        panelForTiles.setMaximumSize(new Dimension(17 * 75, 9 * 75));  // hardCoded sizing
        panelForTiles.setMinimumSize(new Dimension(17 * 75150, 9 * 75));  // har
        tiles = new Tile[width][length];
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {

                Tile temp = new Tile();
                temp.setBackground(g);
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

    GUI(int length, int width) {
        generateField(length, width);
    }




}
