import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * How to play Frame.
 */
public class Rules extends JFrame implements ActionListener {
    
    ImageIcon image1;
    ImageIcon image2;

    JLabel label1;
    JLabel label2;
    JButton button;

    Game gm;

    Rules(Game gm) {
        this.gm = gm;
        setBackground(new Color(89,80,80));
        setPreferredSize(new Dimension(400, 800));
        setLayout(null);

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        java.net.URL imgURL1 = classLoader.getResource("source/images/rulebook1.png");     
        image1 = new ImageIcon(imgURL1);
        java.net.URL imgURL2 = classLoader.getResource("source/images/rulebook2.png");
        image2 = new ImageIcon(imgURL2);

        label1 = new JLabel("", image1, JLabel.CENTER);
        label1.setBounds(0,0,390,770);

        label2 = new JLabel("", image2, JLabel.CENTER);
        label2.setBounds(0,0,390,770);
        label2.setVisible(false);

        button = new JButton("");
        button.setSize(130, 25);
        button.setBounds(90, 590, 130, 25);
        button.setOpaque(false);
        button.addActionListener(this);

        add(label1);
        add(button);
        add(label2);

        revalidate();
        repaint();
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        label2.setVisible(true);
        label1.setVisible(false);
        button.setVisible(false);
    }

}
