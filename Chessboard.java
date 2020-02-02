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

	public Chessboard() {
        side = 3;
        board = new char[side][side];
    }

    public void fill() {
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public void show() {
        for (int row = 0; row < side; row++) {
            for (int col = 0; col < side; col++) {
                System.out.print("[");
                System.out.print(board[row][col]);
                System.out.print("]");
            }
            System.out.println();
        }
    }

    private int side;
    private char[][] board;
}
