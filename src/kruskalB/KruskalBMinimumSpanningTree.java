/**
 * 
 */
package kruskalB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import kruskalA.Edge;
import kruskalA.KruskalAMinimumSpanningTree;


/**
 * @author Dinesh Appavoo
 *
 */
public class KruskalBMinimumSpanningTree {
	
	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;
	
	public static boolean[] B=null;
	public static ArrayList<Edge> A=new ArrayList<Edge>();

	
	public static void main(String[] args) {

		KruskalBMinimumSpanningTree kruskalBMST=new KruskalBMinimumSpanningTree();
		int mstWeight=kruskalBMST.getMSTWeight();
		System.out.println(mstWeight);
		ArrayList<Edge> primEdges=kruskalBMST.getMST();
		
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
		findKruskalBMST(1);
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
		findKruskalBMST(1);
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
	
	public PriorityQueue<Edge> getMaxHeapPriorityQueue()
	{
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>(11, new Comparator<Edge>()
				{
			public int compare(Edge o1, Edge o2)
			{
				int p1=(Integer) o1.w;
				int p2=(Integer) o2.w;
				return (p1-p2); 
			}
				}
				);
		return queue;
	}
	
	/**
	 * Method to find MST using Kruskal B algorithm
	 * @param source
	 * 
	 * MST_KRUSKAL_B(G,w,source)
	 *  A <- Empty
	 *  B[source] <- true
	 *  for each edge e in E
	 *      PQ <- PQ U e
	 *  While (PQ.peek()!=null)
	 *  	m<-PQ.poll()
	 *      if(!B[m.v])
	 *       A <- A U m
	 *       B[m.v] <- true
	 * 
	 */	
	public void findKruskalBMST(int source)
	{

		B[source]=true;
		ArrayList<Edge>[] adjacencyList = graph.getListOfAdjacencylist();
		PriorityQueue<Edge> minHeap=getMaxHeapPriorityQueue();


		ArrayList<Edge> edgeList;
		for(int i=1;i<=noOfVertices;i++)
		{
			edgeList=adjacencyList[i];
			if(edgeList!=null)
			{
				for(Edge e : edgeList)
				{
					minHeap.add(e);
				}
			}
		}

		while(minHeap.peek()!=null)
		{
			Edge e1 = minHeap.poll();
			if(!B[(Integer) e1.v])
			{
				A.add(e1);
				B[(Integer) e1.v]=true;
			}
		}
	}
	
	public boolean isSafeToRemove(int vertex)
	{
		boolean isSafe=false;
		
		ArrayList<Edge> outEdges=graph.getOutEdges(vertex);
		
		
		
		return isSafe;
	}

}
