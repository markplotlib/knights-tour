def to_chess(m, r=8, c=8):
    """Return algebraic chess notation for square m in an r x chess board.
    >>> to_chess(0), to_chess(1), to_chess(7), to_chess(8), to_chess(63)
    ('a8', 'b8', 'h8', 'a7', 'h1')
    """
    row = m % r + 97
    col = c - m // c
    return chr(row) + str(col)


def is_knight_move(j, k, c):
    """ returns true if knight moves from (x0, y0) to (x1, y1),
    where (|y1-y0|, |x1-x0|) ∈ {(1, 2), (2, 1)}
    at most, there are 8 valid L-shaped moves, board edge permitting
    >>> is_knight_move(51, 61, 8), is_knight_move(19, 4, 8), is_knight_move(0, 2, 8)
    (True, True, False)
    """
    diff_col = abs((k % c) - (j % c)) - 1
    diff_row = abs((k // c) - (j // c)) - 1
    return (diff_col*diff_row == 0) and (diff_col + diff_row == 1)
