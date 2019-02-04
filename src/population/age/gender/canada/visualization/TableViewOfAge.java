package populcation.age.gender.canada.visualization;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class TableViewOfAge<PopulationProperties> extends TableViewAbstraction<PopulationProperties> {
	
	@SuppressWarnings("unchecked")
	TableColumn<PopulationProperties,?>[] columns = new TableColumn[4];
	final String[] colnames = {"Year","Geo", "Gender", "Number"};
	final String[] colValues = {"Year","Geo","Gender","Number"};

	public TableViewOfAge(String value, List<PopulationNum> numbers, List<PopulationMedian> medians) {
		
		super(value, numbers, medians);
		initialization();
	}

	@Override
	public void initialization() {
		
		for(int i=0; i<columns.length;i++) {
			columns[i]= new TableColumn<>(colnames[i]);
			columns[i].setMinWidth(200);
			columns[i].setCellValueFactory(new PropertyValueFactory<>(colValues[i]));
			columns[i].setStyle("-fx-alignment: CENTER;");
			this.getColumns().add(columns[i]);
		}
		this.setItems(getObservableList());
	}

	@SuppressWarnings("unchecked")
	private ObservableList<PopulationProperties> getObservableList() {
		
		ObservableList<PopulationProperties> data = FXCollections.observableArrayList();
		List<PopulationNum> nums = numbers.stream().filter(e-> e.getAgeGroup().equals(value)).collect(Collectors.toList());
		for(PopulationNum populationNum : nums ) {		
			data.add((PopulationProperties) new populcation.age.gender.canada.properties_table_column.PopulationProperties(populationNum.getYear(),populationNum.getGEO(),populationNum.getGender(),
					populationNum.getNum()));
			
		}
		return data;
	}

}
