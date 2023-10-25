import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileListener extends MouseAdapter {
    States state = States.IDLE_P1;
    Tile selectedTile;
    Tile actionTile;
    PopUp popup;
    Troop currentTroop;
    Field field;

    public TileListener(Field field){
        state = States.IDLE_P1;
        this.field = field;
    }

    public boolean checkRange(Troop t, Tile destinationTile, String action){
        boolean res = false;
        int difference = Math.abs((int)destinationTile.d.getX() - (int)t.location.getX());
        switch (action){
            case "walk":
            if(difference <= t.walkRange && difference != 0){
                res = true;
            }
            default:
                break;
        }
        return res;
    }
    
    public void changeState (String action){
        switch (action){
            case "walk":
            if(state == States.POPUP_P1){
                state = States.WALK_P1;
            }else if(state == States.POPUP_P2){
                state = States.WALK_P2;
            }
            break;
            case "shoot":
            if(state == States.IDLE_P1){
                state = States.SHOOT_P1;
            }else if(state == States.IDLE_P2){
                state = States.SHOOT_P2;
            }
            break;
            case "popup":
                if (state == States.IDLE_P1) {
                    popup = new PopUp(field, (int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
                    // currentTroop = selectedTile.troop;
                    state = States.POPUP_P1;
                } else if (state == States.IDLE_P2) {
                    popup = new PopUp(field, (int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
                    state = States.POPUP_P2;
                }
            default:
                break;
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {

        
        switch(state) {
            case IDLE_P1:
                selectedTile = (Tile)e.getSource();
                // System.out.println("("+(int)selectedTile.d.getX() +","+ (int)selectedTile.d.getY()+")");

                if (selectedTile.troop != null && selectedTile.troop.player == 1) {
                    // popup = new PopUp(field, (int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
                    currentTroop = selectedTile.troop;
                    System.out.println("understood captain");
                    // state = States.POPUP_P1;
                    changeState("popup");
                } else {
                    System.out.println("Select a troop");
                }
            break;

            case POPUP_P1:

                Tile newClick1 = (Tile)e.getSource();
                if (newClick1 != selectedTile) {
                    System.out.println("very well");
                    popup.hidePop();
                    state = States.IDLE_P1;
                } 
            break;

            case SHOOT_P1:
                
            break;

            case WALK_P1:
                actionTile = (Tile)e.getSource();
                // if(checkRange(currentTroop, actionTile, "walk")){
                System.out.println("waddle");
                
                currentTroop.move(actionTile.d);
                if (currentTroop.move(actionTile.d)) {
                    field.moveTroop(selectedTile.d, actionTile.d);
                    state = States.IDLE_P2;
                } else {
                    state = States.IDLE_P1;
                    changeState("popup");
                }
            break;

            case IDLE_P2:
                selectedTile = (Tile)e.getSource();

                if (selectedTile.troop != null && selectedTile.troop.player == 2) {
                    // popup = new PopUp(field, (int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
                    currentTroop = selectedTile.troop;
                    // state = States.POPUP_P2;
                    changeState("popup");
                } else {
                    System.out.println("Select a troop");
                }
            break;

            case POPUP_P2:
                Tile newClick2 = (Tile)e.getSource();
                if (newClick2 != selectedTile) {
                    System.out.println("very well");
                    popup.hidePop();
                    state = States.IDLE_P1;
                } 
            break;

            case WALK_P2:
                actionTile = (Tile)e.getSource();
                
                System.out.println("waddle");
                
                currentTroop.move(actionTile.d);
                if (currentTroop.move(actionTile.d)) {
                    field.moveTroop(selectedTile.d, actionTile.d);
                    state = States.IDLE_P1;
                } else {
                    state = States.IDLE_P2;
                    changeState("popup");
                }                
            break;

            case SHOOT_P2:

            break;
            default:
                break;
        }
        
    }


    
}
