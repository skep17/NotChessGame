import java.util.ArrayList;

public class Empty extends Piece{
    public Empty(int x, int y){
        super();
        setX(x);
        setY(y);
    }

    @Override
    public ArrayList<Cell> getMoveCells() {
        return new ArrayList<Cell>();
    }

    @Override
    public pieceType getType() {
        return pieceType.EMPT;
    }
}
