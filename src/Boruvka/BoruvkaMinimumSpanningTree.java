/**
 * 
 */
package Boruvka;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Dinesh Appavoo
 *
 */
public class BoruvkaMinimumSpanningTree {

	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;
	
	public static boolean[] B=null;
	public static ArrayList<Edge> A=new ArrayList<Edge>();

	
	public static void main(String[] args) {
		
		BoruvkaMinimumSpanningTree BoruvkaMST=new BoruvkaMinimumSpanningTree();
		int mstWeight=BoruvkaMST.getMSTWeight();
		System.out.println(mstWeight);
		ArrayList<Edge> primEdges=BoruvkaMST.getMST();
		
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
		findBoruvkaMST(1);
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
		findBoruvkaMST(1);
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
			
			B=new boolean[noOfVertices];

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
	 * Method to find MST using Boruvka algorithm
	 * @param source
	 * 
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
	 * 
	 */
	public void findBoruvkaMST(int source)
	{

		B[source]=true;
		ArrayList<Edge>[] adjacencyList = graph.getListOfAdjacencylist();
		PriorityQueue<Edge> minHeap=null;

		
		for(int i=1;i<=noOfVertices;i++)
		{
			ArrayList<Edge> outEdges = graph.getOutEdges(i);
			minHeap=getMinHeapPriorityQueue();
			for(Edge e : outEdges)
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
					break;
				}
			}
		}
	}
}
