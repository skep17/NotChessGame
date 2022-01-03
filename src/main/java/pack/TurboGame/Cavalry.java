import java.util.ArrayList;

public class Cavalry extends Piece{
    public Cavalry(){
        super();
    }

    public Cavalry(Player owner, int x, int y) {
        super(owner, x, y);
    }

    /*
        Before looking through the method, keep in mind poisioning calculations:
        numbering of board starts from bottom-left corner and rises from left to right bottom to top.
        So bottom left cell is (1,1) while top right cell is (12,23).
        Up to 12th row, each northwest cell is of same x coordinate, after 12th row each northeast cell is of same x coordinate.
        to ilustrate:
    5th      1 2 3
    4th     1 2 3 4
    3rd    1 2 3 4 5
    2nd     1 2 3 4
    1st      1 2 3
    */
    @Override
    public ArrayList<Cell> getMoveCells() {
        ArrayList<Cell> cir =  circle(getX(),getY());
        int size = cir.size();
        cir.add(new Cell(getX(),getY()));
        for(int i = 0; i < size; i++){
            ArrayList<Cell> cur;
            int curY = cir.get(i).getY();
            int curX = cir.get(i).getX();
            int initY = getY();
            if(curY == initY){
                cur = circle(curX,curY);
            } else if(curY>initY){
                cur = semiCircleNorth(curX,curY);
            } else {
                cur = semiCircleSouth(curX,curY);
            }
            for(Cell c : cur){
                if(!cir.contains(c)) cir.add(c);
            }
        }
        cir.remove(size);
        return cir;
    }

    @Override
    public pieceType getType() {
        return pieceType.CAV;
    }
}
