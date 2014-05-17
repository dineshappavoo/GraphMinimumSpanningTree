/**
 * 
 */
package prim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * @author Dinesh Appavoo
 * 
 * 
 *	 * Class to find MST using Prim algorithm
	 * 
	 * 
	 * MST_PRIM(G,w,source)
	 *  A <- Empty
	 *  B[source] <- true
	 *  for each edge e adjacent to E(source)
	 *      PQ <- PQ U e
	 *  While (PQ.peek()!=null)
	 *  	m<-PQ.poll()
	 *      if(!B[m.v])
	 *       	A <- A U m
	 *       	B[m.v] <- true
	 *       	for each edge e adjacent to E(m)
	 *       		PQ <- PQ U e
 * 
 *
 */

public class PrimMinimumSpanningTree {

	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;

	public static boolean[] B=null;
	public static ArrayList<Edge> A=new ArrayList<Edge>();

	/**
	 * Main method for testing
	 * @param args
	 */
	public static void main(String[] args) {

		PrimMinimumSpanningTree primMST=new PrimMinimumSpanningTree();
		int mstWeight=primMST.getMSTWeight();
		System.out.println(mstWeight);
		ArrayList<Edge> primEdges=primMST.getMST();
		
		for(Edge e : primEdges)
		{
			System.out.println("u : "+e.u+" v : "+e.v+" w : "+e.w);
		}
	}

	/**
	 * 
	 * Method to MST edges
	 * @return
	 */
	public ArrayList<Edge> getMST()
	{
		constructGraph();
		findPrimMST(1);
		return A;
	}
	
	/**
	 * 
	 * Method to return MST total weight
	 * @return
	 */
	public int getMSTWeight()
	{
		constructGraph();		
		findPrimMST(1);
		int totalWeight=0;
		for(Edge e : A)
		{
			totalWeight+=(Integer)e.w;
		}
		return totalWeight;
	}
	
	/**
	 * Method to construct the graph using adjacency list
	 * 
	 */
	public void constructGraph()
	{

		int u, v, w;
		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext())
		{
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();

			B=new boolean[noOfVertices+1];

			graph=new Graph<Integer>(noOfVertices);
			for(int i=0;i<noOfEdges;i++)
			{
				u=scanner.nextInt();
				v=scanner.nextInt();
				w=scanner.nextInt();
				graph.addEdge(u, v, w);
				//graph.addEdge(v, u, w);
			}
			break;
		}
		//graph.printGraph();

	}

	
	/**
	 * 
	 * Method to return Minimum Heap
	 * @return
	 * 
	 */
	public PriorityQueue<Edge> getMinHeapPriorityQueue()
	{
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(11, new Comparator<Edge>()
				{
			public int compare(Edge o1, Edge o2)
			{
				int p1=(Integer) o1.w;
				int p2=(Integer) o2.w;
				return (p2-p1); 
			}
				}
				);
		return queue;
	}

	/**
	 * Method to find MST using Prim algorithm
	 * @param source
	 * 
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
	 * 
	 */
	
	public void findPrimMST(int source)
	{
		B[source]=true;
		ArrayList<Edge> adjList=graph.getOutEdges(source);
		PriorityQueue<Edge> minHeap=getMinHeapPriorityQueue();

		for(Edge e : adjList)
		{
			minHeap.add(e);

		}
		
		while(minHeap.peek()!=null)
		{
			Edge e1 = minHeap.poll();
			if(!B[(Integer) e1.v])
			{
				A.add(e1);
				B[(Integer) e1.v]=true;

				ArrayList<Edge> outEdges = graph.getOutEdges((Integer) e1.v);
				
				for(Edge ed : adjList)
				{
					minHeap.add(ed);
				}
			}
		}
	}



}
