class Chessboard:
    def __init__(self, r, c):
        self.r = r
        self.c = c

    def to_chess(self, m, r=8, c=8):
        """Return algebraic chess notation for square m in an r x chess board.
        >>> Chessboard.to_chess(self, 0), Chessboard.to_chess(self, 1), Chessboard.to_chess(self, 7), Chessboard.to_chess(self, 8), Chessboard.to_chess(self, 63)
        ('a8', 'b8', 'h8', 'a7', 'h1')
        """
        row = r - m // r
        col = m % c + 97
        return chr(col) + str(row)
