package application;

public class Road {// this class represents the egges that is the road between the cities two
					// vertics
	public City source;// the sours vertix that represent the starting point
	public City destance;// this represent the destance
	public double cost;// the weight of it

	public Road() { // no arg constructor
		this.source = source;
		this.destance = destance;
		this.cost = cost;
	}

	public Road(City source, City destance, double cost) { // arg constructor that take the vertix and its distance and
															// cost
		this.source = source;
		this.destance = destance;
		this.cost = cost;
	}

	public City getSource() {// get the source vertix
		return source;
	}

	public void setSource(City source) {// set the source vertix
		this.source = source;
	}

	public City getdestance() {// get the destance
		return destance;
	}

	public void setdestance(City destance) {// set the destance
		this.destance = destance;
	}

	public double getcost() {// get cost
		return cost;
	}

	public void setcost(double cost) {// set the cost
		this.cost = cost;
	}

	@Override
	public String toString() {// to string method to print out roads whith its source and cost
		return "Edge [source=" + source + ", destance=" + destance + ", cost=" + cost + "]";
	}

}