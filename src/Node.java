import java.util.*;
public class Node{
	private ArrayList<Arc> edges;
	private int index;
	//private int flow;

	public Node(int index){
		this.index = index;
		edges = new ArrayList<Arc>();
		
	}

	public String toString(){
		return "" + index;
	}
	public Arc getEdge(int i){
		return edges.get(i);
	}
	public ArrayList<Arc> getEdges(){
		return edges;
	}

	public void addEdge(Arc e){
		edges.add(e);	
	}
	public int getIndex(){
		return index;
	}

	public int getEdgesSize(){
		return edges.size();
	}
	public boolean equals(Node n){
		if(n.getIndex() == index){
			return true;
		}
		return false;
	}

	public Arc bottleneck(){
	Iterator<Arc> itr = edges.iterator();
	Arc min = null;
	Arc now = null;
	if(itr.hasNext()){
		min=itr.next();
	}
	while(itr.hasNext()){
		now = itr.next();
		if(min.capacity() > now.capacity() && now.capacity()!=-1){
			min = now;
		}
	}
	return min;
	}


}
