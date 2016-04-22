package net.prokhyon.viewfieldcorrector.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Spinner;
import net.prokhyon.viewfieldcorrector.MainApp;
import net.prokhyon.viewfieldcorrector.model.ViewFieldSettings;

public class SettingsOverviewController {

	@FXML
	private Spinner<Integer> resolutionWidth;
	@FXML
	private Spinner<Integer> resolutionHeight;
	@FXML
	private Spinner<Integer> obectStartingPositionX;
	@FXML
	private Spinner<Integer> obectStartingPositionY;
	@FXML
	private Spinner<Integer> centerAxisOffsetX;
	@FXML
	private Spinner<Integer> centerAxisOffsetY;
	@FXML
	private Spinner<Integer> objectSize;
	@FXML
	private Spinner<Integer> objectStartingRadius;
	@FXML
	private Spinner<Integer> axisSize;
	@FXML
	private Spinner<Integer> speed;

	private MainApp mainApp;

	public SettingsOverviewController() {
	}

	@FXML
	private void initialize() {

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleNewPerson() {
		mainApp.showViewField();
	}

	@FXML
	private void handleStartAnimation() {
		if (isViewFieldInputValid()) {

			ViewFieldSettings viewFieldSettings = new ViewFieldSettings();

			viewFieldSettings.setResolutionWidth(resolutionWidth.getValue());
			viewFieldSettings.setResolutionHeight(resolutionHeight.getValue());
			viewFieldSettings.setObectStartingPositionX(obectStartingPositionX.getValue());
			viewFieldSettings.setObectStartingPositionY(obectStartingPositionY.getValue());
			viewFieldSettings.setCenterAxisOffsetX(centerAxisOffsetX.getValue());
			viewFieldSettings.setCenterAxisOffsetY(centerAxisOffsetY.getValue());
			viewFieldSettings.setObjectSize(objectSize.getValue());
			viewFieldSettings.setObjectStartingRadius(objectStartingRadius.getValue());
			viewFieldSettings.setAxisSize(axisSize.getValue());
			viewFieldSettings.setAnimationSpeed(speed.getValue());

			mainApp.setViewFieldSettings(viewFieldSettings);
		}
	}

	private boolean isViewFieldInputValid() {
		String errorMessage = "";

		if (resolutionWidth.getValue() == null) {
			errorMessage += "No valid resolutionWidth!\n";
		}

		if (resolutionHeight.getValue() == null) {
			errorMessage += "No valid resolutionHeight!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			// alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

}