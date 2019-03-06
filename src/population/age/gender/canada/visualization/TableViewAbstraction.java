package populcation.age.gender.canada.visualization;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import populcation.age.gender.canada.PopulationMedian;
import populcation.age.gender.canada.PopulationNum;

public abstract class TableViewAbstraction<PopulationProperties> extends TableView<PopulationProperties> {

	@SuppressWarnings("unchecked")
	public TableViewAbstraction(String value, List<PopulationNum> numbers, 
			List<PopulationMedian> medians,String[] colnames, String[] colValues
			,int numofcol) {
		
		this.value = value;
		this.numbers = numbers;
		this.medians = medians;
		this.colnames = colnames;
		this.colValues = colValues;
		this.columns = new TableColumn[numofcol];
	}

	@SuppressWarnings("unchecked")
	public TableViewAbstraction(int year, List<PopulationNum> numbers, 
			List<PopulationMedian> medians,String[] colnames, String[] colValues
			,int numofcol) {
	
		this.year = year;
		this.numbers = numbers;
		this.medians = medians;
		this.colnames = colnames;
		this.colValues = colValues;
		this.columns = new TableColumn[numofcol];
	}

	protected List<PopulationNum> numbers;
	protected List<PopulationMedian> medians;
	protected String value;
	protected int year;
	protected TableColumn<PopulationProperties,?>[] columns ;
	protected String[] colnames ;
	protected String[] colValues;
	

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
	
	public abstract ObservableList<PopulationProperties> getObservableList();
}
