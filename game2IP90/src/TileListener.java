import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileListener extends MouseAdapter {
    States state = States.IDLE_P1;
    Tile selectedTile;
    Tile actionTile ;
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
        }
        return res;
    }
    
    public void changeState (String action){
        switch (action){
            case "walk":
            if(state == States.IDLE_P1){
                state = States.WALK_P1;
            }else if(state == States.IDLE_P2){
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
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {

        // System.out.println("("+(int)selectedTile.d.getX() +","+ (int)selectedTile.d.getY()+")");
        

        switch(state) {
            case IDLE_P1:
            selectedTile = (Tile)e.getSource();

            if (selectedTile.troop != null && selectedTile.troop.player == 1) {

                popup = new PopUp(field.mf.mainFrame, (int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
                currentTroop = selectedTile.troop;

            } else {
                System.out.println("Select a troop");
                // popup = new PopUp((int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
            }
            break;

            case SHOOT_P1:
                actionTile = (Tile)e.getSource();
                if(Math.abs(actionTile.d.getX() - currentTroop.location.getX()) <= currentTroop.shootRange){
                    currentTroop.shoot(actionTile.d);
                    System.out.println("pew");
                    
                }
            break;

            case WALK_P1:
                actionTile = (Tile)e.getSource();
                // if(checkRange(currentTroop, actionTile, "walk")){
                System.out.println("waddle");
                field.moveTroop(selectedTile.d, actionTile.d);
                currentTroop.move(actionTile.d);
                // }
                System.out.println("hui");
                state = States.IDLE_P2;
            break;
        }
        
    }


    
}
