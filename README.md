# AStarSearch
Uses Java to implement the A* search to intelligently find the fastest route between two points of a N x N sized board.

The test file randomly creates a board with a percentage of the tiles marked as unpassable with an X.
The algorithm can move diagonally at a cost of 14, compared to moving in cardinal directions at a cost of 10. (10^2 + 10^2 ~= 14^2)

Calculates the heuristic as H = F + G, or distance to the finish + cost from the start.
Calculates distance to the finish using the Manhattan Method.

Uses a closed list (hashtable) and an open list (min heap) to keep track of nodes.

Once the optimal path is found, an optional animation will play displaying the path that was found.
