package algo1_Astar;

public class Vertex {
	public int id;
	boolean inVisited = false;
	
	public Vertex parent;
	public int hCost;
	public int gCost;
	public int fTotal = hCost + gCost;
	
	public void calcF() {
		this.fTotal = this.gCost + this.hCost;
	}
	public Vertex(int id) {
		this.id = id;
	}
}
