/*
 * Mark Chesney
 * ACM: Association for Computing Machinery, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

/**
 * <<<DESCRIPTION of this project / driver class>>>
 *
 * @author  Mark Chesney
 * @version 1.0
 */
public class Driver {

    // valid moves: 5x5 board
    public static final int SIZE = 5;
    public static final int START = SIZE * SIZE / 2;

	public static void main(String[] args) {
        System.out.println("Driver file for Chess Computations project.");
        Chessboard board = new Chessboard(SIZE);

        // """Determine if a move from square number j to number k is a valid
        // knight's move on a c-column board.
        int space = START;
        System.out.print("Starting space: " + board.toChess(space) + "\n");
        for (int i = 0; i < SIZE; i++) {
            if (board.isKnightMove(space, i)) {
                System.out.println("knight MOVES from " + board.toChess(space)
                + " to " + board.toChess(i));
                space = i;
            } else
                System.out.println("knight CANNOT move to " + board.toChess(i));
            // System.out.println();
        }
    }

}
