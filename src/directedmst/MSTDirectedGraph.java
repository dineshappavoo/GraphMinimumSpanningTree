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
public class MSTDirectedGraph {

	public static int noOfVertices=0;
	public static int noOfEdges=0;
	public static int startNode=1;
	public static Graph graph=null;
	public static boolean[] visited=null;
	
	public static Graph minInEdgeGraph=null;
	public static Graph minOutEdgeGraph=null;
	public static ArrayList<ArrayList<Integer>> cycleList=null;
	public static ArrayList<Integer> cycle=null;
	
	public static ArrayList<Edge> edgesEnteringcycle=null;
	public static ArrayList<Edge> edgesPresentIncycle=null;
	public static void main(String[] args) {

		
		new MSTDirectedGraph().directedMSTEdmondImpl("/users/dany/downloads/suma.txt");
		}
	
	public void constructGraph(String inFile)
	{
		File infile=new File(inFile);
		int u,v,w;
		try {
			Scanner scanner=new Scanner(infile);
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();
			graph=new Graph(noOfVertices);
			for(int i=0;i<noOfEdges;i++)
			{
			u=scanner.nextInt();
			v=scanner.nextInt();
			w=scanner.nextInt();
			graph.addEdge(u,v,w);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void directedMSTEdmondImpl(String inFile)
	{
		constructGraph(inFile);
		visited=new boolean[noOfVertices+1];
		//graph.printGraph();
		getInMinEdges(graph);
		minInEdgeGraph.printGraph();
		cycleList=new ArrayList<ArrayList<Integer>>();
		cycle=new ArrayList<Integer>();
		FindAndAddCycles();
		gatherEdgesEnterAndPresentInCycle();
		//modifyPseudoEdgeInCycle();
		minInEdgeGraph.printGraph();
		System.out.println("Total Cycles : "+cycleList.size());
		System.out.println("Total Weight : "+minInEdgeGraph.finTotalWeight(noOfVertices));
		
	}
	
	public void FindAndAddCycles()
	{
		ArrayList<Edge> minOutEdges=minInEdgeGraph.getOutEdges(1);
		if(minOutEdges!=null)
		{
		for(Edge e : minOutEdges)
		{
		if(!visited[e.v])
		{
		cycleExistCheck(minInEdgeGraph, e.v,e.v);
		cycleList.add(cycle);
		cycle=new ArrayList<Integer>();
		}
		}
		}
		

		/*cycleExistCheck(minInEdgeGraph, 1,1);
		cycleList.add(cycle);
		cycle=new ArrayList<Integer>();*/

		for(int i=2;i<=noOfVertices;i++)//may be 2 . verify
		{
			//ArrayList<Edge>
			minOutEdges=minInEdgeGraph.getOutEdges(i);
			if(minOutEdges!=null)
			{
			for(Edge e : minOutEdges)
			{
			if(!visited[e.v])
			{
			cycleExistCheck(minInEdgeGraph, e.v,e.v);
			cycleList.add(cycle);
			cycle=new ArrayList<Integer>();
			}
			}
			}
		}
	}
	public void cycleExistCheck(Graph minInEdgeGraph, int cycleIterateNode, int cycleBeginNode)
	{
		visited[cycleIterateNode]=true;
		cycle.add(cycleIterateNode);
		ArrayList<Edge> adList=minInEdgeGraph.getOutEdges(cycleIterateNode);
		if(adList==null)
			return;
		for(Edge e: adList)
		{
			if(!visited[e.v])
			{
				cycleExistCheck(minInEdgeGraph, e.v, cycleBeginNode);
			}/*else if(e.v==cycleBeginNode)
			{
				//cycle.add(e.v);
				cycleList.add(cycle);
			}*/
		}

	}
	
	public void gatherEdgesEnterAndPresentInCycle()
	{
		
		for(ArrayList<Integer> cycle:cycleList)
		{
			/*if(!(cycle.contains(1)))//startNode. There should not be any incoming nodes to start Node
			{*/

			edgesEnteringcycle=new ArrayList<Edge>();
			edgesPresentIncycle=new ArrayList<Edge>();
		for(int n:cycle)
		{
			
			ArrayList<Edge> inEdges=graph.getInEdges(n, noOfVertices);
			for(Edge e : inEdges)
			{
				//if()
				Edge o=minInEdgeGraph.getInEdges(n, noOfVertices).get(0);//doubt check for 0 index
				//System.out.println("in edge value : "+o.u);
				if(!(e.u==o.u&&e.v==o.v&&e.w==o.w))
					edgesEnteringcycle.add(e);
			}
			edgesPresentIncycle.addAll(minInEdgeGraph.getInEdges(n, noOfVertices));
			
		}
		modifyPseudoEdgeInCycle();
			//}
		} 
	}
	
	public void modifyPseudoEdgeInCycle()
	{
			int modifiedCost;
			int minEdgeCostPresentInCycle=Integer.MAX_VALUE;
			Edge edgeToBeRemoved=null;
			Edge minAdjustedEnteringEdge=null;
			int minModifiedEnteringEdgeCost=Integer.MAX_VALUE;
			
			for(Edge e : edgesPresentIncycle)
			{
				if(e.w<minEdgeCostPresentInCycle)
					minEdgeCostPresentInCycle=e.w;
			}
			
		
			for(Edge oe : edgesEnteringcycle)
			{
				for(Edge ie : edgesPresentIncycle)
				{
					if(oe.v==ie.v)
					{
						modifiedCost=oe.w-(ie.w-minEdgeCostPresentInCycle);
						if(modifiedCost<=minModifiedEnteringEdgeCost)
						{
							minModifiedEnteringEdgeCost=modifiedCost;
							edgeToBeRemoved=ie;
							minAdjustedEnteringEdge=oe;
							
						}
						break;// 
					}
				}
			}
			if((edgeToBeRemoved!=null)&&(minAdjustedEnteringEdge!=null))
			{
			minInEdgeGraph.removeEdge(edgeToBeRemoved.u, edgeToBeRemoved.v);
			minInEdgeGraph.addEdge(minAdjustedEnteringEdge.u, minAdjustedEnteringEdge.v, minAdjustedEnteringEdge.w);
			}
	
		
	}
	
	public void compareMinInEdgeCycleAndEdgeOutCycleNode(int nodeId)
	{
		ArrayList<Edge> adInList=minInEdgeGraph.getInEdges(nodeId, noOfVertices);
		Edge e=adInList.get(0);// There will be only one min Edge for every vertex in the minInEdgeGraph
		System.out.println("kfskfjshdfdsfsd fsgs    u  :"+e.u+" v  :"+e.v+" w  :"+e.w);
	}
	
	public void getInMinEdges(Graph graph)
	{
		Edge minEdge=null;
		minInEdgeGraph=new Graph(noOfVertices);
		for(int i=2;i<=noOfVertices;i++)// find min edges except start node. so i=2
		{
			ArrayList<Edge> inEdges=graph.getInEdges(i, noOfVertices);
			for(Edge e:inEdges)
			{
				if(minEdge==null)
					minEdge=e;
				else if(minEdge.w>e.w)
					minEdge=e;
			}
			if(minEdge!=null)
			minInEdgeGraph.addEdge(minEdge.u, minEdge.v, minEdge.w);
			//minOutEdgeMap.put(minEdge.u, minEdge);
			minEdge=null;
			
			
		}
	}


}
