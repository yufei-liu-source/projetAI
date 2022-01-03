package algo1_Astar;

import java.util.ArrayList;

public class Solution {

	private ArrayList<Vertex> frontier = new ArrayList<Vertex>();
	private ArrayList<Vertex> explored = new ArrayList<Vertex>();
	
	int graph[][] = new int[][] { 
		{0, 21, 9, 21, 1},
		{21, 0, 4, 8, 20},  
		{9, 4, 0, 6, 7},  
		{21, 8, 6, 0, 11},        
		{1, 20, 7, 11, 0}};
		
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
		for(int i = 0; i < 5; i++) {	
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
	
	public int calcH(Vertex v) {
		return MST.hCost[v.id];
	}
	
	public int calcG(Vertex v) {
		if (v.parent != null) {
			return v.parent.gCost + graph[v.parent.id][v.id];
		}else
			return 0;
	}
	
	public void findPath(Vertex v) {
		frontier.add(v);
		
		while(frontier.size()>0) {
			Vertex currentVertex = findMinVertexInFrontier();
			
			frontier.remove(currentVertex);
			explored.add(currentVertex);
			ArrayList<Vertex> neighborNodes = findNeighborNodes(currentVertex);
			
			for(Vertex vertex : neighborNodes) {
				if (exists(frontier, vertex)) {
					int G = calcG(vertex);
					if (G < vertex.gCost) {
						vertex.parent = currentVertex;
						vertex.gCost = G;
						vertex.calcF();
					}
				}else {
					vertex.parent = currentVertex;
					vertex.gCost = calcG(vertex);
					vertex.hCost = calcH(vertex);
					vertex.calcF();
					frontier.add(vertex);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
