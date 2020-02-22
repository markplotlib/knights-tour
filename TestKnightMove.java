public class TestKnightMove {

    public static final int SIZE = 3;
    public static final int START = 0;

    public static int getCol(int m) {
        return m % SIZE;
    }

    public static int getRow(int m) {
        return m / SIZE;
    }

    public static boolean isKnightMove(int m0, int m1) {
        int diffCol = getCol(m1) - getCol(m0);
        int diffRow = getRow(m1) - getRow(m0);
        return --diffCol * --diffRow == 0 && diffCol + diffRow == 1;
    }

	public static void main(String[] args) {
        System.out.println("0 to 0 false: " + isKnightMove(0, 0));
        System.out.println("0 to 1 false: " + isKnightMove(0, 1));
        System.out.println("0 to 2 false: " + isKnightMove(0, 2));
        System.out.println("0 to 3 false: " + isKnightMove(0, 3));
        System.out.println("0 to 4 false: " + isKnightMove(0, 4));
        System.out.println("0 to 5 true!: " + isKnightMove(0, 5));
        System.out.println("0 to 6 false: " + isKnightMove(0, 6));
        System.out.println("0 to 7 true!: " + isKnightMove(0, 7));
        System.out.println("0 to 8 false: " + isKnightMove(0, 8));
    }
}
