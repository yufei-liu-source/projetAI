package algo1_Astar;

public class Vertex {
	public int id;
	boolean inVisited = false;
	
	public Vertex parent;
	public int hCost;
	public int gCost;
	public int fTotal;
	
	public Vertex(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		switch(id) {
		case 0:return "A";
		case 1:return "B";
		case 2:return "C";
		case 3:return "D";
		case 4:return "E";
		}
		return "Erreur!";
	}
}
