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
        String action = e.getActionCommand();
        switch(action){
            case "CHANGE_TO_FIELD":
            gm.gameField.changePanel();

            break;
            case "RULES":
                System.out.print("motherfucker");

            break;
        }

    }
       
}
