from decimal import Decimal
from Chessboard import *
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
