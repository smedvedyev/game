import java.awt.Dimension;

public abstract class Troop {
    int player;
    int shootingRange;
    int walkingRange;
    Dimension location;

    Troop(int x, int y, int player){
        this.player = player;
        location = new Dimension(x, y);
    }
    public abstract void Move(Dimension d);

    public abstract void Shoot(Dimension d);
    
    
    
}   
