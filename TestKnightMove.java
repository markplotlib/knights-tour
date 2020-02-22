import java.lang.Math;

public class TestKnightMove {

    public static final int SIZE = 5;
    public static final int START = SIZE * SIZE / 2;

    public static int getCol(int m) {
        return m % SIZE;
    }

    public static int getRow(int m) {
        return m / SIZE;
    }

    public static boolean isKnightMove(int m0, int m1) {
        int diffCol = Math.abs(getCol(m1) - getCol(m0));
        int diffRow = Math.abs(getRow(m1) - getRow(m0));
        return --diffCol * --diffRow == 0 && diffCol + diffRow == 1;
    }

    public static String toChess(int m) {
        // Return algebraic chess notation for square m in an r x c chess board.
        int r = m % SIZE + 97;
        char row = (char) r;
        int col = SIZE - (m / SIZE);
        return Character.toString(row) + Integer.toString(col);
    }

	public static void main(String[] args) {
        System.out.println("Knight starts at " + toChess(START));
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (isKnightMove(START, i))
                System.out.println("valid move: " + toChess(i));
        }
    }
}
