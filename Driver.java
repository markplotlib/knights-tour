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

    // valid moves: 3x3 board
    public static final int SIZE = 3;
    public static final int START = 0;
    public static final int[] GOOD_MOVES = {7, 5};
    public static final int BAD_MOVE = 2;

    // valid moves: 5x5 board
    // public static final int SIZE = 5;
    // public static final int START  = 12;
    // public static final int[] GOOD_MOVES = {23, 21, 19, 15, 1, 3, 5, 9};
    // public static final int BAD_MOVE = 2;

    // valid moves: 8x8 board
    // public static final int SIZE = 8;
    // public static final int START = 28;
    // public static final int[] GOOD_MOVES = {45, 43, 38, 34, 22, 18, 13, 11};
    // public static final int BAD_MOVE = 44;

	public static void main(String[] args) {
        System.out.println("Driver file for Chess Computations project.");
        Chessboard board = new Chessboard(SIZE);
        // sampleMoves(board);  5 sample moves made up on canvas

        // """Determine if a move from square number j to number k is a valid
        // knight's move on a c-column board.
        System.out.println("These 8 moves are expected to be true: ");
        for (int i = 0; i < GOOD_MOVES.length; i++)
            System.out.print(board.isKnightMove(START, GOOD_MOVES[i]) + " ");
        System.out.println();

        System.out.println("This move is expected to be false: ");
        System.out.println(board.isKnightMove(START, BAD_MOVE));
    }

    public static void sampleMoves(Chessboard board) {
        // >>> to_chess(0), to_chess(1), to_chess(7), to_chess(8), to_chess(63)
        // ('a8', 'b8', 'h8', 'a7', 'h1')
        System.out.print(board.toChess(0) + " ");
        System.out.print(board.toChess(1) + " ");
        System.out.print(board.toChess(7) + " ");
        System.out.print(board.toChess(8) + " ");
        System.out.println(board.toChess(63));
    }
}
