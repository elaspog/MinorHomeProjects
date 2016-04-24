package net.prokhyon.viewfieldcorrector.view;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import net.prokhyon.viewfieldcorrector.MainApp;
import net.prokhyon.viewfieldcorrector.model.ViewFieldSettings;

public class ViewFieldController {

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
		this.viewFieldSettings = mainApp.getViewFieldSettings();

		ChangeListener<? super Object> listener = (obs, oldWidth, newWidth) -> {
			refreshValues();
			draw(regularCanvas.getGraphicsContext2D());
		};

		viewFieldSettings.objectSizeProperty().addListener(listener);
		viewFieldSettings.axisSizeProperty().addListener(listener);
		viewFieldSettings.centerAxisOffsetXProperty().addListener(listener);
		viewFieldSettings.centerAxisOffsetYProperty().addListener(listener);
		viewFieldSettings.obectStartingPositionXProperty().addListener(listener);
		viewFieldSettings.obectStartingPositionYProperty().addListener(listener);
		viewFieldSettings.objectStartingRadiusProperty().addListener(listener);

		viewFieldSettings.activeBackgroundColorProperty().addListener(listener);
		viewFieldSettings.passiveBackgroundColorProperty().addListener(listener);
		viewFieldSettings.axisColorProperty().addListener(listener);
		viewFieldSettings.startingFieldColorProperty().addListener(listener);
	}

	private void draw(GraphicsContext gc) {

		clearArea(gc);
		drawStartingArea(gc);
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

	private void clearArea(GraphicsContext gc) {

		gc.setFill(viewFieldSettings.getActiveBackgroundColor());
		gc.clearRect(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
		gc.fillRect(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
	}

	private void drawAxis(GraphicsContext gc) {

		gc.setStroke(viewFieldSettings.getAxisColor());

		float axisOffsetX = width * viewFieldSettings.getCenterAxisOffsetX() / 100.0f;
		float axisOffsetY = height * viewFieldSettings.getCenterAxisOffsetY() / 100.0f;

		float X1 = -screenSizeMin * axisSizeFactor + centerPointX + axisOffsetX;
		float X2 = screenSizeMin * axisSizeFactor + centerPointX + axisOffsetX;
		float Y1 = centerPointY + axisOffsetY;
		float Y2 = centerPointY + axisOffsetY;

		float X3 = centerPointX + axisOffsetX;
		float X4 = centerPointX + axisOffsetX;
		float Y3 = -screenSizeMin * axisSizeFactor + centerPointY + axisOffsetY;
		float Y4 = screenSizeMin * axisSizeFactor + centerPointY + axisOffsetY;

		gc.strokeLine(X1, Y1, X2, Y2);
		gc.strokeLine(X3, Y3, X4, Y4);
	}

	private void drawStartingArea(GraphicsContext gc) {

		float dx = width * viewFieldSettings.getObectStartingPositionX() / 100.0f;
		float dy = height * viewFieldSettings.getObectStartingPositionY() / 100.0f;
		float dw = screenSizeMin * viewFieldSettings.getObjectStartingRadius() / 100.0f;
		float dh = screenSizeMin * viewFieldSettings.getObjectStartingRadius() / 100.0f;

		gc.setFill(viewFieldSettings.getStartingFieldColor());
		gc.fillOval(centerPointX + dx - dw / 2.0, centerPointY + dy - dh / 2.0, dw, dh);
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