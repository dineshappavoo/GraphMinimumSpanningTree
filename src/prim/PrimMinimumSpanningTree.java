/**
 * 
 */
package prim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * @author Dany
 *
 */
public class PrimMinimumSpanningTree {

	public static int noOfVertices,noOfEdges;
	public static Graph<Integer> graph=null;
	
	public static ArrayList<Integer> minimumWeight=new ArrayList<Integer>();
	public static boolean[] mstNodes=null;
	ArrayList<Edge> minWeightEdges=new ArrayList<Edge>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	
	public PriorityQueue<Integer> getMaxHeapPriorityQueue()
	{
		 PriorityQueue<Integer> queue = new PriorityQueue<Integer>(11, new Comparator<Integer>()
		 {
			 public int compare(Integer o1, Integer	 o2)
			 {
				 int p1=o1;
				 int p2=o2;
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
		PriorityQueue<Edge> minHeap=
		for(Edge e:adjList)
		{
			if(!mstNodes[e.v])
			{
				mstNodes
			}
			
			
		}
	}
	
	

}
