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
        Chessboard board = new Chessboard(3);
        board.fill();
        board.show();
    }
}
