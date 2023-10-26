import java.awt.*;
import javax.swing.*;

public class EndScreen extends JFrame {

    Popup pop;
    JButton close;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) size.getWidth();
    int height = (int) size.getHeight();

    MainFrame mainFrame;

    EndScreen(int winner) {
        JPanel endPanel = new JPanel();
        endPanel.setSize(900, 650);
        endPanel.setBackground(Color.LIGHT_GRAY);

        JLabel endLabel = new JLabel("Congratulations P" + winner + "!");
        endPanel.add(endLabel);

        close = new JButton("CLOSE");
        endPanel.add(close);

        close.addActionListener((event) -> System.exit(0));

        PopupFactory pf = new PopupFactory();
        pop = pf.getPopup(null, endPanel, width / 2, height / 2);

        pop.show();
        }
    }

