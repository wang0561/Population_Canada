package populcation.age.gender.canada.visualization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class GenderVisualization implements EventHandler<ActionEvent> {

	List<PopulationNum> numbers;
	List<PopulationMedian> medians;
	String gender;
	List<String> GEOs = new ArrayList<>();
	List<Integer> years = new ArrayList<>();

	public GenderVisualization(String value, List<PopulationNum> numbers, List<PopulationMedian> medians) {

		this.numbers = numbers;
		this.medians = medians;
		this.gender = value;
		
		Set<String> set = medians.stream().collect(Collectors.groupingBy(PopulationMedian::getGEO)).keySet();
		GEOs.addAll(set);
	
		Set<Integer> set2 = medians.stream().collect(Collectors.groupingBy(PopulationMedian::getYear)).keySet();
		years.addAll(set2);
		Collections.sort(years);
		
	}

	@Override
	public void handle(ActionEvent event) {
		Stage stage = new Stage();
		stage.setTitle("Visualization for the Gender: " + this.gender);

		VBox vb = new VBox();
		vb.getChildren().add(getBarPopulation());
		vb.getChildren().add(getBarMedian());

		Scene scene = new Scene(vb, 600, 600);
		stage.setScene(scene);
		stage.show();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node getBarMedian() {
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Years");
		yAxis.setLabel("Median");
		final LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("Age Median of " + gender + " in Canada from 1971-2018");
		XYChart.Series series = new XYChart.Series();
		series.setName("Canada");
		
		for(Integer year: years) {
			double number = medians.stream().filter(e -> e.getYear() == year &&
			 						e.getGender().equals(gender) &&
			 						e.getGEO().equals("Canada")).findFirst().get().getMed();
			series.getData().add(new XYChart.Data(year.toString(), number));

		}
		lc.getData().add(series);
		lc.setCreateSymbols(false);
		return lc;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Node getBarPopulation() {
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Years");
		yAxis.setLabel("Populations");
		final LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("Population of " + gender + " in Canada from 1971-2018");
		

		XYChart.Series series = new XYChart.Series();
		series.setName("Canada");
		for (Integer year : years) {

			Stream<PopulationNum> stream = numbers.stream().filter(
					e -> e.getGender().equals(gender) && e.getYear() == year && e.getAgeGroup().equals("All ages"));
			List<PopulationNum> list = stream.filter(e -> e.getGEO().equals("Canada")).collect(Collectors.toList());
			if (!list.isEmpty())
				series.getData().add(new XYChart.Data(year.toString(), list.get(0).getNum()));

		}
		lc.getData().add(series);
		lc.setCreateSymbols(false);
		return lc;

	}
}
