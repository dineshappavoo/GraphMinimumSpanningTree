Graph Minimum Spanning Tree
=========

Given a connected, undirected graph, a spanning tree of that graph is a subgraph that is a tree and connects all the 
vertices together. A single graph can have many different spanning trees. We can also assign a weight to each edge, 
which is a number representing how unfavorable it is, and use this to assign a weight to a spanning tree by computing 
the sum of the weights of the edges in that spanning tree. A minimum spanning tree (MST) or minimum weight spanning tree 
is then a spanning tree with weight less than or equal to the weight of every other spanning tree. More generally, any 
undirected graph (not necessarily connected) has a minimum spanning forest, which is a union of minimum spanning trees 
for its connected components.

###Boruvka Algorithm (1926) :
The algorithm begins by first examining each vertex and adding the cheapest edge from that vertex to another in the graph, 
without regard to already added edges, and continues joining these groupings in a like manner until a tree spanning all 
vertices is completed.

	 * MST_BORUVKA(G,w,source)
	 *  A <- Empty
	 *  B[source] <- true
	 *  for each VERTEX v in V
	 *  	PQ <- null
	 *  	for each edge e adjacent to v in E
	 *  		PQ <- PQ U e
	 *  	While (PQ.peek()!=null)
	 *  		m<-PQ.poll()
	 *      	if(!B[m.v])
	 *       		A <- A U m
	 *       		B[m.v] <- true

###Kruskal A algorithm (1956):
* create a forest F (a set of trees), where each vertex in the graph is a separate tree
* create a set S containing all the edges in the graph
    while S is nonempty and F is not yet spanning
      * remove an edge with minimum weight from S
      * if that edge connects two different trees, then add it to the forest, combining two trees into a single tree

At the termination of the algorithm, the forest forms a minimum spanning forest of the graph. If the graph is connected, the forest has a single component and forms a minimum spanning tree.
	 
	 * MST_KRUSKAL_A(G,w,source)
	 *  A <- Empty
	 *  B[source] <- true
	 *  for each edge e in E
	 *      PQ <- PQ U e
	 *  While (PQ.peek()!=null)
	 *  	m<-PQ.poll()
	 *      if(!B[m.v])
	 *       A <- A U m
	 *       B[m.v] <- true

###Kruskal B Algorithm (1956):

* create a forest F (a set of trees), where each vertex in the graph is a separate tree
* create a set S containing all the edges in the graph
    while S is nonempty and F is not yet spanning
      * remove an edge with maximum weight from S and which is safe to remove
      * if that edge connects two different trees, then add it to the forest, combining two trees into a single tree


###Jarnik (1930) - Prim (1957) - Dijkstra (1959) algorithm :

* Initialize a tree with a single vertex, chosen arbitrarily from the graph.
* Grow the tree by one edge of the edges that connect the tree to vertices not yet in the tree, find the minimum-weight edge, and transfer it to the tree.
* Repeat step 2 (until all vertices are in the tree)

The algorithm is given below,

	 * MST_PRIM(G,w,source)
	 * 	A <- Empty
	 * 	B[source] <- true
	 * 	for each edge e adjacent to E(source)
	 *		PQ <- PQ U e
	 *	While (PQ.peek()!=null)
	 *		m<-PQ.poll()
	 *		if(!B[m.v])
	 *			A <- A U m
	 *			B[m.v] <- true
	 *			for each edge e adjacent to E(m)
	 *				PQ <- PQ U e

##Complexities

	Improvement Algorithm - O(|E| log |V|)
	Boruvka Algorithm     - O(|E| log |V|) using adjacency list
	Kruskal A algorithm   - O(|E| log |V|)
	Kruskal B Algorithm   - O(|E| log |V|)
	Prim Algoritm         - O(|E| log |V|)


##Install

This library has the java implementation of Minimum Spanning Tree Algorithms to find the MST in a 
undirected graph G=[V,E].The following code snippet shows how to get the minimum spanning tree (MST),

####Boruvka Algorithm
	BoruvkaMinimumSpanningTree BoruvkaMST=new BoruvkaMinimumSpanningTree();
	int mstWeight=BoruvkaMST.getMSTWeight();
	ArrayList<Edge> gEdges=BoruvkaMST.getMST();
	
####Kruskal A Algorithm
	KruskalAMinimumSpanningTree kruskalAMST=new KruskalAMinimumSpanningTree();
	int mstWeight=kruskalAMST.getMSTWeight();
	ArrayList<Edge> gEdges=kruskalAMST.getMST();
	
####Kruskal B Algorithm
	KruskalBMinimumSpanningTree kruskalBMST=new KruskalBMinimumSpanningTree();
	int mstWeight=kruskalBMST.getMSTWeight();
	ArrayList<Edge> gEdges=kruskalBMST.getMST();
	
####Prim Algorithm
	PrimMinimumSpanningTree primMST=new PrimMinimumSpanningTree();
	int mstWeight=primMST.getMSTWeight();
	ArrayList<Edge> gEdges=primMST.getMST();



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