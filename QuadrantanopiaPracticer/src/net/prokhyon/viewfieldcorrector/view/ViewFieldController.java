package net.prokhyon.viewfieldcorrector.view;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewFieldController {

	private Stage dialogStage;

	@FXML
	private StackPane pane;

	@FXML
	private Canvas regularCanvas;

	private float width;

	private float height;

	private float radius;

	private float centerOffsetX;

	private float centerOffsetY;

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

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	private void draw(GraphicsContext gc) {

		drawArea(gc);
		deawAxis(gc);

		Shape s1 = Shape.CIRCLE;
		s1.draw(gc, centerOffsetX, centerOffsetY, objectRadius, objectDiameter);

		Shape s2 = Shape.SQUARE;
		s2.draw(gc, centerOffsetX, centerOffsetY, objectRadius, objectDiameter);

		Shape s3 = Shape.TRIANGLE;
		s3.draw(gc, centerOffsetX, centerOffsetY, objectRadius, objectDiameter);
	}

	private void drawArea(GraphicsContext gc) {

		gc.clearRect(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		gc.strokeLine(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
		gc.strokeLine(0, regularCanvas.getHeight(), regularCanvas.getWidth(), 0);
	}

	private void deawAxis(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);

		float X1 = -centerOffsetX * axisSizeFactor + centerOffsetX;
		float X2 = centerOffsetX * axisSizeFactor + centerOffsetX;
		float Y1 = 0 * axisSizeFactor + centerOffsetY;
		float Y2 = 0 * axisSizeFactor + centerOffsetY;

		float X3 = 0 * axisSizeFactor + centerOffsetX;
		float X4 = 0 * axisSizeFactor + centerOffsetX;
		float Y3 = -centerOffsetY * axisSizeFactor + centerOffsetY;
		float Y4 = centerOffsetY * axisSizeFactor + centerOffsetY;

		gc.strokeLine(X1, Y1, X2, Y2);
		gc.strokeLine(X3, Y3, X4, Y4);
	}

	private void refreshValues() {

		width = (float) regularCanvas.getWidth();
		height = (float) regularCanvas.getHeight();
		centerOffsetX = width / 2.0f;
		centerOffsetY = height / 2.0f;
		radius = Math.min(width, height);
		objectDiameter = objectSizeFactor * radius;
		objectRadius = (float) (objectSizeFactor * radius / 2.0);
		objectSizeFactor = 0.2f;
		axisSizeFactor = 0.5f;
	}

}