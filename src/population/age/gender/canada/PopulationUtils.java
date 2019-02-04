package populcation.age.gender.canada;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class PopulationUtils {
	
	public static File getFileHandle(Stage primaryStage) {

		// create a dialog of file chooser
		FileChooser fc = new FileChooser();
		fc.setTitle("Open CSV File");
		fc.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"),
				new ExtensionFilter("All Files", "*.*"));
		File csvFile = fc.showOpenDialog(primaryStage);

		return (csvFile);
	}

	public  static List<List<? extends Population>> readCSVFile(String filename) {
		// the value retrieved from
		// string []
		List<PopulationNum> numbers = new ArrayList<>();
		List<PopulationMedian> medians = new ArrayList<>();
 		int year = 0;
		String GEO = null;
		String gender = null;
		String ageGroup = null;
		String value = null;
		int num = 0;
		double median = 0;
		int i = 0;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
			String line = "";

			while ((line = reader.readLine()) != null) {

				// Split the line by commas
				if (i > 0) {
					String[] partsOfLine = line.split(",");
					try {

						year = Integer.parseInt(partsOfLine[0]);
						GEO = partsOfLine[1];
						gender = partsOfLine[2];
						ageGroup = partsOfLine[3];
						value = partsOfLine[4];
						if("Median age".equalsIgnoreCase(ageGroup)) {
							median = Double.parseDouble(value);
							medians.add(new PopulationMedian(year, GEO, gender, ageGroup, median));
						}
						else {
							num = Integer.parseInt(value);
							numbers.add(new PopulationNum(year,GEO,gender, ageGroup,num));
						}
					} catch (Exception e) {

						continue;
					}

				}

				i++;

			}
			// System.out.println("Total number of records from CSV file are " + i);
		} catch (IOException ioe) {
			System.out.println("Problem reading csv: " + ioe.getMessage());
		}
		List<List<? extends Population>> list =new ArrayList<>();
		list.add(numbers);
		list.add(medians);
		return list;
	}

}
