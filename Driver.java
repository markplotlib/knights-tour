/*
 * Mark Chesney
 * ACM: Association for Computing Machinery, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

/**
 * This project computes a solution to the Hamiltonian Circuit Problem
 * of the Knights Tour: a sequence of moves for a chess knight to visit every
 * square exactly once in a chessboard of size n x n
 *
 * @author  Mark Chesney
 * @version 1.0
 */
public class Driver {

    public static final int SIZE = 3;
    // public static final int START = SIZE * SIZE / 2;
    public static final int START = 0;

	public static void main(String[] args) {
        System.out.println("Driver file for Chess Computations project.");
        Chessboard board = new Chessboard(SIZE);

        // """Determine if a move from square number j to number k is a valid
        // knight's move on a c-column board.
        int space = START;
        System.out.println("Starting space: " + board.toChess(space));
        boolean reloop = true;
        while (reloop) {
            for (int i = 0; i < SIZE * SIZE; i++) {
                // a valid move is one that forms an L-shape from the knight's
                // current space, into a space that a knight has NOT been to before
                if (!board.beenHereBefore(i) && board.isKnightMove(space, i)) {
                    board.trackMove(space);
                    System.out.println("knight MOVES from " + board.toChess(space)
                    + " to " + board.toChess(i));
                    space = i;
                }
            }
// TEMP -- NEEDS FIX -- should stop at 8, instead of at 7
            if (board.getHistory().size() > 6) {   // special case: 3x3 -- excludes center
                System.out.println(board.getHistory().size());
                reloop = false;
            }
        }
        System.out.print("History of moves: ");
        System.out.println(board.showHistory(space));
    }
}
