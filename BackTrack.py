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

def make_move(stack):
    # base case 1
    if len(stack) == ROW*COL:    # knight's tour found! :)
        anotherMovePossible = False
        return True
    elif len(stack) == 0:        # knight's tour unachievable :(
        anotherMovePossible = False
        return False    # final spaces are impossible to reach
    else:
        good_moves = find_8_moves(stack, ROW, COL)
        while len(good_moves) > 0:
            # recursive case -- advance the knight
            stack.append(good_moves.pop())
            return make_move(stack)
        # this branch is a dead-end --> backtrack
        stack.pop()


# global variables
ROW = COL = 4   # board dimensions
START = 0       # space where the knight begins

if __name__ == '__main__':
    all_spaces = range(ROW*COL)

    table_header = ["seconds", "iteration", "length of try", "{}x{} board moves".format(ROW, COL)]
    print("\t| ".join(table_header))

    # initialize values
    spaces_visited = [START]
    i = 0
    anotherMovePossible = True
    start = time()

    while anotherMovePossible:
        # anotherMovePossible becomes false when:
        # a) final spaces are impossible to reach
        # b) final spaces are reached (i.e., tour_completed is True)

        tour_completed = make_move(spaces_visited)
        hi_score = 1
        hi_score = max(hi_score, len(spaces_visited))

        i += 1
        if tour_completed or i % 1000000 == 0:
            itr_time = round(time() - start, 1)

            # table column: trying (attempt)
            attempt = "trying({})".format(str(len(spaces_visited)))

            move_seq = list(map(to_chess, spaces_visited, repeat(ROW, len(spaces_visited)), repeat(COL, len(spaces_visited))))
            str_hi_score = "longest path so far: " + str(hi_score)

            table_row = [str(itr_time), str(i), attempt, " ".join(move_seq), str_hi_score]
            print("\t| ".join(table_row))
