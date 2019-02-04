package populcation.age.gender.canada.visualization;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public class TableViewOfGender<PopulationProperties> extends TableViewAbstraction<PopulationProperties> {
	
	@SuppressWarnings("unchecked")
	TableColumn<PopulationProperties,?>[] columns = new TableColumn[5];
	final String[] colnames = {"Year","Geo", "AgeGroup", "Number","Median"};
	final String[] colValues = {"Year","Geo","Agegroup","Number","Median"};
	
	public TableViewOfGender(String value, List<PopulationNum> numbers, List<PopulationMedian> medians) {
		
		super(value,numbers,medians);
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
		List<PopulationNum> nums = numbers.stream().filter(e-> e.getGender().equals(value)).collect(Collectors.toList());
		List<PopulationMedian> meds = medians.stream().filter(e->e.getGender().equals(value)).collect(Collectors.toList());
		
		for(PopulationNum populationNum : nums ) {
			
			PopulationMedian popmed = meds.stream().filter(e->e.getYear() == populationNum.getYear() && 
					e.getGEO().equals(populationNum.getGEO())).collect(Collectors.toList()).get(0);
			
			data.add((PopulationProperties) new populcation.age.gender.canada.properties_table_column.PopulationProperties(populationNum.getGEO(),populationNum.getYear(),populationNum.getAgeGroup(),
					popmed.getMed(),populationNum.getNum()));
			
		}
		return data;
	}

}
