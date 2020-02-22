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

    public static int[] find8Moves(int m, int side) {
        int[] arr = { m - 2*side - 1, m - 2*side + 1, m - side - 2, m - side + 2, m + side - 2, m + side + 2, m + 2*side - 1, m + 2*side + 1 };
        return arr;
    }

	public static void main(String[] args) {
        Chessboard board = new Chessboard(SIZE);
        int space = START;
        int[] moves = new int[8];  // at most, there are 8 valid L-shaped moves, board edge permitting
        int i;
        boolean anotherMovePossible = true;
        boolean withinBounds;
        boolean moveMade = false;

        System.out.println("Starting space: " + board.toChess(space));
        while (anotherMovePossible) {
            moves = find8Moves(space, SIZE);
            i = 0;
            moveMade = false;

            while (i < moves.length && !moveMade) {
                withinBounds = moves[i] >= 0 && moves[i] < (SIZE * SIZE);
                // a valid move is among the 8 L-shaped moves, which a knight has NOT visited before.
                if (withinBounds && !board.beenHereBefore(moves[i]) && board.isKnightMove(space, moves[i])) {
                    System.out.print(board.toChess(space) + " to " + board.toChess(moves[i]) + ". ");
                    board.trackMove(space);  // record move
                    space = moves[i];   // finalize move
                    moveMade = true;  // exits inner loop
                } else {
                    i++;    // check next move of 8 possibilities
                }
            }
            // as long as a move is made, another one is possible, UNLESS the final move has been made
            anotherMovePossible = moveMade;
        }
        // add final space to history
        board.trackMove(space);

        System.out.print("\n\nHistory of moves: ");
        System.out.println(board.showHistory());

        System.out.println("Moves made: " + board.getHistory().size());
        System.out.println("Spaces on board: " + SIZE * SIZE);

        System.out.println("\n" + board.showBoard());
    }
}
