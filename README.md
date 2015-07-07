# FlowProblem
This is an algorithm solving the maximum flow minimum cut problem. 

Notes about the program.

The max flow algorithm problem is commonly used in calculation how much flow that can be pushed through a graph ( such as a rail network).
This algorithm can be run on any graph that follows the format in rail.txt.
The result should be presented like the description in result.txt.

The logic behind this program is that maximum amount of flow is to be pushed from the source node (s) to the destination node (t).
The nodes are connected in a network with arcs, that connect node 1 with node 2 and have a capacity. 
Firstly DFS is conducted to find the bottleneck of the graph, the path with the least capacity from s -t.
Then that amount of flow is pushed through the graph.
This is done until nothing more can be pushed through the graph.

Then the min cut is calculated by creating two sets of nodes. In set A only s is added and the rest of the nodes is added to set B.
Then every arc connecting A and B is compared, and if the arc have a remaining capacity the node is added to A and removed from B. 
When this is done the arcs connecting A and B all have a remaining capacity of 0, and these arcs are the min cut.
The max flow is then the flow from A to B, so the flow on these arcs are added to calculate the max flow.

