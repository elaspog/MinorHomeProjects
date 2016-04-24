package net.prokhyon.viewfieldcorrector.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class ViewFieldSettings {

	private final IntegerProperty centerAxisOffsetX;
	private final IntegerProperty centerAxisOffsetY;
	private final IntegerProperty obectStartingPositionX;
	private final IntegerProperty obectStartingPositionY;
	private final IntegerProperty objectSize;
	private final IntegerProperty axisSize;
	private final IntegerProperty objectStartingRadius;
	private final IntegerProperty animationSpeed;
	private final ObjectProperty<Color> activeBackgroundColor;
	private final ObjectProperty<Color> passiveBackgroundColor;
	private final ObjectProperty<Color> axisColor;
	private final ObjectProperty<Color> startingFieldColor;

	public ViewFieldSettings() {
		this(0, 0, 0, 0, 0, 0, 0, 0, null, null, null, null);
	}

	public ViewFieldSettings(int centerAxisOffsetX, int centerAxisOffsetY, int obectStartingPositionX,
			int obectStartingPositionY, int objectSize, int axisSize, int objectStartingRadius, int animationSpeed,
			Color activeBackgroundColor, Color passiveBackgroundColor, Color axisColor, Color startingFieldColor) {

		this.centerAxisOffsetX = new SimpleIntegerProperty(centerAxisOffsetX);
		this.centerAxisOffsetY = new SimpleIntegerProperty(centerAxisOffsetY);
		this.obectStartingPositionX = new SimpleIntegerProperty(obectStartingPositionX);
		this.obectStartingPositionY = new SimpleIntegerProperty(obectStartingPositionY);
		this.objectSize = new SimpleIntegerProperty(objectSize);
		this.axisSize = new SimpleIntegerProperty(axisSize);
		this.objectStartingRadius = new SimpleIntegerProperty(objectStartingRadius);
		this.animationSpeed = new SimpleIntegerProperty(animationSpeed);
		this.activeBackgroundColor = new SimpleObjectProperty<Color>(activeBackgroundColor);
		this.passiveBackgroundColor = new SimpleObjectProperty<Color>(passiveBackgroundColor);
		this.axisColor = new SimpleObjectProperty<Color>(axisColor);
		this.startingFieldColor = new SimpleObjectProperty<Color>(startingFieldColor);
	}

	public int getCenterAxisOffsetX() {
		return centerAxisOffsetX.get();
	}

	public void setCenterAxisOffsetX(int centerAxisOffsetX) {
		this.centerAxisOffsetX.set(centerAxisOffsetX);
	}

	public IntegerProperty centerAxisOffsetXProperty() {
		return centerAxisOffsetX;
	}

	public int getCenterAxisOffsetY() {
		return centerAxisOffsetY.get();
	}

	public void setCenterAxisOffsetY(int centerAxisOffsetY) {
		this.centerAxisOffsetY.set(centerAxisOffsetY);
	}

	public IntegerProperty centerAxisOffsetYProperty() {
		return centerAxisOffsetY;
	}

	public int getObectStartingPositionX() {
		return obectStartingPositionX.get();
	}

	public void setObectStartingPositionX(int obectStartingPositionX) {
		this.obectStartingPositionX.set(obectStartingPositionX);
	}

	public IntegerProperty obectStartingPositionXProperty() {
		return obectStartingPositionX;
	}

	public int getObectStartingPositionY() {
		return obectStartingPositionY.get();
	}

	public void setObectStartingPositionY(int obectStartingPositionY) {
		this.obectStartingPositionY.set(obectStartingPositionY);
	}

	public IntegerProperty obectStartingPositionYProperty() {
		return obectStartingPositionY;
	}

	public int getObjectSize() {
		return objectSize.get();
	}

	public void setObjectSize(int objectSize) {
		this.objectSize.set(objectSize);
	}

	public IntegerProperty objectSizeProperty() {
		return objectSize;
	}

	public int getAxisSize() {
		return axisSize.get();
	}

	public void setAxisSize(int axisSize) {
		this.axisSize.set(axisSize);
	}

	public IntegerProperty axisSizeProperty() {
		return axisSize;
	}

	public int getObjectStartingRadius() {
		return objectStartingRadius.get();
	}

	public void setObjectStartingRadius(int objectStartingRadius) {
		this.objectStartingRadius.set(objectStartingRadius);
	}

	public IntegerProperty objectStartingRadiusProperty() {
		return objectStartingRadius;
	}

	public int getAnimationSpeed() {
		return animationSpeed.get();
	}

	public void setAnimationSpeed(int animationSpeed) {
		this.animationSpeed.set(animationSpeed);
	}

	public IntegerProperty animationSpeedProperty() {
		return animationSpeed;
	}

	public Color getActiveBackgroundColor() {
		return activeBackgroundColor.get();
	}

	public void setActiveBackgroundColor(Color activeBackgroundColor) {
		this.activeBackgroundColor.set(activeBackgroundColor);
	}

	public ObjectProperty<Color> activeBackgroundColorProperty() {
		return activeBackgroundColor;
	}

	public Color getPassiveBackgroundColor() {
		return passiveBackgroundColor.get();
	}

	public void setPassiveBackgroundColor(Color passiveBackgroundColor) {
		this.passiveBackgroundColor.set(passiveBackgroundColor);
	}

	public ObjectProperty<Color> passiveBackgroundColorProperty() {
		return passiveBackgroundColor;
	}

	public Color getAxisColor() {
		return axisColor.get();
	}

	public void setAxisColor(Color axisColor) {
		this.axisColor.set(axisColor);
	}

	public ObjectProperty<Color> axisColorProperty() {
		return axisColor;
	}

	public Color getStartingFieldColor() {
		return startingFieldColor.get();
	}

	public void setStartingFieldColor(Color startingFieldColor) {
		this.startingFieldColor.set(startingFieldColor);
	}

	public ObjectProperty<Color> startingFieldColorProperty() {
		return startingFieldColor;
	}
}
