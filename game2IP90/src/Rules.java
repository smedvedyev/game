import java.awt.*;
import javax.swing.*;
/**
 * How to play Frame.
 */
public class Rules extends JFrame {
    
    ImageIcon image;

    Game gm;
    Rules(Game gm) {
        this.gm = gm;
        setBackground(new Color(89,80,80));
        java.net.URL imgURL = getClass().getResource("source/images/rulebook.png");     
        image = new ImageIcon(imgURL);

        JLabel gameName = new JLabel("",image,JLabel.CENTER);
        gameName.setVisible(true);
        JScrollBar sb = new JScrollBar();
        
        add(sb);
        add(gameName);
        
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
