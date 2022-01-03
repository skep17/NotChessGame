import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

//    enum face{
//        NORTH_LINE,
//        NORTHWEST_LINE,
//        SOUTHWEST_LINE,
//        SOUTH_LINE,
//        SOUTHEAST_LINE,
//        NORTHEAST_LINE,
//        NORTHWEST_CELL,
//        WEST_CELL,
//        SOUTHWEST_CELL,
//        SOUTHEAST_CELL,
//        EAST_CELL,
//        NORTHEAST_CELL
//    }

    enum pieceType{
        INF,
        CAV,
        ART,
        GEN,
        EMPT
    }

    private Player owner;
//    private face orientation;
    private int x;
    private int y;

    public Piece(Player owner, int x, int y) {
        this.owner = owner;
        //this.orientation = orientation;
        this.x = x;
        this.y = y;
    }

    public Piece() {
        //orientation = null;
        owner = null;
        x = -1;
        y = -1;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

//    public face getOrientation() {
//        return orientation;
//    }
//
//    public void setOrientation(face orientation) {
//        this.orientation = orientation;
//    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected ArrayList<Cell> semiCircleNorth(int x,int y){
        ArrayList<Cell> result = new ArrayList<>();

        boolean leftBoundary = x > 1; //left boundary of the board
        boolean rightLowerBoundary = x < (11 + y%12); //right boundary of the board in the lower part (y < 12)
        boolean rightMiddleBoundary = x < 23; //right boundary of the board in the middle row (y = 12)
        boolean rightUpperBoundary = x < (23 - y%12); //right boundary of the board
        boolean topBoundary = y < 23; //top boundary of the board in the upper part ( y > 12)

        //cells are added according to clockwise direction from west to east.
        //infantry moves semi-circle from it's orientation. infantry orientation matches neighboring cell's Lines.

        if (y < 12) {
            if (leftBoundary) result.add(new Cell(x - 1, y)); //west cell
            result.add(new Cell(x,y+1)); //northwest cell
            result.add(new Cell(x+1,y+1)); //northeast cell
            if(rightLowerBoundary) result.add(new Cell(x+1,y)); //east cell
        } else if(y == 12){
            if(leftBoundary){
                result.add(new Cell(x - 1, y)); //west cell
                result.add(new Cell(x-1,y+1));
            }
            if(rightMiddleBoundary){
                result.add(new Cell(x+1,y));
                result.add(new Cell(x,y+1));
            }
        } else {
            if(leftBoundary){
                result.add(new Cell(x-1,y));
                if(topBoundary) result.add(new Cell(x-1,y+1));
            }
            if(rightUpperBoundary){
                result.add(new Cell(x+1,y));
                if(topBoundary) result.add(new Cell(x,y+1));
            }
        }

        return result;
    }

    protected ArrayList<Cell> semiCircleSouth(int x,int y){
        ArrayList<Cell> result = new ArrayList<>();

        boolean leftBoundary = x > 1; //left boundary of the board
        boolean rightLowerBoundary = x < (11 + y%12); //right boundary of the board in the lower part (y < 12)
        boolean rightMiddleBoundary = x < 23; //right boundary of the board in the middle row (y = 12)
        boolean rightUpperBoundary = x < (23 - y%12); //right boundary of the board
        boolean bottomBoundary = y > 1; //bottom boundary of the board

        //cells are added according to clockwise direction from west to east.
        //infantry moves semi-circle from it's orientation. infantry orientation matches neighboring cell's Lines.

        if (y < 12) {
            if(rightLowerBoundary){
                result.add(new Cell(x+1,y));
                if(bottomBoundary) result.add(new Cell(x,y-1));
            }
            if(leftBoundary){
                result.add(new Cell(x-1,y));
                if(bottomBoundary) result.add(new Cell(x-1,y-1));
            }
        } else if(y == 12){
            if(rightMiddleBoundary){
                result.add(new Cell(x+1,y));
                result.add(new Cell(x,y-1));
            }
            if(leftBoundary){
                result.add(new Cell(x-1,y));
                result.add(new Cell(x-1,y-1));
            }
        } else {
            if(rightUpperBoundary) result.add(new Cell(x+1,y));
            result.add(new Cell(x+1,y-1));
            result.add(new Cell(x,y-1));
            if(leftBoundary) result.add(new Cell(x-1,y));
        }

        return result;
    }

    protected ArrayList<Cell> circle(int x, int y){
        ArrayList<Cell> result = new ArrayList<>();
        boolean leftBoundary = x > 1; //left boundary of the board
        boolean rightLowerBoundary = x < (11 + y%12); //right boundary of the board in the lower part (y < 12)
        boolean rightMiddleBoundary = x == 23; //right boundary of the board in the middle row (y = 12)
        boolean rightUpperBoundary = x < (23 - y%12); //right boundary of the board
        boolean topBoundary = y < 23; //top boundary of the board in the upper part ( y > 12)
        boolean bottomBoundary = y > 1; //bottom boundary of the board

        if (y < 12) {
            if (leftBoundary){
                result.add(new Cell(x - 1, y)); //west cell
                if(bottomBoundary) result.add(new Cell(x-1,y-1));
            }
            result.add(new Cell(x,y+1)); //northwest cell
            result.add(new Cell(x+1,y+1)); //northeast cell
            if(rightLowerBoundary){
                result.add(new Cell(x+1,y)); //east cell
                if(bottomBoundary) result.add(new Cell(x,y-1));
            }
        } else if(y == 12){
            if(leftBoundary){
                result.add(new Cell(x - 1, y)); //west cell
                result.add(new Cell(x-1,y+1));
                result.add(new Cell(x-1,y-1));
            }
            if(rightMiddleBoundary){
                result.add(new Cell(x+1,y));
                result.add(new Cell(x,y+1));
                result.add(new Cell(x,y-1));
            }
        } else {
            if(leftBoundary){
                result.add(new Cell(x-1,y));
                if(topBoundary) result.add(new Cell(x-1,y+1));
            }
            if(rightUpperBoundary){
                result.add(new Cell(x+1,y));
                if(topBoundary) result.add(new Cell(x,y+1));
            }
            result.add(new Cell(x,y-1));
            result.add(new Cell(x+1,y-1));
        }
        return result;
    }

    protected ArrayList<Cell> northWestArm(int x, int y){
        ArrayList<Cell> result = new ArrayList<>();

        while(y < 12){
            result.add(new Cell(x,y+1));
            y++;
        }
        while (y < 23 && x > 1){
            result.add(new Cell(x-1,y+1));
            y++;
            x--;
        }

        return result;
    }

    protected ArrayList<Cell> northEastArm(int x, int y){
        ArrayList<Cell> result = new ArrayList<>();

        while(y < 12){
            result.add(new Cell(x+1,y+1));
            y++;
            x++;
        }
        if(x == 23) return result;
        while (y < 23 && x < (23 - y%12)){
            result.add(new Cell(x,y+1));
            y++;
        }

        return result;
    }

    protected ArrayList<Cell> southEastArm(int x, int y){
        ArrayList<Cell> result = new ArrayList<>();

        while(y > 12){
            result.add(new Cell(x+1,y-1));
            y--;
            x++;
        }
        if(x == 23) return result;
        while (y > 1 && (x < (11 + y%12) || y == 12)){
            result.add(new Cell(x,y-1));
            y--;
        }

        return result;
    }

    protected ArrayList<Cell> southWestArm(int x, int y){
        ArrayList<Cell> result = new ArrayList<>();

        while(y > 12){
            result.add(new Cell(x,y-1));
            y--;
        }
        if(x == 1) return result;
        while (y > 1 && (x >1 || y == 12)){
            result.add(new Cell(x-1,y-1));
            y--;
            x--;
        }

        return result;
    }

    protected ArrayList<Cell> westArm(int x, int y){
        ArrayList< Cell> result = new ArrayList<>();
        while (x > 1) {
            result.add(new Cell(x-1,y));
            x--;
        }
        return result;
    }

    protected ArrayList<Cell> eastArm(int x, int y){
        ArrayList<Cell> result = new ArrayList<>();
        if(y < 12){
            while (x < (11 + y%12)){
                result.add(new Cell(x+1,y));
                x++;
            }
        } else if( y == 12){
            while (x<23){
                result.add(new Cell(x+1,y));
                x++;
            }
        } else {
            while (x < (23 - y%12)){
                result.add(new Cell(x+1,y));
                x++;
            }
        }
        return result;
    }

    //returns an ArrayList of possible Cells to move the given piece 
    public abstract ArrayList<Cell> getMoveCells() ;
    public abstract pieceType getType();
}
