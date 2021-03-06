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

    public static int[] find8Moves(int m, int side) {
        int[] arr = {m - 2*side - 1, m - 2*side + 1, m - side - 2, m - side + 2,
            m + side - 2, m + side + 2, m + 2*side - 1, m + 2*side + 1 };
        return arr;
    }

	public static void main(String[] args) {
        int size;
        if (args.length == 0) {
            size = 8;
        } else {
            size = Integer.parseInt(args[0]);
        }

        final int SIZE = size;
        final int START = size < 5 ? 0 : SIZE * SIZE / 2;

        Chessboard board = new Chessboard(SIZE);
        int space = START;
        int[] moves = new int[8];  // at most, there are 8 valid L-shaped moves
        int ct;  // counter to check the 8 moves
        boolean anotherMovePossible = true;
        boolean withinBounds;
        boolean moveMade = false;

        System.out.println("Starting space: " + board.toChess(space));
        while (anotherMovePossible) {
            moves = find8Moves(space, SIZE);
            ct = 0;
            moveMade = false;

            while (ct < moves.length && !moveMade) {
                withinBounds = moves[ct] >= 0 && moves[ct] < (SIZE * SIZE);
                // a valid move is among the 8 L-shaped moves, which a knight has NOT visited before.
                if (withinBounds && !board.beenHereBefore(moves[ct]) && board.isKnightMove(space, moves[ct])) {
                    board.trackMove(space);  // record move
                    space = moves[ct];   // finalize move
                    moveMade = true;  // exits inner loop
                } else {
                    ct++;    // check next move of 8 possibilities
                }
            }
            // as long as a move is made, another one is possible, UNLESS the final move has been made
            anotherMovePossible = moveMade;
        }
        // add final space to history
        board.trackMove(space);

        System.out.println("Number of moves made: " + board.getSize());
        System.out.println("Spaces on board: " + SIZE * SIZE);

        System.out.print("History of moves: ");
        System.out.println(board.showHistory());

        System.out.println("\n" + board.showBoard());
    }
}
