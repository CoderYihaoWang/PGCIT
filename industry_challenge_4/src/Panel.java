import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {

    // two fonts to use
    private static final Font LARGE = new Font(null, Font.BOLD, 30);
    private static final Font SMALL = new Font(null, Font.BOLD | Font.ITALIC, 20);

    // make all position related values constants
    // so as to eliminate 'magic numbers' in code
    // and make it easier to make a change
    private static final int LINE_WIDTH = 6;

    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 480;

    private static final int PIECE_SIZE = 30;
    private static final int ICON_SIZE = 20;

    private static final int BOARD_WIDTH = 180;
    private static final int BOARD_HEIGHT = 180;

    private static final int TITLE_POSITION_X = 65;
    private static final int TITLE_POSITION_Y = 60;
    private static final int BOARD_POSITION_Y = 100;
    private static final int ICON_POSITION_X = 115;
    private static final int SCORE_POSITION_X = 175;
    private static final int SCORE_POSITION_Y = 320;
    private static final int RESTART_POSITION_X = 115;
    private static final int RESTART_POSITION_Y = 440;

    // which piece goes first in the current game
    private Piece firstPiece = Piece.Cross;

    // which piece goes in the current turn
    private Piece nextPiece = firstPiece;

    // the game board, containing cells and there game status
    private Board board = new Board();

    // cross's and nought's scores
    private int crossScore = 0;
    private int noughtScore = 0;

    public Panel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(GameColors.BACKGROUND);

        // bind event listener to mousePressed
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // if the restart 'button' is clicked, restart game, reset scores
                if (restartClicked(e.getPoint()))
                    restart();
                // else if one player wins or game ended in tie, start a new game, update scores
                else if (board.isEnd() || board.isTie())
                    newGame();
                // otherwise the player is trying to make a move
                else
                    nextPiece = board.setCell(getClickedRow(e.getY()), getClickedCol(e.getX()), nextPiece);

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(LINE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        drawTitle(g2d);
        drawLines(g2d);
        drawPieces(g2d);
        drawScores(g2d);
        drawRestart(g2d);
    }

    // write the game title
    private void drawTitle(Graphics2D g) {
        g.setColor(GameColors.DARK);
        g.setFont(LARGE);
        g.drawString("Tic Tac Toe", TITLE_POSITION_X, TITLE_POSITION_Y);
    }

    // draw the board lines: '#'
    private void drawLines(Graphics2D g) {
        g.setColor(GameColors.DARK);
        int startX = (PANEL_WIDTH - BOARD_WIDTH) / 2;
        int startY = BOARD_POSITION_Y;
        int step = BOARD_WIDTH / 3;
        for (int i = 1; i < 3; i++) {
            g.drawLine(
                    startX,
                    startY + i * step,
                    startX + BOARD_WIDTH,
                    startY + i * step
            );
            g.drawLine(
                    startX + i * step,
                    startY,
                    startX + i * step,
                    startY + BOARD_HEIGHT
            );
        }
    }

    // draw all the pieces on the board
    private void drawPieces(Graphics2D g) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                drawCell(g, r, c, board.getCell(r, c));
            }
        }
    }

    // draw the cell on the specified position
    private void drawCell(Graphics2D g, int row, int col, Cell cell) {
        // if the position is empty, do nothing
        if (cell == null)
            return;

        g.setColor(cell.isInLine() ? GameColors.HIGHLIGHT : GameColors.PRIMARY);
        int step = BOARD_WIDTH / 3;
        int x = (PANEL_WIDTH - BOARD_WIDTH) / 2 + step * col + (step - PIECE_SIZE) / 2;
        int y = BOARD_POSITION_Y + step * row + (step - PIECE_SIZE) / 2;
        if (cell.getPiece() == Piece.Nought) {
            g.drawOval(x, y, PIECE_SIZE, PIECE_SIZE);
        } else {
            g.drawLine(x, y, x + PIECE_SIZE, y + PIECE_SIZE);
            g.drawLine(x + PIECE_SIZE, y, x, y + PIECE_SIZE);
        }
    }

    // draw the score information
    private void drawScores(Graphics2D g) {
        // draw the icons: X and O
        g.setColor(nextPiece == Piece.Cross ? GameColors.HIGHLIGHT : GameColors.DARK);
        g.drawLine(
                ICON_POSITION_X,
                SCORE_POSITION_Y,
                ICON_POSITION_X + ICON_SIZE,
                SCORE_POSITION_Y + ICON_SIZE
        );
        g.drawLine(
                ICON_POSITION_X + ICON_SIZE,
                SCORE_POSITION_Y,
                ICON_POSITION_X,
                SCORE_POSITION_Y + ICON_SIZE
        );
        g.setColor(nextPiece == Piece.Nought ? GameColors.HIGHLIGHT : GameColors.DARK);
        g.drawOval(ICON_POSITION_X, SCORE_POSITION_Y + ICON_SIZE * 2, ICON_SIZE, ICON_SIZE);

        // write the scores
        g.setColor(GameColors.DARK);
        g.setFont(LARGE);
        g.drawString(crossScore + "", SCORE_POSITION_X, SCORE_POSITION_Y + ICON_SIZE);
        g.drawString(noughtScore + "", SCORE_POSITION_X, SCORE_POSITION_Y + ICON_SIZE * 3);
    }

    // draw the restart 'button'
    // this is just a clickable string on the panel, not a real button,
    // because the native JButton component's style is too 'Windows Vista',
    // and does not go well with this app's overall look
    private void drawRestart(Graphics2D g) {
        g.setColor(GameColors.DARK);
        g.setFont(SMALL);
        g.drawString("Restart", RESTART_POSITION_X, RESTART_POSITION_Y);
    }

    // get the clicked row number on the board
    private int getClickedRow(int y) {
        return (y - BOARD_POSITION_Y) / (BOARD_HEIGHT / 3);
    }

    // get the clicked column on the board
    private int getClickedCol(int x) {
        return (x - (PANEL_WIDTH - BOARD_WIDTH) / 2) / (BOARD_HEIGHT / 3);
    }

    // is the mouse click on the restart 'button' area
    private boolean restartClicked(Point p) {
        return p.getX() >= RESTART_POSITION_X
                && p.getX() <= PANEL_WIDTH - RESTART_POSITION_X
                && p.getY() >= RESTART_POSITION_Y - ICON_SIZE
                && p.getY() <= RESTART_POSITION_Y;
    }

    // start a new game, update the scores
    private void newGame() {
        // nobody scores if the game is in a tie
        if (!board.isTie()) {
            if (nextPiece == Piece.Cross)
                noughtScore += 1;
            else
                crossScore += 1;
        }

        // toggles the player who plays first
        if (firstPiece == Piece.Cross)
            firstPiece = Piece.Nought;
        else
            firstPiece = Piece.Cross;
        nextPiece = firstPiece;

        // create an empty board
        board = new Board();
    }

    // restart game, restore the game to the initial state, clear the scores
    private void restart() {
        firstPiece = Piece.Cross;
        nextPiece = firstPiece;
        board = new Board();
        crossScore = noughtScore = 0;
    }
}
