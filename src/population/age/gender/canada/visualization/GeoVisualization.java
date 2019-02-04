package populcation.age.gender.canada.visualization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class GeoVisualization implements EventHandler<ActionEvent> {

	List<PopulationNum> numbers;
	List<PopulationMedian> medians;
	String geo;
	List<Integer> years = new ArrayList<>();
	final String[] genders = { "Males", "Females", "Both sexes" };

	public GeoVisualization(String value, List<PopulationNum> numbers, List<PopulationMedian> medians) {

		this.numbers = numbers;
		this.medians = medians;
		this.geo = value;

		Set<Integer> set = medians.stream().filter(e -> e.getGEO().equalsIgnoreCase(geo))
				.collect(Collectors.groupingBy(PopulationMedian::getYear)).keySet();
		years.addAll(set);
		Collections.sort(years);

	}

	@Override
	public void handle(ActionEvent event) {
		// stage of the visualization page
		Stage stage = new Stage();
		stage.setTitle("Visualization for the GEO: " + this.geo);

		VBox vb = new VBox();
		vb.getChildren().add(getBarPopulation());
		vb.getChildren().add(getBarMedian());
		
		Scene scene = new Scene(vb, 600, 600);
		stage.setScene(scene);
		stage.show();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node getBarMedian() {

		// Define the line chart.
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Years");
		yAxis.setLabel("Age Median");
		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("Median age of " + geo + " based on genders from 1971-2018");

		for (String gender : genders) {

			XYChart.Series series = new XYChart.Series();
			series.setName(gender);

			for (Integer year : years) {

				double number = medians
						.stream().filter(e -> e.getGEO().equals(geo)).filter(e -> e.getYear() == year
								&& e.getGender().equals(gender))
						.findFirst().get().getMed();
				series.getData().add(new XYChart.Data(year.toString(), number));
			}
			bc.getData().add(series);

		}

		return bc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node getBarPopulation() {

		// Define the line chart.
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Years");
		yAxis.setLabel("Populations");
		final LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("Population of " + geo + " based on genders from 1971-2018");

		for (String gender : genders) {

			XYChart.Series series = new XYChart.Series();
			series.setName(gender);

			for (Integer year : years) {

				int number = numbers
						.stream().filter(e -> e.getGEO().equals(geo)).filter(e -> e.getYear() == year
								&& e.getAgeGroup().equals("All ages") && e.getGender().equals(gender))
						.findFirst().get().getNum();
				series.getData().add(new XYChart.Data(year.toString(), number));
			}
			lc.getData().add(series);

		}
		lc.setCreateSymbols(false);
		
		return lc;
	}

}
