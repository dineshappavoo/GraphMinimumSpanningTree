/**
 * 
 */
package kruskalA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Dinesh Appavoo
 *
 */
public class KruskalAMinimumSpanningTree {

	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;

	public static ArrayList<Integer> minimumWeight=new ArrayList<Integer>();
	public static boolean[] mstNodes=null;
	public static ArrayList<Edge> minWeightEdges=new ArrayList<Edge>();

	/**
	 * Main method for testing
	 * @param args
	 */
	public static void main(String[] args) {
		
		KruskalAMinimumSpanningTree kruskalAMST=new KruskalAMinimumSpanningTree();
		int mstWeight=kruskalAMST.getMSTWeight();
		System.out.println(mstWeight);
		ArrayList<Edge> primEdges=kruskalAMST.getMST();
		
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
		findKruskalAMST(1);
		return minWeightEdges;
	}
	
	/**
	 * 
	 * Method to return MST total weight
	 * @return
	 */
	public int getMSTWeight()
	{
		constructGraph();		
		findKruskalAMST(1);
		int totalWeight=0;
		for(Edge e : minWeightEdges)
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
	 * Method to find MST using Kruskal A algorithm
	 * @param source
	 * 
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
	 * 
	 */
	
	
	public void findKruskalAMST(int source)
	{

		mstNodes[source]=true;
		ArrayList<Edge>[] adjacencyList = graph.getListOfAdjacencylist();
		PriorityQueue<Edge> minHeap=getMinHeapPriorityQueue();


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
			if(!mstNodes[(Integer) e1.v])
			{
				minWeightEdges.add(e1);
				mstNodes[(Integer) e1.v]=true;
			}
		}
	}

}
