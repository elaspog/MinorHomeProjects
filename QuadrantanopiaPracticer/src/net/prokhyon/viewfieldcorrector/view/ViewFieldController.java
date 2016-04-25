package net.prokhyon.viewfieldcorrector.view;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import net.prokhyon.viewfieldcorrector.MainApp;
import net.prokhyon.viewfieldcorrector.model.ViewFieldSettings;

public class ViewFieldController {

	private ViewFieldSettings viewFieldSettings;

	Random random = new Random();

	@FXML
	private StackPane pane;

	@FXML
	private Canvas regularCanvas;

	private Shape shape = Shape.values()[random.nextInt(Shape.values().length)];

	private String shapeColorCode;

	private final FloatProperty objectActualPositionX = new SimpleFloatProperty();

	private final FloatProperty objectActualPositionY = new SimpleFloatProperty();

	private float width;

	private float height;

	private float screenSizeMin;

	private float centerPointX;

	private float centerPointY;

	private float objectDiameter;

	private float objectRadius;

	private float objectSizeFactor;

	private float axisSizeFactor;

	private float axisOffsetX;

	private float axisOffsetY;

	public float getObjectActualPositionX() {
		return objectActualPositionX.get();
	}

	public void setObjectActualPositionX(float objectActualPositionX) {
		this.objectActualPositionX.set(objectActualPositionX);
	}

	public FloatProperty objectActualPositionXProperty() {
		return objectActualPositionX;
	}

	public float getObjectActualPositionY() {
		return objectActualPositionY.get();
	}

	public void setObjectActualPositionY(float objectActualPositionY) {
		this.objectActualPositionY.set(objectActualPositionY);
	}

	public FloatProperty objectActualPositionYProperty() {
		return objectActualPositionY;
	}

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
		viewFieldSettings.objectStartingDiameterProperty().addListener(listener);

		viewFieldSettings.activeBackgroundColorProperty().addListener(listener);
		viewFieldSettings.passiveBackgroundColorProperty().addListener(listener);
		viewFieldSettings.axisColorProperty().addListener(listener);
		viewFieldSettings.startingFieldColorProperty().addListener(listener);

		objectActualPositionXProperty().addListener(listener);
		objectActualPositionYProperty().addListener(listener);

		generateNewObject();
	}

	private void draw(GraphicsContext gc) {

		clearArea(gc);
		drawStartingArea(gc);
		drawAxis(gc);

		shape.draw(gc, centerPointX + objectActualPositionX.getValue(), centerPointY + objectActualPositionY.getValue(),
				objectRadius, objectDiameter, shapeColorCode);

	}

	private void clearArea(GraphicsContext gc) {

		gc.setFill(viewFieldSettings.getActiveBackgroundColor());
		gc.clearRect(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
		gc.fillRect(0, 0, regularCanvas.getWidth(), regularCanvas.getHeight());
	}

	private void drawAxis(GraphicsContext gc) {

		gc.setStroke(viewFieldSettings.getAxisColor());

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
		float dw = screenSizeMin * viewFieldSettings.getObjectStartingDiameter() / 100.0f;
		float dh = screenSizeMin * viewFieldSettings.getObjectStartingDiameter() / 100.0f;

		gc.setFill(viewFieldSettings.getStartingFieldColor());
		gc.fillOval(centerPointX + dx - dw / 2.0, centerPointY + dy - dh / 2.0, dw, dh);
	}

	public void generateNewObject() {

		generateNewColor();
		generateNextShape();
		generateNewCoordinates();
	}

	boolean isAnimating = false;

	public void startShapeAnimation() {

		float d = calculateDistance(axisOffsetX, objectActualPositionX.get(), axisOffsetY, objectActualPositionY.get());
		float speed = viewFieldSettings.getAnimationSpeed() / 100f;
		float time = (d / screenSizeMin) / speed;

		if (!isAnimating) {

			isAnimating = true;

			Timeline timeline = new Timeline(
					new KeyFrame(Duration.seconds(0), new KeyValue(objectActualPositionX, objectActualPositionX.get()),
							new KeyValue(objectActualPositionX, objectActualPositionX.get())),
					new KeyFrame(Duration.seconds(time), new KeyValue(objectActualPositionX, axisOffsetX),
							new KeyValue(objectActualPositionY, axisOffsetY)));

			timeline.setOnFinished((x) -> {
				isAnimating = false;
			});

			timeline.play();
		}
	}

	private void generateNewColor() {

		if (!isAnimating) {
			shapeColorCode = viewFieldSettings.getObjectColorCodes()
					.get(random.nextInt(viewFieldSettings.getObjectColorCodes().size()));
		}
	}

	private void generateNextShape() {

		if (!isAnimating) {
			shape = Shape.values()[random.nextInt(Shape.values().length)];
		}
	}

	private void generateNewCoordinates() {

		if (!isAnimating) {
			float spX = width * viewFieldSettings.getObectStartingPositionX() / 100.0f;
			float spY = height * viewFieldSettings.getObectStartingPositionY() / 100.0f;
			float r = screenSizeMin * viewFieldSettings.getObjectStartingDiameter() / 200.0f;

			while (true) {

				float genXfloat = random.nextFloat() * 2;
				float genYfloat = random.nextFloat() * 2;
				float generatedX = spX + genXfloat * r - r;
				float generatedY = spY + genYfloat * r - r;

				double dist = Math
						.sqrt((spX - generatedX) * (spX - generatedX) + (spY - generatedY) * (spY - generatedY));

				if (dist <= r) {
					objectActualPositionX.set(generatedX);
					objectActualPositionY.set(generatedY);
					break;
				}
			}
		}
	}

	private float calculateDistance(float x1, float x2, float y1, float y2) {

		float dx = x1 - x2;
		float dy = y1 - y2;
		return (float) Math.sqrt(dx * dx + dy * dy);
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
		axisOffsetX = width * viewFieldSettings.getCenterAxisOffsetX() / 100.0f;
		axisOffsetY = height * viewFieldSettings.getCenterAxisOffsetY() / 100.0f;
	}
}