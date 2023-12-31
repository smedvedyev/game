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
    JButton button1;
    JButton button2;

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

        button1 = new JButton("");
        button1.setSize(130, 25);
        button1.setBounds(90, 590, 130, 25);
        button1.setOpaque(false);
        button1.addActionListener(this);
        button1.setVisible(true);

        button2 = new JButton("←");
        button2.setPreferredSize(new Dimension(70, 40));
        button2.setBounds(90, 590, 130, 25);
        button2.setPreferredSize(new Dimension(70, 40));
        button2.setFont(new Font("Sans", Font.BOLD, 35));
        button2.setForeground(Color.BLACK);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button2.setBackground(new Color(73, 132, 103));
        button2.addActionListener(this);
        button2.setVisible(false);

        add(label1);
        add(button1);
        add(label2);
        add(button2);

        revalidate();
        repaint();
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            label2.setVisible(true);
            button2.setVisible(true);
            label1.setVisible(false);
            button1.setVisible(false);
        } else {
            label2.setVisible(false);
            button2.setVisible(false);
            label1.setVisible(true);
            button1.setVisible(true);
        }
        
    }

}
