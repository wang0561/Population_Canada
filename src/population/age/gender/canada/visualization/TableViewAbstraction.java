package populcation.age.gender.canada.visualization;

import java.util.List;

import javafx.scene.control.TableView;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public abstract class TableViewAbstraction<PopulationProperties> extends TableView<PopulationProperties> {

	public TableViewAbstraction(String value, List<PopulationNum> numbers, List<PopulationMedian> medians) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.numbers =numbers;
		this.medians = medians;
	}
	public TableViewAbstraction(int year, List<PopulationNum> numbers, List<PopulationMedian> medians) {
		// TODO Auto-generated constructor stub
		this.year = year;
		this.numbers =numbers;
		this.medians = medians;
	}
	List<PopulationNum> numbers;
	List<PopulationMedian> medians;
	String value;
	int year;
	public abstract void initialization();
}
