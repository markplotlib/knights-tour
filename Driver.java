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

	public static void main(String[] args) {
        System.out.println("Driver file for Chess Computations project.");
        Chessboard board = new Chessboard(8);
        // >>> to_chess(0), to_chess(1), to_chess(7), to_chess(8), to_chess(63)
        // ('a8', 'b8', 'h8', 'a7', 'h1')
        System.out.println(board.toChess(0));
        System.out.println(board.toChess(1));
        System.out.println(board.toChess(7));
        System.out.println(board.toChess(8));
        System.out.println(board.toChess(63));
    }
}
