package net.prokhyon.viewfieldcorrector.view;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import net.prokhyon.viewfieldcorrector.MainApp;

public class SettingsOverviewController {

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
	private void handleStartAnimation() {

		mainApp.getViewFieldSettings().objectSizeProperty().bind(objectSize.valueProperty());
		mainApp.getViewFieldSettings().axisSizeProperty().bind(axisSize.valueProperty());
		mainApp.getViewFieldSettings().centerAxisOffsetXProperty().bind(centerAxisOffsetX.valueProperty());
		mainApp.getViewFieldSettings().centerAxisOffsetYProperty().bind(centerAxisOffsetY.valueProperty());
		mainApp.getViewFieldSettings().obectStartingPositionXProperty().bind(obectStartingPositionX.valueProperty());
		mainApp.getViewFieldSettings().obectStartingPositionYProperty().bind(obectStartingPositionY.valueProperty());

		mainApp.showViewField();
	}

}