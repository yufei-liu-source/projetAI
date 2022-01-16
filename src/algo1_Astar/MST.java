package algo1_Astar;

//The program is for adjacency matrix representation of the graph

public class MST {
	// Number of vertices in the graph
	static final int V = 5;
	
	// Array to store constructed MST
	int parent[] = new int[V];
	
	// Key values used to pick minimum weight edge in cut
	int key[] = new int[V];
	
	// To represent set of vertices included in MST
	Boolean mstSet[] = new Boolean[V];

 
	// function to find the vertex with minimum key value, from the set of vertices not yet included in MST
	int minKey(int key[], Boolean mstSet[]){ 
		// Initialize minimum value     
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v < V; v++)  
			if (mstSet[v] == false && key[v] < min) {       
				min = key[v];      
				min_index = v;
			}
		return min_index;
	}

	// function to print the constructed MST stored in parent[]
	void printMST(int parent[], int graph[][]){
		System.out.println("The Minimum Spanning Tree : ");
		System.out.println("Edge \tWeight");
		for (int i = 1; i < V; i++) 
			System.out.println(parent[i] + " - " + i + "\t" + key[i]);
		System.out.println("----------------------------");
	}

	// Function to construct and print MST for a graph represented
	// using adjacency matrix representation
	void primMST(int graph[][]){   
		// Initialize all keys as INFINITE  
		for (int i = 0; i < V; i++) {     
			key[i] = Integer.MAX_VALUE;      
			mstSet[i] = false;
		}
		
		// Always include first 1st vertex in MST.
		key[0] = 0; // Make key 0 so that this vertex is
		// picked as first vertex
		parent[0] = -1; // First node is always root of MST
		// The MST will have V vertices
		
		for (int count = 0; count < V - 1; count++) {
			
			// Pick the minimum key vertex from the set of vertices not yet included in MST
			int u = minKey(key, mstSet);
			
			// Add the picked vertex to the MST Set
			mstSet[u] = true;
			
			// Update key value and parent index of the adjacent vertices of the picked vertex. 
			// Consider only those vertices which are not yet included in MST
			for (int v = 0; v < V; v++) {
				
				/***
				 *  graph[u][v] is non zero only for adjacent vertices of m
				 *  mstSet[v] is false for vertices not yet included in MST
				 *  Update the key only if graph[u][v] is smaller than key[v]       
				 */
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {              
					parent[v] = u;              
					key[v] = graph[u][v];         
				}	
			}
		}
		// print the constructed MST
		printMST(parent, graph);
	}

	public int sommeMST() {
		int somme = 0;
		for(int i = 0; i<V; i++) {
			somme += key[i]; 
		}
		return somme;	
	}

	public int[] getKey() {
		return key;
	}

	public int[] getParent() {
		return parent;
	}
	
	//Matrix store the heuristic of every vertex
	static int hCost[] = new int[] {0,0,0,0,0};
	
	boolean AlreadyRunPrim() {
		int checkerP = 0, checkerK = 0;
		for(int j = 0; j < V; j++) {
			checkerP += parent[j];
			checkerK += key[j];
		}
		
		if(checkerP <= 0 || checkerK <= 0) 		
			return false;
		return true;
	}
	
	void writeInHcost() {
		if(this.AlreadyRunPrim()) {
			for(int i = 0; i < V; i++) {
				if(parent[i]<0) {
					hCost[i] += key[i];
				}else {
					if(parent[parent[i]]<0) {
						hCost[i] += key[parent[i]] + key[i];				
					}else {
						if(parent[parent[parent[i]]]<0) {	
							hCost[i] += key[parent[parent[i]]] + key[parent[i]] + key[i];	
						}else {
							if(parent[parent[parent[parent[i]]]]<0) {
								hCost[i] += key[parent[parent[parent[i]]]] + key[parent[parent[i]]] + key[parent[i]] + key[i];
							}
						}
					}		
				}
			}
		}else System.out.println("Please run Prim Algorithme first before you write in hCost!");
	}
	
	void showHcost() {
		for(int i = 0; i < V; i++) {
			System.out.println(hCost[i]);
		}
	}

	
	int heuristic[][] = new int[V][V];
	void writeInHeuristic(){
		if(this.AlreadyRunPrim()) {
			for(int i = 0; i < V; i++) {
				if(parent[i] >= 0) {
					heuristic[i][parent[i]] = key[i];
					heuristic[parent[i]][i] = key[i];
				}
			}
		}else System.out.println("Please run Prim Algorithme first before you write in heuristic matrix!");
	}
	
	void showHeuristic() {
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				System.out.println(heuristic[i][j]);
			}
			System.out.println("\n");
		}
	}
}