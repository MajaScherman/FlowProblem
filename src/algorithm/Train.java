package algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Train {

	public static void main(String[] args) {
		/*
		 * Here we read in the file, first the number of nodes and then put all
		 * of the nodes in a list. The nodes are just a name at a position in
		 * the list.
		 * 
		 * Then we read in the Arcs. A arc is a way from node u to v with a
		 * capacity c. The arc is not directed.
		 */
		Scanner in = null;
		try {
			in = new Scanner(new File("rail.txt"));// (args[0]); or (System.in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// (System.in);

		int nbrNodes;
		nbrNodes = in.nextInt();
		in.nextLine();
		ArrayList<String> strings = new ArrayList<String>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < nbrNodes; i++) {
			strings.add(in.nextLine());
			nodes.add(new Node(i));
		}
		int nbrArcs = in.nextInt();
		String linebreak = in.nextLine();
		ArrayList<Arc> resGraph = new ArrayList<Arc>();
		int a, b, c;
		for (int i = 0; i < nbrArcs; i++) {
			a = in.nextInt();// u
			b = in.nextInt();// v
			c = in.nextInt();// c
			resGraph.add(new Arc(a, b, c, true));// posetive edge
			resGraph.add(new Arc(b, a, c, false)); // neg edge
		}
		// find edges from source
		for (int cur = 0; cur < nbrNodes; cur++) {
			for (int i = 0; i < nbrArcs * 2; i++) {
				// if there is a path from current to i, add the arc to edges
				if (resGraph.get(i).hasU(cur)) {// if the arc (u,v) have cur = u
												// add edge i
					nodes.get(cur).addEdge(resGraph.get(i));
				}
			}
		}

		/*
		 * Here the algorithm starts. Finding maximum flow through the min cut
		 * source node s - ORIGIN sink node t - DESTINATIONS
		 */

		boolean hasPath = true;
		while (hasPath) {
			// Do BFS in respect to flow from s-t
			Path path = new Path(nodes.get(0), nodes.get(nbrNodes - 1), nodes); // Path
																				// (s,t)
			hasPath = path.createSimplePath(resGraph); // P(G,f) men flow är en
														// del i resGraph
			ArrayList<Arc> simplePath = path.getPath();
			for (Arc cs : simplePath) {
				// System.out.println(cs.toStringExtra());
			}
			int bottleneck = path.bottleneck();
			// System.out.println("bottleneck: " + bottleneck);

			Iterator<Arc> itr = simplePath.iterator();
			Arc edge;
			while (itr.hasNext()) {
				edge = itr.next();

				int rk = findIndexInResGraph(edge, resGraph);
				if (edge.isForward()) {
					resGraph.get(rk).addFlow(bottleneck);
					resGraph.get(rk + 1).addFlow(-bottleneck);
				} else {
					resGraph.get(rk).addFlow(bottleneck);
					resGraph.get(rk - 1).addFlow(-bottleneck);
				}
			}
		}

		Output out = new Output(resGraph, nodes);
		out.getMinCut();
		System.out.println("Max flow is:" + out.getMaxFlow());

	}

	private static int findIndexInResGraph(Arc arc, ArrayList<Arc> graph) {
		int i = 0;
		Iterator<Arc> itr = graph.iterator();
		Arc tmp;
		while (itr.hasNext()) {
			tmp = itr.next();
			if (arc.sameArc(tmp)) {
				return i;
			}
			i++;
		}
		return -1;
	}

}
