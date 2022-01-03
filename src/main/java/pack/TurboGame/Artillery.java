import java.util.ArrayList;

public class Artillery extends Piece{
    public Artillery(){
        super();
    }

    public Artillery(Player owner, int x, int y) {
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
        ArrayList<Cell> result = northWestArm(getX(),getY());
        ArrayList<Cell> ne = northEastArm(getX(),getY());
        ArrayList<Cell> e = eastArm(getX(),getY());
        ArrayList<Cell> se = southEastArm(getX(),getY());
        ArrayList<Cell> sw = southWestArm(getX(),getY());
        ArrayList<Cell> w = westArm(getX(),getY());

        result.addAll(ne);
        result.addAll(e);
        result.addAll(se);
        result.addAll(sw);
        result.addAll(w);
        return result;
    }

    @Override
    public pieceType getType() {
        return pieceType.ART;
    }
}
