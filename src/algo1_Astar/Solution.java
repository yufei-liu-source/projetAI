package algo1_Astar;

import java.util.ArrayList;

public class Solution {

	private ArrayList<Vertex> frontier = new ArrayList<Vertex>();
	private ArrayList<Vertex> explored = new ArrayList<Vertex>();
	private int graph[][];
	
	public Solution(int[][] graph) {
		super();
		this.graph = graph;
		MST t = new MST();
		// Print the solution				
		t.primMST(graph);

		t.writeInHcost();		

	}

	public Vertex findMinVertexInFrontier() {
		// Initialize tmp with the first vertex of frontier and search for the minimum
		Vertex tmp = frontier.get(0); 
		for (Vertex node : frontier) {
			if (node.fTotal < tmp.fTotal) {
				tmp = node;
			}
		}
		return tmp;
	}
	
	public ArrayList<Vertex> findNeighborNodes(Vertex currentVertex) {
		ArrayList<Vertex> arrayList = new ArrayList<Vertex>();
		for(int i = 0; i < MST.V; i++) {	
			if(graph[currentVertex.id][i] != 0) {
				Vertex tmp = new Vertex(i);
				arrayList.add(tmp);
			}
		}
		return arrayList;
	}
	
	public static boolean exists(ArrayList<Vertex> vs, Vertex v) {
		for (Vertex v1 : vs) {
			if (v1.id == v.id) {
				return true;
			}
		}
		return false;
	}
	
	public void calcH(Vertex v) {
		v.hCost = MST.hCost[v.id];
	}
	
	//calculate the gCost of a vertex in recursive method
	public void calcG(Vertex v) {
		if (v.parent != null) {
			calcG(v.parent); 
			v.gCost = v.parent.gCost + graph[v.parent.id][v.id];
		}else
			v.gCost = 0;
	}
	
	public void calcF(Vertex v) {
		v.fTotal = v.gCost + v.hCost;
	}
	
	public void findPath(Vertex v) {
		frontier.add(v);
		
		while(frontier.size()>0) {
			Vertex currentVertex = findMinVertexInFrontier();
			
			frontier.remove(currentVertex);
			explored.add(currentVertex);
			ArrayList<Vertex> neighborNodes = findNeighborNodes(currentVertex);
			
			for(Vertex vertex : neighborNodes) {
				if(exists(explored,vertex)) {
					continue;
				}else {
					if (exists(frontier, vertex)) {		
						vertex.parent = currentVertex;
						calcG(vertex);
						calcH(vertex);	
						calcF(vertex);
					}else {
						vertex.parent = currentVertex;
						calcG(vertex);
						calcH(vertex);	
						calcF(vertex);
						frontier.add(vertex);
					}
				}
			}
		}
		
		explored.add(v);
	}
	
	public void showPath() {
		System.out.println("Here is the optimal path of traveller : ");
		for(Vertex v: explored) {
			System.out.println(v);
		}
	}
	
	public static void main(String[] args) {
		
		/***
	  * The graph is represented as follow:
	  * A <-- 0
	  * B <-- 1
	  * C <-- 2
	  * D <-- 3
	  * E <-- 4
	  */
		//The table of weights between all the vertex
		int inputGraph[][] = new int[][] { 
			{0, 21, 9, 21, 1},
			{21, 0, 4, 8, 20},  
			{9, 4, 0, 6, 7},  
			{21, 8, 6, 0, 11},        
			{1, 20, 7, 11, 0}};
			
			Vertex vA = new Vertex(0);
			
			Solution s = new Solution(inputGraph);
			s.findPath(vA);
			s.showPath();
			
	}
}
