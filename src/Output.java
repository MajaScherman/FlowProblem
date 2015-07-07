import java.util.*;
public class Output{
	private ArrayList<Arc> resGraph;
	private ArrayList<Arc> minCut;
	private HashSet<Node> checked;
	private HashSet<Arc> checkedArcs;
	private ArrayList<Node> nodes;
	private Stack<Node> nextLayer;
	private Node s,t;
	private HashSet<Node> a,b;

	public Output(ArrayList<Arc> resGraph, ArrayList<Node> nodes){
		nextLayer=new Stack<Node>();		
		minCut = new ArrayList<Arc>();
		checked = new HashSet<Node>();
		checkedArcs = new HashSet<Arc>();
		this.resGraph = resGraph;
		this.nodes = nodes;
		s=nodes.get(0);
		t=nodes.get(nodes.size()-1);
		a = new HashSet<Node>();
		b = new HashSet<Node>();
	}
	private void addNodesToB(){
		for(Node n : nodes){
			b.add(n);
		}
		b.remove(s);
	}

	public ArrayList<Arc> getMinCut(){
		addNodesToB();
		a.add(s);
		nextLayer.push(s);
		while(nextLayer.size()!=0){
			Node node = nextLayer.pop();
			ArrayList<Arc> edges = node.getEdges();
			for(Arc edge : edges){
				Node v = nodesContains(edge.getV());
				Node u = nodesContains(edge.getU());
		
				if(b.contains(v) && a.contains(u)){ //forward edge
					if(edge.hasFlowAvailible()){
						b.remove(v);
						a.add(v);
						nextLayer.add(v);
					}
					
				}else if(a.contains(v) && b.contains(u)){//backward edge 
					if(edge.getFlow()>0){
						b.remove(u);
						a.add(u);
						nextLayer.add(u);
					}
				}
				
			}
		}
		findEdgesBetweenAB(resGraph);
		return minCut;
	}
	
	
	private ArrayList<Arc> findEdgesBetweenAB(ArrayList<Arc> arcs){
		for(Node n : a){
			for(Node nn :b){
				for(Arc arc : arcs){
					if(arc.containsNodes(n,nn) && (arc.getU() < arc.getV())){
						minCut.add(arc);
						System.out.println(arc.toStringFlow());
						//OUTPUTXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
					}
				}
			}
		}

		return minCut;
	}


	public int getMaxFlow(){
		int s=0;
		for(Arc e:minCut){
			s+=e.getFlow();
		}	
		return s;
	}



	private Node nodesContains(int i){
		Iterator<Node> itr = nodes.iterator();
		Node a;
		while(itr.hasNext()){
			a = itr.next();
			if(a.getIndex() == i){
				return a;
			}
		}
		return null;
	}	











}
