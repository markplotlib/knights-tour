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
    >>> is_knight_move(12, 1, 5), is_knight_move(12, 3, 5), is_knight_move(12, 5, 5), is_knight_move(12, 9, 5), is_knight_move(12, 15, 5), is_knight_move(12, 19, 5), is_knight_move(12, 21, 5), is_knight_move(12, 23, 5)
    (True, True, True, True, True, True, True, True)
    >>> is_knight_move(12, 2, 5), is_knight_move(12, 6, 5), is_knight_move(12, 7, 5), is_knight_move(12, 8, 5)
    (False, False, False, False)
    """
    diff_col = abs((k % c) - (j % c)) - 1
    diff_row = abs((k // c) - (j // c)) - 1
    return (diff_col*diff_row == 0) and (diff_col + diff_row == 1)


def are_all_knight_moves(x, c):
    """
    Returns True if sequences of moves are all knight moves, otherwise False.
    >>> are_all_knight_moves([0, 10, 16], 8)
    True
    >>> are_all_knight_moves([0, 9, 2, 4, 10, 1, 8, 6], 4)
    True
    >>> are_all_knight_moves([0, 1], 8)
    False
    """
    if len(x) <= 1:
        return True
    else:
        a, b = x[0], x[1]
        return is_knight_move(a, b, c) and are_all_knight_moves(x[1:], c)


def find_8_moves(stack, r=8, c=8):
    cur = stack[-1:][0]  # CURRENT location of knight (stack.peek() operation)
    moves_within_bounds = []
    # 8 directions a knight can move, excluding board edges and retracing steps
    moves_all8 = [ cur+2*c+1, cur-c-2, cur-c+2, cur+2*c-1,
                    cur-2*c-1, cur+c+2, cur-2*c+1, cur+c-2 ]
    for m in moves_all8:
        is_m_in_board = m >= 0 and m < r*c
        if is_knight_move(cur, m, c) and is_m_in_board and m not in stack:
            moves_within_bounds.append(m)
    return moves_within_bounds
