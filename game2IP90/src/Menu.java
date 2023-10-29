import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Main menu frame.
 */
public class Menu extends JPanel{
    
    JButton playButton;
    JButton rulesButton;

    Menu() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        setBackground(new Color(145, 196, 153));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        Label gameName = new Label("KNIGHT'S VALOR");
        gameName.setFont(new Font("Helvetica", Font.PLAIN, 80));
        gameName.setForeground(Color.white);
        add(gameName, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setOpaque(false);
        playButton = new JButton("PLAY");
        playButton.setPreferredSize(new Dimension(150, 50));
        playButton.setFocusPainted(false);
        playButton.setFont(new Font("Arial", Font.BOLD, 18));
        playButton.setBackground(Color.white);

        playButton.setActionCommand("CHANGE_TO_FIELD");

        rulesButton = new JButton("RULES");
        rulesButton.setPreferredSize(new Dimension(150, 50));
        rulesButton.setFocusPainted(false);
        rulesButton.setFont(new Font("Arial", Font.BOLD, 18));
        rulesButton.setBackground(Color.white);

        rulesButton.setActionCommand("RULES");
        

        buttons.add(playButton, gbc);
        buttons.add(Box.createVerticalStrut(100));
        buttons.add(rulesButton, gbc);

        
        gbc.weighty = 5;
        add(buttons, gbc);
    }

    public void addListener(ActionHandler al){
        playButton.addActionListener(al);
        rulesButton.addActionListener(al);
    }

}