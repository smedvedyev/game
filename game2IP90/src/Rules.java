import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * How to play Frame.
 */
public class Rules extends JPanel {
    
    private JButton back;

    Rules() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        setBackground(new Color(89,80,80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 0.5;

        Label gameName = new Label("How To Play");
        gameName.setFont(new Font("Arial", Font.PLAIN, 50));
        gameName.setForeground(Color.white);
        add(gameName, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(new Color(89,80,80));
        back = new JButton("X");
        back.setPreferredSize(new Dimension(50, 50));
        back.setOpaque(false);
        back.setFocusPainted(false);
        back.setFont(new Font("Sans", Font.BOLD, 15));
        back.setBackground(Color.white);

        back.setActionCommand("BACK");
      
        buttons.add(back, gbc);
        // buttons.add(Box.createVerticalStrut(100));
        
        gbc.weighty = 5;
        add(buttons, gbc);
    }

    public void addListener(ActionHandler al) {
        back.addActionListener(al);
    }

}
