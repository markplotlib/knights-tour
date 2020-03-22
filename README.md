# Knights Tour

This mini-project explores the computational complexity in answering an age-old question:
how can a knight move around a chessboard to touch every square exactly once?

## Historical Background

More info on the history of the Knight's Tour can be found on [Wikipedia](https://en.wikipedia.org/wiki/Knight%27s_tour),
as well as info about every topic in the universe.

# Approach

## Brute Force algorithm

Using the Python `itertools` library, this approach creates permutations of every space on the chessboard.
Each permutation is analyzed on whether it is or isn't a knight's tour.
Incremental increases in the board size have substantial increases on computation time.
This algorithm's time complexity is of a factorial efficiency class.

## Backtracking - Random Neighbor Order

The knight moves from one space to another valid space through recursive calls. 
**Data Structures** used are a **stack** that keeps a record of moves, 
popping off the last move if it leads down a *non-promising* path (i.e., a dead-end).
The stack then *backtracks* by attempting a different space 
and repeating the cycle until either one of these outcomes occur:

* the knight's tour has been completed,

* the knight's tour cannot be completed -- every permutation has been exhausted

Note that a solution has been [mathematically proven]
(https://en.wikipedia.org/wiki/Knight%27s_tour#Existence)
 to exist on any board of a 5x5 size, or larger.
