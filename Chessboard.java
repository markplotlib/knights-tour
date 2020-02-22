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
        history = new LinkedHashSet<Integer>();
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
        return history.contains(m);
    }

    public void trackMove(int m) {
        // stores history of spaces occupied by knight
        // m is integer from 0 to side * side (e.g. to 63)
        history.add(m);
    }

    public LinkedHashSet<Integer> getHistory() {
        return history;
    }

    public String showHistory() {
        // returns string of move sequence, in algebraic notation
        StringBuilder str = new StringBuilder();
        for (int item : history) {
           str.append(toChess(item) + " ");
        }
        return str.toString();
    }

    public String showBoard() {
        char wasThere;
        StringBuilder str = new StringBuilder();
        int i = 0;
        while (i < side * side) {
            wasThere = beenHereBefore(i) ? 'x' : '_';
            str.append("[" + wasThere + "]");
            i++;
            if (i % side == 0)
                str.append("\n");
        }
        return str.toString();
    }

    private int side;       // n for an n x n square chessboard
    private final LinkedHashSet<Integer> history;
}
