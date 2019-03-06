package populcation.age.gender.canada.visualization;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;
import populcation.age.gender.canada.TableType;
import populcation.age.gender.canada.properties_table_column.PopulationProperties;

public class TableViewPageBuilder extends VBox {
	TableType type;
	int year;
	String value;
	List<PopulationNum> numbers;
	List<PopulationMedian> medians;
	Button button = new Button("Visualization");

	public TableViewPageBuilder(int year, List<PopulationNum> numbers, List<PopulationMedian> medians) {

		this.year = year;
		this.numbers = numbers;
		this.medians = medians;
		TablePageOfYear();
	}

	public TableViewPageBuilder(TableType type, String value, List<PopulationNum> numbers,
			List<PopulationMedian> medians) {
		this.type = type;
		this.value = value;
		this.numbers = numbers;
		this.medians = medians;

		switch (type) {
		case GEO:
			TablePageOfGeo();
			break;
		case GENDER:
			TablePageOfGender();
			break;
		case AGEGROUP:
			TablePageOfAgegroup();
			break;
		default:
			break;
		}

	}

	private void TablePageOfYear() {
		button.setMaxWidth(800);
		button.setOnAction(new YearVisualization(year, numbers, medians));
		this.getChildren().add(button);
		this.getChildren().add(new TableViewOfYear<PopulationProperties>(year, numbers, medians,
				ColumnsArray.YEAR_NAME,
				ColumnsArray.YEAR_VALUE,5));
		
	}

	private void TablePageOfAgegroup() {
		button.setMaxWidth(800);
		button.setOnAction(new AgeVisualization(value, numbers, medians));
		this.getChildren().add(button);
		this.getChildren().add(new TableViewOfAge<PopulationProperties>(value, numbers, medians,
				ColumnsArray.AGE_NAME,
				ColumnsArray.AGE_VALUE, 4));

	}

	private void TablePageOfGender() {
		button.setMaxWidth(800);
		button.setOnAction(new GenderVisualization(value, numbers, medians));
		this.getChildren().add(button);
		this.getChildren().add(new TableViewOfGender<PopulationProperties>(value, numbers, medians,
				ColumnsArray.GENDER_NAME,ColumnsArray.GENDER_VALUE, 5));
	}

	private void TablePageOfGeo() {
		button.setMaxWidth(800);
		button.setOnAction(new GeoVisualization(value, numbers, medians));
		this.getChildren().add(button);
		this.getChildren().add(new TableViewOfGEO<PopulationProperties>(value, numbers, medians,
				ColumnsArray.GEO_NAME,ColumnsArray.GEO_VALUE,5));

	}

}
