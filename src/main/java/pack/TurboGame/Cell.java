public class Cell {
    private int x;
    private int y;
    private boolean isOccupied;
    private Piece Occupant;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

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

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Piece getOccupant() {
        return Occupant;
    }

    public void setOccupant(Piece occupant) {
        Occupant = occupant;
        setOccupied(true);
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cell)) {
            return false;
        }

        Cell c = (Cell) o;

        return (this.x == c.getX() && this.y == c.getY());
    }
}
