package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main extends Application {
	public Graph graph;// graph object
	public Dijkstra dijkstra;// digkstra object to get the shortest path between two city in the graph
	public ImageView imageView;// image view
	public ComboBox<City> sourceComb = new ComboBox<>();// combo box that it will contains the source cities that
														// represents
	// the vertix
	public ComboBox<City> destancesComb = new ComboBox<>();// combo box that it will contains the distination cities
															// that
															// represents the vertix
	public static ArrayList<City> Citys = new ArrayList<>(); // the cities array list that it represent the vertix on
																// the
																// graph
	public static ArrayList<Road> Roads = new ArrayList<>(); // the roads list that it represent the edges on the graph

	AnchorPane ancrpan = new AnchorPane();// i use this we can make the pane two parts and more usable

	@Override
	public void start(Stage primaryStage) throws IOException {
		graph = graphReadFromFile(); // load the graph content from the file i provide
		dijkstra = new Dijkstra();// digkstra object to get the shortest path between two city in the graph
		Label lblsrc = new Label("Source: ");
		lblsrc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lblsrc.setTextFill(Color.WHITE);
		Label lbldist = new Label("Target: ");
		lbldist.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lbldist.setTextFill(Color.WHITE);
		sourceComb.setLayoutX(1150);
		sourceComb.setLayoutY(20);
		destancesComb.setLayoutX(1150);
		destancesComb.setLayoutY(40);
		sourceComb.setPrefHeight(20);
		destancesComb.setPrefHeight(20);
		destancesComb.setPrefWidth(135);
		HBox hbsrc = new HBox();// hbox for the source city content
		hbsrc.getChildren().addAll(lblsrc, sourceComb);
		hbsrc.setSpacing(5);
		HBox hbdist = new HBox();// hbox for the target distination city
		hbdist.getChildren().addAll(lbldist, destancesComb);
		hbdist.setSpacing(5);
		Button run = new Button("Run");// button to run and get the shortest path
		run.setLayoutX(1400);
		run.setLayoutY(100);
		run.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 10;");
		run.setTextFill(Color.WHITE);
		run.setOnAction(e -> primaryStage.close());
		run.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		Button clear = new Button("Clear");// clear button to clear the content to start over again and find another
											// shortest path
		clear.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 10;");
		clear.setTextFill(Color.WHITE);
		clear.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		clear.setLayoutX(1250);
		clear.setLayoutY(250);
		Label lblpath = new Label("Path: ");// path labele
		lblpath.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		lblpath.setTextFill(Color.WHITE);
		TextArea a = new TextArea();// text area that will contains the info about the path we need between two
									// cities
		a.setPrefSize(250, 100);
		a.setFont(Font.font("Arial", FontWeight.BOLD, 9));
		a.setStyle("-fx-text-fill: black;");
		a.setEditable(false);
		HBox hBoxPath = new HBox();// hbox that will contain the path content
		hBoxPath.getChildren().addAll(lblpath, a);
		hBoxPath.setSpacing(5);
		Label distn = new Label("Distance: ");// lable for the distance
		distn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		distn.setTextFill(Color.WHITE);
		TextArea tf = new TextArea();// text area that will contains the info about the path of the distination the
										// time that needed to reach by walk or car
		tf.setStyle("-fx-prompt-text-fill: black;");
		tf.setStyle("-fx-text-fill: black;");
		tf.setPrefWidth(220);
		tf.setPrefHeight(100);
		tf.setFont(Font.font("Arial", FontWeight.BOLD, 9));
		HBox hBoxDistance = new HBox();// hbox to put the distance content in it
		hBoxDistance.getChildren().addAll(distn, tf);
		hBoxDistance.setSpacing(1);
		VBox cntrl = new VBox();// vbox to put the right side content in it
		cntrl.setAlignment(Pos.CENTER);
		cntrl.setSpacing(30);
		cntrl.setLayoutX(600);
		cntrl.setLayoutY(40);
		HBox hb = new HBox();// hbox to put the button clear and run in it
		hb.getChildren().addAll(run, clear);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(20);
		cntrl.getChildren().addAll(hbsrc, hbdist, hBoxPath, hBoxDistance, hb);// content of right side
		Image image = new Image(getClass().getResourceAsStream("yunatest.jpg"), 530.6242424, 566.26, false, false);
		// its dalta longituede and latitude so that Assume the following scale
		// 1cm2500m then it will be daltalong=35021.2m/2500=14.00848
		// Daltalat=37373.18/2500=14.949272
		// Convert to pixels
		// Daltalong=14.00848/0.0264=530.6242424
		// Daltalat=14.949272/0.0264=566.26
		ImageView imageView = new ImageView(image);
		imageView.setLayoutX(0);// set the x lay out and the y layout to the zero
		imageView.setLayoutY(0);
		ancrpan.getChildren().add(imageView);// add the map image
		for (int i = 0; i < Citys.size(); i++) {// walking throught city array list to add the circles on the map and to
												// the combobox
			City city = Citys.get(i);
			Circle circle = circles(city); // Invoke convert method to get a circle
			if (circle == null) {//
				continue;
			}
			ancrpan.getChildren().add(circle); // add the circles to the pane
			ancrpan.getChildren().add(Labels(city)); // generate the lable for each city that i create circle for
			sourceComb.getItems().add(city); // add city to the combobox for the source city
			destancesComb.getItems().add(city); // add city to the combobox for the distination target
		}
		BackgroundFill bkr = new BackgroundFill(Color.BLACK, null, null);// fill the back ground with black for achnor
																			// pane
		Background background = new Background(bkr);
		ScrollPane scrlpan = new ScrollPane();// create scroll pane to put the map in it
		scrlpan.setContent(ancrpan);
		scrlpan.setPrefSize(440, 550);
		AnchorPane ap = new AnchorPane();// create achnor pane to put the scroll pane and the control panel on them
		ap.getChildren().add(scrlpan);// adding scroll pane
		ap.getChildren().add(cntrl);// adding the control info on right side
		ap.setBackground(background);// make the backgrond me the background for thi spane

//***********************************set on actions***************************************//

		clear.setOnAction(e -> {// set on action on the clear button
			ancrpan.getChildren().clear();// clear the pane content
			ancrpan.getChildren().add(imageView);
			sourceComb.setValue(null);// set the value of the source to the null
			destancesComb.setValue(null);// set the value of the target to the null
			ap.getChildren().clear();// clear the content

			for (int i = 0; i < Citys.size(); i++) {// walk throught the cities in the array list of the cities
				City city = Citys.get(i);// get the city
				Circle circle = circles(city); // get the cicle for the cities
				if (circle == null) {// if the circle value is null then continue skip it
					continue;
				}
				ancrpan.getChildren().add(circle); // add the circles on the map
				ancrpan.getChildren().add(Labels(city)); // generate the lables behind the circle on the map
			}

			cntrl.getChildren().clear();// clear the content of the control panale
			cntrl.getChildren().addAll(hbsrc, hbdist, hBoxPath, hBoxDistance, hb);// add again
			a.setText("");// set the text to nth
			tf.clear();// clear the content
			ap.getChildren().add(cntrl);
			ap.getChildren().add(scrlpan);

			try {
				graph = graphReadFromFile();// read the information from the file
			} catch (Exception ex) {
				System.out.println("an error occure!!!!!!!!!");// if an error occured
			}

		});
		run.setOnAction(e -> {// set on action on the run button

			if (sourceComb.getValue() != null && destancesComb.getValue() != null) {// if thereis the value and element
																					// on
																					// the
																					// source and the targetcities
				ArrayList<City> path = dijkstra.dijstra(sourceComb.getValue(), destancesComb.getValue(), graph);

				if (path == null) { // if there is no path between the choosen cities
					error("There is no path between the selected cities!!!!!");
				} else { // else if there is path
					for (int i = 0; i < path.size() - 1; i++) { // walk throught the cities in the path
						City curr = path.get(i); // the current city
						City next = path.get(i + 1); // the next city
						a.appendText("path" + (i + 1) + " from" + curr.name + " to " + next.name + "\n");// the text
																											// area
																											// content
						ancrpan.getChildren().add(Lines(curr, next));// add to the pane that drawing the line from
																		// the source city to the target city for each
																		// path
					}

					double distance = 0.0;// initialize double distance as zero
					int index = graph.getCities().indexOf(destancesComb.getValue());// get the index of the city

					if (index != -1 && index < graph.getDistances().size()) {// if there element and the index less that
																				// the size
						distance = graph.getDistances().get(index);// get the distance at specific index
					}

					DecimalFormat form = new DecimalFormat("#.##");// dicimal format to get only tow digit after the .
					String strdist = form.format(distance);// make the distance in form
					double mdist = distance * 1000;// the distance in meter
					double walkhr = Double.parseDouble(strdist) * 1000 / (1.42 * 60 * 60);// human walk time in hour
					double walkmin = Double.parseDouble(strdist) * 1000 / (1.42 * 60);// human walk time in minites
					double carhr = Double.parseDouble(strdist) / 40;// car travilling time in hours
					double carmin = Double.parseDouble(strdist) / 40 * 60;// car travelling time in min

					String mstrdist = form.format(mdist);// make the distance in meter in the format
					String strwalkhr = form.format(walkhr);// walking in hour in form
					String strwalkmin = form.format(walkmin);// walking in min in form
					String strcarmin = form.format(carmin);// travilling in min in form
					String strcarhr = form.format(carhr);// travelling in hour in form

					tf.setText("distance in KM is " + strdist + " Km" + "\ndistance in m is " + mstrdist + " m"
							+ "\nwalking human time in hours " + strwalkhr + " hours"
							+ "\nwalking human time in minuetes" + strwalkmin + " min" + "\ncar travel time in hours "
							+ strcarhr + " hours" + "\ncar travel time in minets " + strcarmin + " min");

					System.out.println(form.format(distance) + " Km" + "\n walking humen time is"
							+ Double.parseDouble(form.format(distance)) * 1000 / (1.42 * 60) + "min" + "\n by car"
							+ Double.parseDouble(form.format(distance)) / 40 * 60);
				}
			}
		});

		Scene scene = new Scene(ap, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();// show the stage
		primaryStage.setTitle("Gaza map:)");
	}

//***********************************methods******************************************************//

	public Label Labels(City City) { // method that print out labels to appear in the pane for the each city
		Circle circle = circles(City); // for each city to create circle
		if (circle == null) { // if there is no circle
			return null;// return null
		}
		Label lbl = new Label(City.name); // lable for the cities name
		lbl.setTextFill(Color.YELLOW);
		lbl.setLayoutX(circle.getLayoutX()); // Get the x coordinate from the circle+10
		lbl.setLayoutY(circle.getLayoutY()); // Get the y coordinate from the circle-10
		lbl.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		return lbl;// return the lable
	}

	public Circle circles(City City) { // convert the City to a circle to add in the pane

		double yCoordinate = ((Math.abs(31.598 - City.Latitude)) * 111795) / (2500 * 0.0264);
		double xCoordinate = (((Math.abs(34.212858 - City.longitude)) * 97281) / (2500 * 0.0264));
		Circle crcl = new Circle(4);
		crcl.setOnMouseClicked(e -> {// when click on the circle
			if (sourceComb.getValue() == null) {// source city value is null then when i click the circle will add it as
												// the
				// source city
				sourceComb.setValue(City);// set it as the source city
				crcl.setFill(Color.WHITE);// make it color to white
			} else { // else if the source value filled then put the value to the target value
				destancesComb.setValue(City);// set it as the target city
				crcl.setFill(Color.WHITE);//// make it color to white
			}
		});
		crcl.setFill(Color.DARKBLUE);
		crcl.setLayoutX(xCoordinate);
		crcl.setLayoutY(yCoordinate);

		return crcl; // return the circle
	}

	public Line Lines(City source, City destance) { // this method to draw the lines between the cities
		Line line = new Line();// create a new line
		line.setStroke(Color.RED);// make the stroke color to red
		line.setStrokeWidth(7);// and thikniss
		line.setStartX(circles(source).getLayoutX());// get the circle pos coordinate
		line.setStartY(circles(source).getLayoutY());// get the circle pos coordinate
		line.setEndX(circles(destance).getLayoutX());// get the circle pos coordinate
		line.setEndY(circles(destance).getLayoutY());// get the circle pos coordinate
		return line;// return the line
	}

	public static Graph graphReadFromFile() throws IOException {
		BufferedReader reader = new BufferedReader(
				new FileReader("C:\\Users\\Yuna\\eclipse-workspace\\ALGO_3\\src\\application\\cities"));// buffer reder
																										// to read the
																										// file
		boolean fsec = true; // first part of the city information
		String line1 = reader.readLine(); // The first line contains numOfCities and numOfRoads
		String line;// string line
		while ((line = reader.readLine()) != null) {// while there is lines to read walk throught the loop
			if (line.equals(line1) == true) {// if the line equals the first line
				continue;// then continue
			}
			if (line.equals("                                                               ")) {// finishing point for
																									// the par one of
																									// the file content
				fsec = false;// its not the first part of file
				continue;// then continue
			}

			String[] parts = line.split(",");// spit depends on the ,
			if (fsec == true) {// if its first part of the city info
				if (parts.length >= 3) {// city info section and i should make sure to contain 3 in line
					String name = parts[0];// name of the city
					double x = Double.parseDouble(parts[1]);// latitude
					double y = Double.parseDouble(parts[2]);// longitude
					City city = new City(name, x, y);// create a new object that contains the info of the line that had
														// been reden
					Citys.add(city);// add the city to the array list of the cities
				} else {
					error("error while reding the file!!!");// error while reading file
				}
			} else {
				if (parts.length >= 2) {// the second part of the file
					String name1 = parts[0];// the first city
					String name2 = parts[1];// second sity
					City city1 = find(name1);// find the city as if its exist return it other wise return null
					City city2 = find(name2);// find the city as if its exist return it other wise return null
					if (city1 != null && city2 != null) {// if the cities are not null
						double distance = calculatedist(city1, city2);// calculate the distance
						Road road = new Road(city1, city2, distance);// create a new road the contains the source city
																		// and the next city and give them the distance
																		// value cost
						Roads.add(road);// add the road to the array of roads
					}
				} else {
					error("error while reding the file!!!");// error while reading file

				}
			}
		}

		reader.close();// close the reader file eof

		Graph graph = new Graph();// create a new graph object to fill it with the file content information
		for (int i = 0; i < Citys.size(); i++) {// walk throughgt the city array list
			City city = Citys.get(i);
			graph.addCity(city);// add it to the graph
		}

		for (int i = 0; i < Roads.size(); i++) {// walk throught the road array list
			Road road = Roads.get(i);
			graph.addRoad(road.source, road.destance, road.cost);// add the road to the graph that is the distance
																	// between two cities
		}

		return graph;// return a graph that it fill with the cities and the roads with distances for
						// them

	}

	public static City find(String cityName) {// method to find the city in the array list of the city and return it
		for (int i = 0; i < Citys.size(); i++) {// walk throught the array list of the cities
			City city = Citys.get(i);
			if (city.name.equals(cityName) == true) {// if matching the value of the city with the city
				return city;// then return true
			}
		}

		return null;// other wise return null if cant find the value of the city

	}

	public static double calculatedist(City city1, City city2) {// this method is to calculate the distance between two
																// cities
		double lat1 = Math.toRadians(city1.Latitude);// get the long and lat for each city in radians
		double long1 = Math.toRadians(city1.longitude);
		double lat2 = Math.toRadians(city2.Latitude);
		double long2 = Math.toRadians(city2.longitude);

		double distance = Math.acos(
				Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1)) * 6371;
		return distance;// return the distance
	}

	public static void error(String msg) {// display an error message if the number is not between 1 and 100.
		Alert alert = new Alert(Alert.AlertType.ERROR);// create an alert to display the error message.
		alert.setTitle("Error!!!");
		alert.setHeaderText(null);
		alert.setContentText(msg);// set the content of the alert to the error message.
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.showAndWait();// display the alert.
	}

	public static void main(String[] args) {
		launch();
	}
}