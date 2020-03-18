from decimal import Decimal
from itertools import permutations
# code must be done in Python (v3).
# We'll be using r x c chess boards of various sizes with paths displayed in
# algebraic chess notation (bottom left corner is square a1,
# bottom right is h1 for an 8x8 and top right is h8 for an 8x8 board).

# https://seattleu.instructure.com/courses/1588778/assignments/6759181

# BRUTE FORCE
# Write an algorithm (in Python) that loops through all possible permutations of
# the squares of an r x c chess board, checking each one to see if it is a
# Hamiltonian path or not. Print out every millionth path, as you go,
# to show progress. Your output has to look substantially similar to mine,
# as shown here for the start of running it for a 4x4 board:

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


if __name__ == '__main__':

    #### HARD CODED
    t1 = 3.52
    time_total = 2.1E13
    prg1 = 1E6
    prg1_perc = 1/100000    # ask Kevin how to make this show 0.00001%
    tour_done = False
    #### HARD CODED
    r=4; c=4

    progress_ratio = '%.1E' % Decimal(str(prg1)) + '/' + '%.1E' % Decimal(str(time_total))
    progress_percent = '(' + str(prg1_perc) + '%)'
    progress_stats = " ".join([progress_ratio, progress_percent])

    outcome = "success\t" if tour_done else "failure\t"

    table_header = ["time", "*"*7 + " progress " + "*"*7, "outcome\t", "{}x{} board moves".format(r, c)]
    print(" | ".join(table_header))

    # create list of all board squares, to feed into generator
    board_squares = []
    i = 0
    while i < r*c:
        board_squares.append(to_chess(i, r, c))
        i += 1

    # generate each permutation
    # convert each from tuple to string
    for permutation_tuple in permutations(board_squares, r*c):
        i += 1
        if i % 1000000 == 0:
            table_row = [str(t1), progress_stats, outcome, " ".join(permutation_tuple)]
            print(" | ".join(table_row))
