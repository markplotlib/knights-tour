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

    def is_knight_move(self, j, k, c):
        """ returns true if knight moves from (x0, y0) to (x1, y1),
        where (|y1-y0|, |x1-x0|) ∈ {(1, 2), (2, 1)}
        at most, there are 8 valid L-shaped moves, board edge permitting
        >>> Chessboard.is_knight_move(51, 61, 8), Chessboard.is_knight_move(19, 4, 8), Chessboard.is_knight_move(0, 2, 8)
        (True, True, False)
        >>> Chessboard.is_knight_move(12, 1, 5), Chessboard.is_knight_move(12, 3, 5), Chessboard.is_knight_move(12, 5, 5), Chessboard.is_knight_move(12, 9, 5), Chessboard.is_knight_move(12, 15, 5), Chessboard.is_knight_move(12, 19, 5), Chessboard.is_knight_move(12, 21, 5), Chessboard.is_knight_move(12, 23, 5)
        (True, True, True, True, True, True, True, True)
        >>> Chessboard.is_knight_move(12, 2, 5), Chessboard.is_knight_move(12, 6, 5), Chessboard.is_knight_move(12, 7, 5), Chessboard.is_knight_move(12, 8, 5)
        (False, False, False, False)
        """
        diff_col = abs((k % c) - (j % c)) - 1
        diff_row = abs((k // c) - (j // c)) - 1
        return (diff_col*diff_row == 0) and (diff_col + diff_row == 1)

    def find_8_moves(self, m, r=8, c=8):
        moves_within_bounds = []
        moves_all8 = [ m + 2*c + 1, m - c - 2, m - c + 2, m + 2*c - 1, m - 2*c - 1, m + c + 2, m - 2*c + 1, m + c - 2 ]
        for m in moves_all8:
            if m >= 0 and m < r*c:
                moves_within_bounds.append(m)
        return moves_within_bounds
