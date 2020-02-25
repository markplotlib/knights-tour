import java.util.*;
/*
 * Mark Chesney
 * ACM: Association for Computing Machinery, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

/**
 * Renders a chess board to the screen.
 *
 * @author  Mark Chesney
 * @version 1.0
 */
public class Chessboard {

	public Chessboard(int side) {
        this.side = side;
        arrPreviousMoves = new boolean[side * side];
        history = new Stack<Integer>();
    }

    public String toChess(int m) {
        // Return algebraic chess notation for square m in an r x c chess board.
        int r = m % side + 97;
        char row = (char) r;
        int col = side - (m / side);
        return Character.toString(row) + Integer.toString(col);
    }

    public boolean isKnightMove(int m0, int m1) {
        // returns true if knight moves from (x0, y0) to (x1, y1),
        // where (|y1-y0|, |x1-x0|) ∈ {(1, 2), (2, 1)}
        // at most, there are 8 valid L-shaped moves, board edge permitting
        int diffCol = Math.abs((m1 % side) - (m0 % side));
        int diffRow = Math.abs((m1 / side) - (m0 / side));
        return --diffCol * --diffRow == 0 && diffCol + diffRow == 1;
    }

    public boolean beenHereBefore(int m) {
        // If the knight has already been in that space, then move is invalid
        return arrPreviousMoves[m];
    }

    public void trackMove(int m) {
        // stores history of spaces occupied by knight
        // m is integer from 0 to n x n
        arrPreviousMoves[m] = true;
        history.push(m);
    }

    public String showHistory() {
        // returns string of move sequence, in algebraic notation
        StringBuilder sBuilder = new StringBuilder();
        int lastMove;
        while (!history.empty()) {
            lastMove = history.pop();
            sBuilder.append(toChess(lastMove) + " ");
        }
        return sBuilder.toString();
    }

    public String showBoard() {
        char wasThere;
        StringBuilder sBuilder = new StringBuilder();
        int i = 0;
        while (i < side * side) {
            wasThere = beenHereBefore(i) ? 'x' : '_';
            sBuilder.append("[" + wasThere + "]");
            i++;
            if (i % side == 0)
                sBuilder.append("\n");
        }
        return sBuilder.toString();
    }

    public int getSize() {
        return history.size();
    }

    public boolean[] getPreviousMoves() {
        return arrPreviousMoves;
    }

    private int side;       // n for an n x n square chessboard

    private Stack<Integer> history;

    private boolean[] arrPreviousMoves;    // true if knight has been here before
}
