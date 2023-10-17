import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileListener extends MouseAdapter {
    States state = States.IDLE;
    @Override
    public void mouseClicked(MouseEvent e) {
        Tile selectedTile = (Tile)e.getSource();

        System.out.println("("+(int)selectedTile.d.getX() +","+ (int)selectedTile.d.getY()+")");
    //     switch(state){
    //         case IDLE:
    //         if(selectedTile.troop != null){
                
    //         }
    //     }
        
    }


    
}
