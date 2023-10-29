import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EndScreen extends JPanel implements ActionListener {

    MainFrame mf;
    JButton close;
    JPanel endPanel;
    JLabel winnerText;

    EndScreen(MainFrame mf, int gameWinner) {
        this.mf = mf;

        endPanel = new JPanel(); //drawing?
        endPanel.setPreferredSize(new Dimension(900, 650));
        endPanel.setVisible(true);
        endPanel.setBackground(Color.GRAY);
        endPanel.setLayout(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();

        int winner = gameWinner - 2;
        winnerText = new JLabel();
        winnerText.setText("Congratualtions P" + winner + "!");
        winnerText.setFont(new Font("Sans", Font.PLAIN, 60));

        grid.anchor = GridBagConstraints.PAGE_START;
        grid.gridx = 2;
        grid.gridy = 0;
        grid.weighty = 0.5;
        endPanel.add(winnerText, grid);

        close = new JButton("Close");
        close.setFont(new Font("Sans", Font.BOLD, 30));
        close.setSize(100, 100);
        close.addActionListener((event) -> {
            System.exit(0);
        });

        grid.anchor = GridBagConstraints.CENTER;
        grid.gridx = 2;
        grid.gridy = 1;
        grid.weighty = 0.5;
        endPanel.add(close, grid);

        add(endPanel);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
