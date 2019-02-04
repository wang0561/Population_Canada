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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class AgeVisualization implements EventHandler<ActionEvent> {
	
	List<PopulationNum> numbers;
	List<PopulationMedian> medians;
	String age;
	List<Integer> years = new ArrayList<>();
	final String[] genders = { "Males", "Females", "Both sexes" };
	public AgeVisualization(String value, List<PopulationNum> numbers, List<PopulationMedian> medians) {
		
		this.numbers=numbers;
		this.medians = medians;
		this.age = value;
		
		Set<Integer> set = numbers.stream().filter(e -> e.getAgeGroup().equalsIgnoreCase(age))
				.collect(Collectors.groupingBy(PopulationNum::getYear)).keySet();
		years.addAll(set);
		Collections.sort(years);
		System.out.println("years"+years.size());
	}

	@Override
	public void handle(ActionEvent event) {
		Stage stage = new Stage();
		stage.setTitle("Visualization for the Age Group: " + this.age);

		VBox vb = new VBox();
		vb.getChildren().add(getBarPopulation());
		
		Scene scene = new Scene(vb, 600, 600);
		stage.setScene(scene);
		stage.show();

	}

	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Node getBarPopulation() {
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Years");
		yAxis.setLabel("Populations");
		final LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("Population at age " + age + " in Canada, based on genders from 1971-2018");
		
		for (String gender : genders) {

			XYChart.Series series = new XYChart.Series();
			series.setName(gender);

			for (Integer year : years) {

				int number = numbers
						.stream().filter(e -> e.getGEO().equals("Canada")).filter(e -> e.getYear() == year
								&& e.getAgeGroup().equals(age) && e.getGender().equals(gender))
						.findFirst().get().getNum();
				series.getData().add(new XYChart.Data(year.toString(), number));
			}
			lc.getData().add(series);

		}
		lc.setCreateSymbols(false);

		return lc;
	}

}
