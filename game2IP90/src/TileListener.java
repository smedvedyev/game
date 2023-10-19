import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileListener extends MouseAdapter {
    States state = States.IDLE;
    Tile selectedTile;
    PopUp popup;

    @Override
    public void mouseClicked(MouseEvent e) {
        Tile selectedTile = (Tile)e.getSource();

        System.out.println("("+(int)selectedTile.d.getX() +","+ (int)selectedTile.d.getY()+")");
        

        switch(state) {
            case IDLE:
            if (selectedTile.troop != null) {
                System.out.println("That's grass, my liege");
            } else {
                //add pop up to choose between shoot or walk
                popup = new PopUp((int)selectedTile.d.getX(), (int)selectedTile.d.getY());
            }
        }
        
    }


    
}
