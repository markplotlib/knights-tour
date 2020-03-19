from decimal import Decimal
from itertools import permutations, repeat
from time import time

from Chessboard import to_chess, is_knight_move, are_all_knight_moves
# https://seattleu.instructure.com/courses/1588778/assignments/6759181

# BRUTE FORCE
# Write an algorithm (in Python) that loops through all possible permutations of
# the squares of a row x col chess board, checking each one to see if it is a
# Hamiltonian path or not. Print out every millionth path, as you go,
# to show progress. Your output has to look substantially similar to mine,
# as shown here for the start of running it for a 4x4 board:

# global variables
row = col = 4   # board dimensions

def factorial(n):
    return 1 if n == 1 else n*factorial(n-1)


if __name__ == '__main__':

    # generate iterable of all permutations
    iter_permutations = permutations(range(row*col))

    table_header = ["seconds", "*"*7 + " progress " + "*"*7, "outcome", "{}x{} board moves".format(row, col)]
    print("\t| ".join(table_header))

    # convert each from tuple to string
    start = time()
    i = 0
    num_permutations = factorial(row*col)
    for permutation_tuple in iter_permutations:
        i += 1
        if i % 1000000 == 0:
            itr_time = round(time() - start, 1)

            # table column: progress
            progress_ratio = '%.1E' % Decimal(str(i)) + '/' + '%.1E' % Decimal(str(num_permutations))
            progress_percent = '(' + str(round(i / num_permutations, 8)) + '%)'
            progress_stats = " ".join([progress_ratio, progress_percent])

            # table column: outcome
            tour_done = are_all_knight_moves(permutation_tuple, col)
            outcome = "success" if tour_done else "failure"

            move_seq = list(map(to_chess, permutation_tuple, repeat(row, row*col), repeat(col, row*col)))

            table_row = [str(itr_time), progress_stats, outcome, " ".join(move_seq)]
            print("\t| ".join(table_row))
