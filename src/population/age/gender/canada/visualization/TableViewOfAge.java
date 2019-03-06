package populcation.age.gender.canada.visualization;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class TableViewOfAge<PopulationProperties> extends TableViewAbstraction<PopulationProperties> {

	public TableViewOfAge(String value, List<PopulationNum> numbers, List<PopulationMedian> medians, String[] colnames,
			String[] colValues, int num) {

		super(value, numbers, medians, colnames, colValues, num);
		initialization();
	}

	@SuppressWarnings("unchecked")
	public ObservableList<PopulationProperties> getObservableList() {

		ObservableList<PopulationProperties> data = FXCollections.observableArrayList();
		List<PopulationNum> nums = numbers.stream().filter(e -> e.getAgeGroup().equals(value))
				.collect(Collectors.toList());
		for (PopulationNum populationNum : nums) {
			data.add(
					(PopulationProperties) new populcation.age.gender.canada.properties_table_column.PopulationProperties(
							populationNum.getYear(), populationNum.getGEO(), populationNum.getGender(),
							populationNum.getNum()));

		}
		return data;
	}

}
