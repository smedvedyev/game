import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileListener extends MouseAdapter {
    States state = States.IDLE_P1;
    Tile selectedTile;
    PopUp popup;
    Troop currentTroop;

    public TileListener(Game gm){
        state = States.IDLE_P1;
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
        Tile selectedTile = (Tile)e.getSource();

        System.out.println("("+(int)selectedTile.d.getX() +","+ (int)selectedTile.d.getY()+")");
        

        switch(state) {
            case IDLE_P1:
            if (selectedTile.troop != null && selectedTile.troop.player == 1) {

                popup = new PopUp((int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
                currentTroop = selectedTile.troop;

            } else {
                System.out.println("Select a troop");
                popup = new PopUp((int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);
            }
            case SHOOT_P1:
            // if (currentTroop.shootingRange.&& selectedTile.troop.player == 1) {
            //     popup = new PopUp((int)selectedTile.d.getX(), (int)selectedTile.d.getY(), this);

            // } else {
            //     System.out.println("Select a troop");
            // }
        }
        
    }


    
}
