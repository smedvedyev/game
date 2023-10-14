import java.awt.event.*;
/**
 * class to see when something is clicked.
 */
public class ActionHandler implements ActionListener {
    Game gm;
    ActionHandler(Game gm){
        this.gm = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gm.gameField.changePanel();
    }
       
}
