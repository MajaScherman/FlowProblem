import java.lang.*;
public class Arc{
	private int u;
	private int v;
	private int c;
	private int flow;
	private boolean forwardEdge;
	
	public Arc(int u, int v, int c, boolean p){
		this.u = u;
		this.v = v;
		this.c = c;
		flow = 0;
		forwardEdge = p;
		
	}
public int getFlow(){
	return flow;
}
	public boolean hasFlowAvailible(){
		if(0 < c - flow || c == -1){
			//System.out.println(toStringFlow() + "c-flow: " + (c - flow) +" c :" + c + " flow: "+ flow);
			return true;
		}else{
			return false;
		}
	}
	public boolean hasU(int uu){
		if(u==uu){
			return true;
		}
		return false;
	}
	
	public boolean isForward(){ 
		return forwardEdge;
	}

	public String toString(){
		return u + " "+ v + " " + c;
	}
	public String toStringFlow(){
		return u + " "+ v + " " + c + " " + flow;
	}
	public String toStringExtra(){
		return u + " "+ v + " " + c + " " + flow + " " + forwardEdge;
	}


	public int addFlow(int f){
//obs f<|c|	
		if(Math.abs(flow + f) <= c || c==-1){
			//System.out.println("addFlow: " + flow);
			flow = flow + f;
			//System.out.println("flow now: " + flow);
		}
		return flow;
	}
	
	public int capacity(){
		return c;
	}
	
	public int flowLeft(){
		return c - flow;
	}
	public boolean containsNodes(Node node1,Node node2){
		int i = node1.getIndex();
		int ii = node2.getIndex();
		if((u==i && v == ii) ||(v==i && u == ii)){
			return true;
		}
		return false;
	}

	public boolean checkNodes(int uu, int vv){
		if(u==uu && v==vv){
			return true;
		}
		return false;
	}
	public boolean sameNodesInArc(Arc arc){
		if(arc.getV() == v && arc.getU() == u ){
			return true;
		}else if(arc.getV() == u && arc.getU() == v){
			//System.out.println("vill hit BAKATARC!!!");
			return true;
		}
		return false;
	}
	public boolean findEdges(int vv){
		if(v==vv || vv==u){
			return true;
		}
		return false;
	}

	public boolean sameArc(Arc arc){
		if(arc.getV() == v && arc.getU() == u ){
			return true;
		}
		return false;
	}
	public int getV(){
		return v;
	}
	public int getU(){
		return u;
	}
	
}
