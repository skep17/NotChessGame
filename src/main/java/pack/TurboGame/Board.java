import java.util.ArrayList;
import java.util.List;

public class Board {
    private List< Cell[] > board;

    public Board(){
        board = new ArrayList<>();
        constructBrd();
        populateBoard();
    }

    private void constructBrd(){

        // loop for constructing cells till the longest row of 23 cells
        for(int i = 12; i < 24; i++){
            Cell[] cur = new Cell[i];
            //loop for populating a row with appropriate number of cells
            for(int n = 0; n < i; n++){
                cur[n] = new Cell(n+1,i-11);
            }
            board.add(cur);
        }
        //loop for the rest rows
        for(int i = 22; i > 11; i--){
            Cell[] cur = new Cell[i];
            //loop for populating a row with appropriate number of cells
            for(int n = 0; n < i; n++){
                cur[n] = new Cell(n+1,35-i);
            }
            board.add(cur);
        }
    }

    public List<Cell[]> getBoard(){
        return board;
    }

    public boolean movePiece(int initialX, int initialY, int finalX, int finalY){
        Cell initialCell = board.get(initialY-1)[initialX-1];
        Cell finalCell = board.get(finalY-1)[finalX-1];
        // checks if initial cell is occupied by a piece

        Piece initialPiece = initialCell.getOccupant();
        if(initialPiece.getType().equals(Piece.pieceType.EMPT)){
            return false;
        } else {
            Piece finalPiece = finalCell.getOccupant();
            if(!finalPiece.getType().equals(Piece.pieceType.EMPT) && initialPiece.getOwner().equals(finalPiece.getOwner())) return false;
            ArrayList<Cell> movable = initialPiece.getMoveCells();
            if(!movable.contains(finalCell)) return false;
            board.get(initialY-1)[initialX-1].setOccupant(new Empty(initialX,initialY));
            initialPiece.setX(finalX);
            initialPiece.setY(finalY);
            board.get(finalY-1)[finalX-1].setOccupant(initialPiece);
        }
        return true;
    }

    private void populateBoard(){
        //fill the third rows with Infantry pieces
        for(int i = 0; i < 14; i++){
            board.get(2)[i].setOccupant(new Infantry(Player.PLAYER_1,i+1,3));
            board.get(20)[i].setOccupant(new Infantry(Player.PLAYER_2,i+1,21));
        }
        //fill first rows with Artillery, General and Cavalry pieces like: A C C C C A G C C C C A
        for(int i = 0; i < 12;i++){
            if(i == 0 || i == 11){
                board.get(0)[i].setOccupant(new Artillery(Player.PLAYER_1,i+1,1));
                board.get(22)[i].setOccupant(new Artillery(Player.PLAYER_2,i+1,23));
            } else if(i == 5){
                board.get(0)[i].setOccupant(new Artillery(Player.PLAYER_1,i+1,1));
                board.get(22)[i].setOccupant(new General(Player.PLAYER_2,i+1,23));
            } else if(i == 6){
                board.get(0)[i].setOccupant(new General(Player.PLAYER_1,i+1,1));
                board.get(22)[i].setOccupant(new Artillery(Player.PLAYER_2,i+1,23));
            } else {
                board.get(0)[i].setOccupant(new Cavalry(Player.PLAYER_1,i+1,1));
                board.get(22)[i].setOccupant(new Cavalry(Player.PLAYER_2,i+1,23));
            }
        }
        //fill the rest cells with Empty pieces
        for(Cell[] cellArr : board){
            if(cellArr[0].isOccupied()) continue;
            for(Cell c : cellArr){
                c.setOccupant(new Empty(c.getX(),c.getY()));
            }
        }
    }

    public String getData(){
        StringBuilder result = new StringBuilder();

        for(Cell[] c_arr : board){
            for(Cell c : c_arr){
                result.append("(").append(String.valueOf(c.getY()-1)).append(",").append(String.valueOf(c.getX()-1)).append(")").append("|").append(c.getOccupant().getType().name()).append(" ");
            }
        }

        return result.toString();
    }
}
