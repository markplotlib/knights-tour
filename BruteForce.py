from decimal import Decimal
from itertools import permutations
from time import time

from Chessboard import to_chess, is_knight_move
# https://seattleu.instructure.com/courses/1588778/assignments/6759181

# BRUTE FORCE
# Write an algorithm (in Python) that loops through all possible permutations of
# the squares of an r x c chess board, checking each one to see if it is a
# Hamiltonian path or not. Print out every millionth path, as you go,
# to show progress. Your output has to look substantially similar to mine,
# as shown here for the start of running it for a 4x4 board:

# global variables
r = c = 4   # board dimensions

def factorial(n):
    return 1 if n == 1 else n*factorial(n-1)


if __name__ == '__main__':

    #### HARD CODED
    tour_done = False
    #### HARD CODED

    # create list of all board squares, to feed into generator
    board_squares = []
    i = 0
    while i < r*c:
        board_squares.append(to_chess(i, r, c))
        i += 1

    # generate all permutations, returned as iterable
    iter_permutations = permutations(board_squares, r*c)

    table_header = ["seconds", "*"*7 + " progress " + "*"*7, "outcome", "{}x{} board moves".format(r, c)]
    print("\t| ".join(table_header))

    # convert each from tuple to string
    start = time()
    for permutation_tuple in iter_permutations:
        num_permutations = factorial(r*c)
        i += 1
        if i % 1000000 == 0:
            itr_time = round(time() - start, 1)

            # table column: progress
            progress_ratio = '%.1E' % Decimal(str(i)) + '/' + '%.1E' % Decimal(str(num_permutations))
            progress_percent = '(' + str(round(i / num_permutations, 8)) + '%)'
            progress_stats = " ".join([progress_ratio, progress_percent])

            # table column: outcome
            outcome = "success" if tour_done else "failure"

            table_row = [str(itr_time), progress_stats, outcome, " ".join(permutation_tuple)]
            print("\t| ".join(table_row))
