package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Path {

	private ArrayList<Node> nodes;
	private ArrayList<Arc> simplePath;
	private ArrayList<Node> pathNodes;
	private HashMap<Node, Arc> checkedNodes;
	private Node s;
	private Node t;
	
	public Path(Node s, Node t, ArrayList<Node> nodes) {
		this.nodes = nodes;
		simplePath = new ArrayList<Arc>();
		this.s = s;
		this.t = t;
		pathNodes = new ArrayList<Node>();
		pathNodes.add(s);
		checkedNodes = new HashMap<Node, Arc>();

	}

	public ArrayList<Arc> getPath() {
		return simplePath;
	}

	private Node nodesContainsNode(int i) {
		Iterator<Node> itr = nodes.iterator();
		Node a;
		while (itr.hasNext()) {
			a = itr.next();
			if (a.getIndex() == i) {
				return a;
			}
		}
		return null;
	}

	public boolean createSimplePath(ArrayList<Arc> resGraph) {
		LinkedList<Node> nextLayer = new LinkedList<Node>();
		LinkedList<Arc> currentArcs = new LinkedList<Arc>();
		nextLayer.add(s);
		currentArcs.add(null);
		while (!nextLayer.isEmpty() && checkedNodes.get(t) == null) {

			Node tmp = nextLayer.pop();
			Arc arc = currentArcs.pop();
			if (checkedNodes.get(tmp) == null) {

				checkedNodes.put(tmp, arc);
				ArrayList<Arc> edges = tmp.getEdges();

				for (Arc edge : edges) {
					if (edge.hasFlowAvailible()) {
						nextLayer.push(nodesContainsNode(edge.getV()));
						currentArcs.push(edge);
					}

				}
			}

		}

		Arc n2 = checkedNodes.get(t);
		while (n2 != null && nodesContainsNode(n2.getU()) != s) {
			simplePath.add(n2);
			if (n2.hasFlowAvailible()) {
				n2 = checkedNodes.get(nodesContainsNode(n2.getU()));
			} else {
				n2 = checkedNodes.get(nodesContainsNode(n2.getV()));
			}
		}
		if (n2 != null && nodesContainsNode(n2.getU()) == s) {
			simplePath.add(n2);
		}
		if (simplePath.size() == 0) {
			return false;
		}
		return true;

	}

	public int bottleneck() {
		Iterator<Arc> itr = simplePath.iterator();
		if (itr.hasNext()) {
			Arc b = itr.next(); // b = bottleneck
			Arc tmp;
			while (itr.hasNext()) {
				tmp = itr.next();
				if ((tmp.flowLeft() < b.flowLeft() || b.capacity() == -1)
						&& tmp.capacity() != -1) {
					b = tmp;

				}
			}
			return b.flowLeft();
		}
		return 0;
	}

}
