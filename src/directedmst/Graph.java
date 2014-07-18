/**
 * 
 */
package directedmst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Dinesh Appavoo
 *
 */
public class Graph {

	
	ArrayList<Edge>[] adjList;
	//Constructor to create graph class by passing no of vertices and add values to graph in the calling class
	public Graph(int noOfVertice)
	{
		this.adjList=(ArrayList<Edge>[])new ArrayList[noOfVertice+1];
	}
	//Constructor to create and add values to the graph by passing the input file
	public Graph(String inFile)
	{
		this.constructGraph(inFile);
	}

	public void addEdge(int u, int v, int w)
	{
		if(adjList[u]==null)
		adjList[u]=new ArrayList<Edge>();
		Edge edge = new Edge(u, v, w);
		adjList[u].add(edge);
	}
	
	public void removeEdge(int u, int v)
	{
		int indexTobeRemoved=-1;
		for(int i=0;i<adjList[u].size();i++)
		{
			if(v==adjList[u].get(i).v)
				indexTobeRemoved=i;
		}
		if(indexTobeRemoved!=-1)
		{
			adjList[u].remove(indexTobeRemoved);
		}
	}
	
	public boolean hasEdge(int u, Edge e)
	{
		return adjList[u].contains(e);
	}
	
	public ArrayList<Edge> getOutEdges(int u)
	{
		return adjList[u];
	}
	public ArrayList<Edge> getInEdges(int v, int noOfVertice)
	{
		ArrayList<Edge> inEdges=new ArrayList<Edge>();
		for(int i=1;i<=noOfVertice;i++)
		{
			ArrayList<Edge> adjList=getOutEdges(i);
			if(adjList!=null)
			{
			for(Edge e: adjList)
			{
				if(e.v==v)
					inEdges.add(e);
					
			}
			}
			
		}
		return inEdges;
	}
	
	public int findWeightWithUandV(int u , int v)
	{
		ArrayList<Edge> outList=adjList[u];
		for(Edge e : outList)
		{
			if(e.v==v)
			{
				return e.w;
			}
		}
		return -1;
	}
	
	public int finTotalWeight(int noOfVertice)
	{
		//int noOfVertice=adjList.length;
		int totalWeight=0;
		
		for(int i=1;i<=noOfVertice;i++)
		{
			ArrayList<Edge> adj=adjList[i];
			if(adj!=null)
			{
			for(Edge e: adj)
			{
				totalWeight+=e.w;
					
			}
			}
			
		}
		return totalWeight;
	}
	
	public void constructGraph(String inFile)
	{
		File infile=new File(inFile);
		int noOfVertices=0;
		int noOfEdges=0;
		int arrLen=0;
		try {
			Scanner scanner=new Scanner(infile);
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();
			this.adjList=(ArrayList<Edge>[])new ArrayList[noOfVertices+1];
			for(int i=0;i<noOfEdges;i++)
			addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			//printGraph();
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printGraph()
	{
		int k=0;
		for(ArrayList<Edge> edgeList : adjList)
		{
			System.out.println("Level No :"+k++);
			if(edgeList!=null)
			{
			for(Edge e : edgeList)
			{
				System.out.println("u  :"+e.u+" v  :"+e.v+" w  :"+e.w);
			}
			}
		}
	}
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		File infile=new File("/users/dany/downloads/suma.txt");
		int noOfVertices=0;
		int noOfEdges=0;
		int arrLen=0;
		try {
			Scanner scanner=new Scanner(infile);
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();
			Graph graph=new Graph(noOfVertices);
			for(int i=0;i<noOfEdges;i++)
			graph.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			graph.printGraph();
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
*/
	public static void main(String[] args) {
		
		Graph graph=new Graph("/users/dany/downloads/suma.txt");
		graph.printGraph();
	}

}
