// represents a cell on a board
// restores the information about the piece type and whether it is in a winning line
public class Cell {

    // the piece type
    private Piece piece;

    // whether this piece is in a line of three continuous same pieces
    private boolean isInLine;

    public Cell(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isInLine() {
        return isInLine;
    }

    public void setInLine(boolean inLine) {
        this.isInLine = inLine;
    }
}
