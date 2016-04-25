package net.prokhyon.viewfieldcorrector.view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import net.prokhyon.viewfieldcorrector.MainApp;
import net.prokhyon.viewfieldcorrector.model.ViewFieldSettings;

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
	private Spinner<Integer> objectStartingDiameter;

	@FXML
	private Spinner<Integer> axisSize;

	@FXML
	private Spinner<Integer> speed;

	@FXML
	private ColorPicker activeBackgroundColor;

	@FXML
	private ColorPicker passiveBackgroundColor;

	@FXML
	private ColorPicker startingFieldColor;

	@FXML
	private ColorPicker axisColor;

	@FXML
	private ColorPicker objectColorTmp;

	@FXML
	private ComboBox<String> objectColors;

	private MainApp mainApp;

	private ViewFieldSettings viewFieldSettings;

	public SettingsOverviewController() {
	}

	@FXML
	private void initialize() {

		axisColor.setValue(Color.BLACK);
		startingFieldColor.setValue(Color.WHITE);
		activeBackgroundColor.setValue(Color.LIGHTGRAY);
		passiveBackgroundColor.setValue(Color.GREY);

		Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		};
		objectColors.setCellFactory(factory);

		Callback<ListView<String>, ListCell<String>> factoryTooltip = new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectTooltipCell();
			}
		};
		objectColors.setButtonCell(factoryTooltip.call(null));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.viewFieldSettings = mainApp.getViewFieldSettings();
	}

	public boolean isReady() {

		if (objectColors == null)
			return false;

		if (objectColors.getItems().size() > 0)
			return true;

		return false;
	}

	@FXML
	private void handleOpenAnimation() {

		viewFieldSettings.objectSizeProperty().bind(objectSize.valueProperty());
		viewFieldSettings.axisSizeProperty().bind(axisSize.valueProperty());
		viewFieldSettings.centerAxisOffsetXProperty().bind(centerAxisOffsetX.valueProperty());
		viewFieldSettings.centerAxisOffsetYProperty().bind(centerAxisOffsetY.valueProperty());
		viewFieldSettings.obectStartingPositionXProperty().bind(obectStartingPositionX.valueProperty());
		viewFieldSettings.obectStartingPositionYProperty().bind(obectStartingPositionY.valueProperty());
		viewFieldSettings.objectStartingDiameterProperty().bind(objectStartingDiameter.valueProperty());
		viewFieldSettings.animationSpeedProperty().bind(speed.valueProperty());

		viewFieldSettings.activeBackgroundColorProperty().bind(activeBackgroundColor.valueProperty());
		viewFieldSettings.passiveBackgroundColorProperty().bind(passiveBackgroundColor.valueProperty());
		viewFieldSettings.axisColorProperty().bind(axisColor.valueProperty());
		viewFieldSettings.startingFieldColorProperty().bind(startingFieldColor.valueProperty());

		mainApp.showViewField();
	}

	@FXML
	private void handleStartAnimation() {

		mainApp.startAnimation();
	}

	@FXML
	private void handleStopAnimation() {

		mainApp.stopAnimation();
	}

	@FXML
	private void handleAddObjectColor() {

		Color suggestedColor = objectColorTmp.getValue();
		String suggestedColorCode = suggestedColor.toString();

		if (!objectColors.getItems().contains(suggestedColorCode)) {
			objectColors.getItems().add(suggestedColorCode);
			viewFieldSettings.getObjectColorCodes().add(suggestedColorCode);
		}
	}

	@FXML
	private void handleDeleteObjectColor() {

		String codeToRemove = objectColors.getValue();
		objectColors.getItems().remove(codeToRemove);
		viewFieldSettings.getObjectColorCodes().remove(codeToRemove);
	}
}