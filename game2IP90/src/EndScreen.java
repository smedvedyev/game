import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.w3c.dom.Text;

public class EndScreen extends JPanel {

    MainFrame mf;
    JButton close;
    JPanel endPanel;
    JLabel winnerText;
    ImageIcon background;
    EndScreen(MainFrame mf, int gameWinner) {
        this.mf = mf;
        int winner = gameWinner - 2;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        java.net.URL imgURL = classLoader.getResource("source/images/endScreen.png");
        background = new ImageIcon(imgURL);
        // JLabel thumb = new JLabel(new ImageIcon(imgURL), JLabel.CENTER);
        
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Congratulations, P" + winner + " wins!");
        label.setForeground(Color.WHITE); // Set text color
        label.setFont(new Font("Arial", Font.BOLD, 36)); // Set font
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text

        JButton button = new JButton("Close");
        button.addActionListener(e -> {
            System.exit(0);
    });

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.add(label);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(button);

        add(textPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            background.paintIcon(this, g, 0, 0);
        }
    }
}

