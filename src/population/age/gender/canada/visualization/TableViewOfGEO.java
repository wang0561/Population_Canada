package populcation.age.gender.canada.visualization;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class TableViewOfGEO<PopulationProperties> extends TableViewAbstraction<PopulationProperties> {

	public TableViewOfGEO(String value, List<PopulationNum> numbers, List<PopulationMedian> medians, String[] colnames,
			String[] colValues, int num) {

		super(value, numbers, medians, colnames, colValues, num);
		initialization();
	}

	@SuppressWarnings("unchecked")
	public ObservableList<PopulationProperties> getObservableList() {

		ObservableList<PopulationProperties> data = FXCollections.observableArrayList();
		List<PopulationNum> nums = numbers.stream().filter(e -> e.getGEO().equals(value)).collect(Collectors.toList());
		List<PopulationMedian> meds = medians.stream().filter(e -> e.getGEO().equals(value))
				.collect(Collectors.toList());

		for (PopulationNum populationNum : nums) {

			PopulationMedian popmed = meds.stream().filter(
					e -> e.getYear() == populationNum.getYear() && e.getGender().equals(populationNum.getGender()))
					.collect(Collectors.toList()).get(0);

			data.add(
					(PopulationProperties) new populcation.age.gender.canada.properties_table_column.PopulationProperties(
							populationNum.getYear(), populationNum.getGender(), populationNum.getAgeGroup(),
							popmed.getMed(), populationNum.getNum()));

		}
		return data;
	}

}
