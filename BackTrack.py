from itertools import repeat
from time import time

# https://seattleu.instructure.com/courses/1588778/assignments/6759181

from Chessboard import to_chess, is_knight_move, find_8_moves
"""Backtracking algorithm
DFS approach, random neighbor ordering
recursive calls for promising branches
"""


def make_move(stack, i, hi_score):
    i += 1
    if len(stack) == ROW*COL:    # knight's tour found! :)
        move_seq = list(map(to_chess, stack, repeat(ROW, len(stack)), repeat(COL, len(stack))))
        global successful_path
        successful_path = " ".join(move_seq)
        return i, ROW*COL, stack

    elif len(stack) == 0:        # knight's tour unachievable for r < 5, c < 5
        return i, hi_score, []   # final spaces are impossible to reach

    else:
        # print out occasional recursive calls
        if i % SAMPLING_RATE == 0:
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
        while len(good_moves) > 0 and hi_score < ROW*COL:   # break loop if solution found
            # recursive case -- advance the knight
            stack.append(good_moves.pop())
            hi_score = max(hi_score, len(stack))
            i, hi_score, stack = make_move(stack, i, hi_score)

        # if while-loop ends, then this branch is a non-promising dead-end
        # the next step is to backtrack
        # and continue through the while-loop of the previous call on the stack
        stack.pop()
        return i, hi_score, stack


# fixed variables
ROW = COL = 6   # board dimensions
START = 0       # space where the knight begins
SAMPLING_RATE = 10000
successful_path = ""


if __name__ == '__main__':

    table_header = ["seconds", "call #", "length of try", "{}x{} board moves".format(ROW, COL)]
    print("\t| ".join(table_header))

    # initialize values
    start = time()
    hi_score = 0

    # recursive call
    final_i, longest_length, final_path = make_move([START], 0, hi_score)
    print("-"*64)
    print("Final Summary: {} recursive calls. Longest path: {}. Runtime: {} seconds".format(final_i, longest_length, round(time() - start, 1)))
    if longest_length == ROW*COL:
        print("*"*33 + "SOLUTION FOUND" + "*"*33)
        print(successful_path)
    else:
        print("No Hamiltonian circuit was found.")
