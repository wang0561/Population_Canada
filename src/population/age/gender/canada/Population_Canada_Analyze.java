package populcation.age.gender.canada;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import populcation.age.gender.canada.visualization.TableViewPageBuilder;

public class Population_Canada_Analyze extends Application {

	private List<PopulationNum> numbers;
	private List<PopulationMedian> medians;
	private List<List<? extends Population>> list;
	private final static String TITLE = "***This is a system to analyze the populations in Canada based "
			+ "\non the year, GEO, Gender, and Age group. Double click to see each category of analyze and"
			+ "\nthe visualizations based on the category.***";

	public static void main(String[] args) {
	
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		init(stage);
	}

	@SuppressWarnings("unchecked")
	private void init(Stage stage) throws PopulationFileNotFound {
		File csvFile = PopulationUtils.getFileHandle(stage);
		if (!csvFile.exists()) {
			throw new PopulationFileNotFound(csvFile.getAbsolutePath());
		}

		list = PopulationUtils.readCSVFile(csvFile.getAbsolutePath());
		numbers = (List<PopulationNum>) list.get(0);
		medians = (List<PopulationMedian>) list.get(1);
		stage.setTitle("Population by age and genders in Canada from 1971 - 2018");
		Scene scene = new Scene(getHomePage(), 700, 600);

		stage.setScene(scene);
		stage.show();
	}

	private Parent getHomePage() {
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox();
		HBox hb = new HBox();
		
		hb.setSpacing(10);
		hb.setPadding(new Insets(10));
		hb.setStyle("-fx-background-color: #ffffff;" + "-fx-border-style:solid inside;" + "-fx-border-width:3;");
		hb.getChildren().add(yearPaneOnHomePage());
		hb.getChildren().add(agePaneOnHomePage());
		hb.getChildren().add(GEOPaneOnHomePage());
		hb.getChildren().add(genderPaneOnHomePage());
		sp.setContent(hb);
		sp.setFitToWidth(true);
		
		Text text = new Text(TITLE);
		text.setFont(Font.font(14));
		vb.getChildren().add(text);
		vb.getChildren().add(sp);
		
		return vb;
		
	}

	private Node agePaneOnHomePage() {
	
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		vb.getChildren().add(ageTitleOnHomePage());
		vb.getChildren().add(groupOfAgeOnHomePage());
		return vb;
		
	}

	private Node groupOfAgeOnHomePage() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		List<String> list = getAgeArray();
		Text[] ages = new Text[list.size()];

		for(int i = 0; i< ages.length; i++) {

			final int temp = i;
			ages[i] = new Text(list.get(i)+"");
			ages[i].setUnderline(true);
			ages[i].setOnMouseClicked(mouseEvent -> {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						openPageByAgeInput(list.get(temp));
					}
				}
			});

		}
		vb.getChildren().addAll(ages);
		return vb;
	}

	private void openPageByAgeInput(String age) {
		Stage stage = new Stage();
		Scene scene = new Scene(TableViewOfAge(age), 800,600);
		stage.setScene(scene);
		stage.setTitle("AGE GROUP:  "+ age);
		stage.show();
		
	}

	private Parent TableViewOfAge(String age) {
	
		return new TableViewPageBuilder(TableType.AGEGROUP,age,numbers,medians);
	}

	private Node ageTitleOnHomePage() {
		Text text = new Text("AGE");
		text.setFont(Font.font(16));
		return text;
	}

	private Node genderPaneOnHomePage() {
		// TODO Auto-generated method stub
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		vb.getChildren().add(genderTitleOnHomePage());
		vb.getChildren().add(groupOfGendersOnHomePage());
		return vb;
	}

	private Node groupOfGendersOnHomePage() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		List<String> list = getGenderArray();
		Text[] genders = new Text[list.size()];

		for(int i = 0; i< genders.length; i++) {

			final int temp = i;
			genders[i] = new Text(list.get(i)+"");
			genders[i].setUnderline(true);
			genders[i].setOnMouseClicked(mouseEvent -> {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						openPageByGenderInput(list.get(temp));
					}
				}
			});

		}
		vb.getChildren().addAll(genders);
		return vb;
	}

	private void openPageByGenderInput(String gender) {
		Stage stage = new Stage();
		Scene scene = new Scene(TableViewOfGender(gender), 800,600);
		stage.setScene(scene);
		stage.setTitle("GENDER:  "+ gender);
		stage.show();
		
	}

	private Parent TableViewOfGender(String gender) {
		
		return new TableViewPageBuilder(TableType.GENDER,gender,numbers,medians);
	}

	private Node genderTitleOnHomePage() {
		Text text = new Text("GENDER");
		text.setFont(Font.font(16));
		return text;
	}

	private Node GEOPaneOnHomePage() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		vb.getChildren().add(GEOTitleOnHomePage());
		vb.getChildren().add(groupOfGEOsOnHomePage());
		return vb;
	}

	private Node groupOfGEOsOnHomePage() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		List<String> list = getGEOArray();
		Text[] GEOS = new Text[list.size()];

		for(int i = 0; i< GEOS.length; i++) {

			final int temp = i;
			GEOS[i] = new Text(list.get(i)+"");
			GEOS[i].setUnderline(true);
			GEOS[i].setOnMouseClicked(mouseEvent -> {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						openPageByGEOInput(list.get(temp));
					}
				}
			});

		}
		vb.getChildren().addAll(GEOS);
		return vb;
	}

	private void openPageByGEOInput(String GEO) {
		Stage stage = new Stage();
		Scene scene = new Scene(TableViewOfGEO(GEO), 800,600);
		stage.setScene(scene);
		stage.setTitle("GEO:  "+ GEO);
		stage.show();

	}

	private Parent TableViewOfGEO(String gEO) {
	
		return new TableViewPageBuilder(TableType.GEO,gEO,numbers,medians);
	}

	private Node GEOTitleOnHomePage() {
	
		Text text = new Text("GEO");
		text.setFont(Font.font(16));
		return text;
	}

	private Node groupOfYearsOnHomePage() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		List<Integer> list = getYearArray();
		Text[] years = new Text[list.size()];

		for (int i = 0; i < years.length; i++) {
			final int temp = i ;
			years[i] = new Text(list.get(i) + "");
			years[i].setUnderline(true);
			years[i].setOnMouseClicked(mouseEvent -> {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						openPageByYearInput(list.get(temp));
					}
				}
			});
		}
		vb.getChildren().addAll(years);
		return vb;
	}

	private void openPageByYearInput(Integer year) {
		Stage stage = new Stage();
		Scene scene = new Scene(TableViewofYear(year), 800,600);
		stage.setScene(scene);
		stage.setTitle("Year: "+ year);
		stage.show();
	}
	/*
	 * return a table view and visualization button for Year page
	 * */
	private Parent TableViewofYear(Integer year) {
		
		return new TableViewPageBuilder(year, numbers, medians);
	}

	private Node YearTitleOnHomePage() {
		
		Text text = new Text("YEAR");
		text.setFont(Font.font(16));
		return text;
	}

	private Node yearPaneOnHomePage() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10));
		vb.getChildren().add(YearTitleOnHomePage());
		vb.getChildren().add(groupOfYearsOnHomePage());
		return vb;
	}

	private List<Integer> getYearArray() {

		Set<Integer> set = medians.stream().collect(Collectors.groupingBy(PopulationMedian::getYear)).keySet();
		List<Integer> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);
		return list;
	}

	private List<String> getGEOArray() {

		Set<String> set = medians.stream().collect(Collectors.groupingBy(PopulationMedian::getGEO)).keySet();
		List<String> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);
		//System.out.println(list);
		return list;
	}
	
	private List<String> getGenderArray(){
		Set<String> set = medians.stream().collect(Collectors.groupingBy(PopulationMedian::getGender)).keySet();
		List<String> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);
		//System.out.println(list);
		return list;
	}
	private List<String> getAgeArray(){
		Set<String> set = numbers.stream().collect(Collectors.groupingBy(PopulationNum::getAgeGroup)).keySet();
		List<String> list = new ArrayList<>();
		list.addAll(set);
		Collections.sort(list);
		//System.out.println(list);
		return list;
	}

}
