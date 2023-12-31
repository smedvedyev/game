import java.awt.event.*;
/**
 * class to see when something is clicked.
 */
public class ActionHandler implements ActionListener {
    Game gm;
    Rules rules;

    ActionHandler(Game gm){
        this.gm = gm;
    }
    /** Action handler used to switch the panels */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "CHANGE_TO_FIELD":
                gm.gameField.changePanel(1);                
            break;

            case "RULES":
                rules = new Rules(gm);
            break;

            case "BACK":
                gm.gameField.changePanel(2);
            break;
        }

    }
       
}
