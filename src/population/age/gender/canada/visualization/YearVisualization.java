package populcation.age.gender.canada.visualization;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class YearVisualization implements EventHandler<ActionEvent> {

	int year;
	List<PopulationNum> numbers;
	List<PopulationMedian> medians;
	List<String> GEOs = new ArrayList<>();
	//create series and add series to bar chart
	String[] genders = {"Males", "Females","Both sexes"};
	
	public YearVisualization(int year, List<PopulationNum> numbers, List<PopulationMedian> medians) {

		this.year = year;
		this.numbers = numbers;
		this.medians = medians;
		Set<String> set = medians.stream().filter(e -> e.getYear()==this.year).collect(Collectors.groupingBy(PopulationMedian::getGEO)).keySet();
		GEOs.addAll(set);
	}

	@Override
	public void handle(ActionEvent arg0) {
		// stage of the visualization page
		Stage stage = new Stage();
		stage.setTitle("Visualization for the year: " + this.year);

		VBox vb = new VBox();
		vb.getChildren().add(getBarPopulation());
		vb.getChildren().add(getBarMedian());

		Scene scene = new Scene(vb, 600, 600);
		stage.setScene(scene);
		stage.show();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node getBarMedian() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Median age based on GEO and Gender");
		
		xAxis.setLabel("GEO");
		yAxis.setLabel("Age Median");
		
		List<PopulationMedian> list = medians.stream().filter(e ->e.getYear() == this.year).
				collect(Collectors.toList());
		
		for(String gender : genders) {
			XYChart.Series series = new XYChart.Series();
			series.setName(gender);
			for(String geo : GEOs) {
			double value = list.stream().filter(e -> e.getGender().equalsIgnoreCase(gender)&&
					e.getGEO().equalsIgnoreCase(geo)).findFirst().get().getMed();
			
			series.getData().add(new XYChart.Data(geo, value));
			}
			bc.getData().add(series);
		}
		return bc;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node getBarPopulation() {
		// define the x and y axis.
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		// define the bar chart
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Population based on GEO and Gender");

		// set the name of the x and y axis
		xAxis.setLabel("GEO");
		yAxis.setLabel("Population");

		//Get the list of population objects based on this year.
		List<PopulationNum> list = numbers.stream().
				filter(e -> e.getYear()==this.year && e.getAgeGroup().equals("All ages")).
				collect(Collectors.toList());
		
		//add data to the series
		for(String gender : genders) {
			XYChart.Series series = new XYChart.Series();
			series.setName(gender);
			for(String geo: GEOs) {
				
				int number = list.stream().filter(e -> e.getGEO().equalsIgnoreCase(geo)
						&&e.getGender().equalsIgnoreCase(gender)).findAny().get().getNum();
				
				series.getData().add(new XYChart.Data(geo, number));
			}
			bc.getData().add(series);
		}
		
		return bc;
	}

}
