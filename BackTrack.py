from itertools import repeat
from time import time

from Chessboard import Chessboard
# https://seattleu.instructure.com/courses/1588778/assignments/6759181

"""Backtracking algorithm
DFS approach, random neighbor ordering
recursive calls for promising branches
"""
# global variables
row = col = 3   # board dimensions
start = 0

if __name__ == '__main__':

    board = Chessboard(row, col)
    all_spaces = range(row*col)

    table_header = ["seconds", "iteration", "trying...", "{}x{} board moves".format(row, col)]
    print("\t| ".join(table_header))

    # convert each from tuple to string
    start = time()
    i = 0
    temp_stop_iterating = 3E6  ############ TEMP ########################
    while i <= temp_stop_iterating:
        i += 1
        if i % 1000000 == 0:
            itr_time = round(time() - start, 1)

            # table column: trying (attempt)

            # table column: outcome
            tour_done = False   ############ TEMP ########################
            outcome = "success" if tour_done else "failure"

            move_seq = list(map(board.to_chess, all_spaces, repeat(row, row*col), repeat(col, row*col)))

            table_row = [str(itr_time), str(i), outcome, " ".join(move_seq)]
            print("\t| ".join(table_row))
