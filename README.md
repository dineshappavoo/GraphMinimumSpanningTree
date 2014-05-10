Graph Minimum Spanning Tree
=========

Given a connected, undirected graph, a spanning tree of that graph is a subgraph that is a tree and connects all the 
vertices together. A single graph can have many different spanning trees. We can also assign a weight to each edge, 
which is a number representing how unfavorable it is, and use this to assign a weight to a spanning tree by computing 
the sum of the weights of the edges in that spanning tree. A minimum spanning tree (MST) or minimum weight spanning tree 
is then a spanning tree with weight less than or equal to the weight of every other spanning tree. More generally, any 
undirected graph (not necessarily connected) has a minimum spanning forest, which is a union of minimum spanning trees 
for its connected components.


##List of Algorithms for undirected Graph and Complexity

	Improvement Algorithm - O(E log V)
	Boruvka Algorithm     - O(|E| log |V|) for adjacency list
	Kruskal A algorithm   - O(|E| log |V|)
	Kruskal B Algorithm   - O(|E| log |V|)
	Prim Algoritm         - O(|E| log |V|)


##Install

This library has the java implementation of Minimum Spanning Tree Algorithms to find the MST in a 
undirected graph G=[V,E].The following code snippet shows how to get the shortest path,


###Input
	3 3
	1 2 1
	2 3 2
	1 3 3

First integer is the total number of vertices |V| in the graph G. The next integer is the number of edges |E| in the graph.
Next |E| lines has the edges information (u, v, w). All inputs must be given through terminal.

###Output

  
##Project Contributor

* Dinesh Appavoo ([@DineshAppavoo](https://twitter.com/DineshAppavoo))