from itertools import repeat
from time import time

from Chessboard import Chessboard
# https://seattleu.instructure.com/courses/1588778/assignments/6759181

"""Backtracking algorithm
DFS approach, random neighbor ordering
recursive calls for promising branches
"""

def find_8_moves(stack, r=8, c=8):
    cur = stack[-1:][0]  # peek at stack -- get current space that knight occupies
    moves_within_bounds = []
    # moves_all8 = [ cur + 2*c + 1, cur - c - 2, cur - c + 2, cur + 2*c - 1, cur - 2*c - 1, cur + c + 2, cur - 2*c + 1, cur + c - 2 ]
    for m in [cur+2*c+1,cur-c-2,cur-c+2,cur+2*c-1,cur-2*c-1,cur+c+2,cur-2*c+1,cur+c-2]:
        if m >= 0 and m < r*c and m not in stack:
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
        available_moves = find_8_moves(stack, ROW, COL)
        while len(available_moves) >= 0:
            # recursive case -- advance the knight
            stack.append(available_moves.pop())
            return make_move(stack)
        # this branch is a dead-end --> backtrack
        stack.pop()


# global variables
ROW = COL = 3   # board dimensions
START = 0       # space where the knight begins

if __name__ == '__main__':
    board = Chessboard(ROW, COL)
    all_spaces = range(ROW*COL)

    table_header = ["seconds", "iteration", "trying...", "{}x{} board moves".format(ROW, COL)]
    print("\t| ".join(table_header))

    # initialize values
    spaces_visited = [START]
    i = 0
    anotherMovePossible = True
    start = time()

    while anotherMovePossible:
        # anotherMovePossible becomes false when:
        # a) final spaces are impossible to reach
        # b) final spaces are reached (i.e., tour_done is True)

        tour_done = make_move(spaces_visited)

        i += 1
        if tour_done or i % 1000000 == 0:
            itr_time = round(time() - start, 1)

            # table column: trying (attempt)

            # table column: outcome
            outcome = "success" if tour_done else "failure"

            move_seq = list(map(board.to_chess, all_spaces, repeat(ROW, ROW*COL), repeat(COL, ROW*COL)))

            table_row = [str(itr_time), str(i), outcome, " ".join(move_seq)]
            print("\t| ".join(table_row))
