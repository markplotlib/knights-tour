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
        // a knight cannot move into a space of the same row
        if (m0 / side == m1 / side)
            return false;
        int diff = m1 - m0;
        int[] validMoves = {    // at most, there are 8 valid L-shaped moves
                            diff - 2*side - 1,
                            diff - 2*side + 1,
                            diff - side - 2,
                            diff - side + 2,
                            diff + side - 2,
                            diff + side + 2,
                            diff + 2*side - 1,
                            diff + 2*side + 1,
                            };
        for (int m = 0; m < validMoves.length; m++) {
            if (validMoves[m] == 0) {
                return true;    // square is a valid move
            }
        }
        return false;   // square is an invalid move
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

    public String showHistory(int m) {
        // returns string of move sequence, in algebraic notation
        trackMove(m);
        StringBuilder str = new StringBuilder();
        for (int item : history) {
           str.append(toChess(item) + " ");
        }
        return str.toString();
    }

    public int getCol(int m) {
        return m % side;
    }

    public int getRow(int m) {
        return m / side;
    }

    private int side;       // n for an n x n square chessboard
    private final LinkedHashSet<Integer> history;
}
