package populcation.age.gender.canada.visualization;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;
import populcation.age.gender.canada.properties_table_column.PopulationProperties;


@SuppressWarnings("hiding")
public class TableViewOfYear<PopulationProperties> extends TableViewAbstraction<PopulationProperties> {

	
	@SuppressWarnings("unchecked")
	TableColumn<PopulationProperties,?>[] columns = new TableColumn[5];
	final String[] colnames = {"Geo","Gender", "AgeGroup", "Number","Median"};
	final String[] colValues = {"Geo","Gender","Agegroup","Number","Median"};
	
	public TableViewOfYear(int year, List<PopulationNum> numbers, List<PopulationMedian> medians) {
		
		super(year,numbers,medians);
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
		List<PopulationNum> nums = numbers.stream().filter(e-> e.getYear() == year).collect(Collectors.toList());
		List<PopulationMedian> meds = medians.stream().filter(e->e.getYear() == year).collect(Collectors.toList());
		
		for(PopulationNum populationNum : nums ) {
			
			PopulationMedian popmed = meds.stream().filter(e->e.getGEO().equals(populationNum.getGEO()) && 
					e.getGender().equals(populationNum.getGender())).collect(Collectors.toList()).get(0);
			
			data.add((PopulationProperties) new populcation.age.gender.canada.properties_table_column.PopulationProperties(populationNum.getGEO(),populationNum.getGender(),populationNum.getAgeGroup(),
					popmed.getMed(),populationNum.getNum()));
			
		}
		return data;
	}



}
