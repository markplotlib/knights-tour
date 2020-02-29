def to_chess(m, side = 8):
    """Return algebraic chess notation for square m in an r x chess board.
    >>> to_chess(0, 3), to_chess(1, 3), to_chess(7, 3), to_chess(8, 3)
    ('a3', 'b3', 'b1', 'c1')
    >>> to_chess(0), to_chess(1), to_chess(7), to_chess(8)
    ('a8', 'b8', 'h8', 'a7')
    """
    row = m % side + 97
    col = side - m // side
    return chr(row) + str(col)
