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
        board = new char[side][side];
    }

    public String toChess(int m) {
        // Return algebraic chess notation for square m in an r x c chess board.
        int r = m % side + 97;
        char ch = (char) r;
        int c = side - (m / side);
        return Character.toString(ch) + Integer.toString(c);
    }

    private int side;
    private char[][] board;
}
