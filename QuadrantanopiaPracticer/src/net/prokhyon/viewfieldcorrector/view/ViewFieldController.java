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

	@FXML
	private void initialize() {

		GraphicsContext gc = regularCanvas.getGraphicsContext2D();

		ReadOnlyDoubleProperty widthProperty = pane.widthProperty();
		ReadOnlyDoubleProperty heightProperty = pane.heightProperty();
		regularCanvas.widthProperty().bind(widthProperty);
		regularCanvas.heightProperty().bind(heightProperty);
		regularCanvas.widthProperty().addListener((obs, oldWidth, newWidth) -> draw(gc));
		regularCanvas.heightProperty().addListener((obs, oldHeight, newHeight) -> draw(gc));

		draw(gc);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	private void draw(GraphicsContext gc) {

		drawArea(gc);
		deawAxis(gc);

		drawCircle(gc);
		drawRectangle(gc);
		drawTriangle(gc);
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

		float axisOffsetX = (float) (regularCanvas.getWidth() / 2.0);
		float axisOffsetY = (float) (regularCanvas.getHeight() / 2.0);

		float sizeFactor = 0.5f;

		float X1 = -axisOffsetX * sizeFactor + axisOffsetX;
		float X2 = axisOffsetX * sizeFactor + axisOffsetX;
		float Y1 = 0 * sizeFactor + axisOffsetY;
		float Y2 = 0 * sizeFactor + axisOffsetY;

		float X3 = 0 * sizeFactor + axisOffsetX;
		float X4 = 0 * sizeFactor + axisOffsetX;
		float Y3 = -axisOffsetY * sizeFactor + axisOffsetY;
		float Y4 = axisOffsetY * sizeFactor + axisOffsetY;

		gc.strokeLine(X1, Y1, X2, Y2);
		gc.strokeLine(X3, Y3, X4, Y4);
	}

	private void drawCircle(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);

		float sizeFactor = 0.2f;

		float width = (float) regularCanvas.getWidth();
		float height = (float) regularCanvas.getHeight();
		float Radius = Math.min(width, height);

		float centerOffsetX = width / 2.0f;
		float centerOffsetY = height / 2.0f;

		float actualDiameter = sizeFactor * Radius;
		float actualRadius = (float) (sizeFactor * Radius / 2.0);

		gc.strokeOval(centerOffsetX - actualRadius, centerOffsetY - actualRadius, actualDiameter, actualDiameter);
	}

	private void drawRectangle(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);

		float sizeFactor = 0.2f;

		float width = (float) regularCanvas.getWidth();
		float height = (float) regularCanvas.getHeight();
		float Radius = Math.min(width, height);

		float centerOffsetX = width / 2.0f;
		float centerOffsetY = height / 2.0f;

		float actualDiameter = sizeFactor * Radius;
		float actualRadius = (float) (sizeFactor * Radius / 2.0);

		gc.strokeRect(centerOffsetX - actualRadius, centerOffsetY - actualRadius, actualDiameter, actualDiameter);
	}

	private void drawTriangle(GraphicsContext gc) {

		gc.setStroke(Color.BLACK);

		float sizeFactor = 0.2f;

		float width = (float) regularCanvas.getWidth();
		float height = (float) regularCanvas.getHeight();
		float Radius = Math.min(width, height);

		float centerOffsetX = width / 2.0f;
		float centerOffsetY = height / 2.0f;

		float actualDiameter = sizeFactor * Radius;
		float actualRadius = (float) (sizeFactor * Radius / 2.0);

		float X1 = centerOffsetX;
		float X2 = centerOffsetX + actualRadius;
		float X3 = centerOffsetX - actualRadius;
		float Y1 = centerOffsetY - actualRadius;
		float Y2 = centerOffsetY + actualRadius;
		float Y3 = centerOffsetY + actualRadius;

		gc.strokePolygon(new double[] { X1, X2, X3 }, new double[] { Y1, Y2, Y3 }, 3);

	}

}