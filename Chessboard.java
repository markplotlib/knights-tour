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

    private int side;
    private char[][] board;
}
