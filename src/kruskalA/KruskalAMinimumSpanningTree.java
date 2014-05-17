/**
 * 
 */
package kruskalA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Dany
 *
 */
public class KruskalAMinimumSpanningTree {

	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;

	public static ArrayList<Integer> minimumWeight=new ArrayList<Integer>();
	public static boolean[] mstNodes=null;
	public static ArrayList<Edge> minWeightEdges=new ArrayList<Edge>();

	public static void main(String[] args) {
		
		KruskalAMinimumSpanningTree kruskalAMST=new KruskalAMinimumSpanningTree();
		int mstWeight=kruskalAMST.getKruskalAMinimumWeight();
		System.out.println(mstWeight);
		ArrayList<Edge> primEdges=kruskalAMST.getKruskalAMST();
		
		for(Edge e : primEdges)
		{
			System.out.println("u : "+e.u+" v : "+e.v+" w : "+e.w);
		}

	}
	
	public ArrayList<Edge> getKruskalAMST()
	{
		constructGraph();
		findKruskalAMST(1);
		return minWeightEdges;
	}
	
	public int getKruskalAMinimumWeight()
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
