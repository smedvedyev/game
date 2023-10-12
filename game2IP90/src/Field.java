public class Field{
    int[][]fieldArray;
    
    Field(int length,int width){
        fieldArray = new int[width][length];
        GUI gui = new GUI(fieldArray);
    }   


}