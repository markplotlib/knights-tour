from itertools import repeat
from time import time

# https://seattleu.instructure.com/courses/1588778/assignments/6759181

"""Backtracking algorithm
DFS approach, random neighbor ordering
recursive calls for promising branches
"""

def to_chess(m, r=8, c=8):
    """Return algebraic chess notation for square m in an r x chess board.
    >>> to_chess(0), to_chess(1), to_chess(7), to_chess(8), to_chess(63)
    ('a8', 'b8', 'h8', 'a7', 'h1')
    """
    row = r - m // r
    col = m % c + 97
    return chr(col) + str(row)


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
    >>> is_knight_move(0,10,8), is_knight_move(0,17,8), is_knight_move(0,15,8), is_knight_move(0,6,8)
    (True, True, False, False)
    """
    diff_col = abs((k % c) - (j % c)) - 1
    diff_row = abs((k // c) - (j // c)) - 1
    return (diff_col*diff_row == 0) and (diff_col + diff_row == 1)


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


def make_move(stack, i, hi_score):
    i += 1
    if len(stack) == ROW*COL:    # knight's tour found! :)
        for _ in range(9):
            print("*"*9 + "SOLUTION FOUND" + "*"*9)
            return i, ROW*COL

    elif len(stack) == 0:        # knight's tour unachievable for r < 5, c < 5
        return i, hi_score       # final spaces are impossible to reach

    else:
        # print out occasional recursive calls
        if i % DISPLAY_RATE == 0:
            itr_time = round(time() - start, 1)
            # table column: trying (attempt)
            attempt = "trying({})".format(str(len(stack)))
            # table column: move sequence and longest path (high score)
            move_seq = list(map(to_chess, stack, repeat(ROW, len(stack)), repeat(COL, len(stack))))
            hi_score = max(hi_score, len(stack))
            str_hi_score = "longest path so far: " + str(hi_score)
            # entire table
            table_row = [str(itr_time), str(i), attempt, " ".join(move_seq), str_hi_score]
            print("\t| ".join(table_row))

        good_moves = find_8_moves(stack, ROW, COL)
        while len(good_moves) > 0:
            # recursive case -- advance the knight
            stack.append(good_moves.pop())
            hi_score = max(hi_score, len(stack))
            i, hi_score = make_move(stack, i, hi_score)

        # if while-loop ends, then this branch is a non-promising dead-end
        # the next step is to backtrack
        # and continue through the while-loop of the previous call on the stack
        stack.pop()
        return i, hi_score


# global variables
ROW = COL = 4   # board dimensions
START = 0       # space where the knight begins
DISPLAY_RATE = 1000


if __name__ == '__main__':

    table_header = ["seconds", "call#", "length of try", "{}x{} board moves".format(ROW, COL)]
    print("\t| ".join(table_header))

    # initialize values
    start = time()
    hi_score = 0

    # recursive call
    final_i, final_longest_path = make_move([START], 0, hi_score)
    print("Final Summary: {} recursive calls. Longest path: {}".format(final_i, final_longest_path))
