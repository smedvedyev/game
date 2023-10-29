import javax.swing.JLabel;

public class Capitan extends Infantry {

    Boolean res;
    
    public Capitan(int x, int y, int player, JLabel image){
        super(x, y, player, image);
        walkRange = 2;
        shootRange = 3;
    }

    @Override
    public String toString(){
        return "Capitan "+player;
    }

}
