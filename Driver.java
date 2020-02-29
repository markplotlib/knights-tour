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

    public static final int SIZE = 5;
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
        int ct;         // counter to check the 8 moves
        int dir = 0;    // points to 1 of 8 directions for movement
        boolean anotherMovePossible = true;
        boolean withinBounds;
        boolean moveMade = false;

        System.out.println("Starting space: " + board.toChess(space));
        while (anotherMovePossible) {
            moves = find8Moves(space, SIZE);
            moveMade = false;
            ct = 0;

            while (ct < moves.length && !moveMade) {
                withinBounds = moves[dir] >= 0 && moves[dir] < (SIZE * SIZE);
                // a valid move is among the 8 L-shaped moves, which a knight has NOT visited before.
                if (withinBounds && !board.beenHereBefore(moves[dir]) && board.isKnightMove(space, moves[dir])) {
                    System.out.print(board.toChess(space) + " to " + board.toChess(moves[dir]) + ". ");
                    board.trackMove(space);  // record move
                    space = moves[dir];   // finalize move
                    moveMade = true;  // exits inner loop
                }
                dir++;    // check next move of 8 possibilities
                dir %= 8;
                ct++;
            }
            // as long as a move is made, another one is possible, UNLESS the
            // final move has been made (successful tour, or failure)
            anotherMovePossible = moveMade;
        }
        // add final space to history
        board.trackMove(space);
        System.out.println("\n");

        System.out.println("Number of moves made: " + board.getSize());
        System.out.println("Spaces on board: " + SIZE * SIZE);

        System.out.print("History of moves: ");
        System.out.println(board.showHistory());

        System.out.println("\n" + board.showBoard());
    }
}
