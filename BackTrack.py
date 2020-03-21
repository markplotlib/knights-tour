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
    cur = stack[-1:][0]  # peek at stack -- get current space that knight occupies
    moves_within_bounds = []
    # moves_all8 = [ cur + 2*c + 1, cur - c - 2, cur - c + 2, cur + 2*c - 1, cur - 2*c - 1, cur + c + 2, cur - 2*c + 1, cur + c - 2 ]
    for m in [cur+2*c+1,cur-c-2,cur-c+2,cur+2*c-1,cur-2*c-1,cur+c+2,cur-2*c+1,cur+c-2]:
        if is_knight_move(cur, m, c) and m >= 0 and m < r*c and m not in stack:
            moves_within_bounds.append(m)
    return moves_within_bounds

def make_move(stack, i, hi_score):
    i += 1
    if len(stack) == ROW*COL:    # knight's tour found! :)
        for _ in range(9):
            print("*"*9 + "SOLUTION FOUND" + "*"*9)
            i = 0                # to print out solution

    ################### TEMP
    # if i % 1000000 == 0:        # print out millionth recursive call
    if True:        # print out millionth recursive call
    ################### TEMP

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

    elif len(stack) == 0:  # knight's tour unachievable for r,c < 5 :(

    ####### TEMP
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
    ####### TEMP

        return             # final spaces are impossible to reach

    good_moves = find_8_moves(stack, ROW, COL)
    while len(good_moves) > 0:
        # recursive case -- advance the knight
        stack.append(good_moves.pop())

        ####### TEMP
        # a = stack
        # print(stack)
        # import pdb; pdb.set_trace()
        ####### TEMP

        return make_move(stack, i, hi_score)
    # this branch is a dead-end --> backtrack
    stack.pop()


# global variables
ROW = COL = 4   # board dimensions
START = 0       # space where the knight begins

if __name__ == '__main__':

    table_header = ["seconds", "iteration", "length of try", "{}x{} board moves".format(ROW, COL)]
    print("\t| ".join(table_header))

    # initialize values
    start = time()
    hi_score = 0

    # recursive call
    make_move([START], 0, hi_score)
