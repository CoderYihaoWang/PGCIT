public class Board {

    // a 2d array of Cells to represent the game board
    // an element is null if the position is not occupied
    private final Cell[][] cells = new Cell[3][3];

    // has the game ended
    private boolean isEnd;

    // has the game ended in tie
    private boolean isTie;

    public Board() {
        // initialize the board
        for (int r = 0; r < 3; r++) {
            cells[r] = new Cell[3];
        }
    }

    // getters
    public boolean isEnd() { return isEnd; }
    public boolean isTie() { return isTie; }

    // retrieve the Cell at the row and col
    public Cell getCell(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3)
            return null;
        return cells[row][col];
    }

    // attempts to put a piece at the row and col specified,
    // if the position is out of the board bounds or has been occupied,
    // it does nothing and returns the current piece.
    // on the other hand, if the piece can be placed at the specified position,
    // it set the position to the new piece, and returns the next piece to be placed
    public Piece setCell(int row, int col, Piece next) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || cells[row][col] != null)
            return next;
        cells[row][col] = new Cell(next);
        testEnd(next);
        if (!isEnd)
            testTie();
        return next == Piece.Cross ? Piece.Nought : Piece.Cross;
    }

    // test whether the game has ended in a tie
    private void testTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (cells[r][c] == null)
                    return;
            }
        }
        isTie = true;
    }

    // test if the current piece has continuous threes,
    // set any cell's isInLine field to true, if it is part of a continuous three
    private void testEnd(Piece piece) {
        int r, c;

        // find continuous rows
        for (r = 0; r < 3; r++) {
            for (c = 0; c < 3; c++) {
                if (cells[r][c] == null || cells[r][c].getPiece() != piece)
                    break;
            }
            if (c == 3) {
                isEnd = true;
                for (c = 0; c < 3; c++) {
                    cells[r][c].setInLine(true);
                }
            }
        }

        // find continuous columns
        for (c = 0; c < 3; c++) {
            for (r = 0; r < 3; r++) {
                if (cells[r][c] == null || cells[r][c].getPiece() != piece)
                    break;
            }
            if (r == 3) {
                isEnd = true;
                for (r = 0; r < 3; r++) {
                    cells[r][c].setInLine(true);
                }
            }
        }

        // find continuous diagonal, shape of '\'
        for (r = 0; r < 3; r++) {
            if (cells[r][r] == null || cells[r][r].getPiece() != piece)
                break;
        }
        if (r == 3) {
            isEnd = true;
            for (r = 0; r < 3; r++) {
                cells[r][r].setInLine(true);
            }
        }

        // find continuous diagonal, shape of '/'
        for (r = 0; r < 3; r++) {
            if (cells[r][3 - r - 1] == null || cells[r][3 - r - 1].getPiece() != piece)
                break;
        }
        if (r == 3) {
            isEnd = true;
            for (r = 0; r < 3; r++) {
                cells[r][3 - r - 1].setInLine(true);
            }
        }
    }
}
