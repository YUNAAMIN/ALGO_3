package application;

import java.util.Comparator;

public class compareto implements Comparator<City> {// to compare between two cities
	private final Graph graph;// it associated with the graph that we will comparator

	public compareto() {// no argument comstructor
		this.graph = new Graph();
	}

	public compareto(Graph graph) {// argument comstructor with a specific graph
		this.graph = graph;
	}

	public Graph getGraph() {// get the graph
		return graph;
	}

	@Override
	public String toString() {// toString method to provide a string representation of the comparator
		return "CityComparator [graph=" + graph + "]";
	}

	@Override
	public int compare(City City1, City City2) {// compare method to compare two Citys based on
																// their distances in the graph
		return Double.compare(graph.getDistance(City1), graph.getDistance(City2));// double compare to conpare
																							// between distance
	}
}
