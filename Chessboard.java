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
// board = new char[side][side];
    }

    public String toChess(int m) {
        // Return algebraic chess notation for square m in an r x c chess board.
        int r = m % side + 97;
        char ch = (char) r;
        int c = side - (m / side);
        return Character.toString(ch) + Integer.toString(c);
    }
                                //  28,     43
    public boolean isKnightMove(int m0, int m1) {
        int diff = m1 - m0;
        int[] validMoves = {
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
// System.out.print(validMoves[m] + ". ");
            if (validMoves[m] == 0)
                return true;
        }
        return false;
    }

    private int side;
// private char[][] board;
}
