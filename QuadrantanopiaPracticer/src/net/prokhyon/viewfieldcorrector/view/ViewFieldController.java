package net.prokhyon.viewfieldcorrector.view;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.prokhyon.viewfieldcorrector.MainApp;
import net.prokhyon.viewfieldcorrector.model.ViewFieldSettings;

public class ViewFieldController {

	private MainApp mainApp;

	private Stage dialogStage;

	private ViewFieldSettings viewFieldSettings;

	@FXML
	private StackPane pane;

	@FXML
	private Canvas regularCanvas;

	private float width;

	private float height;

	private float screenSizeMin;

	private float centerPointX;

	private float centerPointY;

	private float objectDiameter;

	private float objectRadius;

	private float objectSizeFactor;

	private float axisSizeFactor;

	@FXML
	private void initialize() {

		GraphicsContext gc = regularCanvas.getGraphicsContext2D();

		ReadOnlyDoubleProperty widthProperty = pane.widthProperty();
		ReadOnlyDoubleProperty heightProperty = pane.heightProperty();
		regularCanvas.widthProperty().bind(widthProperty);
		regularCanvas.heightProperty().bind(heightProperty);
		regularCanvas.widthProperty().addListener((obs, oldWidth, newWidth) -> {
			refreshValues();
			draw(gc);
		});
		regularCanvas.heightProperty().addListener((obs, oldHeight, newHeight) -> {
			refreshValues();
			draw(gc);
		});

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.viewFieldSettings = mainApp.getViewFieldSettings();

		ChangeListener<? super Number> listener = (obs, oldWidth, newWidth) -> {
			refreshValues();
			draw(regularCanvas.getGraphicsContext2D());
		};

		viewFieldSettings.objectSizeProperty().addListener(listener);
		viewFieldSettings.axisSizeProperty().addListener(listener);
		viewFieldSettings.centerAxisOffsetXProperty().addListener(listener);
		viewFieldSettings.centerAxisOffsetYProperty().addListener(listener);
		viewFieldSettings.obectStartingPositionXProperty().addListener(listener);
		viewFieldSettings.obectStartingPositionYProperty().addListener(listener);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	private void draw(GraphicsContext gc) {

		clearArea(gc);
		// drawArea(gc);
		drawAxis(gc);

		float objectOffsetX = width * viewFieldSettings.getObectStartingPositionX() / 100.0f;
		float objectOffsetY = height * viewFieldSettings.getObectStartingPositionY() / 100.0f;

		Shape s1 = Shape.CIRCLE;
		s1.draw(gc, centerPointX + objectOffsetX, centerPointY + objectOffsetY, objectRadius, objectDiameter);

		Shape s2 = Shape.SQUARE;
		s2.draw(gc, centerPointX + objectOffsetX, centerPointY + objectOffsetY, objectRadius, objectDiameter);

		Shape s3 = Shape.TRIANGLE;
		s3.draw(gc, centerPointX + objectOffsetX, centerPointY + objectOffsetY, objectRadius, objectDiameter);
	}

	private void drawArea(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeLine(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
		gc.strokeLine(0, regularCanvas.getHeight(), regularCanvas.getWidth(), 0);
	}

	private void clearArea(GraphicsContext gc) {
		gc.clearRect(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
	}

	private void drawAxis(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);

		float axisOffsetX = width * viewFieldSettings.getCenterAxisOffsetX() / 100.0f;
		float axisOffsetY = height * viewFieldSettings.getCenterAxisOffsetY() / 100.0f;

		float X1 = -centerPointX * axisSizeFactor + centerPointX + axisOffsetX;
		float X2 = centerPointX * axisSizeFactor + centerPointX + axisOffsetX;
		float Y1 = centerPointY + axisOffsetY;
		float Y2 = centerPointY + axisOffsetY;

		float X3 = centerPointX + axisOffsetX;
		float X4 = centerPointX + axisOffsetX;
		float Y3 = -centerPointY * axisSizeFactor + centerPointY + axisOffsetY;
		float Y4 = centerPointY * axisSizeFactor + centerPointY + axisOffsetY;

		gc.strokeLine(X1, Y1, X2, Y2);
		gc.strokeLine(X3, Y3, X4, Y4);
	}

	private void refreshValues() {

		width = (float) regularCanvas.getWidth();
		height = (float) regularCanvas.getHeight();
		centerPointX = width / 2.0f;
		centerPointY = height / 2.0f;
		objectSizeFactor = viewFieldSettings.getObjectSize() / 100.0f;
		axisSizeFactor = viewFieldSettings.getAxisSize() / 100.0f;
		screenSizeMin = Math.min(width, height);
		objectDiameter = objectSizeFactor * screenSizeMin;
		objectRadius = (float) (objectDiameter / 2.0);
	}

}