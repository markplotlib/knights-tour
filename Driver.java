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
        Chessboard board = new Chessboard(SIZE);

        int space = START;
        System.out.println("Starting space: " + board.toChess(space));
        boolean anotherMovePossible = true;
        while (anotherMovePossible) {
            boolean continueFromTop = true;
            int i = 0;
            while (continueFromTop) {
                // a valid move is one that forms an L-shape from the knight's
                // current space, into a space that a knight has NOT been to before
                if (!board.beenHereBefore(i) && board.isKnightMove(space, i)) {
                    board.trackMove(space);
                    System.out.println(board.toChess(space) + " to " + board.toChess(i));
                    space = i;
                }
                i++;
                continueFromTop = i < SIZE * SIZE;
            }
            // add final space to history
            board.trackMove(space);
            anotherMovePossible = board.getHistory().size() < SIZE * SIZE - 1;
        }

        System.out.print("History of moves: ");
        System.out.println(board.showHistory());

        System.out.println("Moves made: " + board.getHistory().size());
        System.out.println("Spaces on board: " + SIZE * SIZE);

        System.out.println("\n" + board.showBoard());
    }
}
