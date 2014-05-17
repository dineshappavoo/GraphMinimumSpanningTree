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
 */
public class PrimMinimumSpanningTree {

	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;

	public static ArrayList<Integer> minimumWeight=new ArrayList<Integer>();
	public static boolean[] mstNodes=null;
	ArrayList<Edge> minWeightEdges=new ArrayList<Edge>();

	public static void main(String[] args) {

		PrimMinimumSpanningTree primMST=new PrimMinimumSpanningTree();
		int mstWeight=primMST.getPrimMinimumWeight();
		System.out.println(mstWeight);
		ArrayList<Edge> primEdges=primMST.getPrimMST();
		
		for(Edge e : primEdges)
		{
			System.out.println("u : "+e.u+" v : "+e.v+" w : "+e.w);
		}
	}

	public ArrayList<Edge> getPrimMST()
	{
		constructGraph();
		findPrimMST(1);
		return minWeightEdges;
	}
	
	public int getPrimMinimumWeight()
	{
		constructGraph();		
		findPrimMST(1);
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

			mstNodes=new boolean[noOfVertices+1];

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

	public PriorityQueue<Edge> getMaxHeapPriorityQueue()
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

	public void findPrimMST(int startNode)
	{
		mstNodes[startNode]=true;
		ArrayList<Edge> adjList=graph.getOutEdges(startNode);
		PriorityQueue<Edge> minHeap=getMaxHeapPriorityQueue();


		for(Edge e : adjList)
		{
			minHeap.add(e);

		}
		
		while(minHeap.peek()!=null)
		{
			Edge e1 = minHeap.poll();
			if(!mstNodes[(Integer) e1.v])
			{
				minWeightEdges.add(e1);
				ArrayList<Edge> outEdges = graph.getOutEdges((Integer) e1.v);
				
				for(Edge ed : adjList)
				{
					minHeap.add(ed);
				}
			}
		}
	}



}
