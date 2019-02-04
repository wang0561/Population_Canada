package populcation.age.gender.canada;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PopulationFileNotFound extends Exception {

	private static final long serialVersionUID = 1L;

		public PopulationFileNotFound(String str) {
			super(str);
			alert(str);
		}

		private void alert(String str) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Failed");
			alert.setContentText(
					"Cannot find the population file: "+str);
			
			alert.showAndWait();
			
		}
}
