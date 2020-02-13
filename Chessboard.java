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
        history = new int[side * side];
        moves = 0;
    }

    public String toChess(int m) {
        // Return algebraic chess notation for square m in an r x c chess board.
        int r = m % side + 97;
        char ch = (char) r;
        int c = side - (m / side);
        return Character.toString(ch) + Integer.toString(c);
    }

    public boolean isKnightMove(int m0, int m1) {
        if (m0 / side == m1 / side)
            return false;
        int diff = m1 - m0;
        int[] validMoves = {                    // debugging:
                            diff - 2*side - 1,  // m=0
                            diff - 2*side + 1,  // m=1
                            diff - side - 2,    // m=2
                            diff - side + 2,    // m=3
                            diff + side - 2,    // m=4
                            diff + side + 2,    // m=5
                            diff + 2*side - 1,  // m=6
                            diff + 2*side + 1,  // m=7
                            };
        for (int m = 0; m < validMoves.length; m++) {
// System.out.print(validMoves[m] + ". ");
            if (validMoves[m] == 0) {
// System.out.println("m = " + m);
                return true;
            }
        }
        return false;
    }

    // NOTE: this is linear search.
    // This can be expedited to binary search.
    public boolean isBackTracking(int m) {
        for (int i = 0; i < history.length; i++)
            if (m == history[i])
                return true;
        return false;
    }

    public void trackMove(int m) {
        // stores history of spaces occupied by knight
        // m is integer from 0 to side * side (e.g. to 63)
        history[moves++] = m;
    }

    public String showHistory(int m) {
        trackMove(m);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < moves; i++) {
            str.append(toChess(history[i]) + " ");
        }
        return str.toString();
    }

    private int side;
    private int[] history;
    int moves;
}
