package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dijkstra {
	xHeap<City> xHeap;// min xHeap that stores the cities during the dijkstra algo
	City current;// current city that im in
	City[] prev;// array to store the prev citiy in the shortest path for each City
	ArrayList<City> path;// array list to store the cities in the shortest path

	public Dijkstra() {// no argumant constructor
		this.current = current;
		this.xHeap = xHeap;
		this.path = path;
		this.prev = prev;
	}

	public Dijkstra(ArrayList<City> path) {// argu constructor that take the path
		this.current = current;
		this.xHeap = xHeap;
		this.path = path;
		this.prev = prev;
	}

	public xHeap<City> getxHeap() {// get the xHeap
		return xHeap;
	}

	public void setxHeap(xHeap<City> xHeap) {// set the xHeap
		this.xHeap = xHeap;
	}

	public City[] getprev() {// gaet the prev
		return prev;
	}

	public void setprev(City[] prev) {// set the prev
		this.prev = prev;
	}

	public ArrayList<City> getPath() {// get the path
		return path;
	}

	public void setPath(ArrayList<City> path) {// set the path
		this.path = path;
	}

	public City getCurrent() {// get the current
		return current;
	}

	public void setCurrent(City current) {// set the curret
		this.current = current;
	}

	public ArrayList<City> dijstra(City source, City destination, Graph graph) {// digekstra method algorithim
		dijkstra(source, graph);// initialize the dijktra algo
		while (!xHeap.isEmpty()) {// keep going until the xHeap be empty and there is no more element on it
			current = xHeap.poll();// get the first element on the xHeap that represent the city with the shortest
									// distance
			if (current == destination) {// if the current city is the distination that i want then stop and break out
											// the loop and stop going
				break;// break the loop
			}
			testBehind(graph, current);// here to test the current city on the graph for the shortest path fo it
			// adjacents
		}

		return buildPath(source, destination, graph);// to build path from the source city to destination thet i want in
														// the graph that icreate
	}

	public void dijkstra(City source, Graph graph) {// this method use to initialize the digkstra algorithim using the
													// source city
		xHeap = new xHeap<>(new compareto(graph));// create a new city xHeap based on the distane
		xHeap.add(source);// add the source city to the xHeap
		graph.setDistance(source, 0);// set the initial distance for the source city is zero
		int cityNum = graph.getCities().size();// save the number of the cities in the graph that is the size of the
												// array list of the cities
		prev = new City[cityNum];// initialize a new array of city that is the same size of the array list it
									// will track the previous shortest path
	}

	public void testBehind(Graph graph, City current) {// this methood used to test the behind naibhors for the current
														// city that i stand in the graph
		ArrayList<Road> roads = graph.getRoads();// get the list of the roads

		for (int i = 0; i < roads.size(); i++) {// walk through the roads that in the graph
			Road road = roads.get(i);// get rod at the specifiic index
			if (road.getSource().equals(current) == true) {// checks if the source city of the current road is equal to
															// the current city
				City neighbor = road.getdestance();// get the distination city vertix
				double distance = road.getcost();// the cost of the current road.

				if (graph.getDistance(current) + distance < graph.getDistance(neighbor)) {// if the distance to the
																							// destination city through
																							// the current city is
																							// shorter than the current
																							// distance to the
																							// destination city
					update(graph, current, neighbor, distance);// update the destance of the neibhor city and track the
																// path
				}
			}
		}
	}

	public void update(Graph graph, City current, City neighbor, double distance) {// method to update the distance and
																					// track the previous city
		graph.setDistance(neighbor, graph.getDistance(current) + distance);// update the distance of the neibhor city
		int index = graph.getCities().indexOf(neighbor);// update the array to keep track of the path
		prev[index] = current;
		xHeap.add(neighbor);// add the neighbor city to the xHeap to exploration
	}

	public ArrayList<City> buildPath(City source, City destination, Graph graph) {
	    path = new ArrayList<>();
	    current = destination;

	    while (current != null && current != source) {
	        int index = graph.getCities().indexOf(current);
	        if (index == -1) {
	            // Handle the case where the current city is not in the graph
	            System.out.println("Current city not found in the graph!");
	            return null; // Or handle the error in a way that suits your application
	        }

	        path.add(current);
	        current = prev[index];
	    }

	    if (current == null) {
	        // Handle the case where the destination is not reachable from the source
	        System.out.println("Destination not reachable from the source!");
	        return null; // Or handle the error in a way that suits your application
	    }

	    path.add(source);
	    Collections.reverse(path);
	    return path;
	}


	public void printShortestPath() {
		if (path == null) {// if the path is null
			System.out.println("there is no path!!!!!!!!!");
		} else {// if there is path
			System.out.print("the shortest path is : ");
			for (int i = 0; i < path.size(); i++) {// walk throught the path size
				City City = path.get(i);
				System.out.print(City.name);// pront out its endixs
				if (i < path.size() - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	@Override
	public String toString() {// to string method that get the buildongs and the path and all what we need for
								// thenm
		return "Dijkstra{" + "xHeap=" + xHeap + ", prev=" + Arrays.toString(prev) + ", path=" + path + ", current="
				+ current + '}';
	}
}
